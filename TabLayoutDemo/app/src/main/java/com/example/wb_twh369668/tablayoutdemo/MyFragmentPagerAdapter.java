package com.example.wb_twh369668.tablayoutdemo;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * creat by TWH on 2018/9/12
 */
public class MyFragmentPagerAdapter extends FragmentPagerAdapter {
    private Context mContext;
    private List<String> mTitleList;
    private List<Fragment> mFragmentList;

    public MyFragmentPagerAdapter(Context mContext, List<Fragment> mFragmentList, FragmentManager fm, List<String> mTitleList) {
        super(fm);
        this.mContext = mContext;
        this.mTitleList = mTitleList;
        this.mFragmentList = mFragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitleList.get(position);
    }
}
