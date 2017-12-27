package com.dlwx.hzquser.utiles;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.dlwx.hzquser.R;
import com.dlwx.hzquser.adapter.SeleteAddpressAdapter;
import com.dlwx.hzquser.model.bean.AddressSelete;

import java.util.ArrayList;
import java.util.List;

/**
 * 地址选择
 */

public class SeleteAddressUtiles extends PopupWindow implements NumberPicker.OnValueChangeListener{
    private Context ctx;
    private String[] strs;
    private List<AddressSelete.BodyBean> body;
    private String str;
    private ListView lv_list;
    private TextView tv_delete;
    private List<String> list;



    public SeleteAddressUtiles(int width, int height,Context ctx,final List<AddressSelete.BodyBean> body,View parent) {
        super(width, height);
        this.ctx = ctx;
        strs = new String[body.size()];
        this.setFocusable(true);
        this.setBackgroundDrawable(new ColorDrawable(0x77000000));
        View view = LayoutInflater.from(ctx).inflate(R.layout.dia_address,null);
        this.setContentView(view);
        lv_list = (ListView) view.findViewById(R.id.lv_list);
        tv_delete = (TextView) view.findViewById(R.id.tv_delete);
        tv_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        list = new ArrayList<>();
        for (int i = 0; i < body.size(); i++) {
            list.add(body.get(i).getName());
        }
        SeleteAddpressAdapter addpressAdapter = new SeleteAddpressAdapter(ctx,list);
        lv_list.setAdapter(addpressAdapter);
        lv_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (addListener != null) {
                    addListener.getResult(body.get(position).getName(),body.get(position).getPath_id());
                    dismiss();
                }
            }
        });
        this.showAtLocation(parent, Gravity.BOTTOM|Gravity.CENTER,0,0);
    }

    public void showPopu(){


    }

    private int pos;
    @Override
    public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
        pos = newVal;
        str = strs[newVal];
    }

    public interface SeleteAddListener{
        void getResult(String s,String path_id);
    }
    private SeleteAddListener addListener;

    public void setAddListener(SeleteAddListener addListener){
        this.addListener = addListener;
    }
}
