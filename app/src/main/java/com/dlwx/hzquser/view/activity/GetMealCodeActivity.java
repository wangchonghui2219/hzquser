package com.dlwx.hzquser.view.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.dlwx.hzquser.R;
import com.dlwx.hzquser.base.BaseActivity;
import com.dlwx.hzquser.model.bean.LookmealCodeBean;
import com.dlwx.hzquser.presenter.Presenter;
import com.dlwx.hzquser.utiles.wechatpay.wxapi.WXShareUtiles;
import com.dlwx.hzquser.wxapi.QQUtiles;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.FileCallback;
import com.lzy.okgo.model.Response;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 领餐码
 */
public class GetMealCodeActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.title_txt)
    TextView titleTxt;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.tv_order_no)
    TextView tvOrderNo;
    @BindView(R.id.tv_mmeal_code)
    TextView tvMmealCode;
    @BindView(R.id.iv_pic)
    ImageView ivPic;
    @BindView(R.id.iv_shareWX)
    ImageView ivShareWX;
    @BindView(R.id.iv_shareQQ)
    ImageView ivShareQQ;
    @BindView(R.id.ll_share)
    LinearLayout llShare;
    private LookmealCodeBean.BodyBean body;

    @Override
    protected void initView() {
        Intent intent = getIntent();
        body = (LookmealCodeBean.BodyBean) intent.getSerializableExtra("body");
        setContentView(R.layout.activity_get_meal_code);
        ButterKnife.bind(this);
    }
    private Bitmap codeBitmap;
    private File codefile;
    @Override
    protected void initData() {
        initTabBar(toolbar);
        titleTxt.setText("领餐码");
        tvOrderNo.setText("订单号："+body.getOrder_no());
        tvMmealCode.setText("领餐码："+body.getPick_code());
        Glide.with(ctx).asBitmap().load(body.getPick_img()).into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
                codeBitmap = resource;
//                ivPic.setImageBitmap(resource);
                OkGo.<File>get(body.getPick_img())
                        .execute(new FileCallback() {
                            @Override
                            public void onSuccess(Response<File> response) {
                                File file = response.body();
                                codefile = file;
                                wch(file);
                                Glide.with(ctx).load(file).into(ivPic);
                            }
                        });
            }
        });
        String pick_type = body.getPick_type();
        if (pick_type.equals("1")) {//自领
            llShare.setVisibility(View.GONE);
        }else{
            llShare.setVisibility(View.VISIBLE);
        }
    }


    @Override
    protected void initListener() {

    }

    @Override
    protected Presenter createPresenter() {
        return new Presenter(this);
    }


    @OnClick(R.id.iv_shareWX)
    public void onIvShareWXClicked() {

        WXShareUtiles wxShareUtiles = new WXShareUtiles(ctx);
        wxShareUtiles.wxImgShare(1,codeBitmap);
    }

    @OnClick(R.id.iv_shareQQ)
    public void onIvShareQQClicked() {
        QQUtiles qqUtiles = new QQUtiles();
        qqUtiles.shareImageQQ(ctx,codefile.getAbsolutePath());
    }
}
