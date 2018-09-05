package com.example.wb_twh369668.recycleviewdemo.service.manager;

import android.content.Context;

import com.example.wb_twh369668.recycleviewdemo.service.entity.Book;
import com.example.wb_twh369668.recycleviewdemo.ui.RetrofitService;
import com.example.wb_twh369668.recycleviewdemo.utils.RetrofitHelper;

import rx.Observable;

/**
 * creat by TWH on 2018/9/5
 */
public class DataManager {
    private RetrofitService mRetrofitService;

    public DataManager(Context context) {
        this.mRetrofitService = RetrofitHelper.getInstance(context).getServer();
    }

    public Observable<Book> getSearchBooks(String name, String tag, int start, int count) {
        return mRetrofitService.getSearchBooks(name, tag, start, count);
    }
}
