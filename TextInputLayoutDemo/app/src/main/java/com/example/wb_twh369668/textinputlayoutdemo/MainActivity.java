package com.example.wb_twh369668.textinputlayoutdemo;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextInputLayout mTextInputLayoutAger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextInputLayoutAger = (TextInputLayout) findViewById(R.id.textlayout_input_age);
        //设置可以计数
        mTextInputLayoutAger.setCounterEnabled(true);
        //计数的最大值
        mTextInputLayoutAger.setCounterMaxLength(20);
        //
    }
}
