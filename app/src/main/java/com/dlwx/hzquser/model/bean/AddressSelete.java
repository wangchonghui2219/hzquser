package com.dlwx.hzquser.model.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/8/28/028.
 */

public class AddressSelete {

    /**
     * code : 200
     * result : 获取成功
     * body : [{"path_id":"1","name":"郑州东区富士康"},{"path_id":"2","name":"郑州东区富士康二厂区"}]
     */

    private int code;
    private String result;
    private List<BodyBean> body;

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

    public List<BodyBean> getBody() {
        return body;
    }

    public void setBody(List<BodyBean> body) {
        this.body = body;
    }

    public static class BodyBean {
        /**
         * path_id : 1
         * name : 郑州东区富士康
         */

        private String path_id;
        private String name;

        public String getPath_id() {
            return path_id;
        }

        public void setPath_id(String path_id) {
            this.path_id = path_id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
