package com.example.wb_twh369668.recycleviewdemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wb_twh369668.recycleviewdemo.R;

import java.util.List;

/**
 * creat by TWH on 2018/9/6
 */
public class SwipRecycleAdapter extends RecyclerView.Adapter<SwipRecycleAdapter.SwipRecycleAdapterViewHold> {
    private Context mContext;
    private List<String> list;

    public SwipRecycleAdapter(Context mContext, List<String> list) {
        this.mContext = mContext;
        this.list = list;
    }

    @Override
    public SwipRecycleAdapterViewHold onCreateViewHolder(ViewGroup parent, int viewType) {
        SwipRecycleAdapterViewHold hold = new SwipRecycleAdapterViewHold(LayoutInflater.from(mContext).inflate(R.layout.swip_list,parent,false));
        return hold;
    }

    @Override
    public void onBindViewHolder(SwipRecycleAdapterViewHold holder, int position) {
        holder.mTvSwip.setText(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    /**
     * 重写getItemViewType方法来判断返回加载的布局的类型
     *
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
            return list.size();
    }

    /**
     * 添加 item
     */
    public void addItem(int position) {
        list.add(position, "我刚刚吃了一个西瓜，可撑死我了。。。。隔");
        notifyItemInserted(position);//切记不要写成notifyDataSetChanged()
    }

    /**
     * 删除item
     */
    public void removeItem(int position) {
        list.remove(position);
        Toast.makeText(mContext, "删除成功", Toast.LENGTH_SHORT).show();
        notifyItemRemoved(position);//切记不要写成notifyDataSetChanged()
//        notifyDataSetChanged();
    }

    class SwipRecycleAdapterViewHold extends RecyclerView.ViewHolder {
        private TextView mTvSwip;

        public SwipRecycleAdapterViewHold(View itemView) {
            super(itemView);
            mTvSwip = itemView.findViewById(R.id.tv_swip);
        }
    }
}
