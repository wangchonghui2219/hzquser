package com.dlwx.hzquser.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.dlwx.hzquser.R;
import com.dlwx.hzquser.base.BaseFastAdapter;
import com.dlwx.hzquser.model.bean.MyPickUpBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/8/29/029.
 */

public class MyPickUpAdapter extends BaseFastAdapter {
    private List<MyPickUpBean.BodyBean> body;
    public MyPickUpAdapter(Context ctx, List<MyPickUpBean.BodyBean> body) {
        super(ctx);
        this.body = body;
    }

    @Override
    public int getCount() {
        return body.size();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder vh;
        if (convertView == null) {
            convertView = LayoutInflater.from(ctx).inflate(R.layout.item_mypickup, null);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        }else{
            vh = (ViewHolder) convertView.getTag();
        }
        MyPickUpBean.BodyBean bodyBean = body.get(position);
        vh.tvOrderNo.setText("订单号："+bodyBean.getOrder_no());
        vh.tvTime.setText(bodyBean.getMeal_time());
        vh.tvExenCode.setText("领餐码："+bodyBean.getPick_code());
        String pick_type = bodyBean.getPick_type();
        if (pick_type.equals("1")) {
            vh.tvType.setText("自领");
        }else{
            vh.tvType.setText("代领");
        }
        vh.btnLook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (lookDetailListener != null) {
                    lookDetailListener.setResult(position);
                }
            }
        });
        return convertView;
    }

     class ViewHolder {
        @BindView(R.id.tv_order_no)
        TextView tvOrderNo;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.tv_exen_code)
        TextView tvExenCode;
        @BindView(R.id.tv_type)
        TextView tvType;
        @BindView(R.id.btn_look)
        Button btnLook;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
    public interface LookDetailListener{
        void setResult(int postion);
    }
    private LookDetailListener lookDetailListener;

    public void setLookDetailListener(LookDetailListener lookDetailListener){
        this.lookDetailListener = lookDetailListener;

    }
}
