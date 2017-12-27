package com.dlwx.hzquser.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dlwx.hzquser.R;
import com.dlwx.hzquser.base.BaseFastAdapter;
import com.dlwx.hzquser.model.bean.SubOrderBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 提交订单
 */

public class SubOrderAdapter extends BaseFastAdapter {
    private List<SubOrderBean> orderBeen;
    public SubOrderAdapter(Context ctx,List<SubOrderBean> orderBeen) {
        super(ctx);
        this.orderBeen = orderBeen;
    }

    @Override
    public int getCount() {
        return orderBeen.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh;
        if (convertView == null) {
            convertView = LayoutInflater.from(ctx).inflate(R.layout.item_suborder, null);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        }else{
            vh = (ViewHolder) convertView.getTag();

        }
        SubOrderBean orderBean = orderBeen.get(position);
        Glide.with(ctx).load(orderBean.getImg()).into(vh.ivPic);
        vh.tvName.setText(orderBean.getMenuname());
        vh.tvPrice.setText("￥"+orderBean.getPrice());
        vh.tvNum.setText("x"+orderBean.getNum());
        List<SubOrderBean.MenuType> menuTypes = orderBean.getMenuTypes();
        SubOrderItemAdapter adapter = new SubOrderItemAdapter(ctx,menuTypes);
        vh.gvList.setAdapter(adapter);
        return convertView;
    }

     class ViewHolder {
        @BindView(R.id.iv_pic)
        ImageView ivPic;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_price)
        TextView tvPrice;
        @BindView(R.id.gv_list)
        GridView gvList;
        @BindView(R.id.tv_num)
        TextView tvNum;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
