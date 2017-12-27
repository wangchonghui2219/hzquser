package com.dlwx.hzquser.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dlwx.hzquser.R;
import com.dlwx.hzquser.base.BaseFastAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/8/22/022.
 */

public class OrderItemMenutypeAdapter extends BaseFastAdapter {
    private String[] strs;
    public OrderItemMenutypeAdapter(Context ctx, String[] strs ) {
        super(ctx);
        this.strs = strs;
    }

    @Override
    public int getCount() {
        return strs.length;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh;
        if (convertView == null) {
            convertView = LayoutInflater.from(ctx).inflate(R.layout.item_items_layout, null);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        }else{
            vh = (ViewHolder) convertView.getTag();
        }
        String str = strs[position];
        vh.btnType.setText(str);
        return convertView;
    }

     class ViewHolder {
        @BindView(R.id.btn_type)
        TextView btnType;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
