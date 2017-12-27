package com.dlwx.hzquser.view.activity;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dlwx.hzquser.R;
import com.dlwx.hzquser.base.BaseActivity;
import com.dlwx.hzquser.base.MyApplication;
import com.dlwx.hzquser.model.bean.MessageCenterBean;
import com.dlwx.hzquser.presenter.Presenter;
import com.dlwx.hzquser.utiles.HttpUtiles;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 消息中心
 */
public class MessCenterActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.title_txt)
    TextView titleTxt;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.ll_sysmess)
    LinearLayout llSysmess;
    @BindView(R.id.ll_ordermess)
    LinearLayout llOrdermess;

    @BindView(R.id.imageView2)
    ImageView imageView2;
    @BindView(R.id.ll_meal)
    LinearLayout llMeal;
    @BindView(R.id.tv_sysnum)
    TextView tvSysnum;
    @BindView(R.id.tv_ordnum)
    TextView tvOrdnum;
    @BindView(R.id.tv_getnum)
    TextView tvGetnum;
    private List<MessageCenterBean.BodyBean> body;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_mess_center);
        ButterKnife.bind(this);
        initTabBar(toolbar);
        titleTxt.setText("消息中心");
    }

    @Override
    protected void initData() {
        getData();
    }

    private void getData() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        Map<String, String> map = new HashMap<>();
        map.put("token", MyApplication.User_Token);
        mPreenter.fetch(map,true, HttpUtiles.Mess_Num,"");
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected Presenter createPresenter() {
        return new Presenter(this);
    }

    @OnClick({R.id.ll_sysmess, R.id.ll_ordermess, R.id.ll_meal})
    public void onViewClicked(View view) {
        if (body == null) {
            return;
        }
        Intent intent;
        switch (view.getId()) {
            case R.id.ll_sysmess:
                intent = new Intent(ctx,MessageCenterTwoActivity.class);
                intent.putExtra("type",body.get(0).getType());
                intent.putExtra("title","系统消息");
                startActivity(intent);
                break;
            case R.id.ll_ordermess:
                intent = new Intent(ctx,MessageCenterTwoActivity.class);
                intent.putExtra("type",body.get(1).getType());
                intent.putExtra("title","订单消息");
                startActivity(intent);
                break;

            case R.id.ll_meal:
                intent = new Intent(ctx,MessageCenterTwoActivity.class);
                intent.putExtra("type",body.get(2).getType());
                intent.putExtra("title","领餐消息");
                startActivity(intent);
                break;
        }
    }

    @Override
    public void showData(String s) {
        loading.dismiss();
        wch(s);
        Gson gson = new Gson();
        MessageCenterBean messageCenterBean = gson.fromJson(s, MessageCenterBean.class);
        if (messageCenterBean.getCode() == 200) {
            body = messageCenterBean.getBody();
            int sysis_read = body.get(0).getIs_read();
            if (sysis_read != 0) {
                tvSysnum.setVisibility(View.VISIBLE);
                tvSysnum.setText(body.get(0).getIs_read()+"");
            }
             int ordis_read = body.get(1).getIs_read();
                        if (ordis_read != 0) {
                            tvOrdnum.setVisibility(View.VISIBLE);
                            tvOrdnum.setText(body.get(1).getIs_read()+"");
                        }
             int getis_read = body.get(2).getIs_read();
                        if (getis_read != 0) {
                            tvGetnum.setVisibility(View.VISIBLE);
                            tvGetnum.setText(body.get(2).getIs_read()+"");
                        }

        }else{
            Toast.makeText(ctx, messageCenterBean.getResult(), Toast.LENGTH_SHORT).show();
        }
    }
}
