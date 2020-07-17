package com.llw.goodweather.bean;

import java.util.List;

public class NewSearchCityResponse {

    /**
     * status : 200
     * location : [{"name":"北京","id":"101010100","lat":"39.90498","lon":"116.40528","adm2":"北京","adm1":"北京","country":"中国","tz":"Asia/Shanghai","utcOffset":"+08:00","isDst":"0","type":"city","rank":"10","fxLink":"http://hfx.link/2ax1"},{"name":"朝阳","id":"101010300","lat":"39.92148","lon":"116.48641","adm2":"北京","adm1":"北京","country":"中国","tz":"Asia/Shanghai","utcOffset":"+08:00","isDst":"0","type":"city","rank":"15","fxLink":"http://hfx.link/2az1"},{"name":"海淀","id":"101010200","lat":"39.95607","lon":"116.31031","adm2":"北京","adm1":"北京","country":"中国","tz":"Asia/Shanghai","utcOffset":"+08:00","isDst":"0","type":"city","rank":"15","fxLink":"http://hfx.link/2ay1"},{"name":"丰台","id":"101010900","lat":"39.86364","lon":"116.28696","adm2":"北京","adm1":"北京","country":"中国","tz":"Asia/Shanghai","utcOffset":"+08:00","isDst":"0","type":"city","rank":"25","fxLink":"http://hfx.link/2b51"},{"name":"大兴","id":"101011100","lat":"39.72890","lon":"116.33803","adm2":"北京","adm1":"北京","country":"中国","tz":"Asia/Shanghai","utcOffset":"+08:00","isDst":"0","type":"city","rank":"25","fxLink":"http://hfx.link/2b71"},{"name":"房山","id":"101011200","lat":"39.73553","lon":"116.13916","adm2":"北京","adm1":"北京","country":"中国","tz":"Asia/Shanghai","utcOffset":"+08:00","isDst":"0","type":"city","rank":"23","fxLink":"http://hfx.link/2b81"},{"name":"通州","id":"101010600","lat":"39.90248","lon":"116.65859","adm2":"北京","adm1":"北京","country":"中国","tz":"Asia/Shanghai","utcOffset":"+08:00","isDst":"0","type":"city","rank":"23","fxLink":"http://hfx.link/2b21"},{"name":"石景山","id":"101011000","lat":"39.91460","lon":"116.19544","adm2":"北京","adm1":"北京","country":"中国","tz":"Asia/Shanghai","utcOffset":"+08:00","isDst":"0","type":"city","rank":"35","fxLink":"http://hfx.link/2b61"},{"name":"昌平","id":"101010700","lat":"40.21808","lon":"116.23590","adm2":"北京","adm1":"北京","country":"中国","tz":"Asia/Shanghai","utcOffset":"+08:00","isDst":"0","type":"city","rank":"23","fxLink":"http://hfx.link/2b31"},{"name":"顺义","id":"101010400","lat":"40.12893","lon":"116.65352","adm2":"北京","adm1":"北京","country":"中国","tz":"Asia/Shanghai","utcOffset":"+08:00","isDst":"0","type":"city","rank":"33","fxLink":"http://hfx.link/2b01"}]
     * refer : {"sources":["heweather.com"],"license":["commercial license"]}
     */

    private String status;
    private ReferBean refer;
    private List<LocationBean> location;

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

    public List<LocationBean> getLocation() {
        return location;
    }

    public void setLocation(List<LocationBean> location) {
        this.location = location;
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

    public static class LocationBean {
        /**
         * name : 北京
         * id : 101010100
         * lat : 39.90498
         * lon : 116.40528
         * adm2 : 北京
         * adm1 : 北京
         * country : 中国
         * tz : Asia/Shanghai
         * utcOffset : +08:00
         * isDst : 0
         * type : city
         * rank : 10
         * fxLink : http://hfx.link/2ax1
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
