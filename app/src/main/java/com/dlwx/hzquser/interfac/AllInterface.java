package com.dlwx.hzquser.interfac;

/**
 * Created by Administrator on 2017/7/13/013.
 */

public class AllInterface {
    public interface DeleteListener {
        void deleteResute(int position);
    }
    public static DeleteListener deleteListen;
    public static void setDelete(DeleteListener deleteListener){
        deleteListen = deleteListener;
    }
    /**
     * 微信登录回调
     */
      public interface WxLoginListener{
        void loginListener(boolean isSucc);
    }
    public static WxLoginListener wxLoginListen;
    public static void setWxLoginListener(WxLoginListener wxLoginListener){
        wxLoginListen = wxLoginListener;
    }
}
