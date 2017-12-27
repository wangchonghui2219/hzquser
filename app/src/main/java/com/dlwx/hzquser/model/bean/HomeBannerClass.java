package com.dlwx.hzquser.model.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/8/18/018.
 */

public class HomeBannerClass {


    /**
     * body : {"banner":[{"path":"http://192.168.0.191/hezhiqi//Uploads/banner-1.jpg"},{"path":"http://192.168.0.191/hezhiqi//Uploads/banner-2.jpg"},{"path":"http://192.168.0.191/hezhiqi//Uploads/banner-3.jpg"},{"path":"http://192.168.0.191/hezhiqi//Uploads/banner-4.jpg"},{"path":"http://192.168.0.191/hezhiqi//Uploads/599c152b7b43c.jpg"}],"classify":[{"path":"http://192.168.0.191/hezhiqi//Uploads/5996bb3bea8a7x250.png","type_id":"1","type_name":"套餐"},{"path":"http://192.168.0.191/hezhiqi//Uploads/5996bb47a2aecx250.png","type_id":"2","type_name":"面食"},{"path":"http://192.168.0.191/hezhiqi//Uploads/5996bb5397218x250.png","type_id":"3","type_name":"汤/粥"},{"path":"http://192.168.0.191/hezhiqi//Uploads/5996bb633d816x250.png","type_id":"4","type_name":"饮品"},{"path":"http://192.168.0.191/hezhiqi//Uploads/5996bb7582da4x250.png","type_id":"5","type_name":"水果"},{"path":"http://192.168.0.191/hezhiqi//Uploads/5996bb7f1f221x250.png","type_id":"6","type_name":"小吃"},{"path":"http://192.168.0.191/hezhiqi//Uploads/5996bb893bd6bx250.png","type_id":"7","type_name":"甜点"},{"path":"http://192.168.0.191/hezhiqi//Uploads/5996bb9319107x250.png","type_id":"8","type_name":"其他"}]}
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
        private List<BannerBean> banner;
        private List<ClassifyBean> classify;

        public List<BannerBean> getBanner() {
            return banner;
        }

        public void setBanner(List<BannerBean> banner) {
            this.banner = banner;
        }

        public List<ClassifyBean> getClassify() {
            return classify;
        }

        public void setClassify(List<ClassifyBean> classify) {
            this.classify = classify;
        }

        public static class BannerBean {
            /**
             * path : http://192.168.0.191/hezhiqi//Uploads/banner-1.jpg
             */

            private String path;

            public String getPath() {
                return path;
            }

            public void setPath(String path) {
                this.path = path;
            }
        }

        public static class ClassifyBean {
            /**
             * path : http://192.168.0.191/hezhiqi//Uploads/5996bb3bea8a7x250.png
             * type_id : 1
             * type_name : 套餐
             */

            private String path;
            private String type_id;
            private String type_name;

            public String getPath() {
                return path;
            }

            public void setPath(String path) {
                this.path = path;
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
        }
    }
}
