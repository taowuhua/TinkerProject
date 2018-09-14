package com.example.wb_twh369668.tablayoutdemo;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import java.util.List;

/**
 * creat by TWH on 2018/9/12
 */
public class MyViewpagerAdapter extends PagerAdapter {
    private Context mContext;
    private List<View> list;
    private List<String> mTitleList;


    public MyViewpagerAdapter(Context mContext, List<View> list, List<String> mTitleList) {
        this.mContext = mContext;
        this.list = list;
        this.mTitleList = mTitleList;
    }

    //添加到容器，初始化
    @Override
    public Object instantiateItem(View container, int position) {

        ((ViewPager) container).addView(list.get(position));
        return list.get(position);
    }

    //返回视图个数
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    //从容器移除
    @Override
    public void destroyItem(View container, int position, Object object) {
        ((ViewPager) container).removeView(list.get(position));//删除页卡

    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitleList.get(position);
    }
}
