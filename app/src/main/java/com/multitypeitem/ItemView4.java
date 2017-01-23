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

public class ItemView4 extends ItemViewFactory<Integer, ItemView4.Item4VH> {

    public ItemView4(Context context, Integer data) {
        super(context, data);
    }

    @Override
    public Item4VH onCreateViewHolder(Context context, ViewGroup parent) {
        Log.d(TAG, "onCreateViewHolder:..... 4");
        return new Item4VH(LayoutInflater.from(context).inflate(R.layout.item1, parent, false));
    }

    @Override
    public void onBindViewHolder(Context context, Item4VH holder, final Integer data) {
        holder.mTextView.setText(data + "");
        holder.mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refresh(data + 10000);
            }
        });
    }


    static class Item4VH extends RecyclerView.ViewHolder {

        TextView mTextView;

        public Item4VH(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.tv);
        }
    }
}
