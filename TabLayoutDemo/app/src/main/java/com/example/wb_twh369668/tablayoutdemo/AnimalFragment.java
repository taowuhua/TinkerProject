package com.example.wb_twh369668.tablayoutdemo;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class AnimalFragment extends Fragment {
    private static AnimalFragment mAnimalFragment;

    public static AnimalFragment getInstance() {
        synchronized (AnimalFragment.class) {
            if (mAnimalFragment == null) {
                mAnimalFragment = new AnimalFragment();
            }
        }
        return mAnimalFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_animal, container, false);
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
