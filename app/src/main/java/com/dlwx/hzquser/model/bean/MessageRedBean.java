package com.dlwx.hzquser.model.bean;

/**
 * Created by Administrator on 2017/10/9/009.
 */

public class MessageRedBean {

    /**
     * code : 200
     * result : 获取成功
     * body : {"is_read":"0"}
     */

    private int code;
    private String result;
    private BodyBean body;

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

    public BodyBean getBody() {
        return body;
    }

    public void setBody(BodyBean body) {
        this.body = body;
    }

    public static class BodyBean {
        /**
         * is_read : 0
         */

        private int is_read;

        public int getIs_read() {
            return is_read;
        }

        public void setIs_read(int is_read) {
            this.is_read = is_read;
        }
    }
}
