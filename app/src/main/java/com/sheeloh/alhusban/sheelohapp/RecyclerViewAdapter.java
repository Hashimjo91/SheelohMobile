package com.sheeloh.alhusban.sheelohapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * date: 5/20/2016
 *
 * @author Hashim Al-Husban
 * @version TODO
 * @since TODO
 */
public abstract class RecyclerViewAdapter<Type, ViewHolder extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<ViewHolder> {

    private Type removedItem = null;
    private ArrayList<Type> rootList = new ArrayList<Type>();

    @Override
    public final ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        return cViewHolder(viewGroup, i, layoutInflater);
    }

    @Override
    public  void onBindViewHolder(ViewHolder viewHolder, int i) {
        bViewHolder(viewHolder, i, rootList.get(i));
    }

    @Override
    public int getItemCount() {
        return rootList.size();
    }

    public Type getItem(int position) {
        return rootList.get(position);
    }

    public void add(Type... items) {
        for (Type item : items) {
            add(item);
        }
    }

    public void add(Type item) {
        if (item == null) {
            return;
        }
        rootList.add(item);
        notifyItemInserted(rootList.size() - 1);
    }

    public void addFirst(Type item) {
        if (item == null) {
            return;
        }
        rootList.add(0, item);
        notifyItemInserted(0);
    }

    public void addAll(List<Type> list) {
        if (list == null || list.size() == 0) {
            return;
        }
        for (Type type : list) {
            add(type);
        }
    }

    public void newList(List<Type> list) {
        clear();
        addAll(list);
    }

    public void clear() {
        while (!rootList.isEmpty()) {
            remove(0);
        }
    }

    public Type remove(int position) {
        removedItem = rootList.remove(position);
        notifyItemRemoved(position);
        return removedItem;
    }

    public void undoRemove() {
        if (removedItem != null) {
            add(removedItem);
            removedItem = null;
        }
    }

    public List<Type> getValues() {
        return new ArrayList<Type>(rootList);
    }

    public boolean contain(Object object) {
        return rootList.contains(object);
    }

    public abstract ViewHolder cViewHolder(ViewGroup viewGroup, int i, LayoutInflater layoutInflater);

    public abstract void bViewHolder(ViewHolder viewHolder, int i, Type item);
}
