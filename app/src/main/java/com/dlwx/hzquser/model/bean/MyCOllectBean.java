package com.dlwx.hzquser.model.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/8/26/026.
 */

public class MyCOllectBean {


    /**
     * body : [{"create_time":"2017-08-25 20:03:40","menu_id":"29","menu_name":"荤素","path":"http://192.168.0.191/hezhiqi//Uploads/599cfc7fcc616x250.png","pre_price":"10","price":"13","sale":"10","type_name":"套餐系列"}]
     * code : 200
     * result : 获取成功
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
         * create_time : 2017-08-25 20:03:40
         * menu_id : 29
         * menu_name : 荤素
         * path : http://192.168.0.191/hezhiqi//Uploads/599cfc7fcc616x250.png
         * pre_price : 10
         * price : 13
         * sale : 10
         * type_name : 套餐系列
         */

        private String create_time;
        private String menu_id;
        private String menu_name;
        private String path;
        private double pre_price;
        private double price;
        private String sale;
        private String type_name;

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getMenu_id() {
            return menu_id;
        }

        public void setMenu_id(String menu_id) {
            this.menu_id = menu_id;
        }

        public String getMenu_name() {
            return menu_name;
        }

        public void setMenu_name(String menu_name) {
            this.menu_name = menu_name;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public double getPre_price() {
            return pre_price;
        }

        public void setPre_price(double pre_price) {
            this.pre_price = pre_price;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public String getSale() {
            return sale;
        }

        public void setSale(String sale) {
            this.sale = sale;
        }

        public String getType_name() {
            return type_name;
        }

        public void setType_name(String type_name) {
            this.type_name = type_name;
        }
    }
}
