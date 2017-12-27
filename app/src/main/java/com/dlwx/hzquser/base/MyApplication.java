package com.dlwx.hzquser.base;

import android.app.Application;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;

import com.dlwx.hzquser.service.MyReceiver;
import com.dlwx.hzquser.utiles.SpUtiles;
import com.lzy.okgo.OkGo;
import com.tencent.tauth.Tencent;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by Administrator on 2017/8/14/014.
 */

public class MyApplication extends Application{
    public static String User_Token;
    public static Tencent mTencent;
    public static String Head_Pic;
    public static String Exten_Code;
    @Override
    public void onCreate() {
        super.onCreate();
        SharedPreferences sp = getSharedPreferences(SpUtiles.SP_Mode,MODE_PRIVATE);
        User_Token = sp.getString(SpUtiles.User_Token, "");
        OkGo.getInstance().init(this);
        mTencent = Tencent.createInstance("101427102",this);
        Head_Pic = sp.getString(SpUtiles.Head_pic,"");
        Exten_Code = sp.getString(SpUtiles.terrace,"");
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        MyReceiver mReceiver = new MyReceiver();
        registerReceiver(mReceiver, intentFilter);
       String user_id = sp.getString(SpUtiles.User_ID,"");
        if (user_id != null) {

            Set<String> tags = new HashSet<>();
            tags.add(user_id);
            JPushInterface.setTags(getApplicationContext(),1,tags);
        }
    }
    public static List<String> typeId;
    public static List<String> typeName;
    public static void setClass(List<String> typeIds) {
        typeId = typeIds;
    }

    public static void setClassName(List<String> typeNames) {
        typeName = typeNames;
    }
}
