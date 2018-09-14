package com.example.wb_twh369668.fragmentdemo.ui.fragment.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.example.wb_twh369668.fragmentdemo.R;
import com.example.wb_twh369668.fragmentdemo.ui.fragment.SyscFragment;

public class SyscFragmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sysc_fragment);
        Fragment mFragment = SyscFragment.getInstace();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.bt_sysnFramelayout, mFragment, "SYSN_FRAGMENT").commit();
    }
}
