package com.dlwx.hzquser.model.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/8/22/022.
 */

public class SubOrderBean implements Serializable{

        private String img;
        private String menuname;
        private double price;
        private double num;
        private String suid;
        private String hunid;
        private String spec_id;
        private String cart_id;
        private String qita;
        private String menu_id;
        private String  bonus_point;
        private List<MenuType> menuTypes;

    public String getMenu_id() {
        return menu_id;
    }

    public void setMenu_id(String menu_id) {
        this.menu_id = menu_id;
    }

    public String getSpec_id() {
        return spec_id;
    }

    public void setSpec_id(String spec_id) {
        this.spec_id = spec_id;
    }

    public String getCart_id() {
        return cart_id;
    }

    public void setCart_id(String cart_id) {
        this.cart_id = cart_id;
    }

    public String getQita() {
        return qita;
    }

    public void setQita(String qita) {
        this.qita = qita;
    }

    public String getBonus_point() {
        return bonus_point;
    }

    public void setBonus_point(String bonus_point) {
        this.bonus_point = bonus_point;
    }

    public String getSuid() {
        return suid;
    }

    public void setSuid(String suid) {
        this.suid = suid;
    }

    public String getHunid() {
        return hunid;
    }

    public void setHunid(String hunid) {
        this.hunid = hunid;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getMenuname() {
        return menuname;
    }

    public void setMenuname(String menuname) {
        this.menuname = menuname;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getNum() {
        return num;
    }

    public void setNum(double num) {
        this.num = num;
    }

    public List<MenuType> getMenuTypes() {
        return menuTypes;
    }

    public void setMenuTypes(List<MenuType> menuTypes) {
        this.menuTypes = menuTypes;
    }

    public static class MenuType implements Serializable{
        private String typename;

        public String getTypename() {
            return typename;
        }

        public void setTypename(String typename) {
            this.typename = typename;
        }
    }
}
