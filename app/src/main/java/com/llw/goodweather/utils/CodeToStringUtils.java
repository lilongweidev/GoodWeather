package com.llw.goodweather.utils;

public class CodeToStringUtils {

    public static String WeatherCode(String code){
        String codeInfo = null;
        switch (code){
            case "ok":
                codeInfo = "正常";
                break;
            case "invalid key":
                codeInfo = "无效的key";
                break;
            case "invalid key type":
                codeInfo = "你输入的key不适用于当前获取数据的方式，即SDK的KEY不能用于Web API或通过接口直接访问，反之亦然。";
                break;
            case "invalid param":
                codeInfo = "无效的参数，请检查你传递的参数是否正确、完整";
                break;
            case "bad bind":
                codeInfo = "错误的绑定，例如绑定的package name、bundle id或IP地址不一致的时候";
                break;
            case "no data for this location":
                codeInfo = "该城市/地区没有你所请求的数据";
                break;
            case "no more requests":
                codeInfo = "超过访问次数，需要等到当月最后一天24点（免费用户为当天24点）后进行访问次数的重置或升级你的访问量";
                break;
            case "no balance":
                codeInfo = "没有余额，你的按量计费产品由于余额不足或欠费而不能访问，请尽快充值";
                break;
            case "too fast":
                codeInfo = "超过限定的QPM";
                break;
            case "dead":
                codeInfo = "无响应或超时";
                break;
            case "unknown location":
                codeInfo = "没有你查询的这个地区，或者地区名称错误";
                break;
            case "permission denied":
                codeInfo = "无访问权限，你没有购买你所访问的这部分服务";
                break;
            case "sign error":
                codeInfo = "sign error";
                break;
                
        }

        return codeInfo;
    }
}
