package com.dlwx.hzquser.view.activity;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.dlwx.hzquser.R;
import com.dlwx.hzquser.base.BaseActivity;
import com.dlwx.hzquser.base.MyApplication;
import com.dlwx.hzquser.model.bean.LookmealCodeBean;
import com.dlwx.hzquser.presenter.Presenter;
import com.dlwx.hzquser.utiles.HttpUtiles;
import com.dlwx.hzquser.utiles.Regularutiles;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 代领
 */
public class ReplaceMealActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.title_txt)
    TextView titleTxt;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.lv_list)
    ListView lvList;
    @BindView(R.id.tv_totalPrice)
    TextView tvTotalPrice;
    @BindView(R.id.tv_userName)
    TextView tvUserName;
    @BindView(R.id.tv_terraceno)
    TextView tvTerraceno;
    @BindView(R.id.tv_userphone)
    TextView tvUserphone;
    @BindView(R.id.iv_gotele)
    ImageView ivGotele;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_terraceno)
    EditText etTerraceno;
    @BindView(R.id.btn_submit)
    Button btnSubmit;
    @BindView(R.id.tv_order_no)
    TextView tvOrderno;
    private String order_id;
    private String pick_type;
    private int status;
    private String orderno;
    private String meal_time;

    @Override
    protected void initView() {
        Intent intent = getIntent();
        order_id = intent.getStringExtra("order_id");
        pick_type = intent.getStringExtra("pick_type");
        status = intent.getIntExtra("status", 0);
        orderno = intent.getStringExtra("orderno");
        meal_time = intent.getStringExtra("meal_time");
        setContentView(R.layout.activity_replace_meal);
        ButterKnife.bind(this);
    }

    @Override
    protected void initData() {
        initTabBar(toolbar);
        titleTxt.setText("代领");
        tvOrderno.setText("订单号："+orderno);
        tvTime.setText(meal_time);
    }
    @Override
    protected void initListener() {

    }

    @Override
    protected Presenter createPresenter() {
        return new Presenter(this);
    }
    @OnClick({R.id.iv_gotele, R.id.btn_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_gotele:
                break;
            case R.id.btn_submit:
                makeCode();

                break;
        }
    }
    /**
     * 生成二维码
     */
    private void makeCode() {
        String phone = etPhone.getText().toString().trim();
        if (TextUtils.isEmpty(phone)) {
            vibrator.vibrate(50);
            Toast.makeText(ctx, "手机号不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!Regularutiles.Photo(phone)) {
            vibrator.vibrate(50);
            Toast.makeText(ctx, "手机号格式不正确", Toast.LENGTH_SHORT).show();
            return;
        }

        String terraceno = etTerraceno.getText().toString().trim();
        if (TextUtils.isEmpty(terraceno)) {
            vibrator.vibrate(50);
            Toast.makeText(ctx, "平台号不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        Map<String, String> map = new HashMap<>();
        map.put("token", MyApplication.User_Token);
        map.put("order_id", order_id);
        map.put("pick_type", pick_type);
        map.put("pick_phone",phone);
        map.put("pick_exten",terraceno);
        mPreenter.fetch(map, false, HttpUtiles.Mack_PickUp, "");
    }

    @Override
    public void showData(String s) {
        loading.dismiss();
        jsonMakeCode(s);
    }
    /**
            * 生成二维码
     *
             * @param s
     */
    private void jsonMakeCode(String s) {
        wch(s);
        Gson gson = new Gson();
        LookmealCodeBean makeCodeBean = gson.fromJson(s, LookmealCodeBean.class);
        if (makeCodeBean.getCode() == 200) {
            LookmealCodeBean.BodyBean body = makeCodeBean.getBody();
            Intent intent = new Intent(ctx,GetMealCodeActivity.class);
            intent.putExtra("body",body);
            intent.putExtra("status",status);
            intent.putExtra("pick_type","2");
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(ctx, makeCodeBean.getResult(), Toast.LENGTH_SHORT).show();
        }
    }
}
