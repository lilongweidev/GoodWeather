package com.llw.goodweather.bean;

import java.util.List;

/**
 * 天气预报数据实体  V7
 *
 * @author llw
 */
public class DailyResponse {

    /**
     * code : 200
     * updateTime : 2020-07-15T08:55+08:00
     * fxLink : http://hfx.link/2ax1
     * daily : [{"fxDate":"2020-07-15","sunrise":"04:59","sunset":"19:41","moonrise":"00:44","moonset":"14:35","moonPhase":"残月","tempMax":"32","tempMin":"22","iconDay":"101","textDay":"多云","iconNight":"150","textNight":"晴","wind360Day":"242","windDirDay":"西南风","windScaleDay":"1-2","windSpeedDay":"9","wind360Night":"202","windDirNight":"西南风","windScaleNight":"1-2","windSpeedNight":"4","humidity":"49","precip":"0.0","pressure":"996","vis":"25","cloud":"25","uvIndex":"11"},{"fxDate":"2020-07-16","sunrise":"05:00","sunset":"19:40","moonrise":"01:13","moonset":"15:36","moonPhase":"残月","tempMax":"32","tempMin":"24","iconDay":"101","textDay":"多云","iconNight":"302","textNight":"雷阵雨","wind360Day":"232","windDirDay":"西南风","windScaleDay":"1-2","windSpeedDay":"7","wind360Night":"179","windDirNight":"南风","windScaleNight":"1-2","windSpeedNight":"10","humidity":"50","precip":"0.0","pressure":"996","vis":"25","cloud":"25","uvIndex":"6"},{"fxDate":"2020-07-17","sunrise":"05:01","sunset":"19:39","moonrise":"01:46","moonset":"16:38","moonPhase":"残月","tempMax":"30","tempMin":"23","iconDay":"302","textDay":"雷阵雨","iconNight":"302","textNight":"雷阵雨","wind360Day":"186","windDirDay":"南风","windScaleDay":"3-4","windSpeedDay":"14","wind360Night":"129","windDirNight":"东南风","windScaleNight":"1-2","windSpeedNight":"4","humidity":"75","precip":"1.0","pressure":"997","vis":"24","cloud":"55","uvIndex":"1"},{"fxDate":"2020-07-18","sunrise":"05:02","sunset":"19:39","moonrise":"02:24","moonset":"17:40","moonPhase":"残月","tempMax":"29","tempMin":"21","iconDay":"302","textDay":"雷阵雨","iconNight":"305","textNight":"小雨","wind360Day":"175","windDirDay":"南风","windScaleDay":"1-2","windSpeedDay":"4","wind360Night":"60","windDirNight":"东北风","windScaleNight":"1-2","windSpeedNight":"4","humidity":"81","precip":"0.0","pressure":"997","vis":"7","cloud":"24","uvIndex":"7"},{"fxDate":"2020-07-19","sunrise":"05:02","sunset":"19:38","moonrise":"03:12","moonset":"18:38","moonPhase":"残月","tempMax":"27","tempMin":"21","iconDay":"305","textDay":"小雨","iconNight":"302","textNight":"雷阵雨","wind360Day":"43","windDirDay":"东北风","windScaleDay":"1-2","windSpeedDay":"9","wind360Night":"243","windDirNight":"西南风","windScaleNight":"1-2","windSpeedNight":"1","humidity":"69","precip":"0.0","pressure":"996","vis":"24","cloud":"25","uvIndex":"5"},{"fxDate":"2020-07-20","sunrise":"05:03","sunset":"19:37","moonrise":"04:06","moonset":"19:33","moonPhase":"新月","tempMax":"32","tempMin":"22","iconDay":"101","textDay":"多云","iconNight":"101","textNight":"多云","wind360Day":"243","windDirDay":"西南风","windScaleDay":"1-2","windSpeedDay":"8","wind360Night":"357","windDirNight":"北风","windScaleNight":"1-2","windSpeedNight":"5","humidity":"55","precip":"0.0","pressure":"995","vis":"24","cloud":"0","uvIndex":"10"},{"fxDate":"2020-07-21","sunrise":"05:04","sunset":"19:36","moonrise":"05:10","moonset":"20:20","moonPhase":"峨眉月","tempMax":"33","tempMin":"22","iconDay":"100","textDay":"晴","iconNight":"150","textNight":"晴","wind360Day":"2","windDirDay":"北风","windScaleDay":"1-2","windSpeedDay":"6","wind360Night":"358","windDirNight":"北风","windScaleNight":"1-2","windSpeedNight":"2","humidity":"38","precip":"0.0","pressure":"999","vis":"25","cloud":"1","uvIndex":"10"}]
     * refer : {"sources":["Weather China"],"license":["no commercial use"]}
     */

    private String code;
    private String updateTime;
    private String fxLink;
    private ReferBean refer;
    private List<DailyBean> daily;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getFxLink() {
        return fxLink;
    }

    public void setFxLink(String fxLink) {
        this.fxLink = fxLink;
    }

    public ReferBean getRefer() {
        return refer;
    }

    public void setRefer(ReferBean refer) {
        this.refer = refer;
    }

    public List<DailyBean> getDaily() {
        return daily;
    }

    public void setDaily(List<DailyBean> daily) {
        this.daily = daily;
    }

    public static class ReferBean {
        private List<String> sources;
        private List<String> license;

        public List<String> getSources() {
            return sources;
        }

