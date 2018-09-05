package com.example.wb_twh369668.recycleviewdemo.service.view;


import com.example.wb_twh369668.recycleviewdemo.service.entity.Book;

/**
 * creat by TWH on 2018/9/5
 */
public interface  BookView extends  View {
    void onSuccess(Book mBook);
    void onError(String result);
}
