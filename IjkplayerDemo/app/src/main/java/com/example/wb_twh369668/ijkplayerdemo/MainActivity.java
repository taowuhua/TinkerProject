package com.example.wb_twh369668.ijkplayerdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.Button;

import com.bumptech.glide.Glide;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

public class MainActivity extends AppCompatActivity {
    private String videoUrl = "http://gslb.miaopai.com/stream/ed5HCfnhovu3tyIQAiv60Q__.mp4";
    private Button mBt;
    private JCVideoPlayerStandard player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBt = findViewById(R.id.bt);
        //直接进入全屏
//        mBt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                JCVideoPlayerStandard player = (JCVideoPlayerStandard) findViewById(R.id.player_list_video);
//                boolean setUp = player.setUp(URL, JCVideoPlayer.SCREEN_LAYOUT_LIST, "");
//                if (setUp) {
//                    Glide.with(MainActivity.this).load("http://a4.att.hudong.com/05/71/01300000057455120185716259013.jpg").into(player.thumbImageView);
//                }
//            }
//        });

        player = (JCVideoPlayerStandard) findViewById(R.id.player_list_video);
        boolean setUp = player.setUp(videoUrl, JCVideoPlayer.SCREEN_LAYOUT_LIST, "");
        if (setUp) {
            Glide.with(MainActivity.this).load("http://a4.att.hudong.com/05/71/01300000057455120185716259013.jpg").into(player.thumbImageView);
        }

        //直接进入全屏
        player.startFullscreen(this, JCVideoPlayerStandard.class, videoUrl, "");
        //模拟用户点击开始按钮，NORMAL状态下点击开始播放视频，播放中点击暂停视频
        player.startButton.performClick();

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

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
        //点击物理返回键时 判断视频是否全屏播放 是的话销毁全屏
            if (player.backPress()) {
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
