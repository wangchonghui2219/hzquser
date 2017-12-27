package com.dlwx.hzquser.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dlwx.hzquser.R;
import com.dlwx.hzquser.base.BaseRecrviewAdapter;
import com.dlwx.hzquser.model.bean.HomeBannerClass;

import java.util.List;

/**
 * 首页分类
 */
public class HomeClassAdapter extends BaseRecrviewAdapter {
    private List<HomeBannerClass.BodyBean.ClassifyBean> info;
    public HomeClassAdapter(Context ctx, List<HomeBannerClass.BodyBean.ClassifyBean> info) {
        super(ctx);
        this.info = info;
    }


    @Override
    public int getItemCount() {
        if (info == null) {
            return 0;
        }
        return info.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(ctx).inflate(R.layout.item_home_class, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ((ViewHolder)holder).rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.setOnClick(position);
                }
            }
        });
        HomeBannerClass.BodyBean.ClassifyBean infoBean = info.get(position);
        ((ViewHolder)holder).tv_title.setText(infoBean.getType_name());
        Glide.with(ctx).load(infoBean.getPath()).into(((ViewHolder)holder).iv_pic);

    }

    private  class ViewHolder extends RecyclerView.ViewHolder{
        public View rootView;
        public ImageView iv_pic;
        public TextView tv_title;
        public ViewHolder(View itemView) {
            super(itemView);
            this.rootView = itemView;
            this.iv_pic = (ImageView) itemView.findViewById(R.id.iv_pic);
            this.tv_title = (TextView) itemView.findViewById(R.id.tv_title);
        }
    }
}
