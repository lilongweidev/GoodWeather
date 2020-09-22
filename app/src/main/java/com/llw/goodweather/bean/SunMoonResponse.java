package com.llw.goodweather.bean;

import java.util.List;

/**
 * 太阳和月亮数据实体
 *
 * @author llw
 */

public class SunMoonResponse {


    /**
     * code : 200
     * updateTime : 2020-09-02T18:00+08:00
     * fxLink : http://hfx.link/2ax1
     * sunrise : 2020-09-02T05:44+08:00
     * sunset : 2020-09-02T18:41+08:00
     * moonrise : 2020-09-02T19:10+08:00
     * moonset : 2020-09-03T05:19+08:00
     * moonPhase : [{"fxTime":"2020-09-02T00:00+08:00","value":"0.48","name":"盈凸月","illumination":"100"},{"fxTime":"2020-09-02T01:00+08:00","value":"0.48","name":"盈凸月","illumination":"100"},{"fxTime":"2020-09-02T02:00+08:00","value":"0.48","name":"盈凸月","illumination":"100"},{"fxTime":"2020-09-02T03:00+08:00","value":"0.48","name":"盈凸月","illumination":"100"},{"fxTime":"2020-09-02T04:00+08:00","value":"0.48","name":"盈凸月","illumination":"100"},{"fxTime":"2020-09-02T05:00+08:00","value":"0.49","name":"盈凸月","illumination":"100"},{"fxTime":"2020-09-02T06:00+08:00","value":"0.49","name":"盈凸月","illumination":"100"},{"fxTime":"2020-09-02T07:00+08:00","value":"0.51","name":"亏凸月","illumination":"100"},{"fxTime":"2020-09-02T08:00+08:00","value":"0.51","name":"亏凸月","illumination":"100"},{"fxTime":"2020-09-02T09:00+08:00","value":"0.51","name":"亏凸月","illumination":"100"},{"fxTime":"2020-09-02T10:00+08:00","value":"0.51","name":"亏凸月","illumination":"100"},{"fxTime":"2020-09-02T11:00+08:00","value":"0.51","name":"亏凸月","illumination":"100"},{"fxTime":"2020-09-02T12:00+08:00","value":"0.51","name":"亏凸月","illumination":"100"},{"fxTime":"2020-09-02T13:00+08:00","value":"0.51","name":"亏凸月","illumination":"100"},{"fxTime":"2020-09-02T14:00+08:00","value":"0.51","name":"亏凸月","illumination":"100"},{"fxTime":"2020-09-02T15:00+08:00","value":"0.51","name":"亏凸月","illumination":"100"},{"fxTime":"2020-09-02T16:00+08:00","value":"0.52","name":"亏凸月","illumination":"100"},{"fxTime":"2020-09-02T17:00+08:00","value":"0.52","name":"亏凸月","illumination":"100"},{"fxTime":"2020-09-02T18:00+08:00","value":"0.52","name":"亏凸月","illumination":"100"},{"fxTime":"2020-09-02T19:00+08:00","value":"0.52","name":"亏凸月","illumination":"100"},{"fxTime":"2020-09-02T20:00+08:00","value":"0.52","name":"亏凸月","illumination":"100"},{"fxTime":"2020-09-02T21:00+08:00","value":"0.52","name":"亏凸月","illumination":"100"},{"fxTime":"2020-09-02T22:00+08:00","value":"0.52","name":"亏凸月","illumination":"100"},{"fxTime":"2020-09-02T23:00+08:00","value":"0.52","name":"亏凸月","illumination":"100"}]
     * refer : {"sources":["heweather.com"],"license":["no commercial use"]}
     */

    private String code;
    private String updateTime;
    private String fxLink;
    private String sunrise;
    private String sunset;
    private String moonrise;
    private String moonset;
    private ReferBean refer;
    private List<MoonPhaseBean> moonPhase;

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

    public ReferBean getRefer() {
        return refer;
    }

    public void setRefer(ReferBean refer) {
        this.refer = refer;
    }

    public List<MoonPhaseBean> getMoonPhase() {
        return moonPhase;
    }

    public void setMoonPhase(List<MoonPhaseBean> moonPhase) {
        this.moonPhase = moonPhase;
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

    public static class MoonPhaseBean {
        /**
         * fxTime : 2020-09-02T00:00+08:00
         * value : 0.48
         * name : 盈凸月
         * illumination : 100
         */

        private String fxTime;
        private String value;
        private String name;
        private String illumination;

        public String getFxTime() {
            return fxTime;
        }

        public void setFxTime(String fxTime) {
            this.fxTime = fxTime;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getIllumination() {
            return illumination;
        }

        public void setIllumination(String illumination) {
            this.illumination = illumination;
        }
    }
}
