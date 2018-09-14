package com.example.wb_twh369668.meterdesigin;

import android.annotation.TargetApi;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private ConstraintLayout mConstraint;
    private Button ordinarySnackbar;
    private Button customButoon;
    private Button customCallback;
    private Snackbar customSnackbar;
    private Toolbar mToolBar;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mConstraint = findViewById(R.id.constraint);
        mToolBar = findViewById(R.id.toolbar);
        ordinarySnackbar = findViewById(R.id.ordinaryButoon);
        customButoon = findViewById(R.id.customButoon);
        customCallback = findViewById(R.id.customCallback);
        // 设置 toolbar 背景色
        mToolBar.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        mToolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "我是toolbar的Navigation", Toast.LENGTH_LONG).show();
            }
        });
        //设置 Toolbar menu
        mToolBar.inflateMenu(R.menu.setting_menu);
        mToolBar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.item_setting:
                        //点击设置菜单
                        Toast.makeText(getApplicationContext(), "我是设置菜单", Toast.LENGTH_LONG).show();
                        break;
                }
                return false;
            }
        });
        ordinarySnackbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getOridinarySnackbar();
            }
        });
        customButoon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getCustomSnackbar();
            }
        });
        customCallback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callBack();
            }
        });
    }

    /**
     * 普通snackbar
     */
    void getOridinarySnackbar() {
        Snackbar sb = Snackbar.make(mConstraint, "第二个snakBar", Snackbar.LENGTH_INDEFINITE);
        sb.setAction("确定", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callBack();
            }
        });
        sb.show();
    }

    /**
     * 自定义的snackbar
     */
    void getCustomSnackbar() {
        customSnackbar = Snackbar.make(mConstraint, "第二个snakBar", Snackbar.LENGTH_INDEFINITE);
        customSnackbar.setAction("确定", new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        //action的颜色
        customSnackbar.setActionTextColor(Color.YELLOW);
        View view = customSnackbar.getView();
        view.setBackgroundColor(Color.parseColor("#7B68EE"));
        //获取Snackbar的message控件，修改字体颜色
        ((TextView) view.findViewById(R.id.snackbar_text)).setTextColor(Color.parseColor("#FFDAB9"));
        Snackbar.SnackbarLayout snackbarLayout = (Snackbar.SnackbarLayout) view;
        //custom_layout是你自定义的布局
        View add_view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.custom_layout, null);
        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        p.gravity = Gravity.CENTER_VERTICAL;
        //数字表示新加的布局在SnackBar中的位置，从0开始,取决于你SnackBar里面有多少个子View
        snackbarLayout.addView(add_view, 0, p);
        customSnackbar.show();
    }

    /**
     * 自定义snackbar的回调
     */
    void callBack() {
        Snackbar callBackSnackbar = Snackbar.make(mConstraint, "第二个snakBar", Snackbar.LENGTH_INDEFINITE);
        callBackSnackbar.setAction("确定", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callBack();
            }
        });
        callBackSnackbar.setCallback(new Snackbar.Callback() {
            @Override
            public void onDismissed(Snackbar snackbar, int event) {
                super.onDismissed(snackbar, event);
                Toast.makeText(getApplicationContext(), "onDismissed", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onShown(Snackbar snackbar) {
                super.onShown(snackbar);
                Toast.makeText(getApplicationContext(), "onShown", Toast.LENGTH_SHORT).show();
            }
        });
        callBackSnackbar.show();
    }

}
