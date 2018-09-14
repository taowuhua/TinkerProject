package com.example.wb_twh369668.girlsmvpdemo.view;


import com.example.wb_twh369668.girlsmvpdemo.entity.Book;

/**
 * creat by TWH on 2018/9/5
 */
public interface BookView extends  View {
    void onSuccess(Book mBook);
    void onError(String result);
}
