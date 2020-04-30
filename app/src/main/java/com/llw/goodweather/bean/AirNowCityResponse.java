package com.llw.goodweather.bean;

import java.util.List;

public class AirNowCityResponse {

    private List<HeWeather6Bean> HeWeather6;

    public List<HeWeather6Bean> getHeWeather6() {
        return HeWeather6;
    }

    public void setHeWeather6(List<HeWeather6Bean> HeWeather6) {
        this.HeWeather6 = HeWeather6;
    }

    public static class HeWeather6Bean {
        /**
         * basic : {"cid":"CN101280601","location":"深圳","parent_city":"深圳","admin_area":"广东","cnty":"中国","lat":"22.54700089","lon":"114.08594513","tz":"+8.00"}
         * update : {"loc":"2020-04-30 10:27","utc":"2020-04-30 02:27"}
         * status : ok
         * air_now_city : {"aqi":"47","qlty":"优","main":"-","pm25":"23","pm10":"47","no2":"28","so2":"7","co":"0.6","o3":"95","pub_time":"2020-04-30 09:00"}
         * air_now_station : [{"air_sta":"通心岭子站","aqi":"43","asid":"CNA1356","co":"0.6","lat":"22.5545","lon":"114.1063","main":"-","no2":"10","o3":"104","pm10":"43","pm25":"23","pub_time":"2020-04-30 09:00","qlty":"优","so2":"7"},{"air_sta":"洪湖","aqi":"49","asid":"CNA1357","co":"0.5","lat":"22.5625","lon":"114.117","main":"-","no2":"17","o3":"113","pm10":"49","pm25":"27","pub_time":"2020-04-30 09:00","qlty":"优","so2":"8"},{"air_sta":"华侨城","aqi":"38","asid":"CNA1358","co":"0.5","lat":"22.5417","lon":"113.987","main":"-","no2":"18","o3":"105","pm10":"38","pm25":"20","pub_time":"2020-04-30 09:00","qlty":"优","so2":"6"},{"air_sta":"南海子站","aqi":"47","asid":"CNA1359","co":"0.5","lat":"22.5171","lon":"113.9181","main":"-","no2":"33","o3":"77","pm10":"47","pm25":"25","pub_time":"2020-04-30 09:00","qlty":"优","so2":"5"},{"air_sta":"盐田","aqi":"42","asid":"CNA1360","co":"0.5","lat":"22.5908","lon":"114.263","main":"-","no2":"30","o3":"83","pm10":"42","pm25":"13","pub_time":"2020-04-30 09:00","qlty":"优","so2":"9"},{"air_sta":"龙岗","aqi":"49","asid":"CNA1361","co":"0.6","lat":"22.7267","lon":"114.24","main":"-","no2":"32","o3":"98","pm10":"49","pm25":"27","pub_time":"2020-04-30 09:00","qlty":"优","so2":"9"},{"air_sta":"西乡","aqi":"54","asid":"CNA1362","co":"0.6","lat":"22.5794","lon":"113.891","main":"PM10","no2":"54","o3":"62","pm10":"58","pm25":"24","pub_time":"2020-04-30 09:00","qlty":"良","so2":"5"},{"air_sta":"南澳","aqi":"36","asid":"CNA1363","co":"0.5","lat":"22.5422","lon":"114.494","main":"-","no2":"19","o3":"113","pm10":"33","pm25":"22","pub_time":"2020-04-30 09:00","qlty":"优","so2":"4"},{"air_sta":"葵涌","aqi":"45","asid":"CNA1364","co":"0.5","lat":"22.6342","lon":"114.41","main":"-","no2":"26","o3":"87","pm10":"45","pm25":"18","pub_time":"2020-04-30 09:00","qlty":"优","so2":"4"},{"air_sta":"梅沙","aqi":"40","asid":"CNA1365","co":"0.5","lat":"22.5978","lon":"114.297","main":"-","no2":"20","o3":"100","pm10":"40","pm25":"21","pub_time":"2020-04-30 09:00","qlty":"优","so2":"8"},{"air_sta":"观澜","aqi":"59","asid":"CNA1366","co":"0.8","lat":"22.75","lon":"114.085","main":"PM10","no2":"40","o3":"93","pm10":"68","pm25":"29","pub_time":"2020-04-30 09:00","qlty":"良","so2":"5"}]
         */

        private BasicBean basic;
        private UpdateBean update;
        private String status;
        private AirNowCityBean air_now_city;
        private List<AirNowStationBean> air_now_station;

        public BasicBean getBasic() {
            return basic;
        }

        public void setBasic(BasicBean basic) {
            this.basic = basic;
        }

        public UpdateBean getUpdate() {
            return update;
        }

