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
import com.dlwx.hzquser.model.bean.AddressSelete;
import com.dlwx.hzquser.model.bean.ResultBean;
import com.dlwx.hzquser.model.bean.UserInfoBean;
import com.dlwx.hzquser.presenter.Presenter;
import com.dlwx.hzquser.utiles.HttpUtiles;
import com.dlwx.hzquser.utiles.SeleteAddressUtiles;
import com.dlwx.hzquser.utiles.SpUtiles;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.dlwx.hzquser.base.MyApplication.User_Token;

/**
 * 设置中心
 */
public class SettActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.title_txt)
    TextView titleTxt;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.tv_nickName)
    TextView tvNickName;
    @BindView(R.id.ll_nickName)
    LinearLayout llNickName;
    @BindView(R.id.ll_pwd)
    LinearLayout llPwd;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.ll_phone)
    LinearLayout llPhone;
    @BindView(R.id.imageView4)
    ImageView imageView4;
    @BindView(R.id.imageView5)
    ImageView imageView5;
    @BindView(R.id.tv_address)
    TextView tvAddress;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_sett);
        ButterKnife.bind(this);
    }

    @Override
    protected void initData() {
        titleTxt.setText("设置");
        initTabBar(toolbar);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        HttType = 3;
        Map<String,String> map = new HashMap<>();
        map.put("token",User_Token);
        mPreenter.fetch(map,true,HttpUtiles.Sett_Mess,"sett_mess"+User_Token);

    }

    @Override
    protected Presenter createPresenter() {
        return new Presenter(this);
    }

    @OnClick({R.id.ll_nickName, R.id.ll_pwd, R.id.ll_phone})
    public void onViewClicked(View view) {
        String loginType = sp.getString(SpUtiles.LoginType, "");
        switch (view.getId()) {
            case R.id.ll_nickName:
                if (loginType.equals("1")) {

                    startActivity(new Intent(ctx, ChangeNickNameActivity.class));
                }
                break;
            case R.id.ll_pwd:
                if (loginType.equals("1")) {

                    startActivity(new Intent(ctx, ChangePWdActivity.class));
                }
                break;
            case R.id.ll_phone:
                startActivity(new Intent(ctx, ChangePhoneActivity.class).putExtra("title", "修改绑定手机号"));
                break;
        }
    }
    private int HttType;
    @OnClick(R.id.tv_address)
    public void onViewClicked() {
        HttType = 1;
        Map<String,String> map = new HashMap<>();
        mPreenter.fetch(map,true, HttpUtiles.Register_Path,"Register_Path",true);
    }

    @Override
    public void showData(String s) {
        loading.dismiss();
        Gson gson = new Gson();
        if (HttType == 1) {

            getAddresss(s, gson);

        }else if(HttType == 3){
            UserInfoBean userInfoBean = gson.fromJson(s, UserInfoBean.class);
            if (userInfoBean.getCode() == 200) {
                UserInfoBean.BodyBean body = userInfoBean.getBody();
                tvNickName.setText(body.getNickname());
                tvPhone.setText(body.getPhone());
                tvAddress.setText(body.getPath());
            }

        }
        else{
            ResultBean resultBean = gson.fromJson(s, ResultBean.class);
            if (resultBean.getCode() == 200) {
                Toast.makeText(ctx, resultBean.getResult(), Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(ctx, resultBean.getResult(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void getAddresss(String s, Gson gson) {
        AddressSelete addressSelete = gson.fromJson(s, AddressSelete.class);
        if (addressSelete.getCode() == 200) {
            List<AddressSelete.BodyBean> body = addressSelete.getBody();
            SeleteAddressUtiles addressUtiles = new SeleteAddressUtiles(LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT,ctx,
                    body,llNickName);
            addressUtiles.setAddListener(new SeleteAddressUtiles.SeleteAddListener() {
                @Override
                public void getResult(String s,String path_id) {
                    wch(s);
                    tvAddress.setText(s);
                    HttType = 2;
                    Map<String,String> map = new HashMap<String, String>();
                    map.put("token", User_Token);
                    map.put("path_id",path_id);
                    mPreenter.fetch(map,false,HttpUtiles.UpData_address,"");
                }
            });
        }else{
            Toast.makeText(ctx, addressSelete.getResult(), Toast.LENGTH_SHORT).show();
        }
    }
}
