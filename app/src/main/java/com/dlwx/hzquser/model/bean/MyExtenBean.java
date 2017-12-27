package com.dlwx.hzquser.model.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/8/26/026.
 */

public class MyExtenBean {

    /**
     * code : 200
     * result : 获取成功
     * body : {"exten_code":"953676","list":[{"phone":"186****944","nickname":"1****1","create_time":"2017-08-17 14:21:11"},{"phone":"186****945","nickname":"184****845","create_time":"2017-08-16 15:26:15"}]}
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
         * exten_code : 953676
         * list : [{"phone":"186****944","nickname":"1****1","create_time":"2017-08-17 14:21:11"},{"phone":"186****945","nickname":"184****845","create_time":"2017-08-16 15:26:15"}]
         */

        private String exten_code;
        private List<ListBean> list;

        public String getExten_code() {
            return exten_code;
        }

        public void setExten_code(String exten_code) {
            this.exten_code = exten_code;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * phone : 186****944
             * nickname : 1****1
             * create_time : 2017-08-17 14:21:11
             */

            private String phone;
            private String nickname;
            private String create_time;

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }
        }
    }
}
