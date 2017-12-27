package com.dlwx.hzquser.model.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/8/18/018.
 */

public class HomeNewProduct {


    /**
     * body : {"list":[{"collect":"3","create_time":"2017-08-23 11:55:31","menu_id":"29","menu_name":"荤素","path":"http://192.168.0.191/hezhiqi//Uploads/599cfc7fcc616x250.png","pre_price":"10","price":"13","sale":"10","type_name":"套餐系列"},{"collect":"3","create_time":"2017-08-21 12:03:38","menu_id":"27","menu_name":"二荤一素一其他","path":"http://192.168.0.191/hezhiqi//Uploads/599a5b2d215b9x250.png","pre_price":"13","price":"16","sale":"10","type_name":"套餐系列"},{"collect":"0","create_time":"2017-08-21 12:00:12","menu_id":"26","menu_name":"你说啥三","path":"http://192.168.0.191/hezhiqi//Uploads/599a5ab1d2976x250.png","pre_price":"0","price":"12","sale":"10","type_name":"套餐系列"},{"collect":"24","create_time":"2017-08-19 16:37:18","menu_id":"25","menu_name":"你好","path":"http://192.168.0.191/hezhiqi//Uploads/5997f8a7e87e0x250.png","pre_price":"0","price":"15","sale":"10","type_name":"套餐系列"},{"collect":"0","create_time":"2017-08-19 16:36:36","menu_id":"24","menu_name":"ddddddddd","path":"http://192.168.0.191/hezhiqi//Uploads/5997f87d82566x250.png","pre_price":"0","price":"10","sale":"10","type_name":"套餐系列"},{"collect":"1","create_time":"2017-08-19 16:14:49","menu_id":"23","menu_name":"两荤两素","path":"http://192.168.0.191/hezhiqi//Uploads/5997f36395f17x250.png","pre_price":"0","price":"16","sale":"10","type_name":"套餐系列"},{"collect":"0","create_time":"2017-08-19 16:13:56","menu_id":"22","menu_name":"fff","path":"http://192.168.0.191/hezhiqi//Uploads/5997f32f6944fx250.png","pre_price":"0","price":"15","sale":"10","type_name":"套餐系列"},{"collect":"0","create_time":"2017-08-19 16:11:14","menu_id":"21","menu_name":"O(∩_∩)O哈哈哈~","path":"http://192.168.0.191/hezhiqi//Uploads/5997f2890d972x250.png","pre_price":"0","price":"12","sale":"10","type_name":"套餐系列"},{"collect":"0","create_time":"2017-08-19 16:06:58","menu_id":"20","menu_name":"三魂三素","path":"http://192.168.0.191/hezhiqi//Uploads/5997f18810980x250.png","pre_price":"0","price":"12","sale":"10","type_name":"套餐系列"},{"collect":"1","create_time":"2017-08-18 15:12:18","menu_id":"19","menu_name":"二荤二素一其他","path":"http://192.168.0.191/hezhiqi//Uploads/59969325c764ex250.png","pre_price":"0","price":"13","sale":"10","type_name":"套餐系列"},{"collect":"0","create_time":"2017-08-17 09:01:27","menu_id":"18","menu_name":"两荤一素","path":"http://192.168.0.191/hezhiqi//Uploads/59950cabced11x250.jpg","pre_price":"0","price":"12","sale":"0","type_name":"套餐系列"}]}
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

    public static class BodyBean {
        private List<ListBean> list;

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * collect : 3
             * create_time : 2017-08-23 11:55:31
             * menu_id : 29
             * menu_name : 荤素
             * path : http://192.168.0.191/hezhiqi//Uploads/599cfc7fcc616x250.png
             * pre_price : 10
             * price : 13
             * sale : 10
             * type_name : 套餐系列
             */

            private String collect;
            private String create_time;
            private String menu_id;
            private String menu_name;
            private String path;
            private double pre_price;
            private double price;
            private String sale;
            private String type_name;

            public String getCollect() {
                return collect;
            }

            public void setCollect(String collect) {
                this.collect = collect;
            }

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
}
