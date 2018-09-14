package com.example.wb_twh369668.fragmentdemo.ui.fragment.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import com.example.wb_twh369668.fragmentdemo.R;
import com.example.wb_twh369668.fragmentdemo.ui.fragment.StaticFragment;

public class StaticFragmentActivity extends AppCompatActivity {
    private StaticFragment mStaticFragment;
    private FrameLayout mFrameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_static_fragment);
        mFrameLayout = (FrameLayout) findViewById(R.id.bt_frameLayout);
        mStaticFragment = new StaticFragment();
        FragmentManager mFragmentManager = getSupportFragmentManager();
        FragmentTransaction mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.add(R.id.bt_frameLayout, mStaticFragment, "STATIC_FRAMENT");
        mFragmentTransaction.commit();
    }
}
