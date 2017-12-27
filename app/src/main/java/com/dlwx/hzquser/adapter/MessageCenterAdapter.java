package com.dlwx.hzquser.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dlwx.hzquser.R;
import com.dlwx.hzquser.base.BaseFastAdapter;
import com.dlwx.hzquser.model.bean.MessListBean;

import java.util.List;

/**
 * 信息列表
 */

public class MessageCenterAdapter extends BaseFastAdapter {
    private List<MessListBean.BodyBean> body;

    public MessageCenterAdapter(Context ctx, List<MessListBean.BodyBean> body) {
        super(ctx);
        this.body = body;
    }

    @Override
    public int getCount() {
        return body.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh;
        if (convertView == null) {
            convertView = LayoutInflater.from(ctx).inflate(R.layout.item_message_center, null);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        MessListBean.BodyBean bodyBean = body.get(position);
        vh.tv_date.setText(bodyBean.getCreate_time());
        vh.tv_title.setText(bodyBean.getTitle());
        vh.tv_count.setText(bodyBean.getContent());
        return convertView;
    }

   private class ViewHolder {
        public View rootView;
        public TextView tv_date;
        public TextView tv_title;
        public TextView tv_count;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.tv_date = (TextView) rootView.findViewById(R.id.tv_date);
            this.tv_title = (TextView) rootView.findViewById(R.id.tv_title);
            this.tv_count = (TextView) rootView.findViewById(R.id.tv_count);
        }

    }
}
