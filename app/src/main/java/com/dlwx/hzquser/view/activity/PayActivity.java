package com.dlwx.hzquser.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.dlwx.hzquser.R;
import com.dlwx.hzquser.base.BaseActivity;
import com.dlwx.hzquser.base.MyApplication;
import com.dlwx.hzquser.model.bean.PayResultBean;
import com.dlwx.hzquser.model.bean.WxPayBean;
import com.dlwx.hzquser.presenter.Presenter;
import com.dlwx.hzquser.utiles.AlPay.AliPayUtiles;
import com.dlwx.hzquser.utiles.HttpUtiles;
import com.dlwx.hzquser.utiles.wechatpay.WeChatPayUtiles;
import com.dlwx.hzquser.utiles.wechatpay.wxapi.WXPayResultListener;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 支付界面
 */
public class PayActivity extends BaseActivity {

    @BindView(R.id.cb_check)
    ImageView cbCheck;
    @BindView(R.id.ll_Ali)
    LinearLayout llAli;
    @BindView(R.id.cb_checkWx)
    ImageView cbCheckWx;
    @BindView(R.id.ll_Wx)
    LinearLayout llWx;
    @BindView(R.id.btn_pay)
    Button btnPay;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.title_txt)
    TextView titleTxt;
    @BindView(R.id.tv_right)
    TextView tvRight;
    private String orderNo;

    @Override
    protected void initView() {
        orderNo = getIntent().getStringExtra("orderNo");
        setContentView(R.layout.activity_pay);
        ButterKnife.bind(this);
    }

    @Override
    protected void initData() {
        initTabBar(toolbar);
        titleTxt.setText("支付");
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();

                break;
        }

        return true;
    }
    @Override
    protected void initListener() {

    }

    @Override
    protected Presenter createPresenter() {
        return new Presenter(this);
    }

    private String PayType = "1";//支付方式

    @OnClick({R.id.ll_Ali, R.id.ll_Wx, R.id.btn_pay})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_Ali:
                PayType = "1";
                Glide.with(ctx).load(R.mipmap.icon_zhucetongyi1).into(cbCheckWx);
                Glide.with(ctx).load(R.mipmap.icon_zhucetongyi).into(cbCheck);
                break;
            case R.id.ll_Wx:
                PayType ="2";
                Glide.with(ctx).load(R.mipmap.icon_zhucetongyi1).into(cbCheck);
                Glide.with(ctx).load(R.mipmap.icon_zhucetongyi).into(cbCheckWx);

                break;
            case R.id.btn_pay:
                startPay();

                break;
        }
    }

    /**
     * 支付
     */
    private void startPay() {
        wch(PayType);
        Map<String,String> map = new HashMap<>();
        map.put("token", MyApplication.User_Token);
        map.put("pay_type",PayType);
        map.put("order_no",orderNo);
        mPreenter.fetch(map,false, HttpUtiles.Pay,"");
    }

    @Override
    public void showData(String s) {
        loading.dismiss();
        wch(s);
        Gson gson = new Gson();
        if (PayType.equals("1")) {//支付宝

            PayResultBean payResultBean = gson.fromJson(s, PayResultBean.class);
            if (payResultBean.getCode() == 200) {
                AliPayUtiles utiles = new AliPayUtiles((Activity) ctx,payResultBean.getBody());
                utiles.startPay();
                utiles.setOnResultListener(new AliPayUtiles.PayResultOnListener() {
                    @Override
                    public void getResult(Boolean result) {
                        if (result) {
                            startActivity(new Intent(ctx,MyOrderActivity.class));
                            finish();
                        }else{
                            startActivity(new Intent(ctx,MyOrderActivity.class));
                            finish();
                        }
                    }
                });

            }else{
                Toast.makeText(ctx, payResultBean.getResult(), Toast.LENGTH_SHORT).show();
            }
        }else{//微信
            WxPayBean wxPayBean = gson.fromJson(s, WxPayBean.class);
            WeChatPayUtiles payUtiles = new WeChatPayUtiles(ctx);
            WxPayBean.BodyBean body = wxPayBean.getBody();
            payUtiles.WXPay(body);
            payUtiles.setResult(new WXPayResultListener() {
                @Override
                public void setResult(boolean result) {
                    if (result) {
                        startActivity(new Intent(ctx,MyOrderActivity.class));
                        finish();
                    }else{
                        startActivity(new Intent(ctx,MyOrderActivity.class));
                        finish();
                    }
                }
            });
        }
    }
}
