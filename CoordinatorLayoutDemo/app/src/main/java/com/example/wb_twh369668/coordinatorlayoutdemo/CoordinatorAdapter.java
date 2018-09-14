package com.example.wb_twh369668.coordinatorlayoutdemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * creat by TWH on 2018/9/12
 */
public class CoordinatorAdapter extends RecyclerView.Adapter<CoordinatorAdapter.MyViewHoleder> {
    private Context mContext;
    private List<String> list;
    private MyViewHoleder holeder;

    public CoordinatorAdapter(Context mContext, List<String> list) {
        this.mContext = mContext;
        this.list = list;
    }

    @Override
    public MyViewHoleder onCreateViewHolder(ViewGroup parent, int viewType) {
         holeder = new MyViewHoleder(LayoutInflater.from(mContext).inflate(R.layout.list_item, parent, false));
        return holeder;
    }

    @Override
    public void onBindViewHolder(MyViewHoleder holder, int position) {
        holder.mTv.setText(list.get(position));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHoleder extends RecyclerView.ViewHolder {
        public TextView mTv;

        public MyViewHoleder(View itemView) {
            super(itemView);
            mTv = itemView.findViewById(R.id.tv1);
        }
    }
}
