package com.example.wb_twh369668.recycleviewdemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wb_twh369668.recycleviewdemo.R;
import com.example.wb_twh369668.recycleviewdemo.service.entity.Book;

import java.util.List;

/**
 * creat by TWH on 2018/9/4
 */
public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.RecycleViewHolder> {
    private static final String TAG = "RecycleAdapter";
    private Context mContext;
    private List<Book.BooksBean.TagsBean> tag;
    private String name;
    private int count;
    private String title;

    public RecycleAdapter(Context mContext, List<Book.BooksBean.TagsBean> tag) {
        this.mContext = mContext;
        this.tag = tag;
    }

    //在Adapter中重写该方法，根据条件返回不同的值然后在onBindViewHolder里面进行布局设置
    @Override
    public int getItemViewType(int position) {
        if (position%2==1) {
            return 100;
        } else {
            return super.getItemViewType(position);
        }
    }

    //viewholder 布局代码
    @Override
    public RecycleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        RecycleViewHolder holder = new RecycleViewHolder(LayoutInflater.from(mContext).
//                inflate(R.layout.item_list, parent, false));
        RecycleViewHolder holder = null;
        switch (viewType) {
            //示例逻辑
            case 100:
                holder = new RecycleViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_list_viewtype, parent, false));
                break;
            default:
                holder = new RecycleViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_list, parent, false));
                break;
        }

        return holder;
    }

    //数据处理代码
    @Override
    public void onBindViewHolder(final RecycleViewHolder holder, int position) {
        name = tag.get(position).getName();
        count = tag.get(position).getCount();
        title = tag.get(position).getTitle();
            holder.mTv1.setText(count + "");
            holder.mTv2.setText(title);
        Log.i(TAG, "onBindViewHolder: ===" + title);
        Log.i(TAG, "onBindViewHolder: ===" + count);
        //自定义点击事件和长按事件
        if (mOnItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int layoutPosition = holder.getLayoutPosition();//避免添加item时不刷新item索引
                    mOnItemClickListener.onItemClick(holder.itemView, layoutPosition);
                }
            });
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int layoutPosition = holder.getLayoutPosition();//避免添加item时不刷新item索引
                    mOnItemClickListener.onItemLongClick(holder.itemView, layoutPosition);
                    return true;
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return tag.size();
    }

    class RecycleViewHolder extends RecyclerView.ViewHolder {
        public TextView mTv1;
        public TextView mTv2;

        public RecycleViewHolder(View itemView) {
            super(itemView);
            mTv1 = itemView.findViewById(R.id.tv_count);
            mTv2 = itemView.findViewById(R.id.tv_name);
        }
    }

    /**
     * 点击和长按监听
     */
    public interface OnItemClickListener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }

    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }


    /**
     * 添加 item
     */
    public void addItem(int position) {
//        tag.add(position, "添加了数据");
        notifyItemInserted(position);//切记不要写成notifyDataSetChanged()
    }

    /**
     * 删除item
     */
    public void removeItem(int position) {
        tag.remove(position);
        Toast.makeText(mContext, "删除成功", Toast.LENGTH_SHORT).show();
        notifyItemRemoved(position);//切记不要写成notifyDataSetChanged()
//        notifyDataSetChanged();
    }


}
