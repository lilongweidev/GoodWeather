package com.llw.goodweather.utils;

/**
 * 统一管理缓存中对应的KEY
 */
public class Constant {

    public final static String SUCCESS_CODE = "200";
    public final static String CITY = "city";//市
    public final static String DISTRICT = "district";//区/县
    public final static String LOCATION_ID = "locationId";//通过搜索接口得到的城市id,在V7中所有数据通过id来查询
    public final static String EVERYDAY_IMG = "everyday_img";//每日图片开关
    public final static String IMG_LIST = "img_list";//图片列表开关
    public final static String CUSTOM_IMG = "custom_img";//手动定义开关
    public final static String IMG_POSITION = "img_position";//选中的本地背景图片
    public final static  int SELECT_PHOTO = 2;//启动相册标识
    public final static String CUSTOM_IMG_PATH = "custom_img_path";//手动上传图片地址
    public final static String FLAG_OTHER_RETURN="flag_other_return";//跳转页面的标识
    public final static String LOCATION="location";
    public final static String APP_FIRST_START = "appFirstStart";//App首次启动
    public final static String START_UP_APP_TIME = "startAppTime";//今日启动APP的时间


}
