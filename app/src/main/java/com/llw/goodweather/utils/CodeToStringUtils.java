package com.llw.goodweather.utils;

/**
 * @author llw
 */
public class CodeToStringUtils {

    public static String WeatherCode(String code) {
        String codeInfo = null;
        switch (code) {
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
                codeInfo = "签名错误";
                break;

            //新增V7版本下的状态码判断
            case "204":
                codeInfo = "你查询的地区暂时没有你需要的数据";
                break;
            case "400":
                codeInfo = "请求错误，可能包含错误的请求参数或缺少必选的请求参数";
                break;
            case "401":
                codeInfo = "认证失败，可能使用了错误的KEY、数字签名错误、KEY的类型错误";
                break;
            case "402":
                codeInfo = "超过访问次数或余额不足以支持继续访问服务，你可以充值、升级访问量或等待访问量重置。";
                break;
            case "403":
                codeInfo = "无访问权限，可能是绑定的PackageName、BundleID、域名IP地址不一致，或者是需要额外付费的数据。";
                break;
            case "404":
                codeInfo = "查询的数据或地区不存在。";
                break;
            case "429":
                codeInfo = "超过限定的QPM（每分钟访问次数）";
                break;
        }
        return codeInfo;
    }

}
