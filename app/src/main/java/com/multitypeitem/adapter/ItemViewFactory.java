package com.multitypeitem.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

/**
 * Created by wanjian on 2017/1/23.
 */

public abstract class ItemViewFactory<D, H extends RecyclerView.ViewHolder> {
    D mData;
    private RecyclerView.Adapter mAdapter;
    private Context mContext;
    private int mPosition;

    public ItemViewFactory(Context context, D data) {
        this.mData = data;
        this.mContext = context;
    }

    //不加修饰符，只供内部使用，禁止外部调用
    H innerCreateVH(ViewGroup parent) {
        return onCreateViewHolder(mContext, parent);
    }

    void innerBindVH(RecyclerView.ViewHolder holder, int position) {
        mPosition = position;
        onBindViewHolder(mContext, ((H) holder), mData);
    }

    void attachAdapter(RecyclerView.Adapter adapter) {
        mAdapter = adapter;
    }

    protected void refresh(D data) {
        resetData(data);
        if (mAdapter != null) {
            mAdapter.notifyItemChanged(mPosition);
        }
    }

    protected void resetData(D data) {
        this.mData = data;
    }

    public abstract H onCreateViewHolder(Context context, ViewGroup parent);

    public abstract void onBindViewHolder(Context context, H holder, D data);
}
