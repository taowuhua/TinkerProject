package com.example.wb_twh369668.scoollistener;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private RecyclerView mRecycler;
    private MyAdapter mAdapter;
    private GridLayoutManager gridLayoutManager;
    private int offSet;
    private List<String> urlList = new ArrayList<String>();
    private int firstVisibleItemPosition;//当前第一个可见的item
    private int lastItemPosition;//当前可见的item个数
    private JCVideoPlayerStandard currPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecycler = findViewById(R.id.recycler);
        initView();
        initData();
        initRecyclerView();
    }

    @Override
    public void onBackPressed() {
        if (JCVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        JCVideoPlayer.releaseAllVideos();
    }

//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if (keyCode == KeyEvent.KEYCODE_BACK) {
//        //点击物理返回键时 判断视频是否全屏播放 是的话销毁全屏
//            if (currPlayer.backPress()) {
//                return true;
//            }
//        }
//        return super.onKeyDown(keyCode, event);
//    }

    private void initView() {
        mRecycler = findViewById(R.id.recycler);
        mAdapter = new MyAdapter(this, urlList);
    }

    /**
     * 初始化数据
     */
    private void initData() {
        for (int i = 0; i < 20; i++) {
            urlList.add("http://gslb.miaopai.com/stream/ed5HCfnhovu3tyIQAiv60Q__.mp4");
        }
    }

    /**
     * 初始化recyclerView
     */
    private void initRecyclerView() {
        gridLayoutManager = new GridLayoutManager(this, 1);
        mRecycler.setLayoutManager(gridLayoutManager);
        mRecycler.setAdapter(mAdapter);
        mRecycler.setOnScrollListener(new RecyclerView.OnScrollListener() {
            /**这个方法在RecyclerView的滑动状态改变时会调用
             * 也就是说，newState=0时，RecyclerView在停止状态中
             newState=1和newState=2时，RecyclerView在滑动状态中
             不同的是,当由0—>1 ，2 时，RecyclerView由静止状态下变为滑动状态，然后1–0 滑动状态变为静止
             （调用方法 mHomeDateRecyclerView.smoothScrollToPosition(currentPostion); 等这一类方法所触发） ，
             2–>0 滑动状态变为静止状态（左右滑动RecyclerView 动态的慢慢结束）
             * @param recyclerView
             * @param newState
             */
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                //滑动停止自动播放视频
                if (newState == 0) {
                    autoPlayVideo(mRecycler);
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                initOffset();
                Log.d(TAG, "scroll " + dx + "  " + dy + " flag " + offSet + "");
            }
        });
    }

    /**
     * 初始化偏移量
     */
    private void initOffset() {
        if (gridLayoutManager != null) {
            //获取RecyclerView当前顶部显示的第一个条目对应的索引
            firstVisibleItemPosition = gridLayoutManager.findFirstVisibleItemPosition();
            //获取最后一个可见view的位置
            int lastVisibleItemPosition = gridLayoutManager.findLastVisibleItemPosition();
            //获取可见的个数
            lastItemPosition = gridLayoutManager.getChildCount();
            //根据索引来获取对应的itemView
            View firstVisibleItemView = gridLayoutManager.findViewByPosition(firstVisibleItemPosition);
            if (firstVisibleItemView != null) {
                //获取当前显示条目的高度
                int firstVisibleItemHeigh = firstVisibleItemView.getHeight();
                //获取偏移量
                offSet = firstVisibleItemPosition * firstVisibleItemHeigh - firstVisibleItemView.getTop();
            }
        }
    }

    /**
     * 滑动停止自动播放视频
     */
    void autoPlayVideo(RecyclerView view) {
        for (int i = 0; i < lastItemPosition; i++) {
            if (view != null && view.getChildAt(i) != null && view.getChildAt(i).findViewById(R.id.player_list_video) != null) {
                currPlayer = (JCVideoPlayerStandard) view.getChildAt(i).findViewById(R.id.player_list_video);
                Rect rect = new Rect();
                //获取当前view 的 位置
                currPlayer.getLocalVisibleRect(rect);
                int videoheight = currPlayer.getHeight();
                if (rect.top == 0 && rect.bottom == videoheight) {
                    if (currPlayer.currentState == JCVideoPlayer.CURRENT_STATE_NORMAL
                            || currPlayer.currentState == JCVideoPlayer.CURRENT_STATE_ERROR) {
                        currPlayer.startButton.performClick();
                    }
                    return;
                }
            }
        }
        //释放其他视频资源
        JCVideoPlayer.releaseAllVideos();
    }
}
