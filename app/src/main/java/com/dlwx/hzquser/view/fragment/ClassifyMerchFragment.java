package com.dlwx.hzquser.view.fragment;


import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dlwx.hzquser.R;
import com.dlwx.hzquser.base.BaseFragment;
import com.dlwx.hzquser.presenter.Presenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 分类商家界面
 */
public class ClassifyMerchFragment extends BaseFragment {


    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_counte)
    TextView tvCounte;
    @BindView(R.id.iv_pic)
    ImageView ivPic;
    Unbinder unbinder;

    @Override
    public int getLayoutID() {
        return R.layout.fragment_classify_merch;
    }

    @Override
    protected void initView(View view) {
        unbinder = ButterKnife.bind(this, view);
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


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    public void setMess(String img,String desc,String name) {
        tvName.setText(name);
        tvCounte.setText("       "+desc);
        Glide.with(ctx).load(img).into(ivPic);
    }
}
