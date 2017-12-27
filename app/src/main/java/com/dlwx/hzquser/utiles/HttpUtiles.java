package com.dlwx.hzquser.utiles;

/**
 * Created by Administrator on 2017/8/16/016.
 */
public class HttpUtiles {
//  public static String BaseUrl = "http://192.168.0.191/hezhiqi/index.php/User/";
    public static String BaseUrl = "http://47.94.107.189/hezhiqi/index.php/User/";
    public static String Register = BaseUrl+"Register/add";//注册
    public static String Register_Path = BaseUrl + "/Register/path";
    public static String Login = BaseUrl +"Login/login";//登录
    public static String ForgetPwd = BaseUrl +"Register/find_pwd";//忘记密码
    public static String ChangePwd = BaseUrl + "User/update_pwd";//修改登录密码
    public static String ChangeNickName = BaseUrl +"User/update_info";//修改昵称
    public static String ChangePhone = BaseUrl+"User/update_info";//修改手机号
    public static String UpHeadPic = BaseUrl+"User/update_info";//修改头像
    public static String Home_Banner = BaseUrl+"Index/ban_classify";//获取首页banner和分类
    public static String Home_NewProduct = BaseUrl+"Index/new_menus";//首页最新上架
    public static String Seach_Center = BaseUrl +"Index/menu_list";//首页分类搜索商品
    public static String MenuClass = BaseUrl + "Index/classify";//搜索页菜单分类
    public static String Menu_Details = BaseUrl + "Index/menu_desc";//菜单详情
    public static String Add_ShopCat = BaseUrl +"Shopping/add_cart";//加入购物车
    public static String ShopCat_List = BaseUrl + "Shopping/my_cart";//购物车列表
    public static String Delete_ShopCat = BaseUrl + "Shopping/cart_del";//购物车删除
    public static String ShopCat_Edit = BaseUrl + "Shopping/cart_update";//购物车编辑
    public static String Order_time = BaseUrl + "Shopping/order_time";//订单时间
    public static String Up_Order = BaseUrl + "Shopping/order_add";//提交订单
    public static String Seller_Shop = BaseUrl + "Index/seller_shop";//获取商家信息
    public static String Collect_Product  = BaseUrl + "Other/collect";//收藏/取消
    public static String MyCollect = BaseUrl + "Other/my_collect";//我的收藏
    public static String FeedBack = BaseUrl + "Other/feedback";//意见反馈
    public static String Get_Inte = BaseUrl + "Other/bonus_point";//获取积分
    public static String My_Exten = BaseUrl + "Other/my_exten";//我的推荐
    public static String AllOrder = BaseUrl + "Shopping/my_order";// 我的订单
    public static String Delete_Order = BaseUrl + "Shopping/cancel_order";//我的订单
    public static String My_PickUp = BaseUrl + "Shopping/my_pickup";//我的领餐码
    public static String Mack_PickUp = BaseUrl + "Shopping/make_pickup";//生成领餐券
    public static String Look_pickUp = BaseUrl + "Shopping/look_pickup";//查看领餐码
    public static String Pay = BaseUrl + "Bill/topay";//去支付
    public static String About_We = "http://47.94.107.189/hezhiqi/Web/aboutUs.html";//关于我们
    public static String upVersion = BaseUrl + "Version/getversion";//版本更新
    public static String UpData_address = BaseUrl + "User/update_info";//修改地址
    public static String Regist_Deal =   "http://47.94.107.189/hezhiqi/Web/userProtocol.html";//这测协议
    public static String Mess_Num = BaseUrl + "Message/center";//消息数量
    public static String Mess_List = BaseUrl + "Message/message_list";//消息列表
    public static String Get_sms = BaseUrl + "Register/hezhi";//验证码
    public static String share = BaseUrl + "Index/share";//分享
    public static String Sett_Mess = BaseUrl + "User/get_userinfo";//用户信息
    public static String Img_AUth = BaseUrl + "Register/verify";//获取图片验证码
    public static String IsRed = BaseUrl + "Message/is_read";//获取消息红点
}
