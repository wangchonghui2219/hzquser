package com.dlwx.hzquser.model.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/8/30/030.
 */

public class LookmealCodeBean implements Serializable{

    /**
     * body : {"order_no":"20170830082003VXW6N7485","pick_code":"438457","pick_img":"http://192.168.0.191/hezhiqi//qrcode/150405526276.png"}
     * code : 200
     * result : 获取成功
     */

    private BodyBean body;
    private int code;
    private String result;

    public BodyBean getBody() {
        return body;
    }

    public void setBody(BodyBean body) {
        this.body = body;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public static class BodyBean implements Serializable{
        /**
         * order_no : 20170830082003VXW6N7485
         * pick_code : 438457
         * pick_img : http://192.168.0.191/hezhiqi//qrcode/150405526276.png
         */

        private String order_no;
        private String pick_code;
        private String pick_img;
        private String pick_type;
        public String getOrder_no() {
            return order_no;
        }

        public void setOrder_no(String order_no) {
            this.order_no = order_no;
        }

        public String getPick_code() {
            return pick_code;
        }

        public void setPick_code(String pick_code) {
            this.pick_code = pick_code;
        }

        public String getPick_img() {
            return pick_img;
        }

        public void setPick_img(String pick_img) {
            this.pick_img = pick_img;
        }

        public String getPick_type() {
            return pick_type;
        }

        public void setPick_type(String pick_type) {
            this.pick_type = pick_type;
        }
    }
}
