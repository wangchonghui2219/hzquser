package com.dlwx.hzquser.model.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/8/26/026.
 */

public class MyOrderItemBean {


    private List<OrdersBean> orders;

    public List<OrdersBean> getOrders() {
        return orders;
    }

    public void setOrders(List<OrdersBean> orders) {
        this.orders = orders;
    }

    public static class OrdersBean {
        /**
         * bonus_point : 0
         * meal_time : 晚上七点餐
         * order_id : 22
         * order_no : 201708251453310CAtP107
         * status : 1
         * total_num :
         * total_price : 106
         * update_time : 2017-08-25 14:53:31
         */

        private String bonus_point;
        private String meal_time;
        private String order_id;
        private String order_no;
        private int status;
        private String total_num;
        private String total_price;
        private String update_time;
        private List<DescBean> desc;

        public List<DescBean> getDesc() {
            return desc;
        }

        public void setDesc(List<DescBean> desc) {
            this.desc = desc;
        }

        public String getBonus_point() {
            return bonus_point;
        }

        public void setBonus_point(String bonus_point) {
            this.bonus_point = bonus_point;
        }

        public String getMeal_time() {
            return meal_time;
        }

        public void setMeal_time(String meal_time) {
            this.meal_time = meal_time;
        }

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

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getTotal_num() {
            return total_num;
        }

        public void setTotal_num(String total_num) {
            this.total_num = total_num;
        }

        public String getTotal_price() {
            return total_price;
        }

        public void setTotal_price(String total_price) {
            this.total_price = total_price;
        }

        public String getUpdate_time() {
            return update_time;
        }

        public void setUpdate_time(String update_time) {
            this.update_time = update_time;
        }

        public static class DescBean{
                private String give_bonus_point;
            private String menu_id;
            private String menu_name;
            private int num;
            private double old_price;
            private String path;
            private double price;
            private String type_id;
            private String xuancan;

            public String getGive_bonus_point() {
                return give_bonus_point;
            }

            public void setGive_bonus_point(String give_bonus_point) {
                this.give_bonus_point = give_bonus_point;
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

            public double getOld_price() {
                return old_price;
            }

            public void setOld_price(double old_price) {
                this.old_price = old_price;
            }

            public String getPath() {
                return path;
            }

            public void setPath(String path) {
                this.path = path;
            }

            public double getPrice() {
                return price;
            }

            public void setPrice(double price) {
                this.price = price;
            }

            public String getType_id() {
                return type_id;
            }

            public void setType_id(String type_id) {
                this.type_id = type_id;
            }

            public String getXuancan() {
                return xuancan;
            }

            public void setXuancan(String xuancan) {
                this.xuancan = xuancan;
            }
        }

    }
}
