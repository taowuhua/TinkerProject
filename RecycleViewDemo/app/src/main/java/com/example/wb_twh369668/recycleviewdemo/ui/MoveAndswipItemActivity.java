package com.example.wb_twh369668.recycleviewdemo.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.wb_twh369668.recycleviewdemo.R;
import com.example.wb_twh369668.recycleviewdemo.adapter.BookItemClickRecyclerViewCallback;
import com.example.wb_twh369668.recycleviewdemo.adapter.MoveItemRecycleAdapter;
import com.example.wb_twh369668.recycleviewdemo.interfaces.ItemTouchHelperAdapterListener;
import com.example.wb_twh369668.recycleviewdemo.service.entity.Book;
import com.example.wb_twh369668.recycleviewdemo.service.presenter.BookPresenter;
import com.example.wb_twh369668.recycleviewdemo.service.view.BookView;
import com.example.wb_twh369668.recycleviewdemo.utils.DividerItemDecoration;

public class MoveAndswipItemActivity extends Activity implements View.OnClickListener {
    private static final String TAG = "MoveAndswipItemActivity";
    private RecyclerView mRcyV;
    private MoveItemRecycleAdapter moveItemRecycleAdapter;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private BookPresenter mBookPresenter = new BookPresenter(this);
    private Book book;
    private Button mBtDisplayData;
    private ItemTouchHelperAdapterListener itemTouchHelperAdapterListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_move_andswip_item);
        mBookPresenter.onCreate();
        mBookPresenter.attachView(mBookView);
        mBtDisplayData = findViewById(R.id.bt_displaydata);
        mRcyV = findViewById(R.id.rv_displaydata);
        mBtDisplayData.setOnClickListener(this);
    }

    /**
     * 初始化recycle
     */
    private void initRecycleView() {
        final GridLayoutManager mGridLayoutMgr = new GridLayoutManager(this, 1);
//      mNormalRecyclerView.setLayoutManager(new GridLayoutManager(this, 4));//设置网格布局
//      mNormalRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));//设置瀑布流布局

        mRcyV.setLayoutManager(mGridLayoutMgr);
        //设置自动增删动画
        mRcyV.setItemAnimator(new DefaultItemAnimator());
        mRcyV.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));
        moveItemRecycleAdapter = new MoveItemRecycleAdapter(getApplicationContext(), book.getBooks().get(0).getTags());
        moveItemRecycleAdapter.setOnItemClickListener(new MoveItemRecycleAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(MoveAndswipItemActivity.this, "单击了第" + (position + 1) + "条item", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, int position) {
            }
        });
        mRcyV.setAdapter(moveItemRecycleAdapter);
        //TODO 先实例化Callback创建BookItemClickRecyclerViewCallback;注意moveItemRecycleAdapter为MoveItemRecycleAdapter的子类，
        // 所以才可以作为参数传递，这里需要自己再次揣摩
        /**
         * ★★★★★★★★★★★★★★★★★★★★★★★
         * 要使用ItemTouchHelper，你需要创建一个ItemTouchHelper.Callback。
         * 这个接口可以让你监听“move(上下移动)”与 “swipe（左右滑动）”事件。这里还是
         * ★控制view被选中
         * 的状态以及★重写默认动画的地方。
         *
         * 如果你只是想要一个基本的实现，有一个
         * 帮助类可以使用：SimpleCallback,但是为了了解其工作机制，我们还是自己实现
         */
        ItemTouchHelper.Callback callBack = new BookItemClickRecyclerViewCallback(moveItemRecycleAdapter);
        //用Callback构造ItemtouchHelper
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callBack);
        itemTouchHelper.attachToRecyclerView(mRcyV);

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
            Toast.makeText(MoveAndswipItemActivity.this, result, Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mBookPresenter.onStop();
    }
}
