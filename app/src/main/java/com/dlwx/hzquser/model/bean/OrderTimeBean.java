package com.dlwx.hzquser.model.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/8/24/024.
 */

public class OrderTimeBean {

    /**
     * code : 200
     * result : 获取成功
     * body : {"time_slot":[{"meal_time":"2017-10-07 10:30:00","time":"10:30"},{"meal_time":"2017-10-07 11:30:00","time":"11:30"},{"meal_time":"2017-10-07 12:00:00","time":"12:00"},{"meal_time":"2017-10-07 15:00:00","time":"15:00"},{"meal_time":"2017-10-07 18:00:00","time":"18:00"},{"meal_time":"2017-10-07 19:00:00","time":"19:00"},{"meal_time":"2017-10-07 21:00:00","time":"21:00"},{"meal_time":"2017-10-07 22:00:00","time":"22:00"},{"meal_time":"2017-10-07 23:30:00","time":"23:30"}],"bonus_point":"0","exchange":"10"}
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
         * time_slot : [{"meal_time":"2017-10-07 10:30:00","time":"10:30"},{"meal_time":"2017-10-07 11:30:00","time":"11:30"},{"meal_time":"2017-10-07 12:00:00","time":"12:00"},{"meal_time":"2017-10-07 15:00:00","time":"15:00"},{"meal_time":"2017-10-07 18:00:00","time":"18:00"},{"meal_time":"2017-10-07 19:00:00","time":"19:00"},{"meal_time":"2017-10-07 21:00:00","time":"21:00"},{"meal_time":"2017-10-07 22:00:00","time":"22:00"},{"meal_time":"2017-10-07 23:30:00","time":"23:30"}]
         * bonus_point : 0
         * exchange : 10
         */

        private double bonus_point;
        private int exchange;
        private List<TimeSlotBean> time_slot;

        public double getBonus_point() {
            return bonus_point;
        }

        public void setBonus_point(double bonus_point) {
            this.bonus_point = bonus_point;
        }

        public int getExchange() {
            return exchange;
        }

        public void setExchange(int exchange) {
            this.exchange = exchange;
        }

        public List<TimeSlotBean> getTime_slot() {
            return time_slot;
        }

        public void setTime_slot(List<TimeSlotBean> time_slot) {
            this.time_slot = time_slot;
        }

        public static class TimeSlotBean {
            /**
             * meal_time : 2017-10-07 10:30:00
             * time : 10:30
             */

            private String meal_time;
            private String time;

            public String getMeal_time() {
                return meal_time;
            }

            public void setMeal_time(String meal_time) {
                this.meal_time = meal_time;
            }

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }
        }
    }
}
