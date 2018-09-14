package com.example.wb_twh369668.tablayoutdemo;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class GirlFragment extends Fragment {
    private static GirlFragment mGirlFragment;

    public static GirlFragment getInstance() {
        synchronized (GirlFragment.class) {
            if (mGirlFragment == null) {
                mGirlFragment = new GirlFragment();
            }
        }
        return mGirlFragment;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_girl, container, false);
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
