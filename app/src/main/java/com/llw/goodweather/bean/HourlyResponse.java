package com.llw.goodweather.bean;

import java.util.List;

/**
 * 逐小时天气数据实体  V7
 *
 * @author llw
 */
public class HourlyResponse {
    /**
     * code : 200
     * updateTime : 2020-07-15T08:57+08:00
     * fxLink : http://hfx.link/2ax1
     * hourly : [{"fxTime":"2020-07-15T10:00+08:00","temp":"28","icon":"100","text":"晴","wind360":"194","windDir":"西南风","windScale":"1-2","windSpeed":"10","humidity":"40","pop":"0","precip":"0.0","pressure":"995","cloud":"0","dew":"19"},{"fxTime":"2020-07-15T11:00+08:00","temp":"29","icon":"100","text":"晴","wind360":"195","windDir":"西南风","windScale":"1-2","windSpeed":"6","humidity":"35","pop":"0","precip":"0.0","pressure":"995","cloud":"1","dew":"19"},{"fxTime":"2020-07-15T12:00+08:00","temp":"30","icon":"100","text":"晴","wind360":"199","windDir":"西南风","windScale":"1-2","windSpeed":"8","humidity":"33","pop":"0","precip":"0.0","pressure":"995","cloud":"1","dew":"19"},{"fxTime":"2020-07-15T13:00+08:00","temp":"31","icon":"100","text":"晴","wind360":"203","windDir":"西南风","windScale":"1-2","windSpeed":"7","humidity":"31","pop":"0","precip":"0.0","pressure":"996","cloud":"2","dew":"19"},{"fxTime":"2020-07-15T14:00+08:00","temp":"31","icon":"100","text":"晴","wind360":"257","windDir":"西南风","windScale":"1-2","windSpeed":"6","humidity":"29","pop":"0","precip":"0.0","pressure":"997","cloud":"2","dew":"19"},{"fxTime":"2020-07-15T15:00+08:00","temp":"31","icon":"100","text":"晴","wind360":"245","windDir":"西南风","windScale":"1-2","windSpeed":"2","humidity":"31","pop":"0","precip":"0.0","pressure":"997","cloud":"7","dew":"19"},{"fxTime":"2020-07-15T16:00+08:00","temp":"31","icon":"100","text":"晴","wind360":"199","windDir":"西南风","windScale":"1-2","windSpeed":"2","humidity":"32","pop":"0","precip":"0.0","pressure":"997","cloud":"13","dew":"19"},{"fxTime":"2020-07-15T17:00+08:00","temp":"31","icon":"101","text":"多云","wind360":"231","windDir":"西南风","windScale":"1-2","windSpeed":"7","humidity":"33","pop":"0","precip":"0.0","pressure":"997","cloud":"18","dew":"18"},{"fxTime":"2020-07-15T18:00+08:00","temp":"30","icon":"100","text":"晴","wind360":"249","windDir":"西南风","windScale":"1-2","windSpeed":"10","humidity":"38","pop":"0","precip":"0.0","pressure":"996","cloud":"18","dew":"19"},{"fxTime":"2020-07-15T19:00+08:00","temp":"28","icon":"100","text":"晴","wind360":"234","windDir":"西南风","windScale":"1-2","windSpeed":"5","humidity":"43","pop":"0","precip":"0.0","pressure":"996","cloud":"18","dew":"20"},{"fxTime":"2020-07-15T20:00+08:00","temp":"27","icon":"100","text":"晴","wind360":"206","windDir":"西南风","windScale":"1-2","windSpeed":"9","humidity":"47","pop":"0","precip":"0.0","pressure":"997","cloud":"18","dew":"20"},{"fxTime":"2020-07-15T21:00+08:00","temp":"26","icon":"100","text":"晴","wind360":"198","windDir":"西南风","windScale":"1-2","windSpeed":"6","humidity":"51","pop":"0","precip":"0.0","pressure":"997","cloud":"12","dew":"20"},{"fxTime":"2020-07-15T22:00+08:00","temp":"26","icon":"100","text":"晴","wind360":"249","windDir":"西南风","windScale":"1-2","windSpeed":"5","humidity":"54","pop":"0","precip":"0.0","pressure":"997","cloud":"6","dew":"20"},{"fxTime":"2020-07-15T23:00+08:00","temp":"25","icon":"100","text":"晴","wind360":"248","windDir":"西南风","windScale":"1-2","windSpeed":"4","humidity":"58","pop":"0","precip":"0.0","pressure":"997","cloud":"0","dew":"20"},{"fxTime":"2020-07-16T00:00+08:00","temp":"24","icon":"100","text":"晴","wind360":"230","windDir":"西南风","windScale":"1-2","windSpeed":"6","humidity":"60","pop":"0","precip":"0.0","pressure":"997","cloud":"1","dew":"19"},{"fxTime":"2020-07-16T01:00+08:00","temp":"24","icon":"100","text":"晴","wind360":"255","windDir":"西南风","windScale":"1-2","windSpeed":"4","humidity":"62","pop":"0","precip":"0.0","pressure":"997","cloud":"3","dew":"19"},{"fxTime":"2020-07-16T02:00+08:00","temp":"23","icon":"100","text":"晴","wind360":"230","windDir":"西南风","windScale":"1-2","windSpeed":"7","humidity":"63","pop":"0","precip":"0.0","pressure":"997","cloud":"4","dew":"20"},{"fxTime":"2020-07-16T03:00+08:00","temp":"23","icon":"100","text":"晴","wind360":"209","windDir":"西南风","windScale":"1-2","windSpeed":"1","humidity":"65","pop":"0","precip":"0.0","pressure":"997","cloud":"4","dew":"20"},{"fxTime":"2020-07-16T04:00+08:00","temp":"23","icon":"100","text":"晴","wind360":"213","windDir":"西南风","windScale":"1-2","windSpeed":"8","humidity":"66","pop":"0","precip":"0.0","pressure":"996","cloud":"4","dew":"20"},{"fxTime":"2020-07-16T05:00+08:00","temp":"22","icon":"100","text":"晴","wind360":"193","windDir":"西南风","windScale":"1-2","windSpeed":"7","humidity":"67","pop":"0","precip":"0.0","pressure":"995","cloud":"3","dew":"20"},{"fxTime":"2020-07-16T06:00+08:00","temp":"23","icon":"101","text":"多云","wind360":"216","windDir":"西南风","windScale":"1-2","windSpeed":"11","humidity":"70","pop":"0","precip":"0.0","pressure":"994","cloud":"20","dew":"21"},{"fxTime":"2020-07-16T07:00+08:00","temp":"25","icon":"101","text":"多云","wind360":"229","windDir":"西南风","windScale":"1-2","windSpeed":"11","humidity":"73","pop":"0","precip":"0.0","pressure":"994","cloud":"37","dew":"21"},{"fxTime":"2020-07-16T08:00+08:00","temp":"26","icon":"101","text":"多云","wind360":"200","windDir":"西南风","windScale":"1-2","windSpeed":"4","humidity":"77","pop":"0","precip":"0.0","pressure":"993","cloud":"54","dew":"21"},{"fxTime":"2020-07-16T09:00+08:00","temp":"28","icon":"101","text":"多云","wind360":"200","windDir":"西南风","windScale":"1-2","windSpeed":"4","humidity":"65","pop":"0","precip":"0.0","pressure":"993","cloud":"53","dew":"21"}]
     * refer : {"sources":["Weather China"],"license":["no commercial use"]}
     */

