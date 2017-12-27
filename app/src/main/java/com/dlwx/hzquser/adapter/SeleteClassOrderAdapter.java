package com.dlwx.hzquser.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.dlwx.hzquser.R;
import com.dlwx.hzquser.base.BaseFastAdapter;
import com.dlwx.hzquser.model.bean.SeleteMealOrtherBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 搜索界面窗口
 */

public class SeleteClassOrderAdapter extends BaseFastAdapter {
    private List<String> menuClass;
    private List<SeleteMealOrtherBean.IsCheck> checknumorther;
    public SeleteClassOrderAdapter(Context ctx, List<String> menuClass,List<SeleteMealOrtherBean.IsCheck> checknumorther) {
        super(ctx);
        this.menuClass = menuClass;
        this.checknumorther = checknumorther;
    }

    @Override
    public int getCount() {
        return menuClass.size();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder vh;
        if (convertView == null) {
            convertView = LayoutInflater.from(ctx).inflate(R.layout.item_popu, null);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        String s = menuClass.get(position);
        vh.btnType.setText(s);
        vh.btnType.setChecked(checknumorther.get(position).getCheck());
        vh.btnType.setChecked(true);
        numListener.setCheckResult(true,position);
        checknumorther.get(position).setCheck(true);
        vh.btnType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = ((CheckBox) v).isChecked();
                if (checknumorther.get(position).getCheck()) {
                    checknumorther.get(position).setCheck(true);
                    return;
                }

//                numListener.setCheckResult(checked,position);
            }
        });
        return convertView;
    }
    private int tag = 0;
    public void setShowbg(int position) {
        this.tag = position;
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
}
