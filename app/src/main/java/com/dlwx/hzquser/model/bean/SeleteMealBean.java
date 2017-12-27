package com.dlwx.hzquser.model.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/8/21/021.
 */

public class SeleteMealBean {
    private List<IsCheck> checknum;

    public List<IsCheck> getChecknum() {
        return checknum;
    }

    public void setChecknum(List<IsCheck> checknum) {
        this.checknum = checknum;
    }

    public static class IsCheck{
        private Boolean isCheck;

        public Boolean getCheck() {
            return isCheck;
        }

        public void setCheck(Boolean check) {
            isCheck = check;
        }
    }
}
