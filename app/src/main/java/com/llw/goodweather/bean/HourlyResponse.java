package com.llw.goodweather.bean;


import java.util.List;

public class HourlyResponse {

    private List<HeWeather6Bean> HeWeather6;

    public List<HeWeather6Bean> getHeWeather6() {
        return HeWeather6;
    }

    public void setHeWeather6(List<HeWeather6Bean> HeWeather6) {
        this.HeWeather6 = HeWeather6;
    }

    public static class HeWeather6Bean {
        /**
         * basic : {"cid":"CN101280603","location":"福田","parent_city":"深圳","admin_area":"广东","cnty":"中国","lat":"22.5410099","lon":"114.05095673","tz":"+8.00"}
         * update : {"loc":"2020-04-28 19:56","utc":"2020-04-28 11:56"}
         * status : ok
         * hourly : [{"cloud":"43","cond_code":"101","cond_txt":"多云","dew":"19","hum":"71","pop":"0","pres":"1010","time":"2020-04-28 22:00","tmp":"23","wind_deg":"82","wind_dir":"东风","wind_sc":"1-2","wind_spd":"2"},{"cloud":"35","cond_code":"100","cond_txt":"晴","dew":"18","hum":"74","pop":"0","pres":"1011","time":"2020-04-29 01:00","tmp":"22","wind_deg":"14","wind_dir":"东北风","wind_sc":"1-2","wind_spd":"11"},{"cloud":"69","cond_code":"100","cond_txt":"晴","dew":"18","hum":"72","pop":"0","pres":"1011","time":"2020-04-29 04:00","tmp":"21","wind_deg":"43","wind_dir":"东北风","wind_sc":"1-2","wind_spd":"4"},{"cloud":"90","cond_code":"101","cond_txt":"多云","dew":"19","hum":"62","pop":"0","pres":"1008","time":"2020-04-29 07:00","tmp":"21","wind_deg":"53","wind_dir":"东北风","wind_sc":"1-2","wind_spd":"9"},{"cloud":"91","cond_code":"101","cond_txt":"多云","dew":"19","hum":"58","pop":"0","pres":"1008","time":"2020-04-29 10:00","tmp":"27","wind_deg":"45","wind_dir":"东北风","wind_sc":"1-2","wind_spd":"10"},{"cloud":"75","cond_code":"101","cond_txt":"多云","dew":"18","hum":"59","pop":"0","pres":"1009","time":"2020-04-29 13:00","tmp":"29","wind_deg":"32","wind_dir":"东北风","wind_sc":"1-2","wind_spd":"4"},{"cloud":"59","cond_code":"100","cond_txt":"晴","dew":"18","hum":"60","pop":"0","pres":"1009","time":"2020-04-29 16:00","tmp":"27","wind_deg":"50","wind_dir":"东北风","wind_sc":"1-2","wind_spd":"6"},{"cloud":"37","cond_code":"101","cond_txt":"多云","dew":"19","hum":"61","pop":"0","pres":"1008","time":"2020-04-29 19:00","tmp":"26","wind_deg":"-1","wind_dir":"无持续风向","wind_sc":"1-2","wind_spd":"2"}]
         */

        private BasicBean basic;
        private UpdateBean update;
        private String status;
        private List<HourlyBean> hourly;

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

        public List<HourlyBean> getHourly() {
            return hourly;
        }

        public void setHourly(List<HourlyBean> hourly) {
            this.hourly = hourly;
        }

        public static class BasicBean {
            /**
             * cid : CN101280603
             * location : 福田
             * parent_city : 深圳
             * admin_area : 广东
             * cnty : 中国
             * lat : 22.5410099
             * lon : 114.05095673
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
             * loc : 2020-04-28 19:56
             * utc : 2020-04-28 11:56
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

        public static class HourlyBean {
            /**
             * cloud : 43
             * cond_code : 101
             * cond_txt : 多云
             * dew : 19
             * hum : 71
             * pop : 0
             * pres : 1010
             * time : 2020-04-28 22:00
             * tmp : 23
             * wind_deg : 82
             * wind_dir : 东风
             * wind_sc : 1-2
             * wind_spd : 2
             */

            private String cloud;
            private String cond_code;
            private String cond_txt;
            private String dew;
            private String hum;
            private String pop;
            private String pres;
            private String time;
            private String tmp;
            private String wind_deg;
            private String wind_dir;
            private String wind_sc;
            private String wind_spd;

            public String getCloud() {
                return cloud;
            }

            public void setCloud(String cloud) {
                this.cloud = cloud;
            }

            public String getCond_code() {
                return cond_code;
            }

            public void setCond_code(String cond_code) {
                this.cond_code = cond_code;
            }

            public String getCond_txt() {
                return cond_txt;
            }

            public void setCond_txt(String cond_txt) {
                this.cond_txt = cond_txt;
            }

            public String getDew() {
                return dew;
            }

            public void setDew(String dew) {
                this.dew = dew;
            }

            public String getHum() {
                return hum;
            }

            public void setHum(String hum) {
                this.hum = hum;
            }

            public String getPop() {
                return pop;
            }

            public void setPop(String pop) {
                this.pop = pop;
            }

            public String getPres() {
                return pres;
            }

            public void setPres(String pres) {
                this.pres = pres;
            }

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }

            public String getTmp() {
                return tmp;
            }

            public void setTmp(String tmp) {
                this.tmp = tmp;
            }

            public String getWind_deg() {
                return wind_deg;
            }

            public void setWind_deg(String wind_deg) {
                this.wind_deg = wind_deg;
            }

            public String getWind_dir() {
                return wind_dir;
            }

            public void setWind_dir(String wind_dir) {
                this.wind_dir = wind_dir;
            }

            public String getWind_sc() {
                return wind_sc;
            }

            public void setWind_sc(String wind_sc) {
                this.wind_sc = wind_sc;
            }

            public String getWind_spd() {
                return wind_spd;
            }

            public void setWind_spd(String wind_spd) {
                this.wind_spd = wind_spd;
            }
        }
    }
}
