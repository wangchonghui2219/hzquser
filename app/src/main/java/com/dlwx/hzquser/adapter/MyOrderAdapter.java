package com.dlwx.hzquser.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;

import com.dlwx.hzquser.R;
import com.dlwx.hzquser.base.BaseFastAdapter;
import com.dlwx.hzquser.model.bean.MyOrderItemBean;
import com.dlwx.hzquser.view.MyListView;
import com.dlwx.hzquser.view.activity.MenuDetailsActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 我的订单
 */

public class MyOrderAdapter extends BaseFastAdapter {
    private List<MyOrderItemBean.OrdersBean> ordersBeen;

    public MyOrderAdapter(Context ctx, List<MyOrderItemBean.OrdersBean> ordersBeen) {
        super(ctx);
        this.ordersBeen = ordersBeen;
    }

    @Override
    public int getCount() {
        return ordersBeen.size();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder vh;
        if (convertView == null) {
            convertView = LayoutInflater.from(ctx).inflate(R.layout.item_order, null);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        final MyOrderItemBean.OrdersBean ordersBean = ordersBeen.get(position);
            vh.tvOrderNo.setText("订单号："+ordersBean.getOrder_no());

        final int status = ordersBean.getStatus();//订单状态（1待付款，2 待领餐（已经付款），3完成）
        if (status == 1) {
            vh.tvOrderType.setText("待付款");
            vh.btnClose.setVisibility(View.VISIBLE);
            vh.btnPayOr.setText("待付款");
        }else if (status == 2) {
            vh.tvOrderType.setText("待领餐");
            vh.btnClose.setVisibility(View.VISIBLE);
            vh.btnPayOr.setText("待领餐");
        }else if (status == 3) {
            vh.tvOrderType.setText("完成");
            vh.btnPayOr.setText("完成");
            vh.btnClose.setVisibility(View.VISIBLE);
            vh.btnClose.setText("删除订单");
        }else if (status == 5) {//派送中
            vh.tvOrderType.setText("派送中");
            vh.btnClose.setVisibility(View.GONE);
            vh.btnPayOr.setText("派送中");
        }else if (status == 6) {
            vh.tvOrderType.setText("待领餐");
            vh.btnClose.setVisibility(View.GONE);
            vh.btnPayOr.setText("查看");
        }



        final List<MyOrderItemBean.OrdersBean.DescBean> desc = ordersBean.getDesc();

        MyOrderItemAdapter itemAdapter = new MyOrderItemAdapter(ctx,desc);
        vh.lvList.setAdapter(itemAdapter);
        vh.tvTotNum.setText("共"+ordersBean.getTotal_num()+"件商品");
        vh.tvTime.setText(ordersBean.getMeal_time());
        vh.tvTotaPrice.setText("￥"+ordersBean.getTotal_price());

        vh.btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClickListener != null) {
                    onClickListener.setCloseOnClick(position,ordersBean.getOrder_id());
                }
            }
        });
        vh.btnPayOr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClickListener != null) {
                    onClickListener.setPayOrOtherOnClick(position,ordersBean.getOrder_no(),

                            ordersBean.getOrder_id(),ordersBean.getStatus());
                }
            }
        });
        //跳转产品详情
        vh.lvList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MyOrderItemBean.OrdersBean.DescBean descBean = desc.get(position);
                Intent intent = new Intent(ctx, MenuDetailsActivity.class);
                intent.putExtra("menu_id",descBean.getMenu_id());
                ctx.startActivity(intent);
            }
        });
        return convertView;
    }


     class ViewHolder {
        @BindView(R.id.tv_order_no)
        TextView tvOrderNo;
        @BindView(R.id.tv_order_type)
        TextView tvOrderType;
        @BindView(R.id.lv_list)
        MyListView lvList;
        @BindView(R.id.tv_tot_num)
        TextView tvTotNum;
        @BindView(R.id.tv_tota_price)
        TextView tvTotaPrice;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.btn_close)
        Button btnClose;
        @BindView(R.id.btn_payOr)
        Button btnPayOr;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
    private DealOnClickListener onClickListener;

    public interface DealOnClickListener{
        void setCloseOnClick(int position,String order_id);
        void setPayOrOtherOnClick(int position,String order_no,String order_id,int status);
    }

    public void setOnClickListener(DealOnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }
}
