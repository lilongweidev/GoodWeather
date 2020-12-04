package com.llw.goodweather.bean;

import java.util.List;

/**
 * 分钟级降水 V7
 * @author llw
 */
public class MinutePrecResponse {

    /**
     * code : 200
     * updateTime : 2020-12-02T10:00+08:00
     * fxLink : http://hfx.link/1
     * summary : 未来两小时无降水
     * minutely : [{"fxTime":"2020-12-02T10:00+08:00","precip":"0.0","type":"rain"},{"fxTime":"2020-12-02T10:05+08:00","precip":"0.0","type":"rain"},{"fxTime":"2020-12-02T10:10+08:00","precip":"0.0","type":"rain"},{"fxTime":"2020-12-02T10:15+08:00","precip":"0.0","type":"rain"},{"fxTime":"2020-12-02T10:20+08:00","precip":"0.0","type":"rain"},{"fxTime":"2020-12-02T10:25+08:00","precip":"0.0","type":"rain"},{"fxTime":"2020-12-02T10:30+08:00","precip":"0.0","type":"rain"},{"fxTime":"2020-12-02T10:35+08:00","precip":"0.0","type":"rain"},{"fxTime":"2020-12-02T10:40+08:00","precip":"0.0","type":"rain"},{"fxTime":"2020-12-02T10:45+08:00","precip":"0.0","type":"rain"},{"fxTime":"2020-12-02T10:50+08:00","precip":"0.0","type":"rain"},{"fxTime":"2020-12-02T10:55+08:00","precip":"0.0","type":"rain"},{"fxTime":"2020-12-02T11:00+08:00","precip":"0.0","type":"rain"},{"fxTime":"2020-12-02T11:05+08:00","precip":"0.0","type":"rain"},{"fxTime":"2020-12-02T11:10+08:00","precip":"0.0","type":"rain"},{"fxTime":"2020-12-02T11:15+08:00","precip":"0.0","type":"rain"},{"fxTime":"2020-12-02T11:20+08:00","precip":"0.0","type":"rain"},{"fxTime":"2020-12-02T11:25+08:00","precip":"0.0","type":"rain"},{"fxTime":"2020-12-02T11:30+08:00","precip":"0.0","type":"rain"},{"fxTime":"2020-12-02T11:35+08:00","precip":"0.0","type":"rain"},{"fxTime":"2020-12-02T11:40+08:00","precip":"0.0","type":"rain"},{"fxTime":"2020-12-02T11:45+08:00","precip":"0.0","type":"rain"},{"fxTime":"2020-12-02T11:50+08:00","precip":"0.0","type":"rain"},{"fxTime":"2020-12-02T11:55+08:00","precip":"0.0","type":"rain"}]
     * refer : {"sources":["Weather China"],"license":["no commercial use"]}
     */

    private String code;
    private String updateTime;
    private String fxLink;
    private String summary;
    private ReferBean refer;
    private List<MinutelyBean> minutely;

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

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public ReferBean getRefer() {
        return refer;
    }

    public void setRefer(ReferBean refer) {
        this.refer = refer;
    }

    public List<MinutelyBean> getMinutely() {
        return minutely;
    }

    public void setMinutely(List<MinutelyBean> minutely) {
        this.minutely = minutely;
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

    public static class MinutelyBean {
        /**
         * fxTime : 2020-12-02T10:00+08:00
         * precip : 0.0
         * type : rain
         */

        private String fxTime;
        private String precip;
        private String type;

        public String getFxTime() {
            return fxTime;
        }

        public void setFxTime(String fxTime) {
            this.fxTime = fxTime;
        }

        public String getPrecip() {
            return precip;
        }

        public void setPrecip(String precip) {
            this.precip = precip;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
