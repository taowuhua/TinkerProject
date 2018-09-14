package com.example.wb_twh369668.fragmentdemo.ui.fragment.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.wb_twh369668.fragmentdemo.R;

public class MainActivity extends AppCompatActivity {

    private Button mStaticFragment;
    private Button mSysnFragment;
    private Button mTwoFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mStaticFragment = (Button) findViewById(R.id.bt_staticFragment);
        mSysnFragment = (Button) findViewById(R.id.bt_SysnFragment);
        mTwoFragment = (Button) findViewById(R.id.bt_twoFragment);
        mStaticFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, StaticFragmentActivity.class);
                startActivity(intent);
            }
        });
        mSysnFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SyscFragmentActivity.class);
                startActivity(intent);

            }
        });
        mTwoFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PaginationActivity.class);
                startActivity(intent);

            }
        });
    }
}
