package com.multitypeitem;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.multitypeitem.adapter.ItemViewFactory;

import static com.multitypeitem.adapter.MultiTypeRecyclerAdapter.TAG;

/**
 * Created by wanjian on 2017/1/12.
 */

public class ItemView1 extends ItemViewFactory<Integer, ItemView1.Item1VH> {

    public ItemView1(Context context, Integer data) {
        super(context, data);
    }

    @Override
    public Item1VH onCreateViewHolder(Context context, ViewGroup parent) {
        Log.d(TAG, "onCreateViewHolder:..... 1");
        return new Item1VH(LayoutInflater.from(context).inflate(R.layout.item1, parent, false));
    }

    @Override
    public void onBindViewHolder(Context context, Item1VH holder, final Integer data) {
        holder.mTextView.setText(data + "");
        Log.w(TAG, "onBindViewHolder: " + data);
        holder.mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refresh(data + 10000);
            }
        });
    }


    static class Item1VH extends RecyclerView.ViewHolder {

        TextView mTextView;

        public Item1VH(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.tv);
        }
    }
}
