package com.dlwx.hzquser.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dlwx.hzquser.R;
import com.dlwx.hzquser.base.BaseFastAdapter;
import com.dlwx.hzquser.model.bean.MyOrderItemBean;
import com.dlwx.hzquser.view.MyGridView;

import java.util.List;

/**
 * Created by Administrator on 2017/8/28/028.
 */

public class MyOrderItemAdapter extends BaseFastAdapter {
    private List<MyOrderItemBean.OrdersBean.DescBean> desc;
    public MyOrderItemAdapter(Context ctx,List<MyOrderItemBean.OrdersBean.DescBean> desc) {
        super(ctx);
        this.desc = desc;
    }

    @Override
    public int getCount() {
        return desc.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh;
        if (convertView == null) {
            convertView = LayoutInflater.from(ctx).inflate(R.layout.order_item_layout, null);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        }else{
            vh = (ViewHolder) convertView.getTag();
        }
        MyOrderItemBean.OrdersBean.DescBean descBean = desc.get(position);
        vh.tv_name.setText(descBean.getMenu_name());
        vh.tv_num.setText("x"+descBean.getNum());
        Glide.with(ctx).load(descBean.getPath()).into(vh.iv_img);
        vh.tv_price.setText("￥"+descBean.getPrice());
        String[] strs = descBean.getXuancan().split(",");


        OrderItemMenutypeAdapter menutypeAdapter = new OrderItemMenutypeAdapter(ctx,strs);
        vh.gv_list.setAdapter(menutypeAdapter);
        return convertView;
    }

    private class ViewHolder {
        public View rootView;
        public ImageView iv_img;
        public TextView tv_name;
        public TextView tv_price;
        public TextView tv_num;
        public MyGridView gv_list;
        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.iv_img = (ImageView) rootView.findViewById(R.id.iv_img);
            this.tv_name = (TextView) rootView.findViewById(R.id.tv_name);
            this.tv_price = (TextView) rootView.findViewById(R.id.tv_price);
            this.tv_num = (TextView) rootView.findViewById(R.id.tv_num);
            this.gv_list = (MyGridView) rootView.findViewById(R.id.gv_list);
        }

    }
}
