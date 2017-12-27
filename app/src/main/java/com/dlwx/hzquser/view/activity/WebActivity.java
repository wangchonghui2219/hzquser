package com.dlwx.hzquser.view.activity;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.webkit.WebView;
import android.widget.TextView;

import com.dlwx.hzquser.R;
import com.dlwx.hzquser.base.BaseActivity;
import com.dlwx.hzquser.presenter.Presenter;
import com.dlwx.hzquser.utiles.LoadWEBUtiles;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WebActivity extends BaseActivity {


    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.title_txt)
    TextView titleTxt;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.web_view)
    WebView webView;
    private String url;
    private String title;

    @Override
    protected void initView() {
        Intent intent = getIntent();
        url = intent.getStringExtra("url");
        title = intent.getStringExtra("title");
        setContentView(R.layout.activity_web);
        ButterKnife.bind(this);
//        url = "file:///android_asset/new.html";
        LoadWEBUtiles webUtiles = new LoadWEBUtiles(ctx);

        webUtiles.setListViewData(url, webView, null);
//        webView.loadUrl();
    }

    @Override
    protected void initData() {
        initTabBar(toolbar);
        titleTxt.setText(title);

    }

    @Override
    protected void initListener() {
    }

    @Override
    protected Presenter createPresenter() {
        return new Presenter(this);
    }


}
