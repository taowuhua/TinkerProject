package com.example.wb_twh369668.recycleviewdemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.wb_twh369668.recycleviewdemo.R;
import com.example.wb_twh369668.recycleviewdemo.service.entity.Book;

import java.util.List;

/**
 * creat by TWH on 2018/9/5
 */
public class ListRecycleAdapter extends RecyclerView.Adapter<ListRecycleAdapter.ListRecycleViewHolder> {
    private static final String TAG = "ListRecycleAdapter";
    private Context mContext;
    private List<Book> list;
    private int count;
    private String title;
    private String name;
    int i = 0;

    public ListRecycleAdapter(Context mContext, List<Book> list) {
        this.mContext = mContext;
        this.list = list;
    }

    @Override
    public ListRecycleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ListRecycleViewHolder holder = new ListRecycleViewHolder(LayoutInflater.from(mContext).
                inflate(R.layout.item_list, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(ListRecycleViewHolder holder, int position) {
        for (int i = 0; i < list.get(position).getBooks().get(position).getTags().size(); i++) {
            count = list.get(position).getBooks().get(position).getTags().get(i).getCount();
            title = list.get(position).getBooks().get(position).getTags().get(i).getTitle();
            name = list.get(position).getBooks().get(position).getTags().get(i).getName();
            Log.i(TAG, "onBindViewHolder: ===" + count + "");
            Log.i(TAG, "onBindViewHolder: ===" + name);
            holder.mTv1.setText(count + "");
            holder.mTv2.setText(name);
            Log.i(TAG, "onBindViewHolder: ====" + i + "");
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    //TODO 理解RecyclerView.ViewHolder
    class ListRecycleViewHolder extends RecyclerView.ViewHolder {
        public TextView mTv1;
        public TextView mTv2;

        public ListRecycleViewHolder(View itemView) {
            super(itemView);
            mTv1 = itemView.findViewById(R.id.tv1);
            mTv2 = itemView.findViewById(R.id.tv2);
        }
    }

}
