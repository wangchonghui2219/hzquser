package com.dlwx.hzquser.view.activity;

import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.dlwx.hzquser.R;
import com.dlwx.hzquser.base.BaseActivity;
import com.dlwx.hzquser.model.bean.ImgAuthBean;
import com.dlwx.hzquser.model.bean.ResultBean;
import com.dlwx.hzquser.presenter.Presenter;
import com.dlwx.hzquser.utiles.ButtonUtils;
import com.dlwx.hzquser.utiles.CountDownUtiles;
import com.dlwx.hzquser.utiles.HttpUtiles;
import com.dlwx.hzquser.utiles.Regularutiles;
import com.dlwx.hzquser.utiles.SpUtiles;
import com.dlwx.hzquser.utiles.VerificationCodeUtiles;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.dlwx.hzquser.base.MyApplication.User_Token;


/**
 * 修改手机号
 */
public class ChangePhoneActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.title_txt)
    TextView titleTxt;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_auth)
    EditText etAuth;
    @BindView(R.id.btn_auth)
    Button btnAuth;
    @BindView(R.id.btn_submit)
    Button btnSubmit;
    @BindView(R.id.et_rigauth)
    EditText etRigauth;
    @BindView(R.id.iv_auth)
    ImageView ivAuth;
    private String phone;
    private String title;

    @Override
    protected void initView() {
        title = getIntent().getStringExtra("title");
        setContentView(R.layout.activity_change_phone);
        ButterKnife.bind(this);
    }

    @Override
    protected void initData() {
        initTabBar(toolbar);
        titleTxt.setText(title);
        getAuth();
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected Presenter createPresenter() {
        return new Presenter(this);
    }


    @OnClick({R.id.btn_auth, R.id.btn_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_auth:
                String phone = etPhone.getText().toString().trim();
                if (TextUtils.isEmpty(phone)) {
                    Toast.makeText(ctx, "手机号不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!Regularutiles.Photo(phone)) {
                    Toast.makeText(ctx, "手机号格式不正确", Toast.LENGTH_SHORT).show();
                }
                String img_code = etRigauth.getText().toString().trim();
                if (TextUtils.isEmpty(img_code)) {
                    vibrator.vibrate(50);
                    Toast.makeText(ctx, "请输入右侧验证码", Toast.LENGTH_SHORT).show();
                    return;
                }
                CountDownUtiles countDownUtiles = new CountDownUtiles(btnAuth);
                if (!ButtonUtils.isFastDoubleClick(R.id.btn_auth, 1000)) {
                    if (btnAuth.getText().equals("验证码") || btnAuth.getText().equals("重新发送")) {

                        VerificationCodeUtiles codeUtiles = new VerificationCodeUtiles(ctx, phone, 3, countDownUtiles);
                        codeUtiles.sendVerificationCode(img_code);
                    }
                }

                break;
            case R.id.btn_submit:

                submit();
                break;
        }
    }

    private void submit() {
        phone = etPhone.getText().toString().trim();
        if (TextUtils.isEmpty(phone)) {
            Toast.makeText(ctx, "手机号不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!Regularutiles.Photo(phone)) {
            Toast.makeText(ctx, "手机号格式不正确", Toast.LENGTH_SHORT).show();
        }

        String auth = etAuth.getText().toString().trim();
        if (TextUtils.isEmpty(auth)) {
            Toast.makeText(ctx, "验证码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        HttpType = 1;
        Map<String, String> map = new HashMap<>();
        map.put("token", User_Token);
        map.put("phone", phone);
        map.put("code", auth);
        mPreenter.fetch(map, false, HttpUtiles.ChangePhone, "");
    }

    @Override
    public void showData(String s) {
        loading.dismiss();

        Gson gson = new Gson();
        if (HttpType == 1) {
            ResultBean resultBean = gson.fromJson(s, ResultBean.class);
            if (resultBean.getCode() == 200) {
                sp.edit().putString(SpUtiles.Phone, phone).commit();
                finish();
            }
            Toast.makeText(ctx, resultBean.getResult(), Toast.LENGTH_SHORT).show();
        }else{
            ImgAuthBean imgAuthBean = gson.fromJson(s, ImgAuthBean.class);
            if (imgAuthBean.getCode() == 200) {
                String code_url = imgAuthBean.getBody().getCode_url();
                Glide.with(ctx).load(code_url).into(ivAuth);
            }else{
                Toast.makeText(ctx, imgAuthBean.getResult(), Toast.LENGTH_SHORT).show();
            }
        }
    }



    @OnClick(R.id.iv_auth)
    public void onViewClicked() {
        getAuth();

    }
    private int HttpType;
    /**
     * 获取图片验证码
     */
    private void getAuth() {
        HttpType = 2;
        Map<String,String> map = new HashMap<>();
        mPreenter.fetch(map,true,HttpUtiles.Img_AUth,"");

    }
}
