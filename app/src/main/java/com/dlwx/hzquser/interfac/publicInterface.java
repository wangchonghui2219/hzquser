package com.dlwx.hzquser.interfac;

/**
 * Created by Administrator on 2017/6/30/030.
 */

public class publicInterface {

    /**
     * 时间滚轴选择
     */
    public interface DateInterface{
        void getDate(String date);
    }

    /**
     * 城市
     */
    public interface CityInterface{
        void getCity(String date);
    }

    public interface OrderDealListener{

        void setCloseListener(int position,String order_id);
        void setPayOrOtherListener(int position,String order_no,String order_id,int status);

    }


}
