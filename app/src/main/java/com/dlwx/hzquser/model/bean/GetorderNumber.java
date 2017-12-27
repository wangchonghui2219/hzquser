package com.dlwx.hzquser.model.bean;

/**
 * Created by Administrator on 2017/8/24/024.
 */

public class GetorderNumber {

    /**
     * code : 200
     * result : 订单提交成功
     * body : {"order_no":"20170822100850DA9eC4560"}
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
         * order_no : 20170822100850DA9eC4560
         */

        private String order_no;

        public String getOrder_no() {
            return order_no;
        }

        public void setOrder_no(String order_no) {
            this.order_no = order_no;
        }
    }
}
