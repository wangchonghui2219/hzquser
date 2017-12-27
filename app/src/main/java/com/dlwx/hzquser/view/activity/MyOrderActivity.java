package com.dlwx.hzquser.view.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
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
import com.dlwx.hzquser.interfac.publicInterface;
import com.dlwx.hzquser.model.bean.LookmealCodeBean;
import com.dlwx.hzquser.model.bean.MyOrderBaen;
import com.dlwx.hzquser.model.bean.MyOrderItemBean;
import com.dlwx.hzquser.model.bean.ResultBean;
import com.dlwx.hzquser.presenter.Presenter;
import com.dlwx.hzquser.utiles.HttpUtiles;
import com.dlwx.hzquser.view.fragment.AllOrderFragment;
import com.dlwx.hzquser.view.fragment.LeadMealFragment;
import com.dlwx.hzquser.view.fragment.ObligationFragment;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 我的订单
 */
public class MyOrderActivity extends BaseActivity implements publicInterface.OrderDealListener{
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.title_txt)
    TextView titleTxt;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.tb_layout)
    TabLayout tbLayout;
    @BindView(R.id.vp_viewpage)
    ViewPager vpViewpage;
    List<String> tab_titles;
    List<Fragment> fragments;
    private AllOrderFragment allOrderFragment;
    private ObligationFragment obligationFragment;
    private LeadMealFragment leadMealFragment;
    private List<MyOrderBaen.BodyBean.ListBean> list;
    private List<MyOrderBaen.BodyBean.List2Bean> list2;
    private List<MyOrderBaen.BodyBean.List3Bean> list3;
    private List<MyOrderItemBean.OrdersBean> ordersBeens;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_my_order);
        ButterKnife.bind(this);
        initTabBar(toolbar);
        titleTxt.setText("我的订单");
    }

    @Override
    protected void initData() {
        tab_titles = new ArrayList<>();
        tab_titles.add("全部");
        tab_titles.add("待付款");
        tab_titles.add("待领餐");
        fragments = new ArrayList<>();
        allOrderFragment = new AllOrderFragment();
        obligationFragment = new ObligationFragment();
        leadMealFragment = new LeadMealFragment();
        fragments.add(allOrderFragment);
        fragments.add(obligationFragment);
        fragments.add(leadMealFragment);
        OrderAdapter orderAdapter = new OrderAdapter(getSupportFragmentManager(), tab_titles, fragments);
        vpViewpage.setAdapter(orderAdapter);
        tbLayout.setupWithViewPager(vpViewpage);
        tbLayout.setTabsFromPagerAdapter(orderAdapter);
        allOrderFragment.setDealOrderListener(this);
        obligationFragment.setDealOrderListener(this);
        leadMealFragment.setDealOrderListener(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        getData();
    }

    @Override
    protected void initListener() {
            vpViewpage.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {
                        setData(position);
                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });
    }
    @Override
    protected Presenter createPresenter() {
        return new Presenter(this);
    }
    private void getData() {
        HttpType = 0;
        Map<String,String> map = new HashMap<>();
        map.put("token", MyApplication.User_Token);
        wch(MyApplication.User_Token);
        mPreenter.fetch(map,true, HttpUtiles.AllOrder,"allorder"+MyApplication.User_Token,true);
    }

    @Override
    public void showData(String s) {
        loading.dismiss();
        wch(s);
        if (HttpType == 0) {


        Gson gson =  new Gson();
        MyOrderBaen myOrderBaen = gson.fromJson(s, MyOrderBaen.class);
        if (myOrderBaen.getCode() == 200) {
            MyOrderBaen.BodyBean body = myOrderBaen.getBody();
            list = body.getList();
            list2 = body.getList2();
            list3 = body.getList3();

            ordersBeens = new ArrayList<>();

                for (int i = 0; i < list.size(); i++) {
                    MyOrderItemBean.OrdersBean ordersBean = new MyOrderItemBean.OrdersBean();
                    MyOrderBaen.BodyBean.ListBean listBean = list.get(i);
                    ordersBean.setBonus_point(listBean.getBonus_point());
                    ordersBean.setMeal_time(listBean.getMeal_time());
                    ordersBean.setOrder_id(listBean.getOrder_id());
                    ordersBean.setOrder_no(listBean.getOrder_no());
                    ordersBean.setStatus(listBean.getStatus());
                    ordersBean.setTotal_num(listBean.getTotal_num());
                    ordersBean.setTotal_price(listBean.getTotal_price());
                    ordersBean.setUpdate_time(listBean.getUpdate_time());
                    List<MyOrderBaen.BodyBean.ListBean.DescBean> desc = listBean.getDesc();
                    List<MyOrderItemBean.OrdersBean.DescBean> desc1 = new ArrayList<>();
                    for (int j = 0; j <desc.size() ; j++) {
                        MyOrderItemBean.OrdersBean.DescBean descBean = new MyOrderItemBean.OrdersBean.DescBean();
                        MyOrderBaen.BodyBean.ListBean.DescBean descBean1 = desc.get(j);
                        descBean.setGive_bonus_point(descBean1.getGive_bonus_point());
                        descBean.setMenu_id(descBean1.getMenu_id());
                        descBean.setMenu_name(descBean1.getMenu_name());
                        descBean.setNum(descBean1.getNum());
                        descBean.setOld_price(descBean1.getOld_price());
                        descBean.setPath(descBean1.getPath());
                        descBean.setPrice(descBean1.getPrice());
                        descBean.setType_id(descBean1.getType_id());
                        descBean.setXuancan(descBean1.getXuancan());
                        desc1.add(descBean);
                    }
                    ordersBean.setDesc(desc1);
                    ordersBeens.add(ordersBean);
                }
                allOrderFragment.setData(ordersBeens);
        }else{
            Toast.makeText(ctx, myOrderBaen.getResult(), Toast.LENGTH_SHORT).show();
        }
        }else if (HttpType == 1) {
            closeResult(s);
        }else if (HttpType == 2) {//自领
                jsonMakeCode(s);
        }
    }
    private void closeResult(String s) {
        Gson gson = new Gson();
        ResultBean resultBean = gson.fromJson(s, ResultBean.class);
        if (resultBean.getCode() == 200) {
            ordersBeens.remove(pos);
            list.remove(pos);
            allOrderFragment.setData(ordersBeens);
        }else{
            Toast.makeText(ctx, resultBean.getResult(), Toast.LENGTH_SHORT).show();

        }
    }

    private void setData(int position) {
        ordersBeens = new ArrayList<>();
        if (position == 0) {
            for (int i = 0; i < list.size(); i++) {
                MyOrderItemBean.OrdersBean ordersBean = new MyOrderItemBean.OrdersBean();
                MyOrderBaen.BodyBean.ListBean listBean = list.get(i);
                ordersBean.setBonus_point(listBean.getBonus_point());
                ordersBean.setMeal_time(listBean.getMeal_time());
                ordersBean.setOrder_id(listBean.getOrder_id());
                ordersBean.setOrder_no(listBean.getOrder_no());
                ordersBean.setStatus(listBean.getStatus());
                ordersBean.setTotal_num(listBean.getTotal_num());
                ordersBean.setTotal_price(listBean.getTotal_price());
                ordersBean.setUpdate_time(listBean.getUpdate_time());

                List<MyOrderBaen.BodyBean.ListBean.DescBean> desc = listBean.getDesc();
                List<MyOrderItemBean.OrdersBean.DescBean> desc1 = new ArrayList<>();
                for (int j = 0; j <desc.size() ; j++) {
                    MyOrderItemBean.OrdersBean.DescBean descBean = new MyOrderItemBean.OrdersBean.DescBean();
                    MyOrderBaen.BodyBean.ListBean.DescBean descBean1 = desc.get(j);
                    descBean.setGive_bonus_point(descBean1.getGive_bonus_point());
                    descBean.setMenu_id(descBean1.getMenu_id());
                    descBean.setMenu_name(descBean1.getMenu_name());
                    descBean.setNum(descBean1.getNum());
                    descBean.setOld_price(descBean1.getOld_price());
                    descBean.setPath(descBean1.getPath());
                    descBean.setPrice(descBean1.getPrice());
                    descBean.setType_id(descBean1.getType_id());
                    descBean.setXuancan(descBean1.getXuancan());
                    desc1.add(descBean);
                }
                ordersBean.setDesc(desc1);
                ordersBeens.add(ordersBean);

            }
            allOrderFragment.setData(ordersBeens);
        }else if (position == 1) {

            for (int i = 0; i < list2.size(); i++) {

                MyOrderItemBean.OrdersBean ordersBean = new MyOrderItemBean.OrdersBean();
                MyOrderBaen.BodyBean.List2Bean list2Bean = list2.get(i);
                ordersBean.setBonus_point(list2Bean.getBonus_point());
                ordersBean.setMeal_time(list2Bean.getMeal_time());
                ordersBean.setOrder_id(list2Bean.getOrder_id());
                ordersBean.setOrder_no(list2Bean.getOrder_no());
                ordersBean.setStatus(list2Bean.getStatus());
                ordersBean.setTotal_num(list2Bean.getTotal_num());
                ordersBean.setTotal_price(list2Bean.getTotal_price());
                ordersBean.setUpdate_time(list2Bean.getUpdate_time());
                List<MyOrderBaen.BodyBean.List2Bean.DescBeanX> desc = list2Bean.getDesc();
                List<MyOrderItemBean.OrdersBean.DescBean> desc1 = new ArrayList<>();
                for (int j = 0; j <desc.size() ; j++) {
                    MyOrderItemBean.OrdersBean.DescBean descBean = new MyOrderItemBean.OrdersBean.DescBean();
                    MyOrderBaen.BodyBean.List2Bean.DescBeanX descBean1 = desc.get(j);
                    descBean.setGive_bonus_point(descBean1.getGive_bonus_point());
                    descBean.setMenu_id(descBean1.getMenu_id());
                    descBean.setMenu_name(descBean1.getMenu_name());
                    descBean.setNum(descBean1.getNum());
                    descBean.setOld_price(descBean1.getOld_price());
                    descBean.setPath(descBean1.getPath());
                    descBean.setPrice(descBean1.getPrice());
                    descBean.setType_id(descBean1.getType_id());
                    descBean.setXuancan(descBean1.getXuancan());
                    desc1.add(descBean);
                }
                ordersBean.setDesc(desc1);
                ordersBeens.add(ordersBean);

            }
            obligationFragment.setData(ordersBeens);

        }else{
            for (int i = 0; i < list3.size(); i++) {

                MyOrderItemBean.OrdersBean ordersBean = new MyOrderItemBean.OrdersBean();
                MyOrderBaen.BodyBean.List3Bean list3Bean = list3.get(i);
                ordersBean.setBonus_point(list3Bean.getBonus_point());
                ordersBean.setMeal_time(list3Bean.getMeal_time());
                ordersBean.setOrder_id(list3Bean.getOrder_id());
                ordersBean.setOrder_no(list3Bean.getOrder_no());
                ordersBean.setStatus(list3Bean.getStatus());
                ordersBean.setTotal_num(list3Bean.getTotal_num());
                ordersBean.setTotal_price(list3Bean.getTotal_price());
                ordersBean.setUpdate_time(list3Bean.getUpdate_time());
                List<MyOrderBaen.BodyBean.List3Bean.DescBeanXX> desc = list3Bean.getDesc();
                List<MyOrderItemBean.OrdersBean.DescBean> desc1 = new ArrayList<>();
                for (int j = 0; j <desc.size() ; j++) {
                    MyOrderItemBean.OrdersBean.DescBean descBean = new MyOrderItemBean.OrdersBean.DescBean();
                    MyOrderBaen.BodyBean.List3Bean.DescBeanXX descBean1 = desc.get(j);
                    descBean.setGive_bonus_point(descBean1.getGive_bonus_point());
                    descBean.setMenu_id(descBean1.getMenu_id());
                    descBean.setMenu_name(descBean1.getMenu_name());
                    descBean.setNum(descBean1.getNum());
                    descBean.setOld_price(descBean1.getOld_price());
                    descBean.setPath(descBean1.getPath());
                    descBean.setPrice(descBean1.getPrice());
                    descBean.setType_id(descBean1.getType_id());
                    descBean.setXuancan(descBean1.getXuancan());
                    desc1.add(descBean);
                }
                ordersBean.setDesc(desc1);
                ordersBeens.add(ordersBean);

            }
            leadMealFragment.setData(ordersBeens);

        }


    }
    private int HttpType;
    private int pos;
    @Override
    public void setCloseListener(int position, String order_id) {
        HttpType = 1;
        pos = position;
        Map<String,String> map = new HashMap<String, String>();
        map.put("token", MyApplication.User_Token);
        map.put("order_id",order_id);
        mPreenter.fetch(map,false, HttpUtiles.Delete_Order,"");
    }

    @Override
    public void setPayOrOtherListener(int position, String order_no,String order_id, int status) {
        wch(status);
        if (status == 1) {//待付款
            wch(order_id);
            Intent intent = new Intent(ctx, PayActivity.class);
            intent.putExtra("orderNo",order_no);
            startActivity(intent);
        }else if (status == 2) {//待领餐
            showDaia(position);
        }else if (status == 6) {
            getMycode(order_id);
        }
    }

    private void showDaia(final int position) {
       new AlertDialog.Builder(ctx)
               .setTitle("选择自领或代领")
               .setMessage("请根据您的需求进行选择")

               .setPositiveButton("代领", new DialogInterface.OnClickListener() {
           @Override
           public void onClick(DialogInterface dialog, int which) {
                   Intent intent = new Intent(ctx,ReplaceMealActivity.class);
                   intent.putExtra("order_id",ordersBeens.get(position).getOrder_id());
                   intent.putExtra("pick_type","2");
                   intent.putExtra("orderno",ordersBeens.get(position).getOrder_no());
                   intent.putExtra("meal_time",ordersBeens.get(position).getUpdate_time());
                   intent.putExtra("status",ordersBeens.get(position).getStatus());
                   startActivity(intent);
           }
       }).setNegativeButton("自领", new DialogInterface.OnClickListener() {
           @Override
           public void onClick(DialogInterface dialog, int which) {

               getCode(ordersBeens.get(position).getOrder_id());

           }
       }).show();

    }

    /**
     * 生成领餐码
     */
    private void getCode(String order_id) {
        HttpType = 2;
        Map<String, String> map = new HashMap<>();
        map.put("token", MyApplication.User_Token);
        map.put("order_id", order_id);
        map.put("pick_type", "1");
        mPreenter.fetch(map, false, HttpUtiles.Mack_PickUp, "");
    }
    /**
     * 生成二维码
     *
     * @param s
     */
    private void jsonMakeCode(String s) {
        Gson gson = new Gson();
        LookmealCodeBean makeCodeBean = gson.fromJson(s, LookmealCodeBean.class);
        if (makeCodeBean.getCode() == 200) {
            LookmealCodeBean.BodyBean body = makeCodeBean.getBody();

                Intent intent = new Intent(ctx,GetMealCodeActivity.class);
                intent.putExtra("body",body);
                startActivity(intent);

        } else {
            Toast.makeText(ctx, makeCodeBean.getResult(), Toast.LENGTH_SHORT).show();
        }
    }
    /**
     * 获取领餐码
     */
    private void getMycode(String order_id) {
        HttpType = 2;
        Map<String, String> map = new HashMap<>();
        map.put("token", MyApplication.User_Token);
        map.put("order_id", order_id);
        mPreenter.fetch(map, false, HttpUtiles.Look_pickUp, "");
    }
}
