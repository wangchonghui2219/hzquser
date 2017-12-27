package com.dlwx.hzquser.view.fragment;


import android.view.View;
import android.widget.ListView;

import com.dlwx.hzquser.R;
import com.dlwx.hzquser.adapter.MyOrderAdapter;
import com.dlwx.hzquser.base.BaseFragment;
import com.dlwx.hzquser.interfac.publicInterface;
import com.dlwx.hzquser.model.bean.MyOrderItemBean;
import com.dlwx.hzquser.presenter.Presenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 全部订单
 */
public class AllOrderFragment extends BaseFragment {

    @BindView(R.id.lv_list)
    ListView lvList;
    Unbinder unbinder;
    private MyOrderAdapter adapter;
    @Override
    public int getLayoutID() {
        return R.layout.fragment_all_order;
    }

    @Override
    protected void initView(View view) {
        unbinder = ButterKnife.bind(this, view);
    }

    @Override
    protected void initDate() {
            wch("data");
        adapter = new MyOrderAdapter(ctx,ordersBeen);
        lvList.setAdapter(adapter);
    }


    @Override
    protected void initListener() {

    }

    public AllOrderFragment() {
        // Required empty public constructor
    }
    private List<MyOrderItemBean.OrdersBean> ordersBeen = new ArrayList<>();
    @Override
    protected Presenter createPresenter() {
        return new Presenter(this);
    }

    public void setData(List<MyOrderItemBean.OrdersBean> ordersBeen) {
        if (ordersBeen.size() != 0) {

            this.ordersBeen.clear();
        }
        this.ordersBeen.addAll(ordersBeen);
        adapter.notifyDataSetChanged();

        adapter.setOnClickListener(new MyOrderAdapter.DealOnClickListener() {
            @Override
            public void setCloseOnClick(int position, String order_id) {
                orderListener.setCloseListener(position,order_id);
            }

            @Override
            public void setPayOrOtherOnClick(int position,String order_no, String order_id, int status) {
                orderListener.setPayOrOtherListener(position,order_no,order_id,status);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
    private publicInterface.OrderDealListener orderListener;
    public void setDealOrderListener(publicInterface.OrderDealListener orderListener){
        this.orderListener = orderListener;
    }
}
