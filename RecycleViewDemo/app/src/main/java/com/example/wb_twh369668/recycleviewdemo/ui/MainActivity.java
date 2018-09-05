package com.example.wb_twh369668.recycleviewdemo.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.wb_twh369668.recycleviewdemo.R;
import com.example.wb_twh369668.recycleviewdemo.adapter.RecycleAdapter;
import com.example.wb_twh369668.recycleviewdemo.service.entity.Book;
import com.example.wb_twh369668.recycleviewdemo.service.presenter.BookPresenter;
import com.example.wb_twh369668.recycleviewdemo.service.view.BookView;
import com.example.wb_twh369668.recycleviewdemo.utils.DividerItemDecoration;

public class MainActivity extends Activity {
    private RecyclerView mRcyV;
    private RecycleAdapter recyvleAdapter;
    private BookPresenter mBookPresenter = new BookPresenter(this);
    private Book book;
    private Button mBtList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRcyV = findViewById(R.id.recycle);
        mBtList = findViewById(R.id.bt_list);

        mBtList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBookPresenter.getSearchBooks("金瓶梅", null, 0, 1);
            }
        });
        mBookPresenter.onCreate();
        mBookPresenter.attachView(mBookView);

    }

    public void initRecycleView() {
        // LinearLayout布局
        final GridLayoutManager mGridLayoutMgr = new GridLayoutManager(this, 1);
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

    private BookView mBookView = new BookView() {
        @Override
        public void onSuccess(Book mBook) {
            book = mBook;
            initRecycleView();
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
