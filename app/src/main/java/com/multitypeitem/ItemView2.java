package com.multitypeitem;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.multitypeitem.adapter.ItemViewFactory;
import com.multitypeitem.adapter.MultiTypeRecyclerAdapter;

/**
 * Created by wanjian on 2017/1/12.
 */

public class ItemView2 extends ItemViewFactory<String, ItemView2.Item2VH> {

    public ItemView2(Context context, String data) {
        super(context, data);
    }

    @Override
    public Item2VH onCreateViewHolder(Context context, ViewGroup parent) {
        Log.d(MultiTypeRecyclerAdapter.TAG, "onCreateViewHolder:----------- 2");
        return new ItemView2.Item2VH(LayoutInflater.from(context).inflate(R.layout.item2, parent, false));
    }

    @Override
    public void onBindViewHolder(Context context, Item2VH holder, final String data) {
        holder.mTextView.setText(data);

        holder.mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refresh("change..." + data);
            }
        });
    }

    static class Item2VH extends RecyclerView.ViewHolder {
        TextView mTextView;

        public Item2VH(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.tv);
        }
    }
}
