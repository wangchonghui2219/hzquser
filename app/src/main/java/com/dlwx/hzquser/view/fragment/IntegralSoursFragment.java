package com.dlwx.hzquser.view.fragment;


import android.view.View;

import com.dlwx.hzquser.R;
import com.dlwx.hzquser.base.BaseFragment;
import com.dlwx.hzquser.presenter.Presenter;

/**
 * 积分来源
 */
public class IntegralSoursFragment extends BaseFragment {
    @Override
    public int getLayoutID() {
        return R.layout.fragment_integral_sours;
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected void initDate() {

    }

    @Override
    protected void initListener() {

    }

    public IntegralSoursFragment() {
        // Required empty public constructor
    }
    @Override
    protected Presenter createPresenter() {
        return new Presenter(this);
    }
}
