package com.dlwx.hzquser.model.bean;

/**
 * Created by Administrator on 2017/9/4/004.
 */

public class PayResultBean {

    /**
     * body : alipay_sdk=alipay-sdk-php-20161101&app_id=2017041006629052&biz_content=%7B%22body%22%3A%22%E8%AE%A2%E9%A4%90%E8%AE%A2%E5%8D%95%22%2C%22subject%22%3A%22offer%E8%AE%A2%E5%8D%95%22%2C%22out_trade_no%22%3A%2220170904143251naH2P%22%2C%22timeout_express%22%3A%2230m%22%2C%22total_amount%22%3A%2213%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%7D&charset=UTF-8&format=json&method=alipay.trade.app.pay&notify_url=http%3A%2F%2F47.94.107.189%2Fhezhiqi%2Findex.php%2FUser%2FAlipay%2Freturn_url&sign_type=RSA&timestamp=2017-09-04+14%3A32%3A53&version=1.0&sign=0QxHLupNTABfWe2LglXhbXVPAp0GU%2FZ4GR7F719cxKcX8RF8gxj2OirhCGYT7k7UKlHVllRYd0oTnygyedhmT3SAZPxHR%2Fx%2BeQtCXHjyVLttkvmTpzuhcwATTF2rc0Woq9LZLXo7fQZN07QUHdPu6%2BHluci%2BfDlOAX7Bi2Os4Sc%3D
     * code : 200
     * result : 操作成功
     */

    private String body;
    private int code;
    private String result;

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
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
}
