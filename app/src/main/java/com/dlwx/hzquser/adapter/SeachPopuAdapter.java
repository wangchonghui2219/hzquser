package com.dlwx.hzquser.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dlwx.hzquser.R;
import com.dlwx.hzquser.base.BaseFastAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 搜索界面窗口
 */

public class SeachPopuAdapter extends BaseFastAdapter {
    private List<String> menuClass;
    public SeachPopuAdapter(Context ctx,List<String> menuClass) {
        super(ctx);
        this.menuClass = menuClass;
    }

    @Override
    public int getCount() {
        return menuClass.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh;
        if (convertView == null) {
            convertView = LayoutInflater.from(ctx).inflate(R.layout.item_text, null);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        String s = menuClass.get(position);
        vh.btnType.setText(s);
        if (position == tag) {
            vh.btnType.setBackgroundResource(R.drawable.shape_btn_bg);
        }else{
            vh.btnType.setBackgroundResource(R.drawable.shape_btn_bg_gary);
        }

        return convertView;
    }
    private int tag = 0;
    public void setShowbg(int position) {
        this.tag = position;
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
