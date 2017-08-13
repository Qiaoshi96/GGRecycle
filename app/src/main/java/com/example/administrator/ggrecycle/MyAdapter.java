package com.example.administrator.ggrecycle;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

/**
 * Created by Administrator on 2017/8/12.
 */

public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<First_User.DataBean.ReturnDataBean.ComicsBean> list;

    public MyAdapter(List<First_User.DataBean.ReturnDataBean.ComicsBean> list) {
        this.list = list;
    }
    OnItemClickListener listener;//定义监听事件

    /**
     * 设置监听事件
     *
     * @param listener
     */
    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

//初始化时
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        加载我们的子布局
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.first_item,parent,false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }
//绑定成功
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof  MyViewHolder){
            MyViewHolder my= (MyViewHolder) holder;
           my.title.setText(list.get(position).getName());
            x.image().bind(my.imageView,list.get(position).getCover());
//            设置整根条目的监听
            my.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener!=null){
                        listener.onItemClick(view,position);
                    }
                }
            });
//            设置单个监听
            my.imageView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    if (listener!=null){
                        listener.onImageClick(view,position);
                    }

                    return true;
                }
            });
        }

    }
//返回集合里面的条目总数
    @Override
    public int getItemCount() {
        return list.size();
    }

    //    创建自己的ViewHolder
    static class MyViewHolder extends RecyclerView.ViewHolder{
            @ViewInject(R.id.f_item)
            ImageView imageView;
            @ViewInject(R.id.f_title)
            TextView title;
        public MyViewHolder(View itemView) {
            super(itemView);
            x.view().inject(this,itemView);
        }
    }

    /**
     * 点击事件回掉接口
     */
    public interface OnItemClickListener {
        void onItemClick(View var2, int var3);

        void onImageClick(View view, int pos);

    }

}
