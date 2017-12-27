package com.dlwx.hzquser.model.bean;

/**
 * 我的积分
 */

public class MyInteBean {

    /**
     * code : 200
     * result : 获取成功
     * body : {"bonus_point":"52"}
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
         * bonus_point : 52
         */

        private String bonus_point;

        public String getBonus_point() {
            return bonus_point;
        }

        public void setBonus_point(String bonus_point) {
            this.bonus_point = bonus_point;
        }
    }
}
