package com.dlwx.hzquser.view.fragment;


import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.dlwx.hzquser.R;
import com.dlwx.hzquser.adapter.MyPickUpAdapter;
import com.dlwx.hzquser.base.BaseFragment;
import com.dlwx.hzquser.base.MyApplication;
import com.dlwx.hzquser.model.bean.LookmealCodeBean;
import com.dlwx.hzquser.model.bean.MyPickUpBean;
import com.dlwx.hzquser.presenter.Presenter;
import com.dlwx.hzquser.utiles.HttpUtiles;
import com.dlwx.hzquser.view.activity.GetMealCodeActivity;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class MealFragment extends BaseFragment {

    private ViewHolder vh;
    private List<MyPickUpBean.BodyBean> body;

    @Override
    public int getLayoutID() {
        return R.layout.fragment_meal;
    }

    @Override
    protected void initView(View view) {
        vh = new ViewHolder(view);
    }

    @Override
    protected void initDate() {
        getData();
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected Presenter createPresenter() {
        return new Presenter(this);
    }

    public void getData() {
        Map<String,String> map = new HashMap<>();
        map.put("token", MyApplication.User_Token);
        mPreenter.fetch(map,true, HttpUtiles.My_PickUp,"My_PickUp"+MyApplication.User_Token,true);
    }

    @Override
    public void showData(String s) {
        loading.dismiss();
        wch(s);
        Gson gson = new Gson();
        MyPickUpBean myPickUpBean = gson.fromJson(s, MyPickUpBean.class);
        if (myPickUpBean.getCode() == 200) {
            body = myPickUpBean.getBody();

            MyPickUpAdapter upAdapter = new MyPickUpAdapter(ctx, body);
            vh.lvList.setAdapter(upAdapter);

            upAdapter.setLookDetailListener(lookDetailListener);

        }else{
            Toast.makeText(ctx, myPickUpBean.getResult(), Toast.LENGTH_SHORT).show();
        }
    }

    class ViewHolder {
        @BindView(R.id.lv_list)
        ListView lvList;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    private MyPickUpAdapter.LookDetailListener lookDetailListener = new MyPickUpAdapter.LookDetailListener() {
        @Override
        public void setResult(int postion) {
            MyPickUpBean.BodyBean bodyBean = body.get(postion);
            LookmealCodeBean lookmealCodeBean = new LookmealCodeBean();
            LookmealCodeBean.BodyBean body = new LookmealCodeBean.BodyBean();
            body.setOrder_no(bodyBean.getOrder_no());
            body.setPick_code(bodyBean.getPick_code());
            body.setPick_img(bodyBean.getPick_img());
            body.setPick_type(bodyBean.getPick_type());
            lookmealCodeBean.setBody(body);
            Intent intent = new Intent(ctx, GetMealCodeActivity.class);
            intent.putExtra("body",body);
            startActivity(intent);
        }
    };

}
