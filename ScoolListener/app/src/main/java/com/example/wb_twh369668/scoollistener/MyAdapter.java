package com.example.wb_twh369668.scoollistener;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.util.List;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

/**
 * creat by TWH on 2018/9/14
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private Context mContext;
    private List<String>urlList;

    public MyAdapter(Context mContext, List<String> urlList) {

        this.mContext = mContext;
        this.urlList = urlList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_list, viewGroup, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        if (myViewHolder.mPlayer != null) {
            myViewHolder.mPlayer.release();
        }
        boolean setUp = myViewHolder.mPlayer.setUp(urlList.get(i), JCVideoPlayer.SCREEN_LAYOUT_LIST, "");
        if (setUp) {
            Glide.with(mContext).load("http://a4.att.hudong.com/05/71/01300000057455120185716259013.jpg").into(myViewHolder.mPlayer.thumbImageView);
        }
    }

    @Override
    public int getItemCount() {
        return urlList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private  JCVideoPlayerStandard  mPlayer;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mPlayer = itemView.findViewById(R.id.player_list_video);
        }
    }
}
