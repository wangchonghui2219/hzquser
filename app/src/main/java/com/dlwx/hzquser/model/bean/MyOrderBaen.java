package com.dlwx.hzquser.model.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/8/26/026.
 */

public class MyOrderBaen {

    /**
     * body : {"list":[{"bonus_point":"0","desc":[{"give_bonus_point":"13","menu_id":"29","menu_name":"荤素","num":"1","old_price":"13","path":"http://192.168.0.191/hezhiqi//Uploads/599cfc7fcc616x250.png","price":"10","type_id":"1","xuancan":"木耳炒肉,辣子鸡丁,炒豆角"}],"meal_time":"早上八点餐","order_id":"78","order_no":"20170826185006B5Ck96466","status":"2","total_num":"1","total_price":"13","update_time":"2017-08-26 18:50:06"},{"bonus_point":"0","desc":[{"give_bonus_point":"10","menu_id":"18","menu_name":"两荤一素","num":"1","old_price":"12","path":"http://192.168.0.191/hezhiqi//Uploads/59950cabced11x250.jpg","price":"12","type_id":"1","xuancan":"宫保鸡丁,木耳炒肉,青椒土豆"}],"meal_time":"凌晨五点餐","order_id":"74","order_no":"20170826172300RcxBW8638","status":"2","total_num":"1","total_price":"12","update_time":"2017-08-26 17:23:00"},{"bonus_point":"0","desc":[{"give_bonus_point":"13","menu_id":"19","menu_name":"二荤二素一其他","num":"1","old_price":"13","path":"http://192.168.0.191/hezhiqi//Uploads/59969325c764ex250.png","price":"13","type_id":"1","xuancan":"木耳炒肉,辣子鸡丁,醋溜白菜"}],"meal_time":"早上七点餐","order_id":"72","order_no":"20170826171840c8w453153","status":"2","total_num":"1","total_price":"13","update_time":"2017-08-26 17:18:40"},{"bonus_point":"0","desc":[{"give_bonus_point":"13","menu_id":"29","menu_name":"荤素","num":"1","old_price":"13","path":"http://192.168.0.191/hezhiqi//Uploads/599cfc7fcc616x250.png","price":"10","type_id":"1","xuancan":"木耳炒肉,辣子鸡丁,炒豆角"}],"meal_time":"下午六点餐","order_id":"54","order_no":"20170826150340WYxar8011","status":"3","total_num":"1","total_price":"10","update_time":"2017-08-26 15:03:40"}],"list2":[{"bonus_point":"0","desc":[{"give_bonus_point":"13","menu_id":"29","menu_name":"荤素","num":"1","old_price":"13","path":"http://192.168.0.191/hezhiqi//Uploads/599cfc7fcc616x250.png","price":"10","type_id":"1","xuancan":"木耳炒肉,辣子鸡丁,炒豆角"}],"meal_time":"早上八点餐","order_id":"78","order_no":"20170826185006B5Ck96466","status":"2","total_num":"1","total_price":"13","update_time":"2017-08-26 18:50:06"},{"bonus_point":"0","desc":[{"give_bonus_point":"10","menu_id":"18","menu_name":"两荤一素","num":"1","old_price":"12","path":"http://192.168.0.191/hezhiqi//Uploads/59950cabced11x250.jpg","price":"12","type_id":"1","xuancan":"宫保鸡丁,木耳炒肉,青椒土豆"}],"meal_time":"凌晨五点餐","order_id":"74","order_no":"20170826172300RcxBW8638","status":"2","total_num":"1","total_price":"12","update_time":"2017-08-26 17:23:00"},{"bonus_point":"0","desc":[{"give_bonus_point":"13","menu_id":"19","menu_name":"二荤二素一其他","num":"1","old_price":"13","path":"http://192.168.0.191/hezhiqi//Uploads/59969325c764ex250.png","price":"13","type_id":"1","xuancan":"木耳炒肉,辣子鸡丁,醋溜白菜"}],"meal_time":"早上七点餐","order_id":"72","order_no":"20170826171840c8w453153","status":"2","total_num":"1","total_price":"13","update_time":"2017-08-26 17:18:40"}],"list3":[{"bonus_point":"0","desc":[{"give_bonus_point":"13","menu_id":"29","menu_name":"荤素","num":"1","old_price":"13","path":"http://192.168.0.191/hezhiqi//Uploads/599cfc7fcc616x250.png","price":"10","type_id":"1","xuancan":"木耳炒肉,辣子鸡丁,炒豆角"}],"meal_time":"早上八点餐","order_id":"78","order_no":"20170826185006B5Ck96466","status":"2","total_num":"1","total_price":"13","update_time":"2017-08-26 18:50:06"},{"bonus_point":"0","desc":[{"give_bonus_point":"10","menu_id":"18","menu_name":"两荤一素","num":"1","old_price":"12","path":"http://192.168.0.191/hezhiqi//Uploads/59950cabced11x250.jpg","price":"12","type_id":"1","xuancan":"宫保鸡丁,木耳炒肉,青椒土豆"}],"meal_time":"凌晨五点餐","order_id":"74","order_no":"20170826172300RcxBW8638","status":"2","total_num":"1","total_price":"12","update_time":"2017-08-26 17:23:00"},{"bonus_point":"0","desc":[{"give_bonus_point":"13","menu_id":"19","menu_name":"二荤二素一其他","num":"1","old_price":"13","path":"http://192.168.0.191/hezhiqi//Uploads/59969325c764ex250.png","price":"13","type_id":"1","xuancan":"木耳炒肉,辣子鸡丁,醋溜白菜"}],"meal_time":"早上七点餐","order_id":"72","order_no":"20170826171840c8w453153","status":"2","total_num":"1","total_price":"13","update_time":"2017-08-26 17:18:40"}]}
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
        private List<List2Bean> list2;
        private List<List3Bean> list3;

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public List<List2Bean> getList2() {
            return list2;
        }

        public void setList2(List<List2Bean> list2) {
            this.list2 = list2;
        }

        public List<List3Bean> getList3() {
            return list3;
        }

        public void setList3(List<List3Bean> list3) {
            this.list3 = list3;
        }

        public static class ListBean {
            /**
             * bonus_point : 0
             * desc : [{"give_bonus_point":"13","menu_id":"29","menu_name":"荤素","num":"1","old_price":"13","path":"http://192.168.0.191/hezhiqi//Uploads/599cfc7fcc616x250.png","price":"10","type_id":"1","xuancan":"木耳炒肉,辣子鸡丁,炒豆角"}]
             * meal_time : 早上八点餐
             * order_id : 78
             * order_no : 20170826185006B5Ck96466
             * status : 2
             * total_num : 1
             * total_price : 13
             * update_time : 2017-08-26 18:50:06
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

            public List<DescBean> getDesc() {
                return desc;
            }

            public void setDesc(List<DescBean> desc) {
                this.desc = desc;
            }

            public static class DescBean {
                /**
                 * give_bonus_point : 13
                 * menu_id : 29
                 * menu_name : 荤素
                 * num : 1
                 * old_price : 13
                 * path : http://192.168.0.191/hezhiqi//Uploads/599cfc7fcc616x250.png
                 * price : 10
                 * type_id : 1
                 * xuancan : 木耳炒肉,辣子鸡丁,炒豆角
                 */

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

