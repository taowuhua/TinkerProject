package com.example.wb_twh369668.tablayoutdemo;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ViewPagerAddFragment extends AppCompatActivity {

    public static final String[] sTitle = new String[]{"女生", "男生", "全部"};
    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private List<String> titleList;
    private List<Fragment> fragmentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager_add_fragment);
        mViewPager = (ViewPager) findViewById(R.id.view_pager3);
        mTabLayout = (TabLayout) findViewById(R.id.tabLayout3);
        //数组转化为list
        titleList = Arrays.asList(sTitle);
        initTab();
        //       fragmentList = new ArrayList<Fragment>();
        //viewpager加载要显示的页卡
//        LayoutInflater lf = LayoutInflater.from(this);
//        View view1 = lf.inflate(R.layout.fragment_girl, mViewPager, false);
//        View view2 = lf.inflate(R.layout.fragment_boy, mViewPager, false);
//        View view3 = lf.inflate(R.layout.fragment_animal, mViewPager, false);
        // 将要分页显示的View装入数组中
        fragmentList = new ArrayList<Fragment>();// 将要分页显示的Fragment装入数组中
        fragmentList.add(GirlFragment.getInstance());
        fragmentList.add(BoyFragment.getInstance());
        fragmentList.add(AnimalFragment.getInstance());
        MyFragmentPagerAdapter myViewpagerAdapter = new MyFragmentPagerAdapter(this,fragmentList,getSupportFragmentManager(),  titleList);
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
