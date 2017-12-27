package com.dlwx.hzquser.model.bean;

/**
 * 生成令餐券
 */

public class MakeCodeBean {

    /**
     * body : {"order_id":"81","order_no":"20170830082003VXW6N7485","pick_code":"438457","pick_img":"http://192.168.0.191/hezhiqi//qrcode/150405526276.png"}
     * code : 200
     * result : 操作成功
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

    public static class BodyBean {
        /**
         * order_id : 81
         * order_no : 20170830082003VXW6N7485
         * pick_code : 438457
         * pick_img : http://192.168.0.191/hezhiqi//qrcode/150405526276.png
         */

        private String order_id;
        private String order_no;
        private String pick_code;
        private String pick_img;

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
    }
}
