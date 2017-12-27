package com.dlwx.hzquser.model.bean;

import java.util.List;

/**
 * 购物车列表
 */

public class ShopCatListBean {

    /**
     * body : [{"cart_id":"6","menu_id":"27","menu_name":"大啊啊是","num":"1","path":"http://192.168.0.191/hezhiqi//Uploads/599a5b2d215b9x250.png","pre_price":"0","price":"16","spec_id":"1","spec_name":"不分规格","type_id":"1","type_name":"套餐系列","update_time":"2017-08-22 09:32:57","user_id":"8","xuancan":[{"id":"3","name":"木耳炒肉"},{"id":"4","name":"辣子鸡丁"},{"id":"6","name":"酸辣土豆丝"}]},{"cart_id":"5","menu_id":"27","menu_name":"大啊啊是","num":"5","path":"http://192.168.0.191/hezhiqi//Uploads/599a5b2d215b9x250.png","pre_price":"0","price":"16","spec_id":"1","spec_name":"不分规格","type_id":"1","type_name":"套餐系列","update_time":"2017-08-22 08:57:53","user_id":"8","xuancan":[{"id":"2","name":"宫保鸡丁"},{"id":"3","name":"木耳炒肉"},{"id":"6","name":"酸辣土豆丝"}]}]
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
         * cart_id : 6
         * menu_id : 27
         * menu_name : 大啊啊是
         * num : 1
         * path : http://192.168.0.191/hezhiqi//Uploads/599a5b2d215b9x250.png
         * pre_price : 0
         * price : 16
         * spec_id : 1
         * spec_name : 不分规格
         * type_id : 1
         * type_name : 套餐系列
         * update_time : 2017-08-22 09:32:57
         * user_id : 8
         * xuancan : [{"id":"3","name":"木耳炒肉"},{"id":"4","name":"辣子鸡丁"},{"id":"6","name":"酸辣土豆丝"}]
         */

        private String cart_id;
        private String menu_id;
        private String menu_name;
        private int num;
        private String path;
        private double pre_price;
        private double price;
        private String spec_id;
        private String spec_name;
        private String type_id;
        private String type_name;
        private String update_time;
        private String user_id;
        private boolean isCheck;
        private String bonus_point;

        public boolean isCheck() {
            return isCheck;
        }

        public String getBonus_point() {
            return bonus_point;
        }

        public void setBonus_point(String bonus_point) {
            this.bonus_point = bonus_point;
        }

        public boolean getCheck() {
            return isCheck;
        }

        public void setCheck(boolean check) {
            isCheck = check;
        }

        private List<XuancanBean> xuancan;

        public String getCart_id() {
            return cart_id;
        }

        public void setCart_id(String cart_id) {
            this.cart_id = cart_id;
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

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
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

        public String getSpec_id() {
            return spec_id;
        }

        public void setSpec_id(String spec_id) {
            this.spec_id = spec_id;
        }

        public String getSpec_name() {
            return spec_name;
        }

        public void setSpec_name(String spec_name) {
            this.spec_name = spec_name;
        }

        public String getType_id() {
            return type_id;
        }

        public void setType_id(String type_id) {
            this.type_id = type_id;
        }

        public String getType_name() {
            return type_name;
        }

        public void setType_name(String type_name) {
            this.type_name = type_name;
        }

        public String getUpdate_time() {
            return update_time;
        }

        public void setUpdate_time(String update_time) {
            this.update_time = update_time;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public List<XuancanBean> getXuancan() {
            return xuancan;
        }

        public void setXuancan(List<XuancanBean> xuancan) {
            this.xuancan = xuancan;
        }

        public static class XuancanBean {
            /**
             * id : 3
             * name : 木耳炒肉
             */

            private String id;
            private String name;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }
}
