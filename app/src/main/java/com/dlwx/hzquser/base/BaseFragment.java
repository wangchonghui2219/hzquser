package com.dlwx.hzquser.base;

import android.app.Service;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.dlwx.hzquser.R;
import com.dlwx.hzquser.presenter.Presenter;
import com.dlwx.hzquser.utiles.LogUtiles;
import com.dlwx.hzquser.utiles.MyProgressLoading;
import com.dlwx.hzquser.utiles.SpUtiles;
import com.dlwx.hzquser.view.ViewInterface;


/**
 * Created by Administrator on 2017/8/12/012.
 */

public abstract class BaseFragment<V,T extends Presenter<V>> extends Fragment implements ViewInterface {
    public Context ctx;
    public SharedPreferences sp;
    public T mPreenter;
    public MyProgressLoading loading;
    public Vibrator vibrator=null;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mPreenter = createPresenter();
        ctx = getActivity();
        View view = inflater.inflate(getLayoutID(),null);
        sp = ctx.getSharedPreferences(SpUtiles.SP_Mode,Context.MODE_PRIVATE);
        loading = new MyProgressLoading(ctx, R.style.DialogStyle);
        vibrator=(Vibrator)ctx.getSystemService(Service.VIBRATOR_SERVICE);

        initView(view);
        initDate();
        initListener();

        return view;
    }
    /**
     * 获得布局id
     * @return
     */
    public abstract int getLayoutID();
    /**
     * 初始化控件
     */
    protected abstract void initView(View view);

    /**
     * 初始化数据
     */
    protected abstract void initDate();

    /**
     * 时间监听
     */
    protected abstract void initListener();

    @Override
    public void showLoading() {
        loading.show();
    }

    @Override
    public void showData(String s) {

    }

    @Override
    public void disLoading() {
        loading.dismiss();
    }

    @Override
    public void onError() {
        loading.dismiss();
        Toast.makeText(ctx, "网络连接失败", Toast.LENGTH_SHORT).show();
    }
    protected abstract T createPresenter();

    public void wch(Object o){
        LogUtiles.LogI(o+"");
    }
}
