package com.dlwx.hzquser.model.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/8/29/029.
 */

public class MyPickUpBean {

    /**
     * code : 200
     * result : 获取成功
     * body : [{"order_id":"2","order_no":"20170822102222PeSeV2054","meal_time":"2017-08-28 18:00:00","pick_code":"066194","pick_img":"http://192.168.0.191/hezhiqi//qrcode/150391154072.png","pick_type":"1","pick_phone":"","pick_exten":""}]
     */

    private int code;
    private String result;
    private List<BodyBean> body;

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

    public List<BodyBean> getBody() {
        return body;
    }

    public void setBody(List<BodyBean> body) {
        this.body = body;
    }

    public static class BodyBean {
        /**
         * order_id : 2
         * order_no : 20170822102222PeSeV2054
         * meal_time : 2017-08-28 18:00:00
         * pick_code : 066194
         * pick_img : http://192.168.0.191/hezhiqi//qrcode/150391154072.png
         * pick_type : 1
         * pick_phone :
         * pick_exten :
         */

        private String order_id;
        private String order_no;
        private String meal_time;
        private String pick_code;
        private String pick_img;
        private String pick_type;
        private String pick_phone;
        private String pick_exten;

        public String getOrder_id() {
            return order_id;
        }

        public void setOrder_id(String order_id) {
            this.order_id = order_id;
        }

        public String getOrder_no() {
            return order_no;
        }

        public void setOrder_no(String order_no) {
            this.order_no = order_no;
        }

        public String getMeal_time() {
            return meal_time;
        }

        public void setMeal_time(String meal_time) {
            this.meal_time = meal_time;
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

        public String getPick_phone() {
            return pick_phone;
        }

        public void setPick_phone(String pick_phone) {
            this.pick_phone = pick_phone;
        }

        public String getPick_exten() {
            return pick_exten;
        }

        public void setPick_exten(String pick_exten) {
            this.pick_exten = pick_exten;
        }
    }
}
