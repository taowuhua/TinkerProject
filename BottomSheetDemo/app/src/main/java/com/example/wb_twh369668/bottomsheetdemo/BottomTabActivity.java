package com.example.wb_twh369668.bottomsheetdemo;

import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class BottomTabActivity extends AppCompatActivity {
    private TabLayout mTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_tab);
        mTabLayout = (TabLayout) findViewById(R.id.bottom_tab_layout);
        mTabLayout.addTab(mTabLayout.newTab().setText("音乐"));
        mTabLayout.addTab(mTabLayout.newTab().setText("体育"));
        mTabLayout.addTab(mTabLayout.newTab().setText("笑话"));
    }
}
