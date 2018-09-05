package com.example.wb_twh369668.recycleviewdemo.ui;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.wb_twh369668.recycleviewdemo.R;
import com.example.wb_twh369668.recycleviewdemo.adapter.ListRecycleAdapter;
import com.example.wb_twh369668.recycleviewdemo.adapter.RecycleAdapter;
import com.example.wb_twh369668.recycleviewdemo.service.entity.Book;
import com.example.wb_twh369668.recycleviewdemo.service.presenter.BookPresenter;
import com.example.wb_twh369668.recycleviewdemo.service.view.BookView;
import com.example.wb_twh369668.recycleviewdemo.utils.DividerItemDecoration;

/**
 * initRecycleView（）根据位置决定产生什么布局
 * initListRecycleView()最基本的使用
 */
public class MainActivity extends Activity {
    private RecyclerView mRcyV;
    private RecycleAdapter recyvleAdapter;
    private ListRecycleAdapter listRecycleAdapter;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private BookPresenter mBookPresenter = new BookPresenter(this);
    private Book book;
    private Button mBtList;
    private Button mBtUniversalAdapter;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRcyV = findViewById(R.id.recycle);
        mBtList = findViewById(R.id.bt_list);
        mBtUniversalAdapter = findViewById(R.id.bt_adapter);
        mSwipeRefreshLayout = findViewById(R.id.swipe_refresh);

        mBtList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBookPresenter.getSearchBooks("金瓶梅", null, 0, 1);
            }
        });
        mBtUniversalAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, UniversalAdapterActivity.class);
                startActivity(intent);
            }
        });
        // 顶部刷新的样式
        mSwipeRefreshLayout.setColorScheme(android.R.color.holo_red_light, android.R.color.holo_green_light,
                android.R.color.holo_blue_bright, android.R.color.holo_orange_light);
        //下拉刷新
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //延迟一秒钟再执行任务
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        mSwipeRefreshLayout.setRefreshing(false);     //取消刷新状态
                        listRecycleAdapter.removeItem(0);
                        listRecycleAdapter.notifyDataSetChanged();
                    }
                }, 1000);
            }
        });
        mBookPresenter.onCreate();
        mBookPresenter.attachView(mBookView);

    }

    public void initRecycleView() {
//      LinearLayout布局
        final GridLayoutManager mGridLayoutMgr = new GridLayoutManager(this, 1);
//      mNormalRecyclerView.setLayoutManager(new GridLayoutManager(this, 4));//设置网格布局
//      mNormalRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));//设置瀑布流布局

        mRcyV.setLayoutManager(mGridLayoutMgr);
        //设置自动增删动画
        mRcyV.setItemAnimator(new DefaultItemAnimator());
        mRcyV.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));
        recyvleAdapter = new RecycleAdapter(getApplicationContext(), book.getBooks().get(0).getTags());
        recyvleAdapter.setOnItemClickListener(new RecycleAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(MainActivity.this, "单击了第" + (position + 1) + "条item", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, int position) {
                recyvleAdapter.removeItem(position);//长按删除
            }
        });
        mRcyV.setAdapter(recyvleAdapter);
    }

    private void initListRecycleView() {
//      LinearLayout布局
        final GridLayoutManager mGridLayoutMgr = new GridLayoutManager(this, 1);
//      mNormalRecyclerView.setLayoutManager(new GridLayoutManager(this, 4));//设置网格布局
//      mNormalRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));//设置瀑布流布局

        mRcyV.setLayoutManager(mGridLayoutMgr);
        //设置自动增删动画
        mRcyV.setItemAnimator(new DefaultItemAnimator());
        mRcyV.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));
        listRecycleAdapter = new ListRecycleAdapter(getApplicationContext(), book.getBooks().get(0).getTags());
        listRecycleAdapter.setOnItemClickListener(new ListRecycleAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(MainActivity.this, "单击了第" + (position + 1) + "条item", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, int position) {
                listRecycleAdapter.removeItem(position);//长按删除
            }
        });
        mRcyV.setAdapter(listRecycleAdapter);
    }

    private BookView mBookView = new BookView() {
        @Override
        public void onSuccess(Book mBook) {
            book = mBook;
//            initRecycleView();
          initListRecycleView();
        }

        @Override
        public void onError(String result) {
            Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mBookPresenter.onStop();
    }

}
