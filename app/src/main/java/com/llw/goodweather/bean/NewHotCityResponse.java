package com.llw.goodweather.bean;

import java.util.List;

public class NewHotCityResponse {

    /**
     * status : 200
     * topCityList : [{"name":"余杭","id":"101210106","lat":"30.42118","lon":"120.30173","adm2":"杭州","adm1":"浙江","country":"中国","tz":"Asia/Shanghai","utcOffset":"+08:00","isDst":"0","type":"city","rank":"25","fxLink":"http://hfx.link/32t1"},{"name":"嘉定","id":"101020500","lat":"31.38352","lon":"121.25033","adm2":"上海","adm1":"上海","country":"中国","tz":"Asia/Shanghai","utcOffset":"+08:00","isDst":"0","type":"city","rank":"23","fxLink":"http://hfx.link/2bf1"},{"name":"杭州","id":"101210101","lat":"30.28745","lon":"120.15357","adm2":"杭州","adm1":"浙江","country":"中国","tz":"Asia/Shanghai","utcOffset":"+08:00","isDst":"0","type":"city","rank":"11","fxLink":"http://hfx.link/32o1"},{"name":"上海","id":"101020100","lat":"31.23170","lon":"121.47264","adm2":"上海","adm1":"上海","country":"中国","tz":"Asia/Shanghai","utcOffset":"+08:00","isDst":"0","type":"city","rank":"11","fxLink":"http://hfx.link/2bc1"},{"name":"萧山","id":"101210102","lat":"30.16293","lon":"120.27069","adm2":"杭州","adm1":"浙江","country":"中国","tz":"Asia/Shanghai","utcOffset":"+08:00","isDst":"0","type":"city","rank":"23","fxLink":"http://hfx.link/32p1"},{"name":"朝阳","id":"101010300","lat":"39.92148","lon":"116.48641","adm2":"北京","adm1":"北京","country":"中国","tz":"Asia/Shanghai","utcOffset":"+08:00","isDst":"0","type":"city","rank":"15","fxLink":"http://hfx.link/2az1"},{"name":"江干","id":"101210111","lat":"30.26660","lon":"120.20263","adm2":"杭州","adm1":"浙江","country":"中国","tz":"Asia/Shanghai","utcOffset":"+08:00","isDst":"0","type":"city","rank":"25","fxLink":"http://hfx.link/1tvn1"},{"name":"海淀","id":"101010200","lat":"39.95607","lon":"116.31031","adm2":"北京","adm1":"北京","country":"中国","tz":"Asia/Shanghai","utcOffset":"+08:00","isDst":"0","type":"city","rank":"15","fxLink":"http://hfx.link/2ay1"},{"name":"滨江","id":"101210114","lat":"30.20661","lon":"120.21061","adm2":"杭州","adm1":"浙江","country":"中国","tz":"Asia/Shanghai","utcOffset":"+08:00","isDst":"0","type":"city","rank":"35","fxLink":"http://hfx.link/1tvq1"},{"name":"西湖","id":"101210113","lat":"30.27293","lon":"120.14737","adm2":"杭州","adm1":"浙江","country":"中国","tz":"Asia/Shanghai","utcOffset":"+08:00","isDst":"0","type":"city","rank":"35","fxLink":"http://hfx.link/1tvp1"},{"name":"深圳","id":"101280601","lat":"22.54700","lon":"114.08594","adm2":"深圳","adm1":"广东","country":"中国","tz":"Asia/Shanghai","utcOffset":"+08:00","isDst":"0","type":"city","rank":"13","fxLink":"http://hfx.link/3i71"},{"name":"金水","id":"101180112","lat":"34.77583","lon":"113.68603","adm2":"郑州","adm1":"河南","country":"中国","tz":"Asia/Shanghai","utcOffset":"+08:00","isDst":"0","type":"city","rank":"25","fxLink":"http://hfx.link/1tst1"},{"name":"北京","id":"101010100","lat":"39.90498","lon":"116.40528","adm2":"北京","adm1":"北京","country":"中国","tz":"Asia/Shanghai","utcOffset":"+08:00","isDst":"0","type":"city","rank":"10","fxLink":"http://hfx.link/2ax1"},{"name":"宝山","id":"101020300","lat":"31.39889","lon":"121.48993","adm2":"上海","adm1":"上海","country":"中国","tz":"Asia/Shanghai","utcOffset":"+08:00","isDst":"0","type":"city","rank":"23","fxLink":"http://hfx.link/2be1"},{"name":"拱墅","id":"101210112","lat":"30.31469","lon":"120.15005","adm2":"杭州","adm1":"浙江","country":"中国","tz":"Asia/Shanghai","utcOffset":"+08:00","isDst":"0","type":"city","rank":"35","fxLink":"http://hfx.link/1tvo1"},{"name":"栖霞","id":"101190112","lat":"32.10214","lon":"118.80870","adm2":"南京","adm1":"江苏","country":"中国","tz":"Asia/Shanghai","utcOffset":"+08:00","isDst":"0","type":"city","rank":"35","fxLink":"http://hfx.link/1tu61"},{"name":"浦东新区","id":"101020600","lat":"31.24594","lon":"121.56770","adm2":"上海","adm1":"上海","country":"中国","tz":"Asia/Shanghai","utcOffset":"+08:00","isDst":"0","type":"city","rank":"15","fxLink":"http://hfx.link/2bg1"},{"name":"金凤","id":"101170107","lat":"38.47735","lon":"106.22848","adm2":"银川","adm1":"宁夏","country":"中国","tz":"Asia/Shanghai","utcOffset":"+08:00","isDst":"0","type":"city","rank":"35","fxLink":"http://hfx.link/1tsk1"},{"name":"苏州","id":"101190401","lat":"31.29937","lon":"120.61958","adm2":"苏州","adm1":"江苏","country":"中国","tz":"Asia/Shanghai","utcOffset":"+08:00","isDst":"0","type":"city","rank":"13","fxLink":"http://hfx.link/30y1"},{"name":"郑州","id":"101180101","lat":"34.75797","lon":"113.66541","adm2":"郑州","adm1":"河南","country":"中国","tz":"Asia/Shanghai","utcOffset":"+08:00","isDst":"0","type":"city","rank":"11","fxLink":"http://hfx.link/2qk1"}]
     * refer : {"sources":["heweather.com"],"license":["commercial license"]}
     */

