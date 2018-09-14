package com.example.wb_twh369668.beautifulgirl.mvp.manager;

import android.content.Context;

import com.example.wb_twh369668.beautifulgirl.bean.BeautifulGirl;
import com.example.wb_twh369668.beautifulgirl.ui.RetrofitService;
import com.example.wb_twh369668.beautifulgirl.util.RetrofitHelper;

import rx.Observable;

/**
 * creat by TWH on 2018/9/5
 */
public class DataManager {
    private RetrofitService mRetrofitService;

    public DataManager(Context context) {
        this.mRetrofitService = RetrofitHelper.getInstance(context).getServer();
    }

    public Observable<BeautifulGirl> getSearchBooks(int fuli) {
        return mRetrofitService.getSearchBooks(fuli);
    }
}
