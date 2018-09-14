package com.example.wb_twh369668.tablayoutdemo;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class BaseDisplayActivity extends AppCompatActivity {

    private TabLayout mTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_display);
        mTabLayout = (TabLayout) findViewById(R.id.tablayout);
        //设置tab上面的文字
        mTabLayout.setTabTextColors(Color.GRAY, Color.RED);
        //代码添加tab
        mTabLayout.addTab(mTabLayout.newTab().setText("个性推荐"));
        mTabLayout.addTab(mTabLayout.newTab().setText("歌单"));
        mTabLayout.addTab(mTabLayout.newTab().setText("主播电台"));
        mTabLayout.addTab(mTabLayout.newTab().setText("排行榜").setIcon(R.mipmap.ic_launcher));
    }
}
