package com.llw.goodweather.utils;

import android.util.Log;
import android.widget.ImageView;

import com.llw.goodweather.R;

/**
 * 天气工具类
 */
public class WeatherUtil {

    /**
     * 根据传入的状态码修改填入的天气图标
     *
     * @param weatherStateIcon 显示的ImageView
     * @param code             天气状态码
     */
    public static void changeIcon(ImageView weatherStateIcon, int code) {
        switch (code) {
            case 100://晴
                weatherStateIcon.setBackgroundResource(R.mipmap.icon_100);
                break;
            case 101://多云
                weatherStateIcon.setBackgroundResource(R.mipmap.icon_101);
                break;
            case 102://少云
                weatherStateIcon.setBackgroundResource(R.mipmap.icon_102);
                break;
            case 103://晴间多云
                weatherStateIcon.setBackgroundResource(R.mipmap.icon_103);
                break;
            case 104://阴 V7
                weatherStateIcon.setBackgroundResource(R.mipmap.icon_104);
                break;
            case 150://晴 晚上  V7
                weatherStateIcon.setBackgroundResource(R.mipmap.icon_150);
                break;
            case 153://晴间多云 晚上  V7
                weatherStateIcon.setBackgroundResource(R.mipmap.icon_153);
                break;
            case 154://阴 晚上  V7
                weatherStateIcon.setBackgroundResource(R.mipmap.icon_154);
                break;
            case 200://有风
            case 202://微风
            case 203://和风
            case 204://清风
                weatherStateIcon.setBackgroundResource(R.mipmap.icon_200);//因为这几个状态的图标是一样的
                break;
            case 201://平静
                weatherStateIcon.setBackgroundResource(R.mipmap.icon_201);
                break;
            case 205://强风/劲风
            case 206://疾风
            case 207://大风
                weatherStateIcon.setBackgroundResource(R.mipmap.icon_205);//因为这几个状态的图标是一样的
                break;
            case 208://烈风
            case 209://风暴
            case 210://狂爆风
            case 211://飓风
            case 212://龙卷风
            case 213://热带风暴
                weatherStateIcon.setBackgroundResource(R.mipmap.icon_208);//因为这几个状态的图标是一样的
                break;
            case 300://阵雨
                weatherStateIcon.setBackgroundResource(R.mipmap.icon_300);
                break;
            case 301://强阵雨
                weatherStateIcon.setBackgroundResource(R.mipmap.icon_301);
                break;
            case 302://雷阵雨
                weatherStateIcon.setBackgroundResource(R.mipmap.icon_302);
                break;
            case 303://强雷阵雨
                weatherStateIcon.setBackgroundResource(R.mipmap.icon_303);
                break;
            case 304://雷阵雨伴有冰雹
                weatherStateIcon.setBackgroundResource(R.mipmap.icon_304);
                break;
            case 305://小雨
                weatherStateIcon.setBackgroundResource(R.mipmap.icon_305);
                break;
            case 306://中雨
                weatherStateIcon.setBackgroundResource(R.mipmap.icon_306);
                break;
            case 307://大雨
                weatherStateIcon.setBackgroundResource(R.mipmap.icon_307);
                break;
            case 308://极端降雨
                weatherStateIcon.setBackgroundResource(R.mipmap.icon_312);
                break;
            case 309://毛毛雨/细雨
                weatherStateIcon.setBackgroundResource(R.mipmap.icon_309);
                break;
            case 310://暴雨
                weatherStateIcon.setBackgroundResource(R.mipmap.icon_310);
                break;
            case 311://大暴雨
                weatherStateIcon.setBackgroundResource(R.mipmap.icon_311);
                break;
            case 312://特大暴雨
                weatherStateIcon.setBackgroundResource(R.mipmap.icon_312);
                break;
            case 313://冻雨
                weatherStateIcon.setBackgroundResource(R.mipmap.icon_313);
                break;
            case 314://小到中雨
                weatherStateIcon.setBackgroundResource(R.mipmap.icon_306);
                break;
            case 315://中到大雨
                weatherStateIcon.setBackgroundResource(R.mipmap.icon_307);
                break;
            case 316://大到暴雨
                weatherStateIcon.setBackgroundResource(R.mipmap.icon_310);
                break;
            case 317://大暴雨到特大暴雨
                weatherStateIcon.setBackgroundResource(R.mipmap.icon_312);
                break;
            case 399://雨
                weatherStateIcon.setBackgroundResource(R.mipmap.icon_399);
                break;
            case 400://小雪
                weatherStateIcon.setBackgroundResource(R.mipmap.icon_400);
                break;
            case 401://中雪
                weatherStateIcon.setBackgroundResource(R.mipmap.icon_401);
                break;
            case 402://大雪
                weatherStateIcon.setBackgroundResource(R.mipmap.icon_402);
                break;
            case 403://暴雪
                weatherStateIcon.setBackgroundResource(R.mipmap.icon_403);
                break;
            case 404://雨夹雪
                weatherStateIcon.setBackgroundResource(R.mipmap.icon_404);
                break;
            case 405://雨雪天气
                weatherStateIcon.setBackgroundResource(R.mipmap.icon_405);
                break;
            case 406://阵雨夹雪
                weatherStateIcon.setBackgroundResource(R.mipmap.icon_406);
                break;
            case 407://阵雪
                weatherStateIcon.setBackgroundResource(R.mipmap.icon_407);
                break;
            case 408://小到中雪
                weatherStateIcon.setBackgroundResource(R.mipmap.icon_408);
                break;
            case 409://中到大雪
                weatherStateIcon.setBackgroundResource(R.mipmap.icon_409);
                break;
            case 410://大到暴雪
                weatherStateIcon.setBackgroundResource(R.mipmap.icon_410);
                break;
            case 499://雪
                weatherStateIcon.setBackgroundResource(R.mipmap.icon_499);
                break;
            case 500://薄雾
                weatherStateIcon.setBackgroundResource(R.mipmap.icon_500);
                break;
            case 501://雾
                weatherStateIcon.setBackgroundResource(R.mipmap.icon_501);
                break;
            case 502://霾
                weatherStateIcon.setBackgroundResource(R.mipmap.icon_502);
                break;
            case 503://扬沙
                weatherStateIcon.setBackgroundResource(R.mipmap.icon_503);
                break;
            case 504://扬沙
                weatherStateIcon.setBackgroundResource(R.mipmap.icon_504);
                break;
            case 507://沙尘暴
                weatherStateIcon.setBackgroundResource(R.mipmap.icon_507);
                break;
            case 508://强沙尘暴
                weatherStateIcon.setBackgroundResource(R.mipmap.icon_508);
                break;
            case 509://浓雾
            case 510://强浓雾
            case 514://大雾
            case 515://特强浓雾
                weatherStateIcon.setBackgroundResource(R.mipmap.icon_509);
                break;
            case 511://中度霾
                weatherStateIcon.setBackgroundResource(R.mipmap.icon_511);
                break;
            case 512://重度霾
                weatherStateIcon.setBackgroundResource(R.mipmap.icon_512);
                break;
            case 513://严重霾
                weatherStateIcon.setBackgroundResource(R.mipmap.icon_513);
                break;
            case 900://热
                weatherStateIcon.setBackgroundResource(R.mipmap.icon_900);
                break;
            case 901://冷
                weatherStateIcon.setBackgroundResource(R.mipmap.icon_901);
                break;
            case 999://未知
                weatherStateIcon.setBackgroundResource(R.mipmap.icon_999);
                break;
        }
    }