        public void setSources(List<String> sources) {
            this.sources = sources;
        }

        public List<String> getLicense() {
            return license;
        }

        public void setLicense(List<String> license) {
            this.license = license;
        }
    }

    public static class DailyBean {
        /**
         * fxDate : 2020-07-15
         * sunrise : 04:59
         * sunset : 19:41
         * moonrise : 00:44
         * moonset : 14:35
         * moonPhase : 残月
         * tempMax : 32
         * tempMin : 22
         * iconDay : 101
         * textDay : 多云
         * iconNight : 150
         * textNight : 晴
         * wind360Day : 242
         * windDirDay : 西南风
         * windScaleDay : 1-2
         * windSpeedDay : 9
         * wind360Night : 202
         * windDirNight : 西南风
         * windScaleNight : 1-2
         * windSpeedNight : 4
         * humidity : 49
         * precip : 0.0
         * pressure : 996
         * vis : 25
         * cloud : 25
         * uvIndex : 11
         */

        private String fxDate;
        private String sunrise;
        private String sunset;
        private String moonrise;
        private String moonset;
        private String moonPhase;
        private String tempMax;
        private String tempMin;
        private String iconDay;
        private String textDay;
        private String iconNight;
        private String textNight;
        private String wind360Day;
        private String windDirDay;
        private String windScaleDay;
        private String windSpeedDay;
        private String wind360Night;
        private String windDirNight;
        private String windScaleNight;
        private String windSpeedNight;
        private String humidity;
        private String precip;
        private String pressure;
        private String vis;
        private String cloud;
        private String uvIndex;

        public String getFxDate() {
            return fxDate;
        }

        public void setFxDate(String fxDate) {
            this.fxDate = fxDate;
        }

        public String getSunrise() {
            return sunrise;
        }

        public void setSunrise(String sunrise) {
            this.sunrise = sunrise;
        }

        public String getSunset() {
            return sunset;
        }

        public void setSunset(String sunset) {
            this.sunset = sunset;
        }

        public String getMoonrise() {
            return moonrise;
        }

        public void setMoonrise(String moonrise) {
            this.moonrise = moonrise;
        }

        public String getMoonset() {
            return moonset;
        }

        public void setMoonset(String moonset) {
            this.moonset = moonset;
        }

        public String getMoonPhase() {
            return moonPhase;
        }

        public void setMoonPhase(String moonPhase) {
            this.moonPhase = moonPhase;
        }

        public String getTempMax() {
            return tempMax;
        }

        public void setTempMax(String tempMax) {
            this.tempMax = tempMax;
        }

        public String getTempMin() {
            return tempMin;
        }

        public void setTempMin(String tempMin) {
            this.tempMin = tempMin;
        }

        public String getIconDay() {
            return iconDay;
        }

        public void setIconDay(String iconDay) {
            this.iconDay = iconDay;
        }

        public String getTextDay() {
            return textDay;
        }

        public void setTextDay(String textDay) {
            this.textDay = textDay;
        }

        public String getIconNight() {
            return iconNight;
        }

        public void setIconNight(String iconNight) {
            this.iconNight = iconNight;
        }

        public String getTextNight() {
            return textNight;
        }

        public void setTextNight(String textNight) {
            this.textNight = textNight;
        }

        public String getWind360Day() {
            return wind360Day;
        }

        public void setWind360Day(String wind360Day) {
            this.wind360Day = wind360Day;
        }

        public String getWindDirDay() {
            return windDirDay;
        }

        public void setWindDirDay(String windDirDay) {
            this.windDirDay = windDirDay;
        }

        public String getWindScaleDay() {
            return windScaleDay;
        }

        public void setWindScaleDay(String windScaleDay) {
            this.windScaleDay = windScaleDay;
        }

        public String getWindSpeedDay() {
            return windSpeedDay;
        }

        public void setWindSpeedDay(String windSpeedDay) {
            this.windSpeedDay = windSpeedDay;
        }

        public String getWind360Night() {
            return wind360Night;
        }

        public void setWind360Night(String wind360Night) {
            this.wind360Night = wind360Night;
        }

        public String getWindDirNight() {
            return windDirNight;
        }

        public void setWindDirNight(String windDirNight) {
            this.windDirNight = windDirNight;
        }

        public String getWindScaleNight() {
            return windScaleNight;
        }

        public void setWindScaleNight(String windScaleNight) {
            this.windScaleNight = windScaleNight;
        }

        public String getWindSpeedNight() {
            return windSpeedNight;
        }

        public void setWindSpeedNight(String windSpeedNight) {
            this.windSpeedNight = windSpeedNight;
        }

        public String getHumidity() {
            return humidity;
        }

        public void setHumidity(String humidity) {
            this.humidity = humidity;
        }

        public String getPrecip() {
            return precip;
        }

        public void setPrecip(String precip) {
            this.precip = precip;
        }

        public String getPressure() {
            return pressure;
        }

        public void setPressure(String pressure) {
            this.pressure = pressure;
        }

        public String getVis() {
            return vis;
        }

        public void setVis(String vis) {
            this.vis = vis;
        }

        public String getCloud() {
            return cloud;
        }

        public void setCloud(String cloud) {
            this.cloud = cloud;
        }

        public String getUvIndex() {
            return uvIndex;
        }

        public void setUvIndex(String uvIndex) {
            this.uvIndex = uvIndex;
        }
    }
}
