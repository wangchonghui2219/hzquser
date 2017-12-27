package com.dlwx.hzquser.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dlwx.hzquser.R;
import com.dlwx.hzquser.base.BaseFastAdapter;
import com.dlwx.hzquser.model.bean.MyExtenBean;

import java.util.List;

/**
 * 我的推荐
 */

public class MyExent extends BaseFastAdapter {
    private List<MyExtenBean.BodyBean.ListBean> list;

    public MyExent(Context ctx, List<MyExtenBean.BodyBean.ListBean> list) {
        super(ctx);
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh;
        if (convertView == null) {
            convertView = LayoutInflater.from(ctx).inflate(R.layout.iem_exent, null);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        MyExtenBean.BodyBean.ListBean listBean = list.get(position);
        vh.tv_name.setText(listBean.getNickname());
        vh.tv_phone.setText(listBean.getPhone());



        return convertView;
    }

    private  class ViewHolder {
        public View rootView;
        public TextView tv_name;
        public TextView tv_phone;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.tv_name = (TextView) rootView.findViewById(R.id.tv_name);
            this.tv_phone = (TextView) rootView.findViewById(R.id.tv_phone);
        }

    }
}
