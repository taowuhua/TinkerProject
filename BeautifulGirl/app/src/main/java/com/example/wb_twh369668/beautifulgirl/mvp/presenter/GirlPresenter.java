package com.example.wb_twh369668.beautifulgirl.mvp.presenter;

import android.content.Context;
import android.content.Intent;

import com.example.wb_twh369668.beautifulgirl.bean.BeautifulGirl;
import com.example.wb_twh369668.beautifulgirl.mvp.manager.DataManager;
import com.example.wb_twh369668.beautifulgirl.mvp.view.GirlView;
import com.example.wb_twh369668.beautifulgirl.mvp.view.View;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * creat by TWH on 2018/9/5
 */
public class GirlPresenter implements Presenter {
    private static final String TAG = "GirlPresenter";
    private DataManager manager;
    private CompositeSubscription mCompositeSubscription;
    private Context mContext;
    private GirlView mGirlView;
    private BeautifulGirl mGirl;

    public GirlPresenter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public void onCreate() {
        manager = new DataManager(mContext);
        mCompositeSubscription = new CompositeSubscription();
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {
        if (mCompositeSubscription.hasSubscriptions()) {
            mCompositeSubscription.unsubscribe();
        }
    }

    @Override
    public void pause() {

    }

    @Override
    public void attachView(View view) {
        mGirlView = (GirlView) view;
    }

    @Override
    public void attachIncomingIntent(Intent intent) {
    }

    public void getSearchBooks(int fuli) {
        mCompositeSubscription.add(manager.getSearchBooks(fuli)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BeautifulGirl>() {

                    @Override
                    public void onCompleted() {
                        mGirlView.onSuccess(mGirl);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(BeautifulGirl beautifulGirl) {
                    mGirl =beautifulGirl;
                    }
                })
        );
    }
}

