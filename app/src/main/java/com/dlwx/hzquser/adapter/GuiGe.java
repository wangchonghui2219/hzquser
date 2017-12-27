package com.dlwx.hzquser.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dlwx.hzquser.R;
import com.dlwx.hzquser.base.BaseFastAdapter;
import com.dlwx.hzquser.model.bean.MenuDetails;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/8/25/025.
 */

public class GuiGe extends BaseFastAdapter {
    private List<MenuDetails.BodyBean.GuiGeBean> guige;
    private int tag;
    public GuiGe(Context ctx,List<MenuDetails.BodyBean.GuiGeBean> guige ) {
        super(ctx);
        this.guige = guige;
    }

    @Override
    public int getCount() {
        return guige.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh;
        if (convertView == null) {
            convertView = LayoutInflater.from(ctx).inflate(R.layout.item_text, null);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        }else{
            vh = (ViewHolder) convertView.getTag();
        }
        MenuDetails.BodyBean.GuiGeBean guiGeBean = guige.get(position);
        vh.btnType.setText(guiGeBean.getSpec_name());

        if (position == tag) {
            vh.btnType.setBackgroundResource(R.drawable.shape_btn_bg);
        }else{
            vh.btnType.setBackgroundResource(R.drawable.shape_btn_bg_gary);

        }

        return convertView;
    }

     class ViewHolder {
        @BindView(R.id.btn_type)
        TextView btnType;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    public void setTag (int pos){
        this.tag = pos;
        notifyDataSetChanged();
    }
}
