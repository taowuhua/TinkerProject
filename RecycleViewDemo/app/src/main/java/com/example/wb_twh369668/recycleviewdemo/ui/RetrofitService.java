package com.example.wb_twh369668.recycleviewdemo.ui;

import com.example.wb_twh369668.recycleviewdemo.service.entity.Book;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * creat by TWH on 2018/9/5
 */
public interface RetrofitService {
    @GET("book/search")
    Observable<Book> getSearchBooks(@Query("q") String name,
                                    @Query("tag") String tag, @Query("start") int start,
                                    @Query("count") int count);

}
