package com.dlwx.hzquser.view.activity;

import android.content.Context;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dlwx.hzquser.R;
import com.dlwx.hzquser.base.BaseActivity;
import com.dlwx.hzquser.base.MyApplication;
import com.dlwx.hzquser.model.bean.ResultBean;
import com.dlwx.hzquser.presenter.Presenter;
import com.dlwx.hzquser.utiles.HttpUtiles;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 一件反馈
 */
public class FeedbackActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.title_txt)
    TextView titleTxt;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.et_text)
    EditText etText;
    @BindView(R.id.ll_etitext)
    LinearLayout llEtitext;
    @BindView(R.id.btn_submit)
    Button button;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_feedback);
        ButterKnife.bind(this);
    }

    @Override
    protected void initData() {
        initTabBar(toolbar);
        titleTxt.setText("意见反馈");
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected Presenter createPresenter() {
        return new Presenter(this);
    }



    @Override
    public void showData(String s) {
        wch(s);
        Gson gson = new Gson();
        ResultBean resultBean = gson.fromJson(s, ResultBean.class);
        if (resultBean.getCode() == 200) {
            Toast.makeText(ctx, resultBean.getResult(), Toast.LENGTH_SHORT).show();
            finish();
        }else{
            Toast.makeText(ctx, resultBean.getResult(), Toast.LENGTH_SHORT).show();
        }
    }


    @OnClick(R.id.ll_etitext)
    public void onLlEtitextClicked() {
        etText.setFocusable(true);
        etText.setFocusableInTouchMode(true);
        etText.requestFocus();
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);

    }

    @OnClick(R.id.btn_submit)
    public void onBtnSubmitClicked() {
        String count = etText.getText().toString().trim();
        if (TextUtils.isEmpty(count)) {
            vibrator.vibrate(50);
            Toast.makeText(ctx, "输入内容不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        Map<String, String> map = new HashMap<>();
        map.put("token", MyApplication.User_Token);
        map.put("content", count);
        mPreenter.fetch(map, false, HttpUtiles.FeedBack, "");
    }
}
