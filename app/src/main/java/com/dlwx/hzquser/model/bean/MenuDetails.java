package com.dlwx.hzquser.model.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/8/21/021.
 */

public class MenuDetails implements Serializable{


    /**
     * body : {"bonus_point":"16","create_time":"2017-08-21 12:03:38","desc_path":"http://192.168.0.191/hezhiqi//Uploads/599a5b3a0efbcx250.jpg","menu_desc":"大大大撒","menu_id":"27","menu_name":"二荤一素一其他","path":"http://192.168.0.191/hezhiqi//Uploads/599a5b2d215b9x250.png","pre_price":"0","price":"16","sale":"10","seller_id":"2","spec_id":"1","type_id":"1","type_name":"套餐系列","xuancan":{"hun":[{"id":"2","name":"宫保鸡丁"},{"id":"3","name":"木耳炒肉"},{"id":"4","name":"辣子鸡丁"},{"id":"5","name":"土豆牛肉"}],"hun_num":"2","qita":[{"id":"16","name":"小米粥"},{"id":"17","name":"冰镇西瓜"}],"qita_num":"1","su":[{"id":"6","name":"酸辣土豆丝"},{"id":"7","name":"炒豆角"},{"id":"8","name":"青椒土豆"},{"id":"9","name":"醋溜白菜"},{"id":"10","name":"西红柿炒番茄"}],"su_num":"1"}}
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

    public static class BodyBean implements Serializable{
        /**
         * bonus_point : 16
         * create_time : 2017-08-21 12:03:38
         * desc_path : http://192.168.0.191/hezhiqi//Uploads/599a5b3a0efbcx250.jpg
         * menu_desc : 大大大撒
         * menu_id : 27
         * menu_name : 二荤一素一其他
         * path : http://192.168.0.191/hezhiqi//Uploads/599a5b2d215b9x250.png
         * pre_price : 0
         * price : 16
         * sale : 10
         * seller_id : 2
         * spec_id : 1
         * type_id : 1
         * type_name : 套餐系列
         * xuancan : {"hun":[{"id":"2","name":"宫保鸡丁"},{"id":"3","name":"木耳炒肉"},{"id":"4","name":"辣子鸡丁"},{"id":"5","name":"土豆牛肉"}],"hun_num":"2","qita":[{"id":"16","name":"小米粥"},{"id":"17","name":"冰镇西瓜"}],"qita_num":"1","su":[{"id":"6","name":"酸辣土豆丝"},{"id":"7","name":"炒豆角"},{"id":"8","name":"青椒土豆"},{"id":"9","name":"醋溜白菜"},{"id":"10","name":"西红柿炒番茄"}],"su_num":"1"}
         */

        private String bonus_point;
        private String create_time;
        private String desc_path;
        private String menu_desc;
        private String menu_id;
        private String menu_name;
        private String path;
        private double pre_price;
        private double price;
        private String sale;
        private String seller_id;
        private String spec_id;
        private String type_id;
        private String type_name;
        private List<GuiGeBean> guige;
        private XuancanBean xuancan;
        private int  collect;

        public int getCollect() {
            return collect;
        }

        public void setCollect(int collect) {
            this.collect = collect;
        }

        public List<GuiGeBean> getGuige() {
            return guige;
        }

        public void setGuige(List<GuiGeBean> guige) {
            this.guige = guige;
        }

        public String getBonus_point() {
            return bonus_point;
        }

        public void setBonus_point(String bonus_point) {
            this.bonus_point = bonus_point;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getDesc_path() {
            return desc_path;
        }

        public void setDesc_path(String desc_path) {
            this.desc_path = desc_path;
        }

        public String getMenu_desc() {
            return menu_desc;
        }

        public void setMenu_desc(String menu_desc) {
            this.menu_desc = menu_desc;
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

        public String getSeller_id() {
            return seller_id;
        }

        public void setSeller_id(String seller_id) {
            this.seller_id = seller_id;
        }

        public String getSpec_id() {
            return spec_id;
        }

        public void setSpec_id(String spec_id) {
            this.spec_id = spec_id;
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

        public XuancanBean getXuancan() {
            return xuancan;
        }

        public void setXuancan(XuancanBean xuancan) {
            this.xuancan = xuancan;
        }

        public static class XuancanBean implements Serializable{
            /**
             * hun : [{"id":"2","name":"宫保鸡丁"},{"id":"3","name":"木耳炒肉"},{"id":"4","name":"辣子鸡丁"},{"id":"5","name":"土豆牛肉"}]
             * hun_num : 2
             * qita : [{"id":"16","name":"小米粥"},{"id":"17","name":"冰镇西瓜"}]
             * qita_num : 1
             * su : [{"id":"6","name":"酸辣土豆丝"},{"id":"7","name":"炒豆角"},{"id":"8","name":"青椒土豆"},{"id":"9","name":"醋溜白菜"},{"id":"10","name":"西红柿炒番茄"}]
             * su_num : 1
             */

            private int hun_num;
            private int qita_num;
            private int su_num;
            private List<HunBean> hun;
            private List<QitaBean> qita;
            private List<SuBean> su;

            public int getHun_num() {
                return hun_num;
            }

            public void setHun_num(int hun_num) {
                this.hun_num = hun_num;
            }

            public int getQita_num() {
                return qita_num;
            }

            public void setQita_num(int qita_num) {
                this.qita_num = qita_num;
            }

            public int getSu_num() {
                return su_num;
            }

            public void setSu_num(int su_num) {
                this.su_num = su_num;
            }

            public List<HunBean> getHun() {
                return hun;
            }

            public void setHun(List<HunBean> hun) {
                this.hun = hun;
            }

            public List<QitaBean> getQita() {
                return qita;
            }

            public void setQita(List<QitaBean> qita) {
                this.qita = qita;
            }

            public List<SuBean> getSu() {
                return su;
            }

            public void setSu(List<SuBean> su) {
                this.su = su;
            }

            public static class HunBean implements Serializable{
                /**
                 * id : 2
                 * name : 宫保鸡丁
                 */

                private String menu_id;
                private String name;

                public String getMenu_id() {
                    return menu_id;
                }

                public void setMenu_id(String menu_id) {
                    this.menu_id = menu_id;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }
            }

            public static class QitaBean implements Serializable{
                /**
                 * id : 16
                 * name : 小米粥
                 */

                private String menu_id;
                private String name;

                public String getMenu_id() {
                    return menu_id;
                }

                public void setMenu_id(String menu_id) {
                    this.menu_id = menu_id;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }
            }

            public static class SuBean implements Serializable{
                /**
                 * id : 6
                 * name : 酸辣土豆丝
                 */

                private String menu_id;
                private String name;

                public String getMenu_id() {
                    return menu_id;
                }

                public void setMenu_id(String menu_id) {
                    this.menu_id = menu_id;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }
            }
        }

        public class GuiGeBean implements Serializable{
            private int bonus_point;
            private double pre_price;
            private double price;
            private String spec_id;
            private String spec_name;

            public int getBonus_point() {
                return bonus_point;
            }

            public void setBonus_point(int bonus_point) {
                this.bonus_point = bonus_point;
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
        }

    }
}
