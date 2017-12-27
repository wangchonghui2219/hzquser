package com.dlwx.hzquser.view.activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.dlwx.hzquser.R;
import com.dlwx.hzquser.base.BaseActivity;
import com.dlwx.hzquser.base.MyApplication;
import com.dlwx.hzquser.model.bean.CollectBean;
import com.dlwx.hzquser.model.bean.MenuDetails;
import com.dlwx.hzquser.presenter.Presenter;
import com.dlwx.hzquser.utiles.HttpUtiles;
import com.dlwx.hzquser.utiles.ShareHttpUtiles;
import com.dlwx.hzquser.utiles.SpUtiles;
import com.dlwx.hzquser.utiles.wechatpay.wxapi.WXShareUtiles;
import com.dlwx.hzquser.wxapi.CallBackListenerUtiles;
import com.dlwx.hzquser.wxapi.QQUtiles;
import com.google.gson.Gson;
import com.tencent.tauth.Tencent;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 菜单详情
 */
public class MenuDetailsActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.iv_share)
    ImageView ivShare;
    @BindView(R.id.iv_collect)
    ImageView ivCollect;
    @BindView(R.id.iv_shopcat)
    ImageView ivShopcat;
    @BindView(R.id.iv_pic)
    ImageView ivPic;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_type)
    TextView tvType;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.tv_sale)
    TextView tvSale;
    @BindView(R.id.tv_integral)
    TextView tvIntegral;
    @BindView(R.id.iv_details)
    ImageView ivDetails;
    @BindView(R.id.textView2)
            TextView  textView2;
    @BindView(R.id.ll_shop)
    LinearLayout llShop;
    @BindView(R.id.btn_jobshop)
    Button btnJobshop;
    @BindView(R.id.btn_buy)
    Button btnBuy;
    @BindView(R.id.tv_Preprice)
    TextView tvPreprice;
    @BindView(R.id.rl_prepric)
    RelativeLayout rlPreprice;
    private String menu_id;
    private MenuDetails.BodyBean body;
    private MenuDetails menuDetails;
    private Intent intent;
    private Bundle mBundle;
    private AlertDialog dialog_show;

    @Override
    protected void initView() {
        menu_id = getIntent().getStringExtra("menu_id");
        setContentView(R.layout.activity_menu_details);
        ButterKnife.bind(this);
    }

    @Override
    protected void initData() {
        initTabBar(toolbar);
        toolbar.setTitle("套餐详情");

        toolbar.setTitleTextColor(getResources().getColor(R.color.black_color));
        getDetail();
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

    @OnClick({R.id.iv_share, R.id.iv_collect, R.id.iv_shopcat, R.id.ll_shop, R.id.btn_jobshop, R.id.btn_buy})
    public void onViewClicked(View view) {

        switch (view.getId()) {
            case R.id.iv_share://分享
                if (TextUtils.isEmpty(MyApplication.User_Token)) {
                    startActivity(new Intent(ctx,LoginActivity.class));
                    return;
                }
                if (sp.getString(SpUtiles.Is_Path,"2").equals("2")) {
                    startActivity(new Intent(ctx, MessagebindingActivity.class));
                    return;
                }
                showShareDia();
                break;
            case R.id.iv_collect://收藏
                if (TextUtils.isEmpty(MyApplication.User_Token)) {
                    startActivity(new Intent(ctx,LoginActivity.class));
                    return;
                }
                if (sp.getString(SpUtiles.Is_Path,"2").equals("2")) {
                    startActivity(new Intent(ctx, MessagebindingActivity.class));
                    return;
                }
                collectProduct();
                break;
            case R.id.iv_shopcat://购物车
                if (TextUtils.isEmpty(MyApplication.User_Token)) {
                    startActivity(new Intent(ctx,LoginActivity.class));
                    return;
                }
                if (sp.getString(SpUtiles.Is_Path,"2").equals("2")) {
                    startActivity(new Intent(ctx, MessagebindingActivity.class));
                    return;
                }
                startActivity(new Intent(ctx,ShopCatActivity.class));
                break;
            case R.id.ll_shop://店铺
                startActivity(new Intent(ctx,ClassifyActivity.class));

                break;
            case R.id.btn_jobshop://加入购物车
                if (TextUtils.isEmpty(MyApplication.User_Token)) {
                    startActivity(new Intent(ctx,LoginActivity.class));
                    return;
                }
                if (sp.getString(SpUtiles.Is_Path,"2").equals("2")) {
                    startActivity(new Intent(ctx, MessagebindingActivity.class));
                    return;
                }
                intent = new Intent(ctx, SeleteClassActivity.class);
//                intent.putExtra("xuancan",body.getXuancan());
                Bundle mBundle = new Bundle();
                mBundle.putSerializable("xuancan", menuDetails);
                intent.putExtras(mBundle);
                intent.putExtra("type", "0");
                startActivity(intent);
                break;
            case R.id.btn_buy://购买
                if (TextUtils.isEmpty(MyApplication.User_Token)) {
                    startActivity(new Intent(ctx,LoginActivity.class));
                    return;
                }
                if (sp.getString(SpUtiles.Is_Path,"2").equals("2")) {
                    startActivity(new Intent(ctx, MessagebindingActivity.class));
                    return;
                }
                intent = new Intent(ctx, SeleteClassActivity.class);
//                intent.putExtra("xuancan",body.getXuancan());
                mBundle = new Bundle();
                mBundle.putSerializable("xuancan", menuDetails);
                intent.putExtra("type", "1");
                intent.putExtras(mBundle);
                startActivity(intent);
                break;
        }
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

    /**
     * 收藏商品
     */
    private void collectProduct() {
        HttpType = 2;
        Map<String, String> map = new HashMap<>();
        map.put("token", MyApplication.User_Token);
        map.put("menu_id", menu_id);
        mPreenter.fetch(map, true, HttpUtiles.Collect_Product, "",false);
    }

    private int HttpType;

    /**
     * 获得商品详情信息
     */
    private void getDetail() {
        HttpType = 1;
        Map<String, String> map = new HashMap<>();
        map.put("menu_id", menu_id);
        mPreenter.fetch(map, true, HttpUtiles.Menu_Details, "Menu_Details" + menu_id);
    }

    @Override
    public void showData(String s) {
        loading.dismiss();
        wch(s);
        Gson gson = new Gson();
        if (HttpType == 2) {

            CollectBean collectBean = gson.fromJson(s, CollectBean.class);
            if (collectBean.getCode() == 200) {
                int collect = collectBean.getBody().getCollect();
                if (collect == 0) {
                    Glide.with(ctx).load(R.mipmap.icon_cpxqsc).into(ivCollect);
                } else {
                    Glide.with(ctx).load(R.mipmap.icon_cpxqsca).into(ivCollect);
                }
            } else {
            }

        } else {

            menuDetails = gson.fromJson(s, MenuDetails.class);
            if (menuDetails.getCode() == 200) {
                body = menuDetails.getBody();
                tvName.setText(body.getMenu_name());
                tvType.setText(body.getType_name());
                tvPrice.setText("￥" + body.getPrice());
                tvSale.setText("已售" + body.getSale());
                tvIntegral.setText("可返积分" + body.getBonus_point());
                textView2.setText(body.getMenu_desc());
                Glide.with(ctx).load(body.getPath()).into(ivPic);
                Glide.with(ctx).load(body.getDesc_path()).into(ivDetails);
                int collect = body.getCollect();
                if (collect == 0) {
                    Glide.with(ctx).load(R.mipmap.icon_cpxqsc).into(ivCollect);
                } else {
                    Glide.with(ctx).load(R.mipmap.icon_cpxqsca).into(ivCollect);
                }
                if (body.getPre_price() == 0) {
                   rlPreprice.setVisibility(View.GONE);
                    tvPreprice.setText("￥"+body.getPrice());
                    tvPreprice.setTextColor(ctx.getResources().getColor(R.color.orange));
                }else{
                    rlPreprice.setVisibility(View.VISIBLE);
                    tvPreprice.setText("￥"+body.getPre_price());
                    tvPrice.setText("￥"+body.getPrice());
                    tvPrice.setTextColor(ctx.getResources().getColor(R.color.text_color_gary));
                    tvPreprice.setTextColor(ctx.getResources().getColor(R.color.orange));
                }
            } else {
                Toast.makeText(ctx, menuDetails.getResult(), Toast.LENGTH_SHORT).show();
            }
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
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Tencent.onActivityResultData(requestCode, resultCode, data, new QQUtiles().uiListener);

    }
}
