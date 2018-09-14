package com.example.wb_twh369668.girlsmvpdemo;

import android.content.Context;

import com.example.wb_twh369668.girlsmvpdemo.entity.Book;
import com.example.wb_twh369668.girlsmvpdemo.entity.Girls;

import rx.Observable;

/**
 * creat by TWH on 2018/9/5
 */
public class DataManager {
    private RetrofitService mRetrofitService;

    public DataManager(Context context) {
        this.mRetrofitService = RetrofitHelper.getInstance(context).getServer();
    }

    public Observable<Girls> getSearchGirls(int q) {
        return mRetrofitService.getSearchGirls(q);
    }
    public Observable<Book> getSearchBooks(String name, String tag, int start, int count) {
        return mRetrofitService.getSearchBooks(name, tag, start, count);
    }
}
