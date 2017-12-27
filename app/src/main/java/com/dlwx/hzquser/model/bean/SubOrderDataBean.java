package com.dlwx.hzquser.model.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/8/24/024.
 */

public class SubOrderDataBean {

    /**
     * bonus_point : 200
     * confirm : [{"bonus_point":"16","cart_id":"9","hun":"3,4","menu_id":"23","num":"1","price":"16","qita":"","spec_id":"1","su":"6,7"},{"bonus_point":"6","cart_id":"8","hun":"","menu_id":"17","num":"1","price":"6","qita":"","spec_id":"2","su":""}]
     * meal_time : 2017-08-22 15:00:00
     * total_price : 20
     */

    private double bonus_point;
    private String meal_time;
    private double total_price;
    private List<ConfirmBean> confirm;

    public double getBonus_point() {
        return bonus_point;
    }

    public void setBonus_point(double bonus_point) {
        this.bonus_point = bonus_point;
    }

    public String getMeal_time() {
        return meal_time;
    }

    public void setMeal_time(String meal_time) {
        this.meal_time = meal_time;
    }

    public double getTotal_price() {
        return total_price;
    }

    public void setTotal_price(double total_price) {
        this.total_price = total_price;
    }

    public List<ConfirmBean> getConfirm() {
        return confirm;
    }

    public void setConfirm(List<ConfirmBean> confirm) {
        this.confirm = confirm;
    }

    public static class ConfirmBean {
        /**
         * bonus_point : 16
         * cart_id : 9
         * hun : 3,4
         * menu_id : 23
         * num : 1
         * price : 16
         * qita :
         * spec_id : 1
         * su : 6,7
         */

        private String bonus_point;
        private String cart_id;
        private String hun;
        private String menu_id;
        private double num;
        private double price;
        private String qita;
        private String spec_id;
        private String su;
        public String getBonus_point() {
            return bonus_point;
        }

        public void setBonus_point(String bonus_point) {
            this.bonus_point = bonus_point;
        }

        public String getCart_id() {
            return cart_id;
        }

        public void setCart_id(String cart_id) {
            this.cart_id = cart_id;
        }

        public String getHun() {
            return hun;
        }

        public void setHun(String hun) {
            this.hun = hun;
        }

        public String getMenu_id() {
            return menu_id;
        }

        public void setMenu_id(String menu_id) {
            this.menu_id = menu_id;
        }

        public double getNum() {
            return num;
        }

        public void setNum(double num) {
            this.num = num;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public String getQita() {
            return qita;
        }

        public void setQita(String qita) {
            this.qita = qita;
        }

        public String getSpec_id() {
            return spec_id;
        }

        public void setSpec_id(String spec_id) {
            this.spec_id = spec_id;
        }

        public String getSu() {
            return su;
        }

        public void setSu(String su) {
            this.su = su;
        }
    }
}
