package com.dlwx.hzquser.model.bean;

import java.util.List;

/**
 * 搜索中心 分类中心
 */

public class SeachCenterBean {

    /**
     * body : {"list":[{"create_time":"2017-08-18 15:12:18","menu_id":"19","menu_name":"二荤二素一其他","path":"http://192.168.0.191/hezhiqi//Uploads/59969325c764ex250.png","pre_price":"0","price":"13","sale":"10","type_name":"套餐系列"},{"create_time":"2017-08-17 09:01:27","menu_id":"18","menu_name":"两荤一素","path":"http://192.168.0.191/hezhiqi//Uploads/59950cabced11x250.jpg","pre_price":"0","price":"12","sale":"0","type_name":"套餐系列"}]}
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
             * create_time : 2017-08-18 15:12:18
             * menu_id : 19
             * menu_name : 二荤二素一其他
             * path : http://192.168.0.191/hezhiqi//Uploads/59969325c764ex250.png
             * pre_price : 0
             * price : 13
             * sale : 10
             * type_name : 套餐系列
             */

            private String create_time;
            private String menu_id;
            private String menu_name;
            private String path;
            private double pre_price;
            private String price;
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

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
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
