package com.dlwx.hzquser.view.activity;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dlwx.hzquser.R;
import com.dlwx.hzquser.base.BaseActivity;
import com.dlwx.hzquser.model.bean.AddressSelete;
import com.dlwx.hzquser.model.bean.LoginBean;
import com.dlwx.hzquser.model.bean.ResultBean;
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
 * 信息绑定
 */
public class MessagebindingActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.title_txt)
    TextView titleTxt;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.imageView4)
    ImageView imageView4;
    @BindView(R.id.ll_phone)
    LinearLayout llPhone;
    @BindView(R.id.imageView5)
    ImageView imageView5;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.btn_submit)
    Button btn_submit;
    private LoginBean.BodyBean body;
    private String loginType;

    @Override
    protected void initView() {
        body = (LoginBean.BodyBean) getIntent().getSerializableExtra("login");
        loginType = getIntent().getStringExtra("loginType");
        setContentView(R.layout.activity_messagebinding);
        ButterKnife.bind(this);
    }

    @Override
    protected void initData() {
        titleTxt.setText("完善个人信息");
    }

    @Override
    protected void initListener() {

    }
    @Override
    protected void onResume() {
        super.onResume();
        String phone = sp.getString(SpUtiles.Phone, "");
        tvPhone.setText(phone);
    }
    @Override
    protected Presenter createPresenter() {
        return new Presenter(this);
    }

    private int HttType;
    @OnClick({R.id.ll_phone, R.id.tv_address,R.id.btn_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_phone:
                startActivity(new Intent(ctx, ChangePhoneActivity.class).putExtra("title","绑定手机号"));
                break;
            case R.id.tv_address:
                HttType = 1;
                Map<String,String> map = new HashMap<>();
                mPreenter.fetch(map,true, HttpUtiles.Register_Path,"Register_Path",true);
                break;
            case R.id.btn_submit:
                String address = tvAddress.getText() + "";
                if (TextUtils.isEmpty(address)) {
                    Toast.makeText(ctx, "请完成您的地址选择", Toast.LENGTH_SHORT).show();
                    vibrator.vibrate(50);
                    return;
                }
                String phone = tvPhone.getText() + "";
                if (TextUtils.isEmpty(phone)) {
                    Toast.makeText(ctx, "请输入绑定手机号", Toast.LENGTH_SHORT).show();
                    vibrator.vibrate(50);
                    return;
                }

                finish();
                break;
        }
    }

    @Override
    public void showData(String s) {
        loading.dismiss();
        Gson gson = new Gson();
        if (HttType == 1) {

            getAddresss(s, gson);

        }else{
            ResultBean resultBean = gson.fromJson(s, ResultBean.class);
            if (resultBean.getCode() == 200) {
                sp.edit().putString(SpUtiles.Is_Path,"1").commit();
                Toast.makeText(ctx, resultBean.getResult(), Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(ctx, resultBean.getResult(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void getAddresss(String s, Gson gson) {
        AddressSelete addressSelete = gson.fromJson(s, AddressSelete.class);
        if (addressSelete.getCode() == 200) {
            final List<AddressSelete.BodyBean> bodys = addressSelete.getBody();
            SeleteAddressUtiles addressUtiles = new SeleteAddressUtiles(LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT,ctx,
                    bodys,titleTxt);
            addressUtiles.setAddListener(new SeleteAddressUtiles.SeleteAddListener() {
                @Override
                public void getResult(String s,String path_id) {
                    wch(s);
                    tvAddress.setText(s);
                    HttType = 2;
                    Map<String,String> map = new HashMap<String, String>();
                    map.put("token",User_Token);
                    map.put("path_id",path_id);
                    mPreenter.fetch(map,false,HttpUtiles.UpData_address,"");
                }
            });
        }else{
            Toast.makeText(ctx, addressSelete.getResult(), Toast.LENGTH_SHORT).show();
        }
    }
}
