package com.example.yuan.yizhou.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.yuan.yizhou.R;
import com.example.yuan.yizhou.bean.UserBean;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<UserBean.DataBean> mDatas;
    private Context context;
    private boolean isshow;

    public UserAdapter(Context context, boolean isshow) {
        this.context = context;
        this.isshow = isshow;
        mDatas=new ArrayList<>();
    }

    public void setmDatas(List<UserBean.DataBean> dataBeans) {
       mDatas.clear();
       mDatas.addAll(dataBeans);
       notifyDataSetChanged();
    }
    public void addmDatas(List<UserBean.DataBean> dataBeans) {
        mDatas.addAll(dataBeans);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ViewHolder holder=null;
        if (isshow)
        {
            View view=View.inflate(context,R.layout.linear_item,null);
            holder=new ViewHolder(view);
        }
        else
        {
            View view=View.inflate(context,R.layout.grid_itemn,null);
            holder=new ViewHolder(view);
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        ViewHolder holder= (ViewHolder) viewHolder;
        String[] split = mDatas.get(i).getImages().split("\\|");
        Glide.with(context).load(split[0]).into(holder.linear_image);

        holder.linear_price.setText(mDatas.get(i).getPrice()+"");
        holder.linear_title.setText(mDatas.get(i).getTitle());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (click!=null)
                {
                    click.OnClick(mDatas.get(i).getPid());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView linear_image;
        private final TextView linear_price;
        private final TextView linear_title;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            linear_image = itemView.findViewById(R.id.linear_image);
            linear_price = itemView.findViewById(R.id.linear_price);
            linear_title = itemView.findViewById(R.id.linear_title);
        }
    }
    Click click;

    public void setClick(Click mClick) {
        this.click = mClick;
    }

    public interface Click
    {
        void OnClick(int position);
    }

}