    private String status;
    private ReferBean refer;
    private List<TopCityListBean> topCityList;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ReferBean getRefer() {
        return refer;
    }

    public void setRefer(ReferBean refer) {
        this.refer = refer;
    }

    public List<TopCityListBean> getTopCityList() {
        return topCityList;
    }

    public void setTopCityList(List<TopCityListBean> topCityList) {
        this.topCityList = topCityList;
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

    public static class TopCityListBean {
        /**
         * name : 余杭
         * id : 101210106
         * lat : 30.42118
         * lon : 120.30173
         * adm2 : 杭州
         * adm1 : 浙江
         * country : 中国
         * tz : Asia/Shanghai
         * utcOffset : +08:00
         * isDst : 0
         * type : city
         * rank : 25
         * fxLink : http://hfx.link/32t1
         */

        private String name;
        private String id;
        private String lat;
        private String lon;
        private String adm2;
        private String adm1;
        private String country;
        private String tz;
        private String utcOffset;
        private String isDst;
        private String type;
        private String rank;
        private String fxLink;

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

        public String getLat() {
            return lat;
        }

        public void setLat(String lat) {
            this.lat = lat;
        }

        public String getLon() {
            return lon;
        }

        public void setLon(String lon) {
            this.lon = lon;
        }

        public String getAdm2() {
            return adm2;
        }

        public void setAdm2(String adm2) {
            this.adm2 = adm2;
        }

        public String getAdm1() {
            return adm1;
        }

        public void setAdm1(String adm1) {
            this.adm1 = adm1;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getTz() {
            return tz;
        }

        public void setTz(String tz) {
            this.tz = tz;
        }

        public String getUtcOffset() {
            return utcOffset;
        }

        public void setUtcOffset(String utcOffset) {
            this.utcOffset = utcOffset;
        }

        public String getIsDst() {
            return isDst;
        }

        public void setIsDst(String isDst) {
            this.isDst = isDst;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getRank() {
            return rank;
        }

        public void setRank(String rank) {
            this.rank = rank;
        }

        public String getFxLink() {
            return fxLink;
        }

        public void setFxLink(String fxLink) {
            this.fxLink = fxLink;
        }
    }
}
