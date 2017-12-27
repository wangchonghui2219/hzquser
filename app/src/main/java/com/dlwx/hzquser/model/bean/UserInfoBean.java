package com.dlwx.hzquser.model.bean;

/**
 * Created by Administrator on 2017/9/27/027.
 */

public class UserInfoBean {

    /**
     * body : {"is_path":"2","nickname":"港独分子去食屎\u2026\u2026","path":"","phone":""}
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
         * is_path : 2
         * nickname : 港独分子去食屎……
         * path :
         * phone :
         */

        private String is_path;
        private String nickname;
        private String path;
        private String phone;

        public String getIs_path() {
            return is_path;
        }

        public void setIs_path(String is_path) {
            this.is_path = is_path;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }
    }
}
