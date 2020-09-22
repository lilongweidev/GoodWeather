package com.llw.goodweather.bean;

import java.util.List;

/**
 * 当天空气质量数据实体 V7
 *
 * @author llw
 */
public class AirNowResponse {

    /**
     * code : 200
     * updateTime : 2020-07-15T09:34+08:00
     * fxLink : http://hfx.link/2ax4
     * now : {"pubTime":"2020-07-15T09:00+08:00","aqi":"49","category":"优","primary":"NA","pm10":"49","pm2p5":"29","no2":"20","so2":"3","co":"0.6","o3":"84"}
     * station : [{"pubTime":"2020-07-15T09:00+08:00","name":"万寿西宫","id":"CNA1001","aqi":"68","level":"2","category":"良","primary":"PM2.5","pm10":"66","pm2p5":"49","no2":"29","so2":"3","co":"0.7","o3":"81"},{"pubTime":"2020-07-15T09:00+08:00","name":"定陵","id":"CNA1002","aqi":"36","level":"1","category":"优","primary":"NA","pm10":"36","pm2p5":"15","no2":"11","so2":"3","co":"0.4","o3":"84"},{"pubTime":"2020-07-15T09:00+08:00","name":"东四","id":"CNA1003","aqi":"46","level":"1","category":"优","primary":"NA","pm10":"44","pm2p5":"32","no2":"21","so2":"3","co":"0.9","o3":"95"},{"pubTime":"2020-07-15T09:00+08:00","name":"天坛","id":"CNA1004","aqi":"57","level":"2","category":"良","primary":"PM2.5","pm10":"56","pm2p5":"40","no2":"18","so2":"1","co":"0.7","o3":"96"},{"pubTime":"2020-07-15T09:00+08:00","name":"农展馆","id":"CNA1005","aqi":"55","level":"2","category":"良","primary":"PM10","pm10":"59","pm2p5":"35","no2":"26","so2":"3","co":"0.7","o3":"79"},{"pubTime":"2020-07-15T09:00+08:00","name":"官园","id":"CNA1006","aqi":"53","level":"2","category":"良","primary":"PM2.5","pm10":"52","pm2p5":"37","no2":"24","so2":"1","co":"0.6","o3":"91"},{"pubTime":"2020-07-15T09:00+08:00","name":"海淀区万柳","id":"CNA1007","aqi":"48","level":"1","category":"优","primary":"NA","pm10":"48","pm2p5":"28","no2":"28","so2":"1","co":"0.6","o3":"79"},{"pubTime":"2020-07-15T09:00+08:00","name":"顺义新城","id":"CNA1008","aqi":"46","level":"1","category":"优","primary":"NA","pm10":"46","pm2p5":"22","no2":"15","so2":"3","co":"0.5","o3":"85"},{"pubTime":"2020-07-15T09:00+08:00","name":"怀柔镇","id":"CNA1009","aqi":"43","level":"1","category":"优","primary":"NA","pm10":"43","pm2p5":"22","no2":"18","so2":"3","co":"0.5","o3":"78"},{"pubTime":"2020-07-15T09:00+08:00","name":"昌平镇","id":"CNA1010","aqi":"53","level":"2","category":"良","primary":"PM10","pm10":"56","pm2p5":"23","no2":"21","so2":"1","co":"0.5","o3":"68"},{"pubTime":"2020-07-15T09:00+08:00","name":"奥体中心","id":"CNA1011","aqi":"44","level":"1","category":"优","primary":"NA","pm10":"44","pm2p5":"22","no2":"17","so2":"3","co":"0.5","o3":"89"},{"pubTime":"2020-07-15T09:00+08:00","name":"古城","id":"CNA1012","aqi":"32","level":"1","category":"优","primary":"NA","pm10":"28","pm2p5":"22","no2":"11","so2":"3","co":"0.5","o3":"80"}]
     * refer : {"sources":["cnemc"],"license":["no commercial use"]}
     */

    private String code;
    private String updateTime;
    private String fxLink;
    private NowBean now;
    private ReferBean refer;
    private List<StationBean> station;

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

    public NowBean getNow() {
        return now;
    }

    public void setNow(NowBean now) {
        this.now = now;
    }

    public ReferBean getRefer() {
        return refer;
    }

    public void setRefer(ReferBean refer) {
        this.refer = refer;
    }

    public List<StationBean> getStation() {
        return station;
    }

    public void setStation(List<StationBean> station) {
        this.station = station;
    }

    public static class NowBean {
        /**
         * pubTime : 2020-07-15T09:00+08:00
         * aqi : 49
         * category : 优
         * primary : NA
         * pm10 : 49
         * pm2p5 : 29
         * no2 : 20
         * so2 : 3
         * co : 0.6
         * o3 : 84
         */

        private String pubTime;
        private String aqi;
        private String category;
        private String primary;
        private String pm10;
        private String pm2p5;
        private String no2;
        private String so2;
        private String co;
        private String o3;

        public String getPubTime() {
            return pubTime;
        }

        public void setPubTime(String pubTime) {
            this.pubTime = pubTime;
        }

        public String getAqi() {
            return aqi;
        }

        public void setAqi(String aqi) {
            this.aqi = aqi;
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

        public String getPm10() {
            return pm10;
        }

        public void setPm10(String pm10) {
            this.pm10 = pm10;
        }

        public String getPm2p5() {
            return pm2p5;
        }

        public void setPm2p5(String pm2p5) {
            this.pm2p5 = pm2p5;
        }

        public String getNo2() {
            return no2;
        }

        public void setNo2(String no2) {
            this.no2 = no2;
        }

        public String getSo2() {
            return so2;
        }

        public void setSo2(String so2) {
            this.so2 = so2;
        }

        public String getCo() {
            return co;
        }

        public void setCo(String co) {
            this.co = co;
        }

        public String getO3() {
            return o3;
        }

        public void setO3(String o3) {
            this.o3 = o3;
        }
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

    public static class StationBean {
        /**
         * pubTime : 2020-07-15T09:00+08:00
         * name : 万寿西宫
         * id : CNA1001
         * aqi : 68
         * level : 2
         * category : 良
         * primary : PM2.5
         * pm10 : 66
         * pm2p5 : 49
         * no2 : 29
         * so2 : 3
         * co : 0.7
         * o3 : 81
         */

        private String pubTime;
        private String name;
        private String id;
        private String aqi;
        private String level;
        private String category;
        private String primary;
        private String pm10;
        private String pm2p5;
        private String no2;
        private String so2;
        private String co;
        private String o3;

        public String getPubTime() {
            return pubTime;
        }

        public void setPubTime(String pubTime) {
            this.pubTime = pubTime;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
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

        public String getPm10() {
            return pm10;
        }

        public void setPm10(String pm10) {
            this.pm10 = pm10;
        }

        public String getPm2p5() {
            return pm2p5;
        }

        public void setPm2p5(String pm2p5) {
            this.pm2p5 = pm2p5;
        }

        public String getNo2() {
            return no2;
        }

        public void setNo2(String no2) {
            this.no2 = no2;
        }

        public String getSo2() {
            return so2;
        }

        public void setSo2(String so2) {
            this.so2 = so2;
        }

        public String getCo() {
            return co;
        }

        public void setCo(String co) {
            this.co = co;
        }

        public String getO3() {
            return o3;
        }

        public void setO3(String o3) {
            this.o3 = o3;
        }
    }
}
