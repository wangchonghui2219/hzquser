package com.dlwx.hzquser.model.bean;

import java.util.List;

/**
 * 编辑购物车上传信息
 */

public class EditShopCatBean {

    private List<CartBean> cart;

    public List<CartBean> getCart() {
        return cart;
    }

    public void setCart(List<CartBean> cart) {
        this.cart = cart;
    }

    public static class CartBean {
        /**
         * cart_id : 9
         * num : 2
         */

        private String cart_id;
        private String num;

        public String getCart_id() {
            return cart_id;
        }

        public void setCart_id(String cart_id) {
            this.cart_id = cart_id;
        }

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
        }
    }
}
