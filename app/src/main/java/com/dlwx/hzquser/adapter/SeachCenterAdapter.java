package com.dlwx.hzquser.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dlwx.hzquser.R;
import com.dlwx.hzquser.base.BaseFastAdapter;
import com.dlwx.hzquser.model.bean.SeachCenterBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 搜索中心
 */

public class SeachCenterAdapter extends BaseFastAdapter {
    List<SeachCenterBean.BodyBean.ListBean> listBeen;
    public SeachCenterAdapter(Context ctx,List<SeachCenterBean.BodyBean.ListBean> listBeen) {
        super(ctx);
        this.listBeen = listBeen;
    }

    @Override
    public int getCount() {
        return listBeen.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh;
        if (convertView == null) {
            convertView = LayoutInflater.from(ctx).inflate(R.layout.item_product, null);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }

        SeachCenterBean.BodyBean.ListBean listBean = listBeen.get(position);
        Glide.with(ctx).load(listBean.getPath()).into(vh.ivPic);
        vh.tvName.setText(listBean.getMenu_name());
        vh.tvType.setText(listBean.getType_name());
        vh.tvPrice.setText("￥"+listBean.getPrice());
        vh.tvNum.setText("已售"+listBean.getSale());
        if (listBean.getPre_price() == 0) {
            vh.rl_prepric.setVisibility(View.GONE);
            vh.tvPrePrice.setText("￥"+listBean.getPrice());
            vh.tvPrePrice.setTextColor(ctx.getResources().getColor(R.color.orange));
        }else{
            vh.rl_prepric.setVisibility(View.VISIBLE);
            vh.tvPrePrice.setText("￥"+listBean.getPre_price());
            vh.tvPrice.setText("￥"+listBean.getPrice());
            vh.tvPrice.setTextColor(ctx.getResources().getColor(R.color.text_color_gary));
            vh.tvPrePrice.setTextColor(ctx.getResources().getColor(R.color.orange));
        }
        return convertView;
    }


    class ViewHolder {
        @BindView(R.id.iv_pic)
        ImageView ivPic;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_type)
        TextView tvType;
        @BindView(R.id.tv_price)
        TextView tvPrice;
        @BindView(R.id.tv_num)
        TextView tvNum;
        @BindView(R.id.tv_prePrice)
        TextView tvPrePrice;
        @BindView(R.id.rl_prepric)
        RelativeLayout rl_prepric;
        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
