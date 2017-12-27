package com.dlwx.hzquser.view.fragment;

import android.view.View;
import android.widget.ListView;

import com.dlwx.hzquser.R;
import com.dlwx.hzquser.adapter.MyOrderAdapter;
import com.dlwx.hzquser.base.BaseFragment;
import com.dlwx.hzquser.interfac.publicInterface;
import com.dlwx.hzquser.model.bean.MyOrderItemBean;
import com.dlwx.hzquser.model.bean.ResultBean;
import com.dlwx.hzquser.presenter.Presenter;
import com.google.gson.Gson;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 待付款
 */
public class ObligationFragment extends BaseFragment {


    private ViewHolder vh;

    @Override
    protected void initView(View view) {
        vh = new ViewHolder(view);
    }

    @Override
    protected void initDate() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    public int getLayoutID() {
        return R.layout.fragment_obligation;
    }

    @Override
    protected Presenter createPresenter() {
        return new Presenter(this);
    }
    private int HttpType;
    private int pos;
    private List<MyOrderItemBean.OrdersBean> ordersBeens;
    private MyOrderAdapter adapter;
    public void setData(List<MyOrderItemBean.OrdersBean> ordersBeens) {
        //TODO
        this.ordersBeens = ordersBeens;
        adapter = new MyOrderAdapter(ctx, ordersBeens);
        vh.lvList.setAdapter(adapter);
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
    public void showData(String s) {
        loading.dismiss();
        if (HttpType == 1) {
            closeResult(s);
        }
    }
    private void closeResult(String s) {
        Gson gson = new Gson();
        ResultBean resultBean = gson.fromJson(s, ResultBean.class);
        if (resultBean.getCode() == 200) {
            ordersBeens.remove(pos);
            adapter.notifyDataSetChanged();
        }
    }
    class ViewHolder {
        @BindView(R.id.lv_list)
        ListView lvList;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
    private publicInterface.OrderDealListener orderListener;
    public void setDealOrderListener(publicInterface.OrderDealListener orderListener){
        this.orderListener = orderListener;
    }
}
