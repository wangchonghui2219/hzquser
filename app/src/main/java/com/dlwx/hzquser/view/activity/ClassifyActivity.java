package com.dlwx.hzquser.view.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.dlwx.hzquser.R;
import com.dlwx.hzquser.adapter.OrderAdapter;
import com.dlwx.hzquser.base.BaseActivity;
import com.dlwx.hzquser.model.bean.ShopMesBean;
import com.dlwx.hzquser.presenter.Presenter;
import com.dlwx.hzquser.utiles.HttpUtiles;
import com.dlwx.hzquser.view.fragment.ClassifyMerchFragment;
import com.dlwx.hzquser.view.fragment.ClassifyProductFragment;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 今日特价等分类列表
 */
public class ClassifyActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tb_layout)
    TabLayout tbLayout;
    @BindView(R.id.vp_viewpage)
    ViewPager vpViewpage;
    List<String> tab_titles;
    List<Fragment> fragments;
    @BindView(R.id.iv_head)
    ImageView ivHead;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    private ClassifyMerchFragment classifyMerchFragment;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_classify);
        ButterKnife.bind(this);
    }

    @Override
    protected void initData() {
        initTabBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.icon_jiantoubaise);
        tab_titles = new ArrayList<>();
        tab_titles.add("商品");
        tab_titles.add("商家");
        fragments = new ArrayList<>();
        classifyMerchFragment = new ClassifyMerchFragment();
        fragments.add(new ClassifyProductFragment());
        fragments.add(classifyMerchFragment);

        OrderAdapter orderAdapter = new OrderAdapter(getSupportFragmentManager(),
                tab_titles, fragments);
        vpViewpage.setAdapter(orderAdapter);
        tbLayout.setupWithViewPager(vpViewpage);
        tbLayout.setTabsFromPagerAdapter(orderAdapter);
        getShopMes();

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
        wch(s);
        Gson gson = new Gson();
        ShopMesBean shopMesBean = gson.fromJson(s, ShopMesBean.class);
        if (shopMesBean.getCode() == 200) {
            ShopMesBean.BodyBean body = shopMesBean.getBody();
            Glide.with(ctx).load(body.getShop_logo()).into(ivHead);
            tvName.setText(body.getShop_name());
            tvPhone.setText("电话："+body.getShop_phone());
            tvAddress.setText("地址："+body.getShop_address());
            classifyMerchFragment.setMess(body.getDesc_img(),body.getShop_desc(),body.getShop_name());
        } else {
            Toast.makeText(ctx, shopMesBean.getResult(), Toast.LENGTH_SHORT).show();
        }
    }

    private void getShopMes() {
        Map<String, String> map = new HashMap<>();
        mPreenter.fetch(map, true, HttpUtiles.Seller_Shop, "Seller_Shop");
    }


}
