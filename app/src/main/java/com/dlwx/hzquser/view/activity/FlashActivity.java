package com.dlwx.hzquser.view.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;

import com.dlwx.hzquser.MainActivity;
import com.dlwx.hzquser.R;
import com.dlwx.hzquser.base.BaseActivity;
import com.dlwx.hzquser.presenter.Presenter;
import com.dlwx.hzquser.utiles.SpUtiles;

public class FlashActivity extends BaseActivity {

    private SharedPreferences sp;
    private String first;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_flash);
        sp = getSharedPreferences(SpUtiles.SP_Mode,MODE_PRIVATE);
        first = sp.getString(SpUtiles.First, "");
        new Thread(){
            @Override
            public void run() {
                for (int i = 2; i >=0 ; i--) {
                    if (i == 0) {
                        handler.sendEmptyMessage(1);
                    }else{
                        try {
                            sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }.start();
    }

    @Override
    protected void initData() {
    }

    @Override
    protected void initListener() {
    }

    @Override
    protected Presenter createPresenter() {
        return new Presenter(this);
    }
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
             switch (msg.what){
                        case 1:
                            if ("2".equals(first)) {
                                startActivity(new Intent(FlashActivity.this, MainActivity.class));
                            }else{
                                startActivity(new Intent(FlashActivity.this, GuiDeActivity.class));
                            }
                            finish();
                            break;
                    }
        }
    };

}
