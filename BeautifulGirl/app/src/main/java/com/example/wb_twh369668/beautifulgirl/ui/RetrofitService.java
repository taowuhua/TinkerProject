package com.example.wb_twh369668.beautifulgirl.ui;

import com.example.wb_twh369668.beautifulgirl.bean.BeautifulGirl;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * creat by TWH on 2018/9/5
 * /福利/100/2
 */
public interface RetrofitService {
    @GET("福利/20/2")
    Observable<BeautifulGirl> getSearchBooks(@Query("fuli") int fuli);

}
