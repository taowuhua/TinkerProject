package com.example.wb_twh369668.tablayoutdemo;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class BoyFragment extends Fragment {
    private static BoyFragment mBoyFragment;

    public static BoyFragment getInstance() {
        synchronized (BoyFragment.class) {
            if (mBoyFragment == null) {
                mBoyFragment = new BoyFragment();
            }
        }
        return mBoyFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_boy, container, false);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
