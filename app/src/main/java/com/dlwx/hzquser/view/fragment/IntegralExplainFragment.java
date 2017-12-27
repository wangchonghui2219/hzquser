package com.dlwx.hzquser.view.fragment;

import android.view.View;

import com.dlwx.hzquser.R;
import com.dlwx.hzquser.base.BaseFragment;
import com.dlwx.hzquser.presenter.Presenter;

/**
 * 积分说明
 */
public class IntegralExplainFragment extends BaseFragment {
    @Override
    public int getLayoutID() {
        return R.layout.fragment_integral_explain;
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
    @Override
    protected Presenter createPresenter() {
        return new Presenter(this);
    }
}
