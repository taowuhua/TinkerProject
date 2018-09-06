package com.example.wb_twh369668.recycleviewdemo.ui;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.wb_twh369668.recycleviewdemo.R;
import com.example.wb_twh369668.recycleviewdemo.adapter.SwipRecycleAdapter;
import com.example.wb_twh369668.recycleviewdemo.utils.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

public class FreshItemActivity extends Activity implements View.OnClickListener {

    private SwipeRefreshLayout mSwip;
    private RecyclerView mRv_swip;
    private List<String> list;
    private SwipRecycleAdapter swipRecycleAdapter;
    private GridLayoutManager mGridLayoutMgr;
    private Button mBtSwip;
    private int lastVisibleItem;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fresh_item);
        mSwip = findViewById(R.id.swipe_refresh);
        mRv_swip = findViewById(R.id.rv_swipRefresh);
        mBtSwip = findViewById(R.id.bt_swip);
        mBtSwip.setOnClickListener(this);
        initData();
        // 顶部刷新的样式
        mSwip.setColorScheme(android.R.color.holo_red_light, android.R.color.holo_green_light,
                android.R.color.holo_blue_bright, android.R.color.holo_orange_light);
        //下拉刷新
        mSwip.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //延迟一秒钟再执行任务
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        mSwip.setRefreshing(false);     //取消刷新状态
                        addData();
                        swipRecycleAdapter.notifyItemChanged(mGridLayoutMgr.getItemCount() + 1);
                    }
                }, 1000);
            }
        });
        //上拉加载
        mRv_swip.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 1 == swipRecycleAdapter.getItemCount()) {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            for (int i = 0; i < 5; i++) {
                                int index = i + 1;
                                list.add("上拉加载" + index);
                                swipRecycleAdapter.notifyItemChanged(mGridLayoutMgr.getItemCount() + 1);
                            }
                        }
                    }, 1000);
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItem =mGridLayoutMgr.findLastVisibleItemPosition();
            }
        });

    }


    private void initRecycleView() {
        mGridLayoutMgr = new GridLayoutManager(this, 1);
//      mNormalRecyclerView.setLayoutManager(new GridLayoutManager(this, 4));//设置网格布局
//      mNormalRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));//设置瀑布流布局

        mRv_swip.setLayoutManager(mGridLayoutMgr);
        //设置自动增删动画
        mRv_swip.setItemAnimator(new DefaultItemAnimator());
        mRv_swip.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));
        swipRecycleAdapter = new SwipRecycleAdapter(getApplicationContext(), list);
        mRv_swip.setAdapter(swipRecycleAdapter);
    }

    void initData() {
        list = new ArrayList<String>();
        for (int i = 0; i < 10; i++) {
            list.add("苹果" + i);
        }
    }

    void addData() {
        list.add("下拉加载中秋节不回家就帮着别人看猫");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_swip:
                initRecycleView();
                break;
            default:
                break;
        }
    }

}
