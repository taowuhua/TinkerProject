package com.example.wb_twh369668.fragmentdemo.ui.fragment.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wb_twh369668.fragmentdemo.R;
import com.example.wb_twh369668.fragmentdemo.ui.fragment.SyscFragment;
import com.example.wb_twh369668.fragmentdemo.ui.fragment.StaticFragment;

public class PaginationActivity extends AppCompatActivity {
    private static final String TAG = "PaginationActivity";
    private FrameLayout mBtFrameLayout;
    private TabLayout mBTablayout;
    private String titleArray[] = new String[]{"女生", "男生"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagination);
        initView();

    }

    /**
     * 初始化布局
     */
    private void initView() {
        mBtFrameLayout = (FrameLayout) findViewById(R.id.bt_frameLayout);
        mBTablayout = (TabLayout) findViewById(R.id.bt_tablayout);
        mBTablayout.addTab(mBTablayout.newTab().setText(""));
        mBTablayout.addTab(mBTablayout.newTab().setText("男生"));
        for (int i = 0; i < mBTablayout.getTabCount(); i++) {
            int posit = mBTablayout.getSelectedTabPosition();
            Log.i(TAG, "initView: ===" + posit);
            TabLayout.Tab tab = mBTablayout.getTabAt(i);
            tab.setCustomView(getTabView(i));
            if (tab != null) {
                View tabView = (View) tab.getCustomView().getParent();
                tabView.setTag(i);
                tabView.setOnClickListener(mTabOnClickListener);
            }
        }
    }

    View.OnClickListener mTabOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int pos = (int) v.getTag();
            if (pos == 0) {
                Toast.makeText(PaginationActivity.this, "男生", Toast.LENGTH_SHORT).show();
                SyscFragment syscFragment = SyscFragment.getInstace();
                if (syscFragment != null) {
                    if (syscFragment.isAdded()) {
                        getSupportFragmentManager().beginTransaction().show(syscFragment)
                                .commit();
                    }
//                    else {
//                        getSupportFragmentManager().beginTransaction().remove(syscFragment)
//                                .commit();
//                        getSupportFragmentManager().beginTransaction().add(R.id.bt_frameLayout, syscFragment, "SYSN_FRAGMENT")
//                                .commit();
//                    }
                } else {
                    getSupportFragmentManager().beginTransaction().add(R.id.bt_frameLayout, syscFragment, "SYSN_FRAGMENT")
                            .commit();
                }

            } else {
                TabLayout.Tab tab = mBTablayout.getTabAt(pos);
                if (tab != null) {
                    tab.select();
                    Toast.makeText(PaginationActivity.this, "女生", Toast.LENGTH_SHORT).show();
                    StaticFragment staticFragment = new StaticFragment();
                    if (staticFragment != null) {
                        if (staticFragment.isAdded()) {
                            getSupportFragmentManager().beginTransaction().show(staticFragment)
                                    .commit();
                        }
//                        else {
//                            getSupportFragmentManager().beginTransaction().remove(staticFragment)
//                                    .commit();
//                            getSupportFragmentManager().beginTransaction().add(R.id.bt_frameLayout, staticFragment, "STATIC_FRAGMENT")
//                                    .commit();
//                        }
                    } else {
                        getSupportFragmentManager().beginTransaction().add(R.id.bt_frameLayout, staticFragment, "STATIC_FRAGMENT")
                                .commit();
                    }
                }
            }
        }
    };

    public View getTabView(int position) {
        //获得view
        View v = LayoutInflater.from(PaginationActivity.this).inflate(R.layout.customtablayout, null);
        TextView mTv1 = v.findViewById(R.id.tv1);
        mTv1.setText(titleArray[position]);
        return v;
    }

}
