package com.dlwx.hzquser.utiles;

import android.content.Context;
import android.widget.Toast;

import com.dlwx.hzquser.base.MyApplication;
import com.dlwx.hzquser.model.bean.ResultBean;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

/**
 * Created by Administrator on 2017/9/13/013.
 */

public class ShareHttpUtiles {

    public void  UpResult(final Context ctx){
        OkGo.<String>get(HttpUtiles.share)
                .params("token", MyApplication.User_Token)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Gson gson  = new Gson();
                        ResultBean resultBean = gson.fromJson(response.body(), ResultBean.class);
                       if (resultBean.getCode() == 200) {
                           Toast.makeText(ctx, resultBean.getResult(), Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(ctx, resultBean.getResult(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        Toast.makeText(ctx, "网络连接失败", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
