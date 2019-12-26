package com.wei.emptyviewexample;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.wei.adapter.ViewHolder;
import com.wei.adapter.base.CommonBaseAdapter;
import com.wei.emptyview.WEmptyView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wei on 2019/12/25.
 */
public class ListDemoActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    ListDemoAdapter mAdapter;
    WEmptyView mEmptyView;
    Handler mHandler = new Handler();
    List mData = new ArrayList();

    public static void startActivity(Context context){
    	Intent intent = new Intent(context, ListDemoActivity.class);
    	context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        mRecyclerView = findViewById(R.id.recycleview);
        mEmptyView = findViewById(R.id.emptyView);
        mAdapter = new ListDemoAdapter(this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mEmptyView.loading();
        loadData();


        mEmptyView.setOnBtnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mEmptyView.loading();
                loadData();
            }
        });

        findViewById(R.id.btn_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAdapter.insert("66666");
                mEmptyView.success();
            }
        });

        findViewById(R.id.btn_delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (1 != mAdapter.getDataCount()){
                    mAdapter.remove(0);
                }else {
                    mAdapter.cleanData();
                    mEmptyView.empty();
                }
            }
        });
    }

    private void loadData() {
        mAdapter.cleanData();
        mData.add("66666");
        mData.add("66666");
        mData.add("66666");
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mAdapter.insert(mData);
                mEmptyView.success();
            }
        },3000);
    }


    class ListDemoAdapter extends CommonBaseAdapter<String> {

        public ListDemoAdapter(Context context) {
            super(context);
        }

        @Override
        protected void convert(ViewHolder holder, String data, int position) {
            holder.setText(R.id.tv_title ,data);
        }

        @Override
        protected int getItemLayoutId() {
            return R.layout.item_index;
        }
    }
}
