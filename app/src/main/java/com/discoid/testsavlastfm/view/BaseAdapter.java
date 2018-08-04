package com.discoid.testsavlastfm.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static int VIEW_TYPE_EMPTY = View.NO_ID;
    public final static int HAS_HEADER = 1;
    protected static final int DATA_HEADER_TYPE = View.NO_ID + 1;
    protected static final int DATA_ITEM_TYPE = View.NO_ID + 2;
    protected static final int DATA_FOOTER_TYPE = View.NO_ID + 3;

    protected static final int HEADER_POSITION = 0;

    public boolean hasHeaderView() {
        return getHeaderViewType() != VIEW_TYPE_EMPTY;
    }

    public boolean hasFooterView() {
        return getFooterViewType() != VIEW_TYPE_EMPTY;
    }

    public boolean isHeaderView(int position) {
        return (hasHeaderView() && position == HEADER_POSITION);
    }

    public boolean isFooterView(int position) {
        return (hasFooterView() && position == getFooterPosition());
    }

    protected int getFooterPosition() {
        return getItemCount()-1;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == getHeaderViewType()) {
            return onCreateHeaderViewHolder(parent, viewType);
        } else if (viewType == getFooterViewType()) {
            return onCreateFooterViewHolder(parent, viewType);
        } else {
            return onCreateItemViewHolder(parent, viewType);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final int viewType = getItemViewType(position);

        if (viewType == getHeaderViewType()) {
            onBindHeaderViewHolder(holder, position);
        } else if (viewType == getFooterViewType()) {
            onBindFooterViewHolder(holder, position);
        } else {
            onBindItemViewHolder(holder, position);
        }
    }

    @Override
    public int getItemCount() {

        int rawDataCount = getItemDataCount();

        if (hasHeaderView())
            rawDataCount++;

        if (hasFooterView())
            rawDataCount++;

        return rawDataCount;
    }

    @Override
    public int getItemViewType(int position) {
        if (isHeaderView(position)) {
            return getHeaderViewType();
        } else if (isFooterView(position)) {
            return getFooterViewType();
        } else {
            return getItemDataViewType(position);
        }
    }

    protected int getItemPosition(int position) {
        if (hasHeaderView() && position > 0)
            position--;

        return position;
    }

    protected abstract int getItemDataCount();
    protected abstract int getHeaderViewType();
    protected abstract int getFooterViewType();
    protected abstract int getItemDataViewType(int position);

    /**
     * @param   viewType    This is a custom defined type by the concrete class, if it has more than
     *                      one view type in the list
     */
    protected abstract RecyclerView.ViewHolder onCreateItemViewHolder(ViewGroup parent, int viewType);
    protected abstract RecyclerView.ViewHolder onCreateHeaderViewHolder(ViewGroup parent, int viewType);
    protected abstract RecyclerView.ViewHolder onCreateFooterViewHolder(ViewGroup parent, int viewType);
    protected abstract void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position);
    protected abstract void onBindHeaderViewHolder(RecyclerView.ViewHolder holder, int position);
    protected abstract void onBindFooterViewHolder(RecyclerView.ViewHolder holder, int position);

    /**
     * Clear down resources
     */
    public void clear() {

    }
}
