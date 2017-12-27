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
import com.dlwx.hzquser.model.bean.HomeNewProduct;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 首页最新上架
 */

public class MeachProductAdapter extends BaseFastAdapter {
    List<HomeNewProduct.BodyBean.ListBean> new_menus;
    public MeachProductAdapter(Context ctx, List<HomeNewProduct.BodyBean.ListBean> new_menus) {
        super(ctx);
        this.new_menus = new_menus;
    }

    @Override
    public int getCount() {
        return new_menus.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh;
        if (convertView == null) {
            convertView = LayoutInflater.from(ctx).inflate(R.layout.item_meach_product, null);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        } else {

                vh = (ViewHolder) convertView.getTag();
        }

        HomeNewProduct.BodyBean.ListBean newMenusBean = new_menus.get(position);
        Glide.with(ctx).load(newMenusBean.getPath()).into(vh.ivPic);
        vh.tvName.setText(newMenusBean.getMenu_name());
        vh.tvType.setText(newMenusBean.getType_name());

        vh.tvNum.setText("已售"+newMenusBean.getSale());
        vh.tvNum.setText("已售"+newMenusBean.getSale());
        if (newMenusBean.getPre_price() == 0) {
            vh.rl_prepric.setVisibility(View.GONE);
            vh.tvPrePrice.setText("￥"+newMenusBean.getPrice());
            vh.tvPrePrice.setTextColor(ctx.getResources().getColor(R.color.orange));
        }else{
            vh.rl_prepric.setVisibility(View.VISIBLE);
            vh.tvPrePrice.setText("￥"+newMenusBean.getPre_price());
            vh.tvPrice.setText("￥"+newMenusBean.getPrice());
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
