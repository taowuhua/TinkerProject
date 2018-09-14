package com.example.wb_twh369668.bottomsheetdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private CoordinatorLayout mCoordinatorLayout;
    private NestedScrollView bottomsheet;
    private Button mBtDisplay;
    private Button mBtHide;
    private BottomSheetBehavior<NestedScrollView> bottomSheetBehavior;
    private Button mBtTab;
    private Button customTab;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mCoordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorLayout);
        mBtDisplay = (Button) findViewById(R.id.display);
        mBtHide = (Button) findViewById(R.id.hide);
        mBtTab = (Button) findViewById(R.id.tab);
        customTab = (Button) findViewById(R.id.custom_tab);
        mBtDisplay.setOnClickListener(this);
        mBtHide.setOnClickListener(this);
        mBtTab.setOnClickListener(this);
        customTab.setOnClickListener(this);
        bottomsheet = (NestedScrollView) findViewById(R.id.bottomsheet);
        bottomSheetBehavior = BottomSheetBehavior.from(bottomsheet);
        bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                //这里是bottomSheet 状态的改变回调
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                //这里是拖拽中的回调，根据slideOffset可以做一些动画
            }
        });
    }

    /**
     * 设置bottomSheetBehavior的显示和隐藏
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.display:
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                break;
            case R.id.hide:
                if (bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED){
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
                break;
            case R.id.tab:
                 intent = new Intent(MainActivity.this,BottomTabActivity.class);
                startActivity(intent);
                break;
            case R.id.custom_tab:
                 intent = new Intent(MainActivity.this,CustomTabActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
