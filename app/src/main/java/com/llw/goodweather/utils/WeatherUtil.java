package com.llw.goodweather.utils;

import android.util.Log;
import android.widget.ImageView;

import com.llw.goodweather.R;

/**
 * 天气工具类
 *
 * @author llw
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
            //晴
            case 100:
                weatherStateIcon.setBackgroundResource(R.mipmap.icon_100);
                break;
            //多云
            case 101:
                weatherStateIcon.setBackgroundResource(R.mipmap.icon_101);
                break;
            //少云
            case 102:
                weatherStateIcon.setBackgroundResource(R.mipmap.icon_102);
                break;
            //晴间多云
            case 103:
                weatherStateIcon.setBackgroundResource(R.mipmap.icon_103);
                break;
            //阴 V7
            case 104:
                weatherStateIcon.setBackgroundResource(R.mipmap.icon_104);
                break;
            //晴 晚上  V7
            case 150:
                weatherStateIcon.setBackgroundResource(R.mipmap.icon_150);
                break;
            //晴间多云 晚上  V7
            case 153:
                weatherStateIcon.setBackgroundResource(R.mipmap.icon_153);
                break;
            //阴 晚上  V7
            case 154:
                weatherStateIcon.setBackgroundResource(R.mipmap.icon_154);
                break;
            //有风
            case 200:
                //微风
            case 202:
                //和风
            case 203:
                //清风
            case 204:
                //因为这几个状态的图标是一样的
                weatherStateIcon.setBackgroundResource(R.mipmap.icon_200);
                break;
            //平静
            case 201:
                weatherStateIcon.setBackgroundResource(R.mipmap.icon_201);
                break;
            //强风/劲风
            case 205:
                //疾风
            case 206:
                //大风
            case 207:
                //因为这几个状态的图标是一样的
                weatherStateIcon.setBackgroundResource(R.mipmap.icon_205);
                break;
            //烈风
            case 208:
                //风暴
            case 209:
                //狂爆风
            case 210:
                //飓风
            case 211:
                //龙卷风
            case 212:
                //热带风暴
            case 213:
                //因为这几个状态的图标是一样的
                weatherStateIcon.setBackgroundResource(R.mipmap.icon_208);
                break;
            //阵雨
            case 300:
                weatherStateIcon.setBackgroundResource(R.mipmap.icon_300);
                break;
            //强阵雨
            case 301:
                weatherStateIcon.setBackgroundResource(R.mipmap.icon_301);
                break;
            //雷阵雨
            case 302:
                weatherStateIcon.setBackgroundResource(R.mipmap.icon_302);
                break;
            //强雷阵雨
            case 303:
                weatherStateIcon.setBackgroundResource(R.mipmap.icon_303);
                break;
            //雷阵雨伴有冰雹
            case 304:
                weatherStateIcon.setBackgroundResource(R.mipmap.icon_304);
                break;
            //小雨
            case 305:
                weatherStateIcon.setBackgroundResource(R.mipmap.icon_305);
                break;
            //中雨
            case 306:
                weatherStateIcon.setBackgroundResource(R.mipmap.icon_306);
                break;
            //大雨
            case 307:
                weatherStateIcon.setBackgroundResource(R.mipmap.icon_307);
                break;
            //极端降雨
            case 308:
                weatherStateIcon.setBackgroundResource(R.mipmap.icon_312);
                break;
            //毛毛雨/细雨
            case 309:
                weatherStateIcon.setBackgroundResource(R.mipmap.icon_309);
                break;
            //暴雨
            case 310:
                weatherStateIcon.setBackgroundResource(R.mipmap.icon_310);
                break;
            //大暴雨
            case 311:
                weatherStateIcon.setBackgroundResource(R.mipmap.icon_311);
                break;
            //特大暴雨
            case 312:
                weatherStateIcon.setBackgroundResource(R.mipmap.icon_312);
                break;
            //冻雨
            case 313:
                weatherStateIcon.setBackgroundResource(R.mipmap.icon_313);
                break;
            //小到中雨
            case 314:
                weatherStateIcon.setBackgroundResource(R.mipmap.icon_306);
                break;
            //中到大雨
            case 315:
                weatherStateIcon.setBackgroundResource(R.mipmap.icon_307);
                break;
            //大到暴雨
            case 316:
                weatherStateIcon.setBackgroundResource(R.mipmap.icon_310);
                break;
            //大暴雨到特大暴雨
            case 317:
                weatherStateIcon.setBackgroundResource(R.mipmap.icon_312);
                break;
            //雨
            case 399:
                weatherStateIcon.setBackgroundResource(R.mipmap.icon_399);
                break;
            //小雪
            case 400:
                weatherStateIcon.setBackgroundResource(R.mipmap.icon_400);
                break;
            //中雪
            case 401:
                weatherStateIcon.setBackgroundResource(R.mipmap.icon_401);
                break;
            //大雪
            case 402:
                weatherStateIcon.setBackgroundResource(R.mipmap.icon_402);
                break;
            //暴雪
            case 403:
                weatherStateIcon.setBackgroundResource(R.mipmap.icon_403);
                break;
            //雨夹雪
            case 404:
                weatherStateIcon.setBackgroundResource(R.mipmap.icon_404);
                break;
            //雨雪天气
            case 405:
                weatherStateIcon.setBackgroundResource(R.mipmap.icon_405);
                break;
            //阵雨夹雪
            case 406:
                weatherStateIcon.setBackgroundResource(R.mipmap.icon_406);
                break;
            //阵雪
            case 407:
                weatherStateIcon.setBackgroundResource(R.mipmap.icon_407);
                break;
            //小到中雪
            case 408:
                weatherStateIcon.setBackgroundResource(R.mipmap.icon_408);
                break;
            //中到大雪
            case 409:
                weatherStateIcon.setBackgroundResource(R.mipmap.icon_409);
                break;
            //大到暴雪
            case 410:
                weatherStateIcon.setBackgroundResource(R.mipmap.icon_410);
                break;
            //雪
            case 499:
                weatherStateIcon.setBackgroundResource(R.mipmap.icon_499);
                break;
            //薄雾
            case 500:
                weatherStateIcon.setBackgroundResource(R.mipmap.icon_500);
                break;
            //雾
            case 501:
                weatherStateIcon.setBackgroundResource(R.mipmap.icon_501);
                break;
            //霾
            case 502:
                weatherStateIcon.setBackgroundResource(R.mipmap.icon_502);
                break;
            //扬沙
            case 503:
                weatherStateIcon.setBackgroundResource(R.mipmap.icon_503);
                break;
            //扬沙
            case 504:
                weatherStateIcon.setBackgroundResource(R.mipmap.icon_504);
                break;
            //沙尘暴
            case 507:
                weatherStateIcon.setBackgroundResource(R.mipmap.icon_507);
                break;
            //强沙尘暴
            case 508:
                weatherStateIcon.setBackgroundResource(R.mipmap.icon_508);
                break;
            //浓雾
            case 509:
                //强浓雾
            case 510:
                //大雾
            case 514:
                //特强浓雾
            case 515:
                weatherStateIcon.setBackgroundResource(R.mipmap.icon_509);
                break;
            //中度霾
            case 511:
                weatherStateIcon.setBackgroundResource(R.mipmap.icon_511);
                break;
            //重度霾
            case 512:
                weatherStateIcon.setBackgroundResource(R.mipmap.icon_512);
                break;
            //严重霾
            case 513:
                weatherStateIcon.setBackgroundResource(R.mipmap.icon_513);
                break;
            //热
            case 900:
                weatherStateIcon.setBackgroundResource(R.mipmap.icon_900);
                break;
            //冷
            case 901:
                weatherStateIcon.setBackgroundResource(R.mipmap.icon_901);
                break;
            //未知
            case 999:
                weatherStateIcon.setBackgroundResource(R.mipmap.icon_999);
                break;
            default:
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

        if (timeData == null || timeData.equals("")) {
            timeInfo = "获取失败";
        } else {
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
        }


        return timeInfo;
    }


    /**
     * 紫外线等级描述
     *
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
        //优，良，轻度污染，中度污染，重度污染，严重污染
        switch (str) {
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
            default:
                break;
        }
        return result;
    }

    /**
     * 紫外线详细描述
     *
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
            default:
                break;
        }
        return result;
    }

    /**
     * 早晚温差提示
     *
     * @param height 当天最高温
     * @param low    当天最低温
     */
    public static String differenceTempTip(int height, int low) {
        StringBuffer stringBuffer = new StringBuffer();

        stringBuffer.append("    今天最高温" + height + "℃，最低温" + low + "℃。");
        if (height - low > 5) {//温差大
            if (height < 25) {
                stringBuffer.append("早晚温差较大，加强自我防护，防治感冒，对自己好一点(*￣︶￣)");
            } else if (height < 20) {
                stringBuffer.append("天气转阴温度低，上下班请注意添衣保暖(*^▽^*)");
            } else if (height < 15) {
                stringBuffer.append("关怀不是今天才开始，关心也不是今天就结束，希望你注意保暖ヾ(◍°∇°◍)ﾉﾞ");
            }
        } else {//温差小
            if (low < 25) {
                stringBuffer.append("多运动，多喝水，注意补充水分(*￣︶￣)");
            } else if (low < 20) {
                stringBuffer.append("早睡早起，别熬夜，无论晴天还是雨天，每天都是新的一天(*^▽^*)");
            } else if (low < 15) {
                stringBuffer.append("天气寒冷，注意防寒和保暖，也不要忘记锻炼喔ヾ(◍°∇°◍)ﾉﾞ");
            }
        }


        return stringBuffer.toString();
    }
}
