package com.example.wb_twh369668.tablayoutdemo;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TablaywithfragmentActivity2 extends AppCompatActivity {
    public static final String[] sTitle = new String[]{"ITEM FIRST", "ITEM SECOND", "ITEM THIRD"};
    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private List<View> viewList;
    private List<String> titleList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tablaywithfragment2);
        mViewPager = (ViewPager) findViewById(R.id.view_pager2);
        mTabLayout = (TabLayout) findViewById(R.id.tabLayout2);
        //数组转化为list
        titleList = Arrays.asList(sTitle);
        initTab();
        viewList = new ArrayList<View>();
        //viewpager加载要显示的页卡
        LayoutInflater lf = LayoutInflater.from(this);
        View view1 = lf.inflate(R.layout.layout1, mViewPager, false);
        View view2 = lf.inflate(R.layout.layout2, mViewPager, false);
        View view3 = lf.inflate(R.layout.layout3, mViewPager, false);
        // 将要分页显示的View装入数组中
        viewList = new ArrayList<View>();// 将要分页显示的View装入数组中
        viewList.add(view1);
        viewList.add(view2);
        viewList.add(view3);
        MyViewpagerAdapter2 myViewpagerAdapter = new MyViewpagerAdapter2(this,viewList,titleList);
        mViewPager.setAdapter(myViewpagerAdapter);
    }

    /**
     * 设置tab
     */
    void initTab() {
        mTabLayout.addTab(mTabLayout.newTab().setText(sTitle[0]));
        mTabLayout.addTab(mTabLayout.newTab().setText(sTitle[1]));
        mTabLayout.addTab(mTabLayout.newTab().setText(sTitle[2]));
    }
}
