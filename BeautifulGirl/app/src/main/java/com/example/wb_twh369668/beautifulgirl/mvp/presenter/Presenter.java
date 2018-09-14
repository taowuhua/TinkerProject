package com.example.wb_twh369668.beautifulgirl.mvp.presenter;

import android.content.Intent;

import com.example.wb_twh369668.beautifulgirl.mvp.view.View;

/**
 * creat by TWH on 2018/9/5
 */
public interface Presenter {
    void onCreate();

    void onStart();//暂时没用到

    void onStop();

    void pause();//暂时没用到

    void attachView(View view);

    void attachIncomingIntent(Intent intent);//暂时没用到
}
