# 如何优雅的为recyclerview设置多种布局

> 要优雅就要符合 开闭原则，单一职责，当增加新的类型时只能扩展，不能修改源代码。

* 每增加一种类型view时需要新增一个ItemViewFactory的直接子类，或者间接子类

* 通过调用MultiTypeRecyclerAdapter的setData或appendData控制view显示顺序

* 可以通过调用MultiTypeRecyclerAdapter的setTypeMapPolicy为adapter设置类型映射策略，默认使用DefaultTypeMapPolicy

#  具体用法参考下面

main activity

```java
package com.multitypeitem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.multitypeitem.adapter.ItemViewFactory;
import com.multitypeitem.adapter.MultiTypeRecyclerAdapter;

public class MainActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;

    MultiTypeRecyclerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mRecyclerView = (RecyclerView) findViewById(R.id.recycler);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);

        mRecyclerView.setLayoutManager(layoutManager);

        RecyclerView.RecycledViewPool recycledViewPool = new RecyclerView.RecycledViewPool();
        //设置每种布局最大缓存数量，可以不设置
        recycledViewPool.setMaxRecycledViews(0, 15);
        recycledViewPool.setMaxRecycledViews(1, 15);
        recycledViewPool.setMaxRecycledViews(2, 15);
        recycledViewPool.setMaxRecycledViews(3, 15);
        recycledViewPool.setMaxRecycledViews(4, 15);
        mRecyclerView.setRecycledViewPool(recycledViewPool);

        mAdapter = new MultiTypeRecyclerAdapter();
        mRecyclerView.setAdapter(mAdapter);

        List<ItemViewFactory> data = getData();
        mAdapter.setData(data);

        findViewById(R.id.but).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAdapter.setData(getData2());
            }
        });
    }


    public List<ItemViewFactory> getData() {
        List<ItemViewFactory> data = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            data.add(new ItemView1(this, i));
        }

        for (int i = 0; i < 1; i++) {
            data.add(new ItemView2(this, "view2---------- " + i));
        }

//        for (int i = 0; i < 10; i++) {
//            data.add(new ItemView3(this, new Date(2017-1900, 1, 1 + i)));
//        }
//
        for (int i = 0; i < 8; i++) {
            data.add(new ItemView1(this, 800 + i));
        }
//
        for (int i = 0; i < 2; i++) {
            data.add(new ItemView4(this, i));
        }
//
//        for (int i = 0; i < 50; i++) {
//            data.add(new ItemView5(this, "view5//////// " + i));
//        }

        return data;
    }

    public List<ItemViewFactory> getData2() {
        List<ItemViewFactory> data = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            data.add(new ItemView3(this, new Date(2017 - 1900, 1, 1 + i)));
        }

        for (int i = 0; i < 3; i++) {
            data.add(new ItemView2(this, "view2---------- " + i));
        }


//        for (int i = 0; i < 5; i++) {
//            data.add(new ItemView1(this, i));
//        }
//


        for (int i = 0; i < 2; i++) {
            data.add(new ItemView4(this, i));
        }

        for (int i = 0; i < 5; i++) {
            data.add(new ItemView5(this, "view5//////// " + i));
        }


        for (int i = 0; i < 8; i++) {
            data.add(new ItemView1(this, 800 + i));
        }

        return data;
    }
}

```


各种类型view,其他view类似 

```java
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

```


```java
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

```

main布局

```html
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/activity_main"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:paddingBottom="@dimen/activity_vertical_margin"
    android:paddingLeft="@dimen/activity_horizontal_margin"
    android:paddingRight="@dimen/activity_horizontal_margin"
    android:paddingTop="@dimen/activity_vertical_margin"
    android:orientation="vertical"
    tools:context="com.multitypeitem.MainActivity">

    <Button
        android:id="@+id/but"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="ok"/>

    <android.support.v7.widget.RecyclerView
        android:id="@+id/recycler"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        >

    </android.support.v7.widget.RecyclerView>

</LinearLayout>

```


item布局

```html
    <?xml version="1.0" encoding="utf-8"?>  
    <LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"  
                  android:orientation="vertical"  
                  android:layout_width="match_parent"  
                  android:layout_height="wrap_content"  
                  android:padding="5dp">  
      
        <TextView  
            android:id="@+id/tv"  
            android:layout_width="match_parent"  
            android:layout_height="wrap_content"  
            android:background="#e1e1e1"  
            android:textSize="20sp"  
            android:textColor="#000"  
            android:padding="10dp"/>  
    </LinearLayout>  
```

效果图

![效果图](https://github.com/android-notes/blogimg/blob/master/%E4%BC%98%E9%9B%85%E5%AE%9E%E7%8E%B0%E5%A4%9A%E7%A7%8Drecyclerview%E5%B8%83%E5%B1%80.png?raw=true)
