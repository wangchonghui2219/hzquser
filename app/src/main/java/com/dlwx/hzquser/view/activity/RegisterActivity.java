package com.dlwx.hzquser.view.activity;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.dlwx.hzquser.R;
import com.dlwx.hzquser.base.BaseActivity;
import com.dlwx.hzquser.model.bean.AddressSelete;
import com.dlwx.hzquser.model.bean.ImgAuthBean;
import com.dlwx.hzquser.model.bean.ResultBean;
import com.dlwx.hzquser.presenter.Presenter;
import com.dlwx.hzquser.utiles.ButtonUtils;
import com.dlwx.hzquser.utiles.CountDownUtiles;
import com.dlwx.hzquser.utiles.HttpUtiles;
import com.dlwx.hzquser.utiles.Regularutiles;
import com.dlwx.hzquser.utiles.SeleteAddressUtiles;
import com.dlwx.hzquser.utiles.VerificationCodeUtiles;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 注册
 */
public class RegisterActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.title_txt)
    TextView titleTxt;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.et_phone)
    EditText etphone;
    @BindView(R.id.et_auth)
    EditText etauth;
    @BindView(R.id.btn_auth)
    Button btnAuth;
    @BindView(R.id.et_pwd)
    EditText etPwd;
    @BindView(R.id.et_affpwd)
    EditText etAffpwd;
    @BindView(R.id.btn_submit)
    Button btnSubmit;
    @BindView(R.id.tv_protocol)
    TextView tvProtocol;
    @BindView(R.id.tv_gologin)
    TextView tvGologin;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.et_recomcode)
    EditText etRecomcode;
    @BindView(R.id.checkBox)
    CheckBox checkBox;
    @BindView(R.id.imageView5)
    ImageView imageView5;
    @BindView(R.id.et_descaddress)
    EditText etDescAddress;
    @BindView(R.id.et_rigauth)
    EditText etRigauth;
    @BindView(R.id.iv_auth)
    ImageView ivAuth;
    @BindView(R.id.imageView6)
    ImageView imageView6;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
    }

    @Override
    protected void initData() {
        initTabBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.icon_jiantoubaise);
        getAuth();
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
        Gson gson = new Gson();
        if (HttpType == 1) {
            ResultBean resultBean = gson.fromJson(s, ResultBean.class);
            if (resultBean.getCode() == 200) {
                finish();
            }
            Toast.makeText(ctx, resultBean.getResult(), Toast.LENGTH_SHORT).show();
        } else if(HttpType == 3){
            ImgAuthBean imgAuthBean = gson.fromJson(s, ImgAuthBean.class);
            if (imgAuthBean.getCode() == 200) {
                String code_url = imgAuthBean.getBody().getCode_url();
                Glide.with(ctx).load(code_url).into(ivAuth);
            }else{
                Toast.makeText(ctx, imgAuthBean.getResult(), Toast.LENGTH_SHORT).show();
            }
        }
        else {
            wch(s);
            AddressSelete addressSelete = gson.fromJson(s, AddressSelete.class);
            if (addressSelete.getCode() == 200) {
                List<AddressSelete.BodyBean> body = addressSelete.getBody();
                SeleteAddressUtiles addressUtiles = new SeleteAddressUtiles(LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT, ctx,
                        body, etAffpwd);
                addressUtiles.setAddListener(new SeleteAddressUtiles.SeleteAddListener() {
                    @Override
                    public void getResult(String s, String path_id) {
                        wch(s);
                        address = path_id;
                        tvAddress.setText(s);
                    }
                });
            } else {
                Toast.makeText(ctx, addressSelete.getResult(), Toast.LENGTH_SHORT).show();
            }

        }
    }

    private String address;

    @Override
    public void disLoading() {
        super.disLoading();
    }

    @OnClick({R.id.btn_auth, R.id.btn_submit, R.id.tv_protocol, R.id.tv_gologin,R.id.iv_auth})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_auth://验证码

                String phone = etphone.getText().toString().trim();
                if (TextUtils.isEmpty(phone)) {
                    vibrator.vibrate(50);

                    Toast.makeText(ctx, "手机号不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!Regularutiles.Photo(phone)) {
                    Toast.makeText(ctx, "手机号格式不正确", Toast.LENGTH_SHORT).show();
                    vibrator.vibrate(50);
                    return;
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

                        VerificationCodeUtiles codeUtiles = new VerificationCodeUtiles(ctx, phone, 1, countDownUtiles);
                        codeUtiles.sendVerificationCode(img_code);
                    }
                }
                break;
            case R.id.btn_submit://注册
                submit();
                break;
            case R.id.tv_protocol://协议
                Intent intent = new Intent(ctx, WebActivity.class);
                intent.putExtra("url", HttpUtiles.Regist_Deal);
                intent.putExtra("title", "注册协议");
                startActivity(intent);
                break;
            case R.id.tv_gologin://去登陆
                finish();
                break;
            case R.id.iv_auth:
                    getAuth();
                break;

        }
    }

    /**
     * 获取图片验证码
     */
    private void getAuth() {
        HttpType = 3;
        Map<String,String> map = new HashMap<>();
       mPreenter.fetch(map,true,HttpUtiles.Img_AUth,"");

    }

    private void submit() {
        String phone = etphone.getText().toString().trim();
        if (TextUtils.isEmpty(phone)) {
            Toast.makeText(ctx, "手机号不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!Regularutiles.Photo(phone)) {
            Toast.makeText(ctx, "手机号格式不正确", Toast.LENGTH_SHORT).show();
        }

        String auth = etauth.getText().toString().trim();
        if (TextUtils.isEmpty(auth)) {
            vibrator.vibrate(50);
            Toast.makeText(ctx, "验证码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        String pwd = etPwd.getText().toString().trim();
        if (TextUtils.isEmpty(pwd)) {
            vibrator.vibrate(50);

            Toast.makeText(ctx, "密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        String affpwd = etAffpwd.getText().toString().trim();
        if (TextUtils.isEmpty(affpwd)) {
            vibrator.vibrate(50);
            Toast.makeText(ctx, "确认密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!affpwd.equals(pwd)) {
            etPwd.setText("");
            etAffpwd.setText("");
            Toast.makeText(ctx, "两次密码不一致 请重新输入", Toast.LENGTH_SHORT).show();
            vibrator.vibrate(50);
            return;
        }

        if (TextUtils.isEmpty(address)) {
            vibrator.vibrate(50);
            Toast.makeText(ctx, "地址不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        String descAdd = etDescAddress.getText().toString().trim();
        if (TextUtils.isEmpty(descAdd)) {
            vibrator.vibrate(50);
            Toast.makeText(ctx, "详细地址不能为空", Toast.LENGTH_SHORT).show();
            return;
        }


        String recomcode = etRecomcode.getText().toString().trim();
        HttpType = 1;
        Map<String, String> map = new HashMap<>();
        map.put("phone", phone);
        map.put("code", auth);
        map.put("password", pwd);
        map.put("repwd", affpwd);
        map.put("path_id", address);
        map.put("address", descAdd);
        map.put("p_exten_code", recomcode);
        mPreenter.fetch(map, false, HttpUtiles.Register, "register");

    }

    private int HttpType;



    @OnClick(R.id.tv_address)
    public void onViewClicked() {
        HttpType = 2;
        Map<String, String> map = new HashMap<>();
        mPreenter.fetch(map, true, HttpUtiles.Register_Path, "Register_Path", true);
    }

}