    /**
     * 根据传入的时间显示时间段描述信息
     *
     * @param timeData
     * @return
     */
    public static String showTimeInfo(String timeData) {
        String timeInfo = null;
        int time = 0;
        time = Integer.parseInt(timeData.trim().substring(0, 2));
        if (time >= 0 && time <= 6) {
            timeInfo = "凌晨";
        } else if (time > 6 && time <= 12) {
            timeInfo = "上午";
        } else if (time > 12 && time <= 13) {
            timeInfo = "中午";
        } else if (time > 13 && time <= 18) {
            timeInfo = "下午";
        } else if (time > 18 && time <= 24) {
            timeInfo = "晚上";
        } else {
            timeInfo = "未知";
        }
        return timeInfo;
    }


    /**
     * 紫外线等级描述
     * @param uvIndex
     * @return
     */
    public static String uvIndexInfo(String uvIndex) {
        String result = null;
        Log.d("uvIndex-->", uvIndex);
        int level = Integer.parseInt(uvIndex);
        if (level <= 2) {
            result = "较弱";
        } else if (level <= 5) {
            result = "弱";
        } else if (level <= 7) {
            result = "中等";
        } else if (level <= 10) {
            result = "强";
        } else if (level <= 15) {
            result = "很强";
        }
        return result;
    }

    /**
     * 根据api的提示转为更为人性化的提醒
     *
     * @param apiInfo
     * @return
     */
    public static String apiToTip(String apiInfo) {
        String result = null;
        String str = null;
        if (apiInfo.contains("AQI ")) {
            str = apiInfo.replace("AQI ", " ");
        } else {
            str = apiInfo;
        }
        switch (str) {//优，良，轻度污染，中度污染，重度污染，严重污染
            case "优":
                result = "♪(^∇^*)  空气很好。";
                break;
            case "良":
                result = "ヽ(✿ﾟ▽ﾟ)ノ  空气不错。";
                break;
            case "轻度污染":
                result = "(⊙﹏⊙)  空气有些糟糕。";
                break;
            case "中度污染":
                result = " ε=(´ο｀*)))  唉 空气污染较为严重，注意防护。";
                break;
            case "重度污染":
                result = "o(≧口≦)o  空气污染很严重，记得戴口罩哦！";
                break;
            case "严重污染":
                result = "ヽ(*。>Д<)o゜  完犊子了!空气污染非常严重，要减少出门，定期检查身体，能搬家就搬家吧！";
                break;
        }
        return result;
    }

    /**
     * 紫外线详细描述
     * @param uvIndexInfo
     * @return
     */
    public static String uvIndexToTip(String uvIndexInfo) {
        String result = null;
        switch (uvIndexInfo) {
            case "较弱":
                result = "紫外线较弱，不需要采取防护措施；若长期在户外，建议涂擦SPF在8-12之间的防晒护肤品。";
                break;
            case "弱":
                result = "紫外线弱，可以适当采取一些防护措施，涂擦SPF在12-15之间、PA+的防晒护肤品。";
                break;
            case "中等":
                result = "紫外线中等，外出时戴好遮阳帽、太阳镜和太阳伞等；涂擦SPF高于15、PA+的防晒护肤品。";
                break;
            case "强":
                result = "紫外线较强，避免在10点至14点暴露于日光下.外出时戴好遮阳帽、太阳镜和太阳伞等，涂擦SPF20左右、PA++的防晒护肤品。";
                break;
            case "很强":
                result = "紫外线很强，尽可能不在室外活动，必须外出时，要采取各种有效的防护措施。";
                break;
        }
        return result;
    }
}
