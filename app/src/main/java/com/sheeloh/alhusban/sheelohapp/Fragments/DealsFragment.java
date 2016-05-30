package com.sheeloh.alhusban.sheelohapp.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.sheeloh.alhusban.sheelohapp.R;
import com.sheeloh.alhusban.sheelohapp.RecyclerViewAdapter;
import com.sheeloh.alhusban.sheelohapp.ViewHolder_2_Text;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.recyclerview.animators.SlideInLeftAnimator;

import static android.view.KeyEvent.KEYCODE_ENTER;

/**
 * date: 5/20/2016
 *
 * @author Hashim Al-Husban
 * @version 1
 * @since 1
 */
public class DealsFragment extends Fragment implements TextWatcher, View.OnClickListener {

    private String title;
    private int page;

    public static DealsFragment newInstance(int page, String title) {
        DealsFragment fragmentFirst = new DealsFragment();
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        args.putString("someTitle", title);
        fragmentFirst.setArguments(args);
        return fragmentFirst;
    }

    private RecyclerView recyclerView;
    private RecyclerViewAdapter<String, ViewHolder_2_Text> recyclerViewAdapter = new RecyclerViewAdapter<String, ViewHolder_2_Text>() {

        @Override
        public ViewHolder_2_Text cViewHolder(ViewGroup viewGroup, int i, LayoutInflater layoutInflater) {
            return new ViewHolder_2_Text(layoutInflater.inflate(R.layout.deals_item, null), i);
        }

        @Override
        public void bViewHolder(ViewHolder_2_Text viewHolder, int i, String item) {
            viewHolder.setText1(item, i);
        }
    };
    EditText nameEdit;
    List<String> list;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        page = getArguments().getInt("someInt", 0);
        title = getArguments().getString("someTitle");
    }

    // Inflate the view for the fragment based on layout XML
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.deals_frag, container, false);
        nameEdit = (EditText) view.findViewById(R.id.name_edt);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycleViewItems);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setItemAnimator(new SlideInLeftAnimator());
        list = new ArrayList<>();
        list.add("Deal 1");
        list.add("Deal 2");
        list.add("Deal 3");
        list.add("Deal 4");
        list.add("Deal 5");
        list.add("Deal 6");
        list.add("Deal 7");
        recyclerViewAdapter.newList(list);
        nameEdit.addTextChangedListener(this);
        view.findViewById(R.id.search_img).setOnClickListener(this);
        nameEdit.setImeActionLabel("Done", KEYCODE_ENTER);
        return view;
    }

    public void makeDealFrag() {

    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        search(s.toString());
    }

    @Override
    public void afterTextChanged(Editable s) {


    }

    private void search(String s) {
        Log.e("searchString", s);
        List<String> temp = new ArrayList<>();
        recyclerViewAdapter.clear();
        for (String string : list) {
            if (string.toLowerCase().contains(s.toString().toLowerCase())) {
                temp.add(string);
                recyclerViewAdapter.add(string);
            }
        }
        Log.e("itemCount", temp.size() + "");

        if (temp.size() < 1) {
            recyclerViewAdapter.newList(list);
        }


    }

    @Override
    public void onClick(View v) {
        search(nameEdit.getText().toString());
    }

}