    private String code;
    private String updateTime;
    private String fxLink;
    private ReferBean refer;
    private List<HourlyBean> hourly;

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

    public List<HourlyBean> getHourly() {
        return hourly;
    }

    public void setHourly(List<HourlyBean> hourly) {
        this.hourly = hourly;
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

    public static class HourlyBean {
        /**
         * fxTime : 2020-07-15T10:00+08:00
         * temp : 28
         * icon : 100
         * text : 晴
         * wind360 : 194
         * windDir : 西南风
         * windScale : 1-2
         * windSpeed : 10
         * humidity : 40
         * pop : 0
         * precip : 0.0
         * pressure : 995
         * cloud : 0
         * dew : 19
         */

        private String fxTime;
        private String temp;
        private String icon;
        private String text;
        private String wind360;
        private String windDir;
        private String windScale;
        private String windSpeed;
        private String humidity;
        private String pop;
        private String precip;
        private String pressure;
        private String cloud;
        private String dew;

        public String getFxTime() {
            return fxTime;
        }

        public void setFxTime(String fxTime) {
            this.fxTime = fxTime;
        }

        public String getTemp() {
            return temp;
        }

        public void setTemp(String temp) {
            this.temp = temp;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getWind360() {
            return wind360;
        }

        public void setWind360(String wind360) {
            this.wind360 = wind360;
        }

        public String getWindDir() {
            return windDir;
        }

        public void setWindDir(String windDir) {
            this.windDir = windDir;
        }

        public String getWindScale() {
            return windScale;
        }

        public void setWindScale(String windScale) {
            this.windScale = windScale;
        }

        public String getWindSpeed() {
            return windSpeed;
        }

        public void setWindSpeed(String windSpeed) {
            this.windSpeed = windSpeed;
        }

        public String getHumidity() {
            return humidity;
        }

        public void setHumidity(String humidity) {
            this.humidity = humidity;
        }

        public String getPop() {
            return pop;
        }

        public void setPop(String pop) {
            this.pop = pop;
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

        public String getCloud() {
            return cloud;
        }

        public void setCloud(String cloud) {
            this.cloud = cloud;
        }

        public String getDew() {
            return dew;
        }

        public void setDew(String dew) {
            this.dew = dew;
        }
    }
}
