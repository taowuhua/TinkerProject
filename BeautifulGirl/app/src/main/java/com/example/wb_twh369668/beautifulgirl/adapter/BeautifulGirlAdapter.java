package com.example.wb_twh369668.beautifulgirl.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.wb_twh369668.beautifulgirl.R;
import com.example.wb_twh369668.beautifulgirl.bean.BeautifulGirl;

import java.util.List;

/**
 * creat by TWH on 2018/9/7
 */
public class BeautifulGirlAdapter extends RecyclerView.Adapter<BeautifulGirlAdapter.GirlViewHold> {
    private static final String TAG = "BeautifulGirlAdapter";
    private List<BeautifulGirl.ResultsBean> list;
    private Context mContext;

    public BeautifulGirlAdapter(Context context, List<BeautifulGirl.ResultsBean> list) {
        mContext = context;
        this.list = list;
    }

    @Override
    public GirlViewHold onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.i(TAG, "onCreateViewHolder: ==="+"onCreateViewHolder");
        GirlViewHold hold = new GirlViewHold(LayoutInflater.from(mContext).inflate(R.layout.item_layout, parent, false));
        return hold;
    }

    @Override
    public void onBindViewHolder(GirlViewHold holder, int position) {
        Log.i(TAG, "onBindViewHolder: ==="+"onBindViewHolder");
        Glide.with(mContext).load(list.get(position).getUrl())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.mIv);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class GirlViewHold extends RecyclerView.ViewHolder {
        private ImageView mIv;

        public GirlViewHold(View itemView) {
            super(itemView);
            mIv = itemView.findViewById(R.id.iv_beautifulItem);
        }
    }
}
