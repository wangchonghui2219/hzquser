package com.dlwx.hzquser.view.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dlwx.hzquser.R;
import com.dlwx.hzquser.base.BaseActivity;
import com.dlwx.hzquser.base.MyApplication;
import com.dlwx.hzquser.model.bean.LoginBean;
import com.dlwx.hzquser.presenter.Presenter;
import com.dlwx.hzquser.utiles.HttpUtiles;
import com.dlwx.hzquser.utiles.Regularutiles;
import com.dlwx.hzquser.utiles.SpUtiles;
import com.dlwx.hzquser.utiles.wechatpay.wxapi.WxLoginutiles;
import com.dlwx.hzquser.wxapi.CallBackListenerUtiles;
import com.dlwx.hzquser.wxapi.QQUtiles;
import com.google.gson.Gson;
import com.tencent.tauth.Tencent;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.jpush.android.api.JPushInterface;

/**
 * 登录
 */
public class LoginActivity extends BaseActivity {

    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_pwd)
    EditText etPwd;
    @BindView(R.id.tv_findpwd)
    TextView tvFindpwd;
    @BindView(R.id.btn_submit)
    Button btnSubmit;
    @BindView(R.id.tv_regis)
    TextView tvRegis;
    @BindView(R.id.rl_black)
    RelativeLayout rlBlack;
    @BindView(R.id.iv_qqlogin)
    ImageView ivQqlogin;
    @BindView(R.id.iv_wxLogin)
    ImageView ivWxLogin;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @Override
    protected void initData() {


    }
    @Override
    protected void initListener() {
        CallBackListenerUtiles.setCallBackListener(new CallBackListenerUtiles.ShareCallBackListener() {
            @Override
            public void setComplete() {
                //分享
            }
            @Override
            public void getUserInfo(String nickName, String imagePic, String openID) {
                //发起服务端登录接口
                loading.dismiss();
                otherlogin(nickName, imagePic, openID, loginType);
            }

            @Override
            public void Error(String mess) {
                loading.dismiss();
                Toast.makeText(ctx, mess, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected Presenter createPresenter() {
        return new Presenter(this);
    }

    @OnClick({R.id.tv_findpwd, R.id.btn_submit, R.id.tv_regis})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_findpwd:
                startActivity(new Intent(ctx, ForgetPwdActivity.class));
                break;
            case R.id.btn_submit:

                submit();
                break;
            case R.id.tv_regis:
                startActivity(new Intent(ctx, RegisterActivity.class));
                break;
        }
    }

    private void submit() {
        String phone = etPhone.getText().toString().trim();
        if (TextUtils.isEmpty(phone)) {
            Toast.makeText(ctx, "手机号不能为空", Toast.LENGTH_SHORT).show();
            vibrator.vibrate(50);
            return;
        }
        if (!Regularutiles.Photo(phone)) {
            etPhone.setText("");
            Toast.makeText(ctx, "手机号格式不正确,请重新输入", Toast.LENGTH_SHORT).show();
            vibrator.vibrate(50);
            return;
        }
        String pwd = etPwd.getText().toString().trim();
        if (TextUtils.isEmpty(pwd)) {
            Toast.makeText(ctx, "密码不能为空", Toast.LENGTH_SHORT).show();
            vibrator.vibrate(50);
            return;
        }
        Map<String, String> map = new HashMap<>();
        map.put("login_type", "1");
        map.put("phone", phone);
        map.put("password", pwd);
        mPreenter.fetch(map, false, HttpUtiles.Login, "login");
    }

    @Override
    public void showData(String s) {
        loading.dismiss();
        wch(s);

        Gson gson = new Gson();
        LoginBean loginBean = gson.fromJson(s, LoginBean.class);
        if (loginBean.getCode() == 200) {
            LoginBean.BodyBean body = loginBean.getBody();
            sp.edit().putString(SpUtiles.User_Token, body.getToken()).commit();
            sp.edit().putString(SpUtiles.Is_Path,body.getIs_path()).commit();
            sp.edit().putString(SpUtiles.Phone, body.getPhone()).commit();
            sp.edit().putString(SpUtiles.NickName, body.getNickname()).commit();
            sp.edit().putString(SpUtiles.Grade, body.getGrade()).commit();
            sp.edit().putString(SpUtiles.seller_id, body.getSeller_id()).commit();
            sp.edit().putString(SpUtiles.header_pic, body.getHeader_pic()).commit();
            sp.edit().putString(SpUtiles.terrace, body.getExten_code()).commit();
            sp.edit().putString(SpUtiles.User_ID,body.getUser_id()).commit();
            sp.edit().putString(SpUtiles.Head_pic,body.getHeader_pic()).commit();
            sp.edit().putString(SpUtiles.LoginType,loginType).commit();

            MyApplication.User_Token = body.getToken();
            MyApplication.Head_Pic = body.getHeader_pic();
            Set<String> tags = new HashSet<>();
            tags.add(body.getUser_id());
            JPushInterface.setTags(getApplicationContext(),1,tags);
            finish();
            if (body.getIs_path().equals("2")) {
                startActivity(new Intent(ctx,MessagebindingActivity.class).putExtra("login",body)
                        .putExtra("loginType",loginType));
            }else{

            }
        } else {
            Toast.makeText(ctx, loginBean.getResult(), Toast.LENGTH_SHORT).show();
        }
    }


    @OnClick(R.id.rl_black)
    public void onViewClicked() {
        finish();
    }
    private String loginType = "1";
    /**
     * 微信登陆
     */
    @OnClick(R.id.iv_wxLogin)
    public void onIvWxLoginClicked() {
        loading.show();
        loginType = "2";
        WxLoginutiles wxLoginutiles = new WxLoginutiles(ctx);
        wxLoginutiles.loginWx(ctx);


    }

    /**
     * qq登陆
     */
    @OnClick(R.id.iv_qqlogin)
    public void onIvQqloginClicked() {
        loading.show();
        loginType = "3";
        QQUtiles shareutiles = new QQUtiles();
        shareutiles.LoginQQ(ctx);


    }

    private void otherlogin(String nickName, String imagePic, String openID, String logintype) {
        Map<String, String> map = new HashMap<>();
        map.put("login_type", logintype);
        map.put("third_num",openID);
        map.put("third_name",nickName);
        map.put("pho",imagePic);
        mPreenter.fetch(map, false, HttpUtiles.Login, "login");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Tencent.onActivityResultData(requestCode, resultCode, data, new QQUtiles().uiListener);

    }
}
