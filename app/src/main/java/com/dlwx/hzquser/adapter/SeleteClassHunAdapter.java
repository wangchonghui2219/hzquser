package com.dlwx.hzquser.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.dlwx.hzquser.R;
import com.dlwx.hzquser.base.BaseFastAdapter;
import com.dlwx.hzquser.model.bean.SeleteMealBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 搜索界面窗口
 */

public class SeleteClassHunAdapter extends BaseFastAdapter {
    private List<String> menuClass;
    private List<SeleteMealBean.IsCheck> checknum;
    private int hun_num;
    private int selete_num;
    public SeleteClassHunAdapter(Context ctx, List<String> menuClass,List<SeleteMealBean.IsCheck> checknum,int hunNum) {
        super(ctx);
        this.menuClass = menuClass;
        this.checknum = checknum;
        this.hun_num = hunNum;
    }

    @Override
    public int getCount() {
        return menuClass.size();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder vh;
        if (convertView == null) {
            convertView = LayoutInflater.from(ctx).inflate(R.layout.item_popu, null);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        String s = menuClass.get(position);
        vh.btnType.setText(s);
        vh.btnType.setChecked(checknum.get(position).getCheck());
        vh.btnType.setChecked(true);
        numListener.setCheckResult(true,position);
        checknum.get(position).setCheck(true);
            vh.btnType.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean checked = ((CheckBox) v).isChecked();
                    if (checknum.get(position).getCheck()) {
                        vh.btnType.setChecked(true);
                        return;
                    }
                    numListener.setCheckResult(checked,position);
                }
            });
        return convertView;
    }
    private int tag = 0;
    public void setShowbg(int position) {
        this.tag = position;
        notifyDataSetChanged();
    }
    private int pos = -1;
    public void setCheckPos(int position) {
        this.pos = position;
        notifyDataSetChanged();
    }

    class ViewHolder {
        @BindView(R.id.btn_type)
        CheckBox btnType;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    public interface SeleteNumListener{
        void setCheckResult(boolean checked,int position);
    }
    private SeleteNumListener numListener;
    public void setOnCheckNumListener(SeleteNumListener numListener){
        this.numListener = numListener;

    }

    public int getSelete_num() {
        return selete_num;
    }

    public void setSelete_num(int selete_num) {
        this.selete_num = selete_num;
    }
}
