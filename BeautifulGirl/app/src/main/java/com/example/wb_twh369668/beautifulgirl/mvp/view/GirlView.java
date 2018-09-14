package com.example.wb_twh369668.beautifulgirl.mvp.view;


import com.example.wb_twh369668.beautifulgirl.bean.BeautifulGirl;

/**
 * creat by TWH on 2018/9/5
 */
public interface GirlView extends View {
    void onSuccess(BeautifulGirl beautifulGirl);

    void onError(String result);
}
