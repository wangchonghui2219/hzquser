package com.dlwx.hzquser.view.activity;

import android.support.v7.widget.Toolbar;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.dlwx.hzquser.R;
import com.dlwx.hzquser.adapter.MessageCenterAdapter;
import com.dlwx.hzquser.base.BaseActivity;
import com.dlwx.hzquser.base.MyApplication;
import com.dlwx.hzquser.model.bean.MessListBean;
import com.dlwx.hzquser.presenter.Presenter;
import com.dlwx.hzquser.utiles.HttpUtiles;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 消息中心
 */
public class MessageCenterTwoActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.title_txt)
    TextView titleTxt;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.lv_list)
    ListView lvList;
    private String title;
    private int type;

    @Override
    protected void initView() {
        title = getIntent().getStringExtra("title");
        type = getIntent().getIntExtra("type", 0);
        setContentView(R.layout.activity_message_center_two);
        ButterKnife.bind(this);
    }

    @Override
    protected void initData() {
            initTabBar(toolbar);
        titleTxt.setText(title);
        Map<String,String> map = new HashMap<>();
        map.put("token", MyApplication.User_Token);
        map.put("type",type+"");
        mPreenter.fetch(map,true, HttpUtiles.Mess_List,"Mess_List"+type,true);


    }

    @Override
    protected void initListener() {

    }

    @Override
    protected Presenter createPresenter() {
        return new Presenter(this);
    }

    @Override
    public void showData(String s) {
        loading.dismiss();
        wch(s);
        Gson gson = new Gson();
        MessListBean messListBean = gson.fromJson(s, MessListBean.class);
        if (messListBean.getCode() == 200) {
            List<MessListBean.BodyBean> body = messListBean.getBody();
            lvList.setAdapter(new MessageCenterAdapter(ctx,body));
        }else{
            Toast.makeText(ctx, messListBean.getResult(), Toast.LENGTH_SHORT).show();
        }
    }
}
