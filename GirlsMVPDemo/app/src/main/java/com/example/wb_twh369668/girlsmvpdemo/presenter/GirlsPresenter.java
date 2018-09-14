package com.example.wb_twh369668.girlsmvpdemo.presenter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.wb_twh369668.girlsmvpdemo.DataManager;
import com.example.wb_twh369668.girlsmvpdemo.entity.Girls;
import com.example.wb_twh369668.girlsmvpdemo.view.GirlsView;
import com.example.wb_twh369668.girlsmvpdemo.view.View;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * creat by TWH on 2018/9/5
 */
public class GirlsPresenter implements Presenter {
    private static final String TAG = "BookPresenter";
    private DataManager manager;
    private CompositeSubscription mCompositeSubscription;
    private Context mContext;
    private Girls mGirls;
    private GirlsView mGrilsView;

    public GirlsPresenter(Context mContext) {
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
        mGrilsView = (GirlsView) view;
    }

    @Override
    public void attachIncomingIntent(Intent intent) {

    }

    public void getSearchGirls(int q) {
        mCompositeSubscription.add(manager.getSearchGirls(q)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Girls>() {

                    @Override
                    public void onCompleted() {
                        mGrilsView.onSuccess(mGirls);
                        Log.i(TAG, "onCompleted: ===" + mGirls);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Girls girls) {
                        mGirls = girls;
                        Log.i(TAG, "onNext: ===" + mGirls);

                    }
                })
        );
    }
}

