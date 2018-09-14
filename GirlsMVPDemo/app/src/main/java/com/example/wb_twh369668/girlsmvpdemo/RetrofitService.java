package com.example.wb_twh369668.girlsmvpdemo;

import com.example.wb_twh369668.girlsmvpdemo.entity.Book;
import com.example.wb_twh369668.girlsmvpdemo.entity.Girls;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * creat by TWH on 2018/9/5
 */
public interface RetrofitService {
    @GET("福利/20/2")
    Observable<Girls> getSearchGirls(@Query("q") int q);
    @GET("book/search")
    Observable<Book> getSearchBooks(@Query("q") String name,
                                    @Query("tag") String tag, @Query("start") int start,
                                    @Query("count") int count);

}
