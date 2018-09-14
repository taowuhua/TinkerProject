package com.example.wb_twh369668.beautifulgirl.ui;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.wb_twh369668.beautifulgirl.R;
import com.example.wb_twh369668.beautifulgirl.adapter.BeautifulGirlAdapter;
import com.example.wb_twh369668.beautifulgirl.bean.BeautifulGirl;
import com.example.wb_twh369668.beautifulgirl.mvp.presenter.GirlPresenter;
import com.example.wb_twh369668.beautifulgirl.mvp.view.GirlView;
import com.example.wb_twh369668.beautifulgirl.util.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
    private static final String TAG = "MainActivity";
    private String URL = "https://gank.io/api/data/%E7%A6%8F%E5%88%A9/20/2";
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecycleview;
    private GridLayoutManager gridLayoutManager;
    private BeautifulGirlAdapter beautifulGirlAdapter;
    private List<BeautifulGirl.ResultsBean> dataList = new ArrayList<BeautifulGirl.ResultsBean>();
    private GirlPresenter mGirlPresent = new GirlPresenter(this);
    private int lastVisibleItem;
    private Button mBt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        mGirlPresent.onCreate();
        mGirlPresent.attachView(mBookView);
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//
//            }
//        }).start();
    }

    /**
     * 初始化控件
     */
    @SuppressLint("ResourceAsColor")
    private void initView() {
        mBt = findViewById(R.id.bt);
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swiprefresh);
        mRecycleview = (RecyclerView) findViewById(R.id.recycleview);
        //设置布局
        gridLayoutManager = new GridLayoutManager(this, 2);
        mRecycleview.setLayoutManager(gridLayoutManager);
        // 顶部控件刷新的样式
        mSwipeRefreshLayout.setColorScheme(android.R.color.holo_red_light, android.R.color.holo_green_light,
                android.R.color.holo_blue_bright, android.R.color.holo_orange_light);
        mRecycleview.setItemAnimator(new DefaultItemAnimator());
        mRecycleview.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));
        beautifulGirlAdapter = new BeautifulGirlAdapter(getApplicationContext(), dataList);
        mRecycleview.setAdapter(beautifulGirlAdapter);
        beautifulGirlAdapter.notifyDataSetChanged();
        mBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mGirlPresent.getSearchBooks(1);
            }
        });
        //下拉刷新
//        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                //延迟一秒钟再执行任务
//                new Handler().postDelayed(new Runnable() {
//                    public void run() {
//                        mSwipeRefreshLayout.setRefreshing(false);     //取消刷新状态
////                        addData();
////                        beautifulGirlAdapter.notifyItemChanged(mGridLayoutMgr.getItemCount() + 1);
//                    }
//                }, 1000);
//            }
//        });
//        //上拉加载
//        mRecycleview.setOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//                super.onScrollStateChanged(recyclerView, newState);
//                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 1 == beautifulGirlAdapter.getItemCount()) {
//                    new Handler().postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
////                            for (int i = 0; i < 5; i++) {
////                                int index = i + 1;
////                                dataList.add("上拉加载" + index);
////                                swipRecycleAdapter.notifyItemChanged(mGridLayoutMgr.getItemCount() + 1);
////                            }
//                        }
//                    }, 1000);
//                }
//            }
//
//            @Override
//            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//                lastVisibleItem = gridLayoutManager.findLastVisibleItemPosition();
//            }
//        });

    }

    /**
     * 获取beautifulGirl的数据
     */
//    void getData() {
//        OkHttpClient okHttpClient = new OkHttpClient();
//        Request request = new Request.Builder().get().url(URL).build();
//        okHttpClient.newCall(request).enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                Log.i(TAG, "onFailure: =====");
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                final String str = response.body().string();
//                Log.i(TAG, "run:==== " + str);
//                //更新UI运行在子线程中
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        //转化为实体类
//                        Gson gson = new Gson();
//                        BeautifulGirl beautifulGirlBean = gson.fromJson(str, BeautifulGirl.class);
//                        dataList.addAll(beautifulGirlBean.getResults());
//                        //绑定适配器
//                    }
//                });
//            }
//        });
//    }

    private BeautifulGirl mGirl;
    private GirlView mBookView = new GirlView() {
        @Override
        public void onSuccess(BeautifulGirl girl) {
            mGirl = girl;
            Log.i(TAG, "onSuccess: ==="+mGirl);
            dataList.addAll(mGirl.getResults());
            //绑定适配器
//            beautifulGirlAdapter = new BeautifulGirlAdapter(getApplicationContext(), dataList);
//            mRecycleview.setAdapter(beautifulGirlAdapter);
//            beautifulGirlAdapter.notifyDataSetChanged();
        }

        @Override
        public void onError(String result) {
            Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mGirlPresent.onStop();
    }
}
