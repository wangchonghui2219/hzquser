package com.dlwx.hzquser.model.bean;

/**
 * Created by Administrator on 2017/8/25/025.
 */

public class ShopMesBean {

    /**
     * body : {"desc_img":"http://192.168.0.191/hezhiqi//Uploads/599c26718929bx250.png","shop_address":"(～ o ～)~zZ的地址","shop_desc":"dddddd公司特与世纪联华超市股份有限公司、物美商业集团股份有限公司、天天好大药房等合作，在浙江省多个城市100多家门店进行销售。此外公司还搭档杭州电视台生活频道《生活大参考》、杭州电视台生活频道电商平台共同进行优参堂海参的销售，致力于将品牌以更多样化的形式进行推广，将产品以更方便快捷的渠道送达到消费者手中。","shop_logo":"http://192.168.0.191/hezhiqi//Uploads/599c266de006bx250.png","shop_name":"美美的店","shop_phone":"18668973949"}
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
        /**
         * desc_img : http://192.168.0.191/hezhiqi//Uploads/599c26718929bx250.png
         * shop_address : (～ o ～)~zZ的地址
         * shop_desc : dddddd公司特与世纪联华超市股份有限公司、物美商业集团股份有限公司、天天好大药房等合作，在浙江省多个城市100多家门店进行销售。此外公司还搭档杭州电视台生活频道《生活大参考》、杭州电视台生活频道电商平台共同进行优参堂海参的销售，致力于将品牌以更多样化的形式进行推广，将产品以更方便快捷的渠道送达到消费者手中。
         * shop_logo : http://192.168.0.191/hezhiqi//Uploads/599c266de006bx250.png
         * shop_name : 美美的店
         * shop_phone : 18668973949
         */

        private String desc_img;
        private String shop_address;
        private String shop_desc;
        private String shop_logo;
        private String shop_name;
        private String shop_phone;

        public String getDesc_img() {
            return desc_img;
        }

        public void setDesc_img(String desc_img) {
            this.desc_img = desc_img;
        }

        public String getShop_address() {
            return shop_address;
        }

        public void setShop_address(String shop_address) {
            this.shop_address = shop_address;
        }

        public String getShop_desc() {
            return shop_desc;
        }

        public void setShop_desc(String shop_desc) {
            this.shop_desc = shop_desc;
        }

        public String getShop_logo() {
            return shop_logo;
        }

        public void setShop_logo(String shop_logo) {
            this.shop_logo = shop_logo;
        }

        public String getShop_name() {
            return shop_name;
        }

        public void setShop_name(String shop_name) {
            this.shop_name = shop_name;
        }

        public String getShop_phone() {
            return shop_phone;
        }

        public void setShop_phone(String shop_phone) {
            this.shop_phone = shop_phone;
        }
    }
}
