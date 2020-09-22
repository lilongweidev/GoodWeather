package com.llw.goodweather.bean;

import java.util.List;

/**
 * 搜索城市数据实体
 *
 * @author llw
 */
public class SearchCityResponse {

    private List<HeWeather6Bean> HeWeather6;

    public List<HeWeather6Bean> getHeWeather6() {
        return HeWeather6;
    }

    public void setHeWeather6(List<HeWeather6Bean> HeWeather6) {
        this.HeWeather6 = HeWeather6;
    }

    public static class HeWeather6Bean {
        /**
         * basic : [{"cid":"CN101010100","location":"北京","parent_city":"北京","admin_area":"北京","cnty":"中国","lat":"39.90498734","lon":"116.4052887","tz":"+8.00","type":"city"},{"cid":"CN101132101","location":"北屯","parent_city":"北屯","admin_area":"新疆","cnty":"中国","lat":"47.35317612","lon":"87.82492828","tz":"+8.00","type":"city"},{"cid":"CN101340101","location":"台北","parent_city":"台北","admin_area":"台湾","cnty":"中国","lat":"25.04000092","lon":"121.51599884","tz":"+8.00","type":"city"},{"cid":"CN101221201","location":"淮北","parent_city":"淮北","admin_area":"安徽","cnty":"中国","lat":"33.97170639","lon":"116.79466248","tz":"+8.00","type":"city"},{"cid":"CN101301301","location":"北海","parent_city":"北海","admin_area":"广西","cnty":"中国","lat":"21.4733429","lon":"109.11925507","tz":"+8.00","type":"city"},{"cid":"CN101090303","location":"张北","parent_city":"张家口","admin_area":"河北","cnty":"中国","lat":"41.15171432","lon":"114.71595001","tz":"+8.00","type":"city"},{"cid":"4A570","location":"北雅加达","parent_city":"北雅加达","admin_area":"雅加达","cnty":"印度尼西亚","lat":"-6.18638897","lon":"106.82944489","tz":"+7.00","type":"city"},{"cid":"CN101091106","location":"北戴河","parent_city":"秦皇岛","admin_area":"河北","cnty":"中国","lat":"39.82512283","lon":"119.48628235","tz":"+8.00","type":"city"},{"cid":"34272","location":"Beitou District","parent_city":"台北市","admin_area":"台湾","cnty":"中国","lat":"25.11669922","lon":"121.5","tz":"+8.00","type":"city"},{"cid":"VN1591449","location":"北宁市","parent_city":"北宁市","admin_area":"北宁省","cnty":"越南","lat":"21.18333244","lon":"106.05000305","tz":"+7.00","type":"city"}]
         * status : ok
         */

        private String status;
        private List<BasicBean> basic;

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public List<BasicBean> getBasic() {
            return basic;
        }

        public void setBasic(List<BasicBean> basic) {
            this.basic = basic;
        }

        public static class BasicBean {
            /**
             * cid : CN101010100
             * location : 北京
             * parent_city : 北京
             * admin_area : 北京
             * cnty : 中国
             * lat : 39.90498734
             * lon : 116.4052887
             * tz : +8.00
             * type : city
             */

            private String cid;
            private String location;
            private String parent_city;
            private String admin_area;
            private String cnty;
            private String lat;
            private String lon;
            private String tz;
            private String type;

            public String getCid() {
                return cid;
            }

            public void setCid(String cid) {
                this.cid = cid;
            }

            public String getLocation() {
                return location;
            }

            public void setLocation(String location) {
                this.location = location;
            }

            public String getParent_city() {
                return parent_city;
            }

            public void setParent_city(String parent_city) {
                this.parent_city = parent_city;
            }

            public String getAdmin_area() {
                return admin_area;
            }

            public void setAdmin_area(String admin_area) {
                this.admin_area = admin_area;
            }

            public String getCnty() {
                return cnty;
            }

            public void setCnty(String cnty) {
                this.cnty = cnty;
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

            public String getTz() {
                return tz;
            }

            public void setTz(String tz) {
                this.tz = tz;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }
        }
    }
}
