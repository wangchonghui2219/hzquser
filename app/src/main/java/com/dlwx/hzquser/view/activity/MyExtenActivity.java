package com.dlwx.hzquser.view.activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.dlwx.hzquser.R;
import com.dlwx.hzquser.adapter.MyExent;
import com.dlwx.hzquser.base.BaseActivity;
import com.dlwx.hzquser.base.MyApplication;
import com.dlwx.hzquser.model.bean.MyExtenBean;
import com.dlwx.hzquser.presenter.Presenter;
import com.dlwx.hzquser.utiles.HttpUtiles;
import com.dlwx.hzquser.utiles.ShareHttpUtiles;
import com.dlwx.hzquser.utiles.wechatpay.wxapi.WXShareUtiles;
import com.dlwx.hzquser.wxapi.CallBackListenerUtiles;
import com.dlwx.hzquser.wxapi.QQUtiles;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 我的推荐
 */
public class MyExtenActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.title_txt)
    TextView titleTxt;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.tv_exen_code)
    TextView tvExenCode;
    @BindView(R.id.lv_list)
    ListView lvList;
    @BindView(R.id.tv_share)
    TextView tvShare;
    private List<MyExtenBean.BodyBean.ListBean> list;
    private AlertDialog dialog_show;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_my_exten);
        ButterKnife.bind(this);
    }

    @Override
    protected void initData() {

        initTabBar(toolbar);
        titleTxt.setText("我的推荐");
        getData();
    }
    @Override
    protected void initListener() {
        CallBackListenerUtiles.setCallBackListener(new CallBackListenerUtiles.ShareCallBackListener() {
            @Override
            public void setComplete() {
                //分享成功

                new ShareHttpUtiles().UpResult(ctx);
            }

            @Override
            public void getUserInfo(String nickName, String imagePic, String openID) {

            }

            @Override
            public void Error(String mess) {

            }
        });
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
        MyExtenBean myExtenBean = gson.fromJson(s, MyExtenBean.class);
        if (myExtenBean.getCode() == 200) {
            MyExtenBean.BodyBean body = myExtenBean.getBody();
            tvExenCode.setText("我的推荐码:" + body.getExten_code());
            list = body.getList();
            MyExent adapter = new MyExent(ctx, list);
            lvList.setAdapter(adapter);
        } else {
            Toast.makeText(ctx, myExtenBean.getResult(), Toast.LENGTH_SHORT).show();
        }
    }

    private void getData() {
        Map<String, String> map = new HashMap<>();
        map.put("token", MyApplication.User_Token);
        mPreenter.fetch(map, true, HttpUtiles.My_Exten, "My_Exten", true);
    }
    @OnClick(R.id.tv_share)
    public void onViewClicked() {
        showShareDia();

    }
    private void showShareDia() {
        View view = LayoutInflater.from(ctx).inflate(R.layout.dia_share, null);
        ViewHolder vh = new ViewHolder(view);
        AlertDialog.Builder dialog = new AlertDialog.Builder(ctx);
        dialog.setView(view);

        dialog_show = dialog.show();
        vh.ll_QQ.setOnClickListener(this);
        vh.ll_Zone.setOnClickListener(this);
        vh.ll_pyq.setOnClickListener(this);
        vh.ll_Wx.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (TextUtils.isEmpty(MyApplication.User_Token)) {
            startActivity(new Intent(ctx,LoginActivity.class));
            return;
        }
        QQUtiles qqUtiles = new QQUtiles();
        WXShareUtiles shareUtiles = new WXShareUtiles(ctx);
        switch (v.getId()){
            case R.id.ll_QQ:
                dialog_show.dismiss();

                qqUtiles.shareQQ(ctx);
                break;
            case R.id.ll_Zone:
                qqUtiles.shareQzone(ctx);
                dialog_show.dismiss();
                break;
            case R.id.ll_pyq:
                dialog_show.dismiss();
                shareUtiles.share2weixin(0);
                break;
            case R.id.ll_Wx:
                dialog_show.dismiss();
                shareUtiles.share2weixin(1);
                break;

        }
    }
    private  class ViewHolder {
        public View rootView;
        public LinearLayout ll_QQ;
        public LinearLayout ll_Zone;
        public LinearLayout ll_pyq;
        public LinearLayout ll_Wx;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.ll_QQ = (LinearLayout) rootView.findViewById(R.id.ll_QQ);
            this.ll_Zone = (LinearLayout) rootView.findViewById(R.id.ll_Zone);
            this.ll_pyq = (LinearLayout) rootView.findViewById(R.id.ll_pyq);
            this.ll_Wx = (LinearLayout) rootView.findViewById(R.id.ll_Wx);
        }

    }
}
