package com.example.wb_twh369668.girlsmvpdemo;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wb_twh369668.girlsmvpdemo.entity.Book;
import com.example.wb_twh369668.girlsmvpdemo.entity.Girls;
import com.example.wb_twh369668.girlsmvpdemo.presenter.BookPresenter;
import com.example.wb_twh369668.girlsmvpdemo.presenter.GirlsPresenter;
import com.example.wb_twh369668.girlsmvpdemo.view.BookView;
import com.example.wb_twh369668.girlsmvpdemo.view.GirlsView;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends Activity implements View.OnClickListener {
    private static final String TAG = "MainActivity";
    //    String URL = "https://api.douban.com/v2/book/search?q=金瓶梅&tag=&start=0&count=1";
    String URL = "https://gank.io/api/data/%E7%A6%8F%E5%88%A9/20/2";
//    String URL = "https://kyfw.12306.cn/otn/";
    private Button mBt;
    private TextView mTv;
    private GirlsPresenter mGirlsPresenter = new GirlsPresenter(this);
    private BookPresenter mBookPresenter = new BookPresenter(this);
    TrustAllHostnameVerifier trustAllHostnameVerifier = new TrustAllHostnameVerifier();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBt = findViewById(R.id.bt);
        mTv = findViewById(R.id.tv);
        mBt.setOnClickListener(this);
        mGirlsPresenter.onCreate();
        mGirlsPresenter.attachView(girlsView);
        mBookPresenter.onCreate();
        mBookPresenter.attachView(mBookView);
        new Thread(new Runnable() {
            @Override
            public void run() {
                getData();
            }
        }).start();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt:
                mGirlsPresenter.getSearchGirls(1);
//              mBookPresenter.getSearchBooks("金瓶梅", null, 0, 1);
                break;

            default:
                break;
        }

    }

    private Girls girl;
    public GirlsView girlsView = new GirlsView() {
        @Override
        public void onSuccess(Girls mGirl) {
            girl = mGirl;
            mTv.setText(girl.getResults().get(1).getUrl());
        }

        @Override
        public void onError(String result) {
            Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT).show();
        }
    };
    private Book book;
    private BookView mBookView = new BookView() {
        @Override
        public void onSuccess(Book mBook) {
            book = mBook;
            mTv.setText(book.getBooks().get(0).getAlt_title());
        }

        @Override
        public void onError(String result) {
            Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mBookPresenter.onStop();
        mGirlsPresenter.onStop();
    }

    /**
     * 获取beautifulGirl的数据
     */
    void getData() {
        OkHttpClient.Builder mBuilder = new OkHttpClient.Builder();
        mBuilder.sslSocketFactory(trustAllHostnameVerifier.createSSLSocketFactory());
        mBuilder.hostnameVerifier(new TrustAllHostnameVerifier());
        OkHttpClient okHttpClient = mBuilder.build();
        Request request = new Request.Builder().get().url(URL).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i(TAG, "onFailure: ====="+e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String str = response.body().string();
                Log.i(TAG, "run:==== " + str);
                //更新UI运行在子线程中
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //转化为实体类
                        Gson gson = new Gson();
                        Girls girls = gson.fromJson(str, Girls.class);
                        mTv.setText(girls.getResults().get(0).getUrl());
//                        Book book = gson.fromJson(str, Book.class);
//                        mTv.setText(book.getBooks().get(0).getTags().get(0).getName()+book.getBooks().get(0).getTags().get(0).getTitle());
                    }
                });
            }
        });
    }
}
