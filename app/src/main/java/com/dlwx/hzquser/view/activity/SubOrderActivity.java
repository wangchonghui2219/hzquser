package com.dlwx.hzquser.view.activity;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.dlwx.hzquser.R;
import com.dlwx.hzquser.adapter.OrderTImeAdapter;
import com.dlwx.hzquser.adapter.SubOrderAdapter;
import com.dlwx.hzquser.base.BaseActivity;
import com.dlwx.hzquser.base.MyApplication;
import com.dlwx.hzquser.model.bean.AffOrderBean;
import com.dlwx.hzquser.model.bean.GetorderNumber;
import com.dlwx.hzquser.model.bean.OrderTimeBean;
import com.dlwx.hzquser.model.bean.ResultBean;
import com.dlwx.hzquser.model.bean.SubOrderBean;
import com.dlwx.hzquser.model.bean.SubOrderDataBean;
import com.dlwx.hzquser.presenter.Presenter;
import com.dlwx.hzquser.utiles.HttpUtiles;
import com.dlwx.hzquser.view.MyGridView;
import com.dlwx.hzquser.view.MyListView;
import com.google.gson.Gson;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 提交订单
 */
public class SubOrderActivity extends BaseActivity implements AdapterView.OnItemClickListener{

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.title_txt)
    TextView titleTxt;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.lv_list)
    MyListView lvList;
    @BindView(R.id.gv_list)
    MyGridView gvList;
    @BindView(R.id.cb_check)
    CheckBox cbCheck;
    @BindView(R.id.btn_submit)
    Button btnSubmit;
    @BindView(R.id.tv_totalPrice)
    TextView tv_totalPrice;
    @BindView(R.id.tv_integral)
    TextView tv_integral;
    private double arriveInteg;
    private String affOrder;
    private List<OrderTimeBean.BodyBean.TimeSlotBean> time_slot;
    private OrderTImeAdapter adapter;
    private String meal_time;//提交数据时的用餐时间
    private double totalPrice;
    private List<SubOrderBean> orderBeen;

    @Override
    protected void initView() {
        affOrder = getIntent().getStringExtra("affOrder");
        setContentView(R.layout.activity_sub_order);
        ButterKnife.bind(this);
    }

    @Override
    protected void initData() {
        initTabBar(toolbar);
        titleTxt.setText("提交订单");
        Gson gson = new Gson();
        AffOrderBean affOrderBean = gson.fromJson(affOrder, AffOrderBean.class);
        orderBeen = affOrderBean.getOrderBeen();
        SubOrderAdapter adapter = new SubOrderAdapter(ctx, orderBeen);
        lvList.setAdapter(adapter);
        getTime();

        for (int i = 0; i < orderBeen.size(); i++) {
            SubOrderBean orderBean = orderBeen.get(i);
                totalPrice += orderBean.getNum()*orderBean.getPrice();
            wch(totalPrice);
        }
        DecimalFormat df = new DecimalFormat("#.00");
        totalPrice = Double.parseDouble(df.format(totalPrice));
        tv_totalPrice.setText("￥"+totalPrice);
    }


    private boolean isCheck;
    @Override
    protected void initListener() {
        gvList.setOnItemClickListener(this);
        cbCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
               isCheck = isChecked;
            }
        });
    }

    @Override
    protected Presenter createPresenter() {
        return new Presenter(this);
    }
    @OnClick(R.id.btn_submit)
    public void onViewClicked() {
        HttpType = 3;
           pay();


    }

    private void pay() {
        SubOrderDataBean orderDataBean = new SubOrderDataBean();
        List<SubOrderDataBean.ConfirmBean> confirm = new ArrayList<>();

        orderDataBean.setBonus_point(arriveInteg);
        orderDataBean.setMeal_time(meal_time);
        orderDataBean.setTotal_price(totalPrice);

        for (int i = 0; i < orderBeen.size(); i++) {
            SubOrderDataBean.ConfirmBean confirmBean = new SubOrderDataBean.ConfirmBean();
            SubOrderBean suorderBean = orderBeen.get(i);
            confirmBean.setPrice(suorderBean.getPrice());
            confirmBean.setBonus_point(suorderBean.getBonus_point());
            confirmBean.setHun(suorderBean.getHunid());
            confirmBean.setNum(suorderBean.getNum());
            confirmBean.setCart_id(suorderBean.getCart_id());
            confirmBean.setQita(suorderBean.getQita());
            confirmBean.setSu(suorderBean.getSuid());
            confirmBean.setSpec_id(suorderBean.getSpec_id());
            confirmBean.setMenu_id(suorderBean.getMenu_id());
            confirmBean.setBonus_point(suorderBean.getBonus_point());
            confirm.add(confirmBean);
        }
        orderDataBean.setConfirm(confirm);
        String orderJson = new Gson().toJson(orderDataBean);

        wch(orderJson);
        Map<String,String> map = new HashMap<>();
        map.put("token", MyApplication.User_Token);
        map.put("content",orderJson);
        mPreenter.fetch(map,false, HttpUtiles.Up_Order,"");
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        OrderTimeBean.BodyBean.TimeSlotBean timeSlotBean = time_slot.get(position);
        meal_time = timeSlotBean.getMeal_time();
        adapter.setSelete(position);
    }

    @Override
    public void showData(String s) {
        loading.dismiss();
        wch(s);

        if (HttpType == 1) {
            jsonTime(s);
        }else if (HttpType == 2) {
            Gson gson = new Gson();
            ResultBean resultBean = gson.fromJson(s, ResultBean.class);
            if (resultBean.getCode() == 200) {
                Toast.makeText(ctx, resultBean.getResult(), Toast.LENGTH_SHORT).show();
                startActivity(new Intent(ctx,MyOrderActivity.class));
            }else{
                Toast.makeText(ctx, resultBean.getResult(), Toast.LENGTH_SHORT).show();
            }
        } else{
            getOrder(s);
        }
    }

    private void getOrder(String s) {
        Gson gson = new Gson();
        GetorderNumber getorderNumber = gson.fromJson(s, GetorderNumber.class);
        if (getorderNumber.getCode() == 200) {
            String order_no = getorderNumber.getBody().getOrder_no();

            if (arriveInteg == totalPrice) {
                HttpType = 2;
                Map<String,String> map = new HashMap<>();
                map.put("token", MyApplication.User_Token);
                map.put("pay_type","4");
                map.put("order_no",order_no);
                mPreenter.fetch(map,false, HttpUtiles.Pay,"");
            }else{
                Intent intent = new Intent(ctx,PayActivity.class);
                intent.putExtra("orderNo",order_no);
                startActivity(intent);
                finish();
            }

        }else{
            vibrator.vibrate(50);
            Toast.makeText(ctx, getorderNumber.getResult(), Toast.LENGTH_SHORT).show();
        }
    }

    private void jsonTime(String s) {
        Gson gson = new Gson();
        OrderTimeBean orderTimeBean = gson.fromJson(s, OrderTimeBean.class);
        if (orderTimeBean.getCode() == 200) {
            OrderTimeBean.BodyBean body = orderTimeBean.getBody();
            time_slot = body.getTime_slot();
            if (time_slot != null) {
                if (time_slot.size() > 0) {
                    meal_time = time_slot.get(0).getMeal_time();
                }
                adapter = new OrderTImeAdapter(ctx, time_slot);
                gvList.setAdapter(adapter);
            }
            double bonus_point = body.getBonus_point();
            int exchange = body.getExchange();

            if (exchange != 0) {
                if (bonus_point >= totalPrice*exchange) {
                    arriveInteg = (int) (totalPrice*exchange);
                    tv_integral.setText("可使用"+totalPrice*exchange+"积分抵用"+totalPrice+"元");
                }else{
                    arriveInteg = bonus_point;
                    tv_integral.setText("可使用"+bonus_point+"积分抵用"+bonus_point/exchange+"元");
                }
            }
        }else{
            Toast.makeText(ctx, orderTimeBean.getResult(), Toast.LENGTH_SHORT).show();
        }
    }

    private int HttpType;
    private void getTime() {
        HttpType = 1;
        Map<String,String> map = new HashMap<>();
        map.put("token", MyApplication.User_Token);
        mPreenter.fetch(map,true, HttpUtiles.Order_time,"Order_time");

    }
}
