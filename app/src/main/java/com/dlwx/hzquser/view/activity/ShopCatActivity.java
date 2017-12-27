package com.dlwx.hzquser.view.activity;

import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.dlwx.hzquser.R;
import com.dlwx.hzquser.base.BaseActivity;
import com.dlwx.hzquser.presenter.Presenter;
import com.dlwx.hzquser.view.fragment.ShopCatFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShopCatActivity extends BaseActivity {

    @BindView(R.id.rl_count)
    FrameLayout rlCount;
    @BindView(R.id.rl_black)
    RelativeLayout rlBlack;


    @Override
    protected void initView() {
        setContentView(R.layout.activity_shop_cat);
        ButterKnife.bind(this);
    }

    @Override
    protected void initData() {

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.rl_count, new ShopCatFragment());
        fragmentTransaction.commit();
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected Presenter createPresenter() {
        return new Presenter(this);
    }



    @OnClick(R.id.rl_black)
    public void onViewClicked() {
        finish();


    }
}
