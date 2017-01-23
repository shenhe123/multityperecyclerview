package com.multitypeitem.adapter;

/**
 * Created by wanjian on 2017/1/23.
 */

public interface TypeMapPolicy {
    int toType(ItemViewFactory viewFactory);

    ItemViewFactory toItemView(int type);
}
