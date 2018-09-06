package com.example.wb_twh369668.recycleviewdemo.interfaces;

/**
 * creat by TWH on 2018/9/6
 */
public interface ItemTouchHelperAdapterListener {
    void onItemMove(int fromPosition, int desPosition);

    void onItemSwip(int position);
}