        public void setUpdate(UpdateBean update) {
            this.update = update;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public AirNowCityBean getAir_now_city() {
            return air_now_city;
        }

        public void setAir_now_city(AirNowCityBean air_now_city) {
            this.air_now_city = air_now_city;
        }

        public List<AirNowStationBean> getAir_now_station() {
            return air_now_station;
        }

        public void setAir_now_station(List<AirNowStationBean> air_now_station) {
            this.air_now_station = air_now_station;
        }

        public static class BasicBean {
            /**
             * cid : CN101280601
             * location : 深圳
             * parent_city : 深圳
             * admin_area : 广东
             * cnty : 中国
             * lat : 22.54700089
             * lon : 114.08594513
             * tz : +8.00
             */

            private String cid;
            private String location;
            private String parent_city;
            private String admin_area;
            private String cnty;
            private String lat;
            private String lon;
            private String tz;

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
        }

        public static class UpdateBean {
            /**
             * loc : 2020-04-30 10:27
             * utc : 2020-04-30 02:27
             */

            private String loc;
            private String utc;

            public String getLoc() {
                return loc;
            }

            public void setLoc(String loc) {
                this.loc = loc;
            }

            public String getUtc() {
                return utc;
            }

            public void setUtc(String utc) {
                this.utc = utc;
            }
        }

        public static class AirNowCityBean {
            /**
             * aqi : 47
             * qlty : 优
             * main : -
             * pm25 : 23
             * pm10 : 47
             * no2 : 28
             * so2 : 7
             * co : 0.6
             * o3 : 95
             * pub_time : 2020-04-30 09:00
             */

            private String aqi;
            private String qlty;
            private String main;
            private String pm25;
            private String pm10;
            private String no2;
            private String so2;
            private String co;
            private String o3;
            private String pub_time;

            public String getAqi() {
                return aqi;
            }

            public void setAqi(String aqi) {
                this.aqi = aqi;
            }

            public String getQlty() {
                return qlty;
            }

            public void setQlty(String qlty) {
                this.qlty = qlty;
            }

            public String getMain() {
                return main;
            }

            public void setMain(String main) {
                this.main = main;
            }

            public String getPm25() {
                return pm25;
            }

            public void setPm25(String pm25) {
                this.pm25 = pm25;
            }

            public String getPm10() {
                return pm10;
            }

            public void setPm10(String pm10) {
                this.pm10 = pm10;
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

            public String getPub_time() {
                return pub_time;
            }

            public void setPub_time(String pub_time) {
                this.pub_time = pub_time;
            }
        }

        public static class AirNowStationBean {
            /**
             * air_sta : 通心岭子站
             * aqi : 43
             * asid : CNA1356
             * co : 0.6
             * lat : 22.5545
             * lon : 114.1063
             * main : -
             * no2 : 10
             * o3 : 104
             * pm10 : 43
             * pm25 : 23
             * pub_time : 2020-04-30 09:00
             * qlty : 优
             * so2 : 7
             */

            private String air_sta;
            private String aqi;
            private String asid;
            private String co;
            private String lat;
            private String lon;
            private String main;
            private String no2;
            private String o3;
            private String pm10;
            private String pm25;
            private String pub_time;
            private String qlty;
            private String so2;

            public String getAir_sta() {
                return air_sta;
            }

            public void setAir_sta(String air_sta) {
                this.air_sta = air_sta;
            }

            public String getAqi() {
                return aqi;
            }

            public void setAqi(String aqi) {
                this.aqi = aqi;
            }

            public String getAsid() {
                return asid;
            }

            public void setAsid(String asid) {
                this.asid = asid;
            }

            public String getCo() {
                return co;
            }

            public void setCo(String co) {
                this.co = co;
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

            public String getMain() {
                return main;
            }

            public void setMain(String main) {
                this.main = main;
            }

            public String getNo2() {
                return no2;
            }

            public void setNo2(String no2) {
                this.no2 = no2;
            }

            public String getO3() {
                return o3;
            }

            public void setO3(String o3) {
                this.o3 = o3;
            }

            public String getPm10() {
                return pm10;
            }

            public void setPm10(String pm10) {
                this.pm10 = pm10;
            }

            public String getPm25() {
                return pm25;
            }

            public void setPm25(String pm25) {
                this.pm25 = pm25;
            }

            public String getPub_time() {
                return pub_time;
            }

            public void setPub_time(String pub_time) {
                this.pub_time = pub_time;
            }

            public String getQlty() {
                return qlty;
            }

            public void setQlty(String qlty) {
                this.qlty = qlty;
            }

            public String getSo2() {
                return so2;
            }

            public void setSo2(String so2) {
                this.so2 = so2;
            }
        }
    }
}
