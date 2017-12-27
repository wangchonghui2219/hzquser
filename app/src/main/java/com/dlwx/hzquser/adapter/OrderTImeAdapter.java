package com.dlwx.hzquser.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dlwx.hzquser.R;
import com.dlwx.hzquser.base.BaseFastAdapter;
import com.dlwx.hzquser.model.bean.OrderTimeBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 就餐时间
 */

public class OrderTImeAdapter extends BaseFastAdapter {
    private  List<OrderTimeBean.BodyBean.TimeSlotBean> time_slot;
    public OrderTImeAdapter(Context ctx, List<OrderTimeBean.BodyBean.TimeSlotBean> time_slot) {
        super(ctx);
        this.time_slot = time_slot;
    }

    @Override
    public int getCount() {
        return time_slot.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh;
        if (convertView == null) {
            convertView = LayoutInflater.from(ctx).inflate(R.layout.item_order_time, null);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        OrderTimeBean.BodyBean.TimeSlotBean timeSlotBean = time_slot.get(position);
        vh.btnType.setText(timeSlotBean.getTime());
        if (position == pos) {
            vh.btnType.setBackgroundResource(R.drawable.shape_btn_bg);
        }else{
            vh.btnType.setBackgroundResource(R.drawable.shape_btn_bg_gary);
        }
        return convertView;
    }
    private int pos;
    public void setSelete(int position) {
        this.pos = position;
        notifyDataSetChanged();
    }

    class ViewHolder {
        @BindView(R.id.btn_type)
        TextView btnType;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}

