package com.dlwx.hzquser.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dlwx.hzquser.R;
import com.dlwx.hzquser.base.BaseFastAdapter;
import com.dlwx.hzquser.model.bean.ShopCatListBean;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 购物车列表
 */

public class ShopCatAdapter extends BaseFastAdapter {
    private List<ShopCatListBean.BodyBean> body;
    public Map<String,String> map;
    public ShopCatAdapter(Context ctx,List<ShopCatListBean.BodyBean> body) {
        super(ctx);
        this.body = body;
        map = new HashMap<>();

    }

    @Override
    public int getCount() {
        return body.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh;
        if (convertView == null) {
            convertView = LayoutInflater.from(ctx).inflate(R.layout.item_shop_cat, null);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        final ShopCatListBean.BodyBean bodyBean = body.get(position);
        Glide.with(ctx).load(bodyBean.getPath()).into(vh.ivPic);
        vh.tvName.setText(bodyBean.getMenu_name());
        vh.tvType.setText(bodyBean.getType_name());
        double totalPrice = 0.0;
        for (int i = 1; i <= bodyBean.getNum(); i++) {
            totalPrice += bodyBean.getPrice();
            Log.i("wch",totalPrice+"");
        }
        DecimalFormat df = new DecimalFormat("#.00");
        totalPrice = Double.parseDouble(df.format(totalPrice));
        vh.tvPrice.setText("￥"+totalPrice);
        vh.tvUnitprice.setText("￥"+bodyBean.getPrice());
        vh.tvNums.setText("x"+bodyBean.getNum());
        vh.cbCheck.setChecked(bodyBean.getCheck());
        vh.tvNum.setText(bodyBean.getNum()+"");
        if (isEditext) {
            vh.llEdit.setVisibility(View.VISIBLE);
            vh.tvNums.setVisibility(View.GONE);
        }else{
            vh.llEdit.setVisibility(View.GONE);
            vh.tvNums.setVisibility(View.VISIBLE);
        }
        vh.cbCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = ((CheckBox) v).isChecked();
               bodyBean.setCheck(checked);
                if (onChecklistener != null) {
                    onChecklistener.getResult();
                }
            }
        });
        vh.ivAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bodyBean.setNum(bodyBean.getNum()+1);
                map.put(bodyBean.getCart_id(),bodyBean.getNum()+"");
                notifyDataSetChanged();
            }
        });
        vh.ivMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bodyBean.getNum()==1) {
                    bodyBean.setNum(1);

                }else{
                    bodyBean.setNum(bodyBean.getNum()-1);

                }
                map.put(bodyBean.getCart_id(),bodyBean.getNum()+"");
                notifyDataSetChanged();

            }
        });
        return convertView;
    }
    private boolean isEditext;
    /**
     * 编辑
     * @param isEditext
     */
    public void setEdiState(boolean isEditext) {
        this.isEditext = isEditext;
        notifyDataSetChanged();
    }
    class ViewHolder {
        @BindView(R.id.cb_check)
        CheckBox cbCheck;
        @BindView(R.id.iv_pic)
        ImageView ivPic;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_type)
        TextView tvType;
        @BindView(R.id.tv_unitprice)
        TextView tvUnitprice;
        @BindView(R.id.tv_price)
        TextView tvPrice;
        @BindView(R.id.iv_minus)
        ImageView ivMinus;
        @BindView(R.id.tv_num)
        TextView tvNum;
        @BindView(R.id.iv_add)
        ImageView ivAdd;
        @BindView(R.id.ll_edit)
        LinearLayout llEdit;
        @BindView(R.id.tv_nums)
        TextView tvNums;
        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
    private OnCheckListener onChecklistener;
    public interface OnCheckListener{
        void getResult();
    }
    public void setOnCheckListener(OnCheckListener onChecklistener){
        this.onChecklistener = onChecklistener;
    }
}
