package com.sheeloh.alhusban.sheelohapp;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * date: 5/20/2016
 *
 * @author Hashim Al-Husban
 * @version 1
 * @since 1
 */
public class ViewHolder_2_Text extends RecyclerView.ViewHolder {
    Button textView1;
    private View root;

    public View getRoot() {
        return root;
    }

    public void setRoot(View root) {
        this.root = root;
    }

    public ViewHolder_2_Text(View itemView, int i) {
        super(itemView);
        Log.e("viewIndex", i + "");
        root = itemView;
        textView1 = (Button) root.findViewById(R.id.text);
        setColor(i);

    }

    private void setColor(int i) {
        int q = i % 4;
        switch (q) {

            case 0:
                getTextView1().setBackgroundResource(R.drawable.text_ripple2);
                break;
            case 1:
                getTextView1().setBackgroundResource(R.drawable.text_ripple1);
                break;
            case 2:
                getTextView1().setBackgroundResource(R.drawable.text_ripple3);
                break;
            case 3:
                getTextView1().setBackgroundResource(R.drawable.text_ripple4);
                break;
        }
    }

    public TextView getTextView1() {
        return textView1;
    }

    public void setText1(String item, int i) {
        setColor(i);

        getTextView1().setText(item);
    }
}
