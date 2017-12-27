package com.dlwx.hzquser.wxapi;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;

import com.dlwx.hzquser.R;
import com.dlwx.hzquser.base.MyApplication;
import com.tencent.connect.UserInfo;
import com.tencent.connect.auth.QQToken;
import com.tencent.connect.share.QQShare;
import com.tencent.connect.share.QzoneShare;
import com.tencent.mm.opensdk.utils.Log;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.dlwx.hzquser.base.MyApplication.mTencent;


/**
 * Created by Administrator on 2017/9/1/001.
 */

public class QQUtiles {
    private Context ctx;
    private int CallBackType = 1;
    public void shareQzone( Context ctx)
    {
        this.ctx = ctx;
        CallBackType = 1;
        Bundle bundle = new Bundle();
        //这条分享消息被好友点击后的跳转URL。
        bundle.putInt(QzoneShare.SHARE_TO_QZONE_KEY_TYPE, QzoneShare.SHARE_TO_QZONE_TYPE_IMAGE_TEXT);
        bundle.putString(QzoneShare.SHARE_TO_QQ_TITLE, "合之奇");// 标题
        bundle.putString(QzoneShare.SHARE_TO_QQ_SUMMARY, "%"+ MyApplication.Exten_Code+"@,快来加入我们吧");// 摘要
        bundle.putString(QzoneShare.SHARE_TO_QQ_TARGET_URL, "http://47.94.107.189/hezhiqi/share.html");// 内容地址
        bundle.putString(QzoneShare.SHARE_TO_QQ_IMAGE_URL, MyApplication.Head_Pic);
        //网络图片地址　　
        bundle.putString(QQShare.SHARE_TO_QQ_APP_NAME, "合之奇");// 应用名称
        bundle.putString(QzoneShare.SHARE_TO_QQ_EXT_INT, "其它附加功能");
        ArrayList<String> imgUrlList = new ArrayList<>();
        imgUrlList.add(MyApplication.Head_Pic);
        bundle.putStringArrayList(QzoneShare.SHARE_TO_QQ_IMAGE_URL, imgUrlList);// 图片地址
        mTencent.shareToQzone((Activity) ctx, bundle , uiListener);

    }

    public void shareQQ(Context ctx){
        this.ctx = ctx;
        CallBackType = 1;
        final Bundle params = new Bundle();
        params.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, QQShare.SHARE_TO_QQ_TYPE_DEFAULT);
        params.putString(QQShare.SHARE_TO_QQ_TITLE, "合之奇");
        params.putString(QQShare.SHARE_TO_QQ_SUMMARY,  "%"+MyApplication.Exten_Code+"@,快来加入我们吧");
        params.putString(QQShare.SHARE_TO_QQ_TARGET_URL,  "http://47.94.107.189/hezhiqi/share.html");
        params.putString(QQShare.SHARE_TO_QQ_IMAGE_URL,MyApplication.Head_Pic);
        params.putString(QQShare.SHARE_TO_QQ_APP_NAME,  "测试应用222222");
        params.putInt(QQShare.SHARE_TO_QQ_EXT_INT, R.string.app_name);
        mTencent.shareToQQ((Activity) ctx, params , uiListener);
    }

    /**
     * 分享纯图片
     * @param ctx
     */
    public void shareImageQQ(Context ctx, String file){

        this.ctx = ctx;
        CallBackType = 1;
        final Bundle params = new Bundle();
        params.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, QQShare.SHARE_TO_QQ_TYPE_IMAGE);

        params.putString(QQShare.SHARE_TO_QQ_IMAGE_LOCAL_URL, file);

        mTencent.shareToQQ((Activity) ctx, params , uiListener);
    }

    /* 获得支持ACTION_SEND的应用列表 */
    private List<ResolveInfo> getShareTargets(Context context) {
        Intent intent = new Intent(Intent.ACTION_SEND, null);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.setType("text/plain");
        PackageManager pm = context.getPackageManager();
        return pm.queryIntentActivities(intent, PackageManager.COMPONENT_ENABLED_STATE_DEFAULT);
    }

    /**
     * 登录
     * @param ctx
     */
    public void LoginQQ(Context ctx){
        if (!mTencent.isSessionValid())
        {
            CallBackType = 2;
            mTencent.login((Activity) ctx, "all", uiListener);
        }
    }

    /**
     * 退出QQ
     * @param ctx
     */
    public void logout(Context ctx)
    {
        this.ctx = ctx;
        mTencent.logout(ctx);
    }
    private static String openId;
    public IUiListener uiListener = new IUiListener() {
        @Override
        public void onComplete(Object o) {
            if (CallBackType == 1) {

                if (CallBackListenerUtiles.callBackListener != null) {

                    CallBackListenerUtiles.callBackListener.setComplete();
                }
            }else{
                getUserMess((JSONObject) o);

            }

        }
        @Override
        public void onError(UiError uiError) {
            Log.i("wch",uiError.errorCode+"");
            if (CallBackType == 2) {

                if (CallBackListenerUtiles.callBackListener != null) {
                    CallBackListenerUtiles.callBackListener.Error("登录失败");
                }
            }
        }

        @Override
        public void onCancel() {

        }
    };

    /**
     * 登陆成功 获取用户信息
     * @param o
     */
    private void getUserMess(JSONObject o) {
        JSONObject jsonObject = o;
        //获取用户的openid
        try {
            openId = jsonObject.getString("openid");
            String expirs = jsonObject.getString("expires_in");
            String access_token = jsonObject.getString("access_token");
            mTencent.setOpenId(openId);
            mTencent.setAccessToken(access_token, expirs);
            QQToken qqToken = mTencent.getQQToken();
            UserInfo userInfo = new UserInfo(ctx, qqToken);
            userInfo.getUserInfo(new IUiListener() {
                @Override
                public void onComplete(Object o) {
                    if (o == null) {
                        return;
                    }

                    JSONObject json = (JSONObject) o;
                    try {
                        int ret = json.getInt("ret");
                        //获取用户信息
                       String nickname = json.getString("nickname");
                        String gender = json.getString("gender");
                       String picUrl = json.getString("figureurl_qq_2");
                        if (CallBackListenerUtiles.callBackListener != null) {

                            CallBackListenerUtiles.callBackListener.getUserInfo(nickname,picUrl,openId);
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }

                @Override
                public void onError(UiError uiError) {

                }

                @Override
                public void onCancel() {

                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
