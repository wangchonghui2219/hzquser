package com.dlwx.hzquser.view.activity;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.dlwx.hzquser.R;
import com.dlwx.hzquser.base.BaseActivity;
import com.dlwx.hzquser.presenter.Presenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 搜索
 */
public class SeachActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.title_txt)
    TextView titleTxt;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.et_seach)
    EditText etSeach;
    @BindView(R.id.btn_seach)
    Button btnSeach;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_seach);
        ButterKnife.bind(this);
    }

    @Override
    protected void initData() {
        initTabBar(toolbar);
        titleTxt.setText("搜索");
    }

    @Override
    protected void initListener() {

    }
    @Override
    protected Presenter createPresenter() {
        return new Presenter(this);
    }



    @OnClick(R.id.btn_seach)
    public void onViewClicked() {
        String seach = etSeach.getText().toString().trim();
        if (TextUtils.isEmpty(seach)) {

            Toast.makeText(ctx, "请输入要搜索的内容", Toast.LENGTH_SHORT).show();
            vibrator.vibrate(50);
            return;
        }
        Intent intent = new Intent(ctx,SeachCenterActivity.class);
        intent.putExtra("seach",seach);
        startActivity(intent);
    }
}
