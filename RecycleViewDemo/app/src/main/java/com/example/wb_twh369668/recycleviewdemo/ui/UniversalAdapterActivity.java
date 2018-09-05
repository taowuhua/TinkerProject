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
import com.example.wb_twh369668.recycleviewdemo.service.entity.Book;
import com.example.wb_twh369668.recycleviewdemo.service.presenter.BookPresenter;
import com.example.wb_twh369668.recycleviewdemo.service.view.BookView;
import com.example.wb_twh369668.recycleviewdemo.utils.DividerItemDecoration;
import com.zhy.base.adapter.ViewHolder;
import com.zhy.base.adapter.recyclerview.CommonAdapter;

public class UniversalAdapterActivity extends Activity implements View.OnClickListener {
    private static final String TAG = "UniversalAdapterActivit";
    private Book book;
    private Button mBtDisplayData;
    private RecyclerView mRvDisplayData;
    private BookPresenter mBookPresenter = new BookPresenter(this);
    private int count;
    private String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_universal_adapter);
        mBookPresenter.onCreate();
        mBookPresenter.attachView(mBookView);
        mBtDisplayData = findViewById(R.id.bt_displaydata);
        mRvDisplayData = findViewById(R.id.rv_displaydata);
        mBtDisplayData.setOnClickListener(this);
    }

    /**
     * 初始化recycle，注意使用万能适配器的时候的泛型和参数的类型需要一直
     */
    private void initRecycleView() {
        final GridLayoutManager mGridLayoutMgr = new GridLayoutManager(this, 1);
//      mNormalRecyclerView.setLayoutManager(new GridLayoutManager(this, 4));//设置网格布局
//      mNormalRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));//设置瀑布流布局

        mRvDisplayData.setLayoutManager(mGridLayoutMgr);
        //设置自动增删动画
        mRvDisplayData.setItemAnimator(new DefaultItemAnimator());
        mRvDisplayData.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));
        mRvDisplayData.setAdapter(new CommonAdapter<Book.BooksBean.TagsBean>(this, R.layout.item_list_viewtype, book.getBooks().get(0).getTags()) {
            @Override
            public void convert(ViewHolder holder, Book.BooksBean.TagsBean s) {
                holder.setText(R.id.tv_count, s.getCount() + "");
                holder.setText(R.id.tv_name, s.getTitle());
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_displaydata:
                mBookPresenter.getSearchBooks("金瓶梅", null, 0, 1);
                break;
            default:
                break;
        }

    }

    private BookView mBookView = new BookView() {
        @Override
        public void onSuccess(Book mBook) {
            book = mBook;
            initRecycleView();
        }

        @Override
        public void onError(String result) {
            Toast.makeText(UniversalAdapterActivity.this, result, Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mBookPresenter.onStop();
    }
}
