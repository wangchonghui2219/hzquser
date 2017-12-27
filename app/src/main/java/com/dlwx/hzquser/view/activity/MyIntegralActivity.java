package com.dlwx.hzquser.view.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import android.widget.Toast;

import com.dlwx.hzquser.R;
import com.dlwx.hzquser.adapter.OrderAdapter;
import com.dlwx.hzquser.base.BaseActivity;
import com.dlwx.hzquser.base.MyApplication;
import com.dlwx.hzquser.model.bean.MyExtenBean;
import com.dlwx.hzquser.model.bean.MyInteBean;
import com.dlwx.hzquser.presenter.Presenter;
import com.dlwx.hzquser.utiles.HttpUtiles;
import com.dlwx.hzquser.view.fragment.IntegralExplainFragment;
import com.dlwx.hzquser.view.fragment.IntegralSoursFragment;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 我的积分
 */
public class MyIntegralActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.title_txt)
    TextView titleTxt;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.tv_money)
    TextView tvMoney;
    @BindView(R.id.tb_layout)
    TabLayout tbLayout;
    @BindView(R.id.vp_viewpage)
    ViewPager vpViewpage;
    List<String> tab_titles;
    List<Fragment> fragments;
    @Override
    protected void initView() {
        setContentView(R.layout.activity_my_integral);
        ButterKnife.bind(this);
    }

    @Override
    protected void initData() {
        initTabBar(toolbar);
        titleTxt.setText("我的积分");
        tab_titles = new ArrayList<>();
        tab_titles.add("积分来源");
        tab_titles.add("积分说明");
        fragments = new ArrayList<>();
        fragments.add(new IntegralSoursFragment());
        fragments.add(new IntegralExplainFragment());
        OrderAdapter orderAdapter = new OrderAdapter(getSupportFragmentManager(),
                tab_titles, fragments);
        vpViewpage.setAdapter(orderAdapter);
        tbLayout.setupWithViewPager(vpViewpage);
        tbLayout.setTabsFromPagerAdapter(orderAdapter);
    }

    @Override
    protected void initListener() {
        getInter();
    }



    @Override
    protected Presenter createPresenter() {
        return new Presenter(this);
    }

    private void getInter() {
        HttpType = 1;
        Map<String,String> map = new HashMap<>();
        map.put("token", MyApplication.User_Token);
        mPreenter.fetch(map,true, HttpUtiles.Get_Inte,"Get_Inte",true);
    }
    private int HttpType;
    @Override
    public void showData(String s) {
        loading.dismiss();
        wch(s);
        Gson gson = new Gson();
        if (HttpType == 1) {
            MyInteBean myInteBean = gson.fromJson(s, MyInteBean.class);
            if (myInteBean.getCode() == 200) {
                tvMoney.setText(myInteBean.getBody().getBonus_point());


            }else{
                Toast.makeText(ctx, myInteBean.getResult(), Toast.LENGTH_SHORT).show();
            }
        }else{

        }


    }
}
