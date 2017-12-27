package com.dlwx.hzquser.model.bean;

/**
 * Created by Administrator on 2017/9/29/029.
 */

public class ImgAuthBean {

    /**
     * code : 200
     * result : 获取成功
     * body : {"code_url":"http://47.94.107.189/hezhiqi//verify/150667142450.png"}
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
         * code_url : http://47.94.107.189/hezhiqi//verify/150667142450.png
         */

        private String code_url;

        public String getCode_url() {
            return code_url;
        }

        public void setCode_url(String code_url) {
            this.code_url = code_url;
        }
    }
}
