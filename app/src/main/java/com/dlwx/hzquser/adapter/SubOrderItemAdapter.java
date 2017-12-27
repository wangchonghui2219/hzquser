package com.dlwx.hzquser.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dlwx.hzquser.R;
import com.dlwx.hzquser.base.BaseFastAdapter;
import com.dlwx.hzquser.model.bean.SubOrderBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/8/22/022.
 */

public class SubOrderItemAdapter extends BaseFastAdapter {
    private List<SubOrderBean.MenuType> menuTypes;
    public SubOrderItemAdapter(Context ctx,List<SubOrderBean.MenuType> menuTypes ) {
        super(ctx);
        this.menuTypes = menuTypes;
    }

    @Override
    public int getCount() {
        return menuTypes.size();
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
        SubOrderBean.MenuType menuType = menuTypes.get(position);
        vh.btnType.setText(menuType.getTypename());
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
