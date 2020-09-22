package com.llw.goodweather.bean;

import java.util.List;

/**
 * 更多空气质量数据实体
 *
 * @author llw
 */
public class MoreAirFiveResponse {

    /**
     * code : 200
     * updateTime : 2020-08-06T09:28+08:00
     * fxLink : http://hfx.link/2ax4
     * daily : [{"fxDate":"2020-08-06","aqi":"60","level":"2","category":"良","primary":"NA"},{"fxDate":"2020-08-07","aqi":"90","level":"2","category":"良","primary":"NA"},{"fxDate":"2020-08-08","aqi":"100","level":"2","category":"良","primary":"NA"},{"fxDate":"2020-08-09","aqi":"110","level":"3","category":"轻度污染","primary":"NA"},{"fxDate":"2020-08-10","aqi":"90","level":"2","category":"良","primary":"NA"}]
     * refer : {"sources":["cnemc"],"license":["no commercial use"]}
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
         * fxDate : 2020-08-06
         * aqi : 60
         * level : 2
         * category : 良
         * primary : NA
         */

        private String fxDate;
        private String aqi;
        private String level;
        private String category;
        private String primary;

        public String getFxDate() {
            return fxDate;
        }

        public void setFxDate(String fxDate) {
            this.fxDate = fxDate;
        }

        public String getAqi() {
            return aqi;
        }

        public void setAqi(String aqi) {
            this.aqi = aqi;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public String getPrimary() {
            return primary;
        }

        public void setPrimary(String primary) {
            this.primary = primary;
        }
    }
}
