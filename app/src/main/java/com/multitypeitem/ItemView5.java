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

public class ItemView5 extends ItemViewFactory<String, ItemView5.Item5VH> {

    public ItemView5(Context context, String data) {
        super(context, data);
    }

    @Override
    public Item5VH onCreateViewHolder(Context context, ViewGroup parent) {
        Log.d(MultiTypeRecyclerAdapter.TAG, "onCreateViewHolder:----------- 5");
        return new ItemView5.Item5VH(LayoutInflater.from(context).inflate(R.layout.item2, parent, false));
    }

    @Override
    public void onBindViewHolder(Context context, Item5VH holder, final String data) {
        holder.mTextView.setText(data);

        holder.mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refresh("change..." + data);
            }
        });
    }

    static class Item5VH extends RecyclerView.ViewHolder {
        TextView mTextView;

        public Item5VH(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.tv);
        }
    }
}
