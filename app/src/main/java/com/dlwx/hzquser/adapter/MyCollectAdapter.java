package com.dlwx.hzquser.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dlwx.hzquser.R;
import com.dlwx.hzquser.base.BaseRecrviewAdapter;
import com.dlwx.hzquser.model.bean.MyCOllectBean;

import java.util.List;

/**
 * 我的收藏
 */

public class MyCollectAdapter extends BaseRecrviewAdapter {
    private List<MyCOllectBean.BodyBean> body;
    public MyCollectAdapter(Context ctx,List<MyCOllectBean.BodyBean> body) {
        super(ctx);
        this.body = body;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(ctx).inflate(R.layout.item_collect, null);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyCOllectBean.BodyBean bodyBean = body.get(position);

        Glide.with(ctx).load(bodyBean.getPath()).into(((ViewHolder)holder).iv_pic);
        ((ViewHolder)holder).tv_name.setText(bodyBean.getMenu_name());
        ((ViewHolder)holder).tv_type.setText(bodyBean.getType_name());
        ((ViewHolder)holder).tv_price.setText("￥"+bodyBean.getPrice());
        ((ViewHolder)holder).tv_num.setText("已售"+bodyBean.getSale());
        if (bodyBean.getPre_price() == 0) {
            ((ViewHolder)holder).rl_prepric.setVisibility(View.GONE);
            ((ViewHolder)holder).tv_prePrice.setText("￥"+bodyBean.getPrice());
            ((ViewHolder)holder).tv_prePrice.setTextColor(ctx.getResources().getColor(R.color.orange));
        }else{
            ((ViewHolder)holder).rl_prepric.setVisibility(View.VISIBLE);
            ((ViewHolder)holder).tv_prePrice.setText("￥"+bodyBean.getPre_price());
            ((ViewHolder)holder).tv_price.setText("￥"+bodyBean.getPrice());
            ((ViewHolder)holder).tv_price.setTextColor(ctx.getResources().getColor(R.color.text_color_gary));
            ((ViewHolder)holder).tv_prePrice.setTextColor(ctx.getResources().getColor(R.color.orange));
        }

    } 

    @Override
    public int getItemCount() {
        return body.size();
    }

    private  class ViewHolder extends RecyclerView.ViewHolder{
        public View rootView;
        public ImageView iv_pic;
        public TextView tv_name;
        public TextView tv_type;
        public TextView tv_price;
        public TextView tv_num;
        private RelativeLayout rl_prepric;
        private TextView tv_prePrice;
        public ViewHolder(View rootView)  {
            super(rootView);
            this.rootView = rootView;
            this.iv_pic = (ImageView) rootView.findViewById(R.id.iv_pic);
            this.tv_name = (TextView) rootView.findViewById(R.id.tv_name);
            this.tv_type = (TextView) rootView.findViewById(R.id.tv_type);
            this.tv_price = (TextView) rootView.findViewById(R.id.tv_price);
            this.tv_num = (TextView) rootView.findViewById(R.id.tv_num);
            this.rl_prepric = (RelativeLayout) rootView.findViewById(R.id.rl_prepric);
            this.tv_prePrice = (TextView) rootView.findViewById(R.id.tv_prePrice);
        }

    }
}
