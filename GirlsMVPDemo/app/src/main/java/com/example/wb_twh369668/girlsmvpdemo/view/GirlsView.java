package com.example.wb_twh369668.girlsmvpdemo.view;


import com.example.wb_twh369668.girlsmvpdemo.entity.Girls;

/**
 * creat by TWH on 2018/9/5
 */
public interface GirlsView extends View {
    void onSuccess(Girls mGirl);

    void onError(String result);
}