        public static class List2Bean {
            /**
             * bonus_point : 0
             * desc : [{"give_bonus_point":"13","menu_id":"29","menu_name":"荤素","num":"1","old_price":"13","path":"http://192.168.0.191/hezhiqi//Uploads/599cfc7fcc616x250.png","price":"10","type_id":"1","xuancan":"木耳炒肉,辣子鸡丁,炒豆角"}]
             * meal_time : 早上八点餐
             * order_id : 78
             * order_no : 20170826185006B5Ck96466
             * status : 2
             * total_num : 1
             * total_price : 13
             * update_time : 2017-08-26 18:50:06
             */

            private String bonus_point;
            private String meal_time;
            private String order_id;
            private String order_no;
            private int status;
            private String total_num;
            private String total_price;
            private String update_time;
            private List<DescBeanX> desc;

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

            public List<DescBeanX> getDesc() {
                return desc;
            }

            public void setDesc(List<DescBeanX> desc) {
                this.desc = desc;
            }

            public static class DescBeanX {
                /**
                 * give_bonus_point : 13
                 * menu_id : 29
                 * menu_name : 荤素
                 * num : 1
                 * old_price : 13
                 * path : http://192.168.0.191/hezhiqi//Uploads/599cfc7fcc616x250.png
                 * price : 10
                 * type_id : 1
                 * xuancan : 木耳炒肉,辣子鸡丁,炒豆角
                 */

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

        public static class List3Bean {
            /**
             * bonus_point : 0
             * desc : [{"give_bonus_point":"13","menu_id":"29","menu_name":"荤素","num":"1","old_price":"13","path":"http://192.168.0.191/hezhiqi//Uploads/599cfc7fcc616x250.png","price":"10","type_id":"1","xuancan":"木耳炒肉,辣子鸡丁,炒豆角"}]
             * meal_time : 早上八点餐
             * order_id : 78
             * order_no : 20170826185006B5Ck96466
             * status : 2
             * total_num : 1
             * total_price : 13
             * update_time : 2017-08-26 18:50:06
             */

            private String bonus_point;
            private String meal_time;
            private String order_id;
            private String order_no;
            private int status;
            private String total_num;
            private String total_price;
            private String update_time;
            private List<DescBeanXX> desc;

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

            public List<DescBeanXX> getDesc() {
                return desc;
            }

            public void setDesc(List<DescBeanXX> desc) {
                this.desc = desc;
            }

            public static class DescBeanXX {
                /**
                 * give_bonus_point : 13
                 * menu_id : 29
                 * menu_name : 荤素
                 * num : 1
                 * old_price : 13
                 * path : http://192.168.0.191/hezhiqi//Uploads/599cfc7fcc616x250.png
                 * price : 10
                 * type_id : 1
                 * xuancan : 木耳炒肉,辣子鸡丁,炒豆角
                 */

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
}
