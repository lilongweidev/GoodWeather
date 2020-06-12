package com.llw.goodweather.bean;

import java.util.List;
/**
 * 天气数据实体
 */
public class WeatherResponse {

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
         * update : {"loc":"2020-05-16 10:03","utc":"2020-05-16 02:03"}
         * status : ok
         * now : {"cloud":"10","cond_code":"100","cond_txt":"晴","fl":"33","hum":"74","pcpn":"0.0","pres":"1001","tmp":"29","vis":"16","wind_deg":"227","wind_dir":"西南风","wind_sc":"1","wind_spd":"2"}
         * daily_forecast : [{"cond_code_d":"101","cond_code_n":"104","cond_txt_d":"多云","cond_txt_n":"阴","date":"2020-05-16","hum":"83","mr":"01:57","ms":"13:36","pcpn":"0.0","pop":"4","pres":"998","sr":"05:42","ss":"18:57","tmp_max":"34","tmp_min":"24","uv_index":"9","vis":"25","wind_deg":"-1","wind_dir":"无持续风向","wind_sc":"1-2","wind_spd":"6"},{"cond_code_d":"302","cond_code_n":"306","cond_txt_d":"雷阵雨","cond_txt_n":"中雨","date":"2020-05-17","hum":"83","mr":"02:31","ms":"14:26","pcpn":"0.0","pop":"25","pres":"995","sr":"05:42","ss":"18:58","tmp_max":"33","tmp_min":"25","uv_index":"1","vis":"15","wind_deg":"-1","wind_dir":"无持续风向","wind_sc":"1-2","wind_spd":"11"},{"cond_code_d":"306","cond_code_n":"307","cond_txt_d":"中雨","cond_txt_n":"大雨","date":"2020-05-18","hum":"88","mr":"03:04","ms":"15:15","pcpn":"5.1","pop":"80","pres":"996","sr":"05:41","ss":"18:58","tmp_max":"28","tmp_min":"24","uv_index":"2","vis":"22","wind_deg":"-1","wind_dir":"无持续风向","wind_sc":"1-2","wind_spd":"6"},{"cond_code_d":"306","cond_code_n":"300","cond_txt_d":"中雨","cond_txt_n":"阵雨","date":"2020-05-19","hum":"78","mr":"03:37","ms":"16:04","pcpn":"6.1","pop":"68","pres":"997","sr":"05:41","ss":"18:59","tmp_max":"30","tmp_min":"26","uv_index":"0","vis":"22","wind_deg":"234","wind_dir":"西南风","wind_sc":"3-4","wind_spd":"15"},{"cond_code_d":"300","cond_code_n":"316","cond_txt_d":"阵雨","cond_txt_n":"大到暴雨","date":"2020-05-20","hum":"81","mr":"04:09","ms":"16:53","pcpn":"0.0","pop":"25","pres":"997","sr":"05:41","ss":"18:59","tmp_max":"31","tmp_min":"25","uv_index":"10","vis":"22","wind_deg":"234","wind_dir":"西南风","wind_sc":"3-4","wind_spd":"20"},{"cond_code_d":"316","cond_code_n":"316","cond_txt_d":"大到暴雨","cond_txt_n":"大到暴雨","date":"2020-05-21","hum":"81","mr":"04:42","ms":"17:45","pcpn":"5.1","pop":"80","pres":"994","sr":"05:40","ss":"19:00","tmp_max":"30","tmp_min":"25","uv_index":"3","vis":"13","wind_deg":"226","wind_dir":"西南风","wind_sc":"3-4","wind_spd":"20"},{"cond_code_d":"306","cond_code_n":"310","cond_txt_d":"中雨","cond_txt_n":"暴雨","date":"2020-05-22","hum":"86","mr":"05:19","ms":"18:37","pcpn":"6.1","pop":"68","pres":"996","sr":"05:40","ss":"19:00","tmp_max":"31","tmp_min":"27","uv_index":"5","vis":"8","wind_deg":"244","wind_dir":"西南风","wind_sc":"3-4","wind_spd":"23"}]
         * hourly : [{"cloud":"11","cond_code":"101","cond_txt":"多云","dew":"22","hum":"73","pop":"7","pres":"998","time":"2020-05-16 13:00","tmp":"33","wind_deg":"174","wind_dir":"南风","wind_sc":"1-2","wind_spd":"5"},{"cloud":"9","cond_code":"101","cond_txt":"多云","dew":"22","hum":"73","pop":"7","pres":"999","time":"2020-05-16 16:00","tmp":"33","wind_deg":"188","wind_dir":"南风","wind_sc":"1-2","wind_spd":"2"},{"cloud":"19","cond_code":"101","cond_txt":"多云","dew":"24","hum":"81","pop":"0","pres":"997","time":"2020-05-16 19:00","tmp":"29","wind_deg":"170","wind_dir":"南风","wind_sc":"1-2","wind_spd":"11"},{"cloud":"74","cond_code":"101","cond_txt":"多云","dew":"25","hum":"85","pop":"0","pres":"998","time":"2020-05-16 22:00","tmp":"25","wind_deg":"181","wind_dir":"南风","wind_sc":"1-2","wind_spd":"3"},{"cloud":"68","cond_code":"104","cond_txt":"阴","dew":"24","hum":"85","pop":"0","pres":"999","time":"2020-05-17 01:00","tmp":"25","wind_deg":"187","wind_dir":"南风","wind_sc":"1-2","wind_spd":"9"},{"cloud":"70","cond_code":"104","cond_txt":"阴","dew":"25","hum":"84","pop":"0","pres":"998","time":"2020-05-17 04:00","tmp":"24","wind_deg":"182","wind_dir":"南风","wind_sc":"1-2","wind_spd":"11"},{"cloud":"65","cond_code":"104","cond_txt":"阴","dew":"26","hum":"84","pop":"43","pres":"996","time":"2020-05-17 07:00","tmp":"26","wind_deg":"188","wind_dir":"南风","wind_sc":"1-2","wind_spd":"1"},{"cloud":"52","cond_code":"104","cond_txt":"阴","dew":"23","hum":"77","pop":"46","pres":"995","time":"2020-05-17 10:00","tmp":"31","wind_deg":"-1","wind_dir":"无持续风向","wind_sc":"1-2","wind_spd":"8"}]
         * lifestyle : [{"type":"comf","brf":"较不舒适","txt":"白天天气多云，并且空气湿度偏大，在这种天气条件下，您会感到有些闷热，不很舒适。"},{"type":"drsg","brf":"炎热","txt":"天气炎热，建议着短衫、短裙、短裤、薄型T恤衫等清凉夏季服装。"},{"type":"flu","brf":"少发","txt":"各项气象条件适宜，发生感冒机率较低。但请避免长期处于空调房间中，以防感冒。"},{"type":"sport","brf":"较不宜","txt":"天气较好，无雨水困扰，但考虑气温很高，请注意适当减少运动时间并降低运动强度，运动后及时补充水分。"},{"type":"trav","brf":"较适宜","txt":"天气较好，温度较高，天气较热，但有微风相伴，还是比较适宜旅游的，不过外出时要注意防暑防晒哦！"},{"type":"uv","brf":"强","txt":"紫外线辐射强，建议涂擦SPF20左右、PA++的防晒护肤品。避免在10点至14点暴露于日光下。"},{"type":"cw","brf":"较适宜","txt":"较适宜洗车，未来一天无雨，风力较小，擦洗一新的汽车至少能保持一天。"},{"type":"air","brf":"中","txt":"气象条件对空气污染物稀释、扩散和清除无明显影响。"}]
         */

        private BasicBean basic;
        private UpdateBean update;
        private String status;
        private NowBean now;
        private List<DailyForecastBean> daily_forecast;
        private List<HourlyBean> hourly;
        private List<LifestyleBean> lifestyle;

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

        public NowBean getNow() {
            return now;
        }

        public void setNow(NowBean now) {
            this.now = now;
        }

        public List<DailyForecastBean> getDaily_forecast() {
            return daily_forecast;
        }

        public void setDaily_forecast(List<DailyForecastBean> daily_forecast) {
            this.daily_forecast = daily_forecast;
        }

        public List<HourlyBean> getHourly() {
            return hourly;
        }

        public void setHourly(List<HourlyBean> hourly) {
            this.hourly = hourly;
        }

        public List<LifestyleBean> getLifestyle() {
            return lifestyle;
        }

        public void setLifestyle(List<LifestyleBean> lifestyle) {
            this.lifestyle = lifestyle;
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
             * loc : 2020-05-16 10:03
             * utc : 2020-05-16 02:03
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

        public static class NowBean {
            /**
             * cloud : 10
             * cond_code : 100
             * cond_txt : 晴
             * fl : 33
             * hum : 74
             * pcpn : 0.0
             * pres : 1001
             * tmp : 29
             * vis : 16
             * wind_deg : 227
             * wind_dir : 西南风
             * wind_sc : 1
             * wind_spd : 2
             */

            private String cloud;
            private String cond_code;
            private String cond_txt;
            private String fl;
            private String hum;
            private String pcpn;
            private String pres;
            private String tmp;
            private String vis;
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

            public String getFl() {
                return fl;
            }

            public void setFl(String fl) {
                this.fl = fl;
            }

            public String getHum() {
                return hum;
            }

            public void setHum(String hum) {
                this.hum = hum;
            }

            public String getPcpn() {
                return pcpn;
            }

            public void setPcpn(String pcpn) {
                this.pcpn = pcpn;
            }

            public String getPres() {
                return pres;
            }

            public void setPres(String pres) {
                this.pres = pres;
            }

            public String getTmp() {
                return tmp;
            }

            public void setTmp(String tmp) {
                this.tmp = tmp;
            }

            public String getVis() {
                return vis;
            }

            public void setVis(String vis) {
                this.vis = vis;
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

        public static class DailyForecastBean {
            /**
             * cond_code_d : 101
             * cond_code_n : 104
             * cond_txt_d : 多云
             * cond_txt_n : 阴
             * date : 2020-05-16
             * hum : 83
             * mr : 01:57
             * ms : 13:36
             * pcpn : 0.0
             * pop : 4
             * pres : 998
             * sr : 05:42
             * ss : 18:57
             * tmp_max : 34
             * tmp_min : 24
             * uv_index : 9
             * vis : 25
             * wind_deg : -1
             * wind_dir : 无持续风向
             * wind_sc : 1-2
             * wind_spd : 6
             */

            private String cond_code_d;
            private String cond_code_n;
            private String cond_txt_d;
            private String cond_txt_n;
            private String date;
            private String hum;
            private String mr;
            private String ms;
            private String pcpn;
            private String pop;
            private String pres;
            private String sr;
            private String ss;
            private String tmp_max;
            private String tmp_min;
            private String uv_index;
            private String vis;
            private String wind_deg;
            private String wind_dir;
            private String wind_sc;
            private String wind_spd;

            public String getCond_code_d() {
                return cond_code_d;
            }

            public void setCond_code_d(String cond_code_d) {
                this.cond_code_d = cond_code_d;
            }

            public String getCond_code_n() {
                return cond_code_n;
            }

            public void setCond_code_n(String cond_code_n) {
                this.cond_code_n = cond_code_n;
            }

            public String getCond_txt_d() {
                return cond_txt_d;
            }

            public void setCond_txt_d(String cond_txt_d) {
                this.cond_txt_d = cond_txt_d;
            }

            public String getCond_txt_n() {
                return cond_txt_n;
            }

            public void setCond_txt_n(String cond_txt_n) {
                this.cond_txt_n = cond_txt_n;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getHum() {
                return hum;
            }

            public void setHum(String hum) {
                this.hum = hum;
            }

            public String getMr() {
                return mr;
            }

            public void setMr(String mr) {
                this.mr = mr;
            }

            public String getMs() {
                return ms;
            }

            public void setMs(String ms) {
                this.ms = ms;
            }

            public String getPcpn() {
                return pcpn;
            }

            public void setPcpn(String pcpn) {
                this.pcpn = pcpn;
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

            public String getSr() {
                return sr;
            }

            public void setSr(String sr) {
                this.sr = sr;
            }

            public String getSs() {
                return ss;
            }

            public void setSs(String ss) {
                this.ss = ss;
            }

            public String getTmp_max() {
                return tmp_max;
            }

            public void setTmp_max(String tmp_max) {
                this.tmp_max = tmp_max;
            }

            public String getTmp_min() {
                return tmp_min;
            }

            public void setTmp_min(String tmp_min) {
                this.tmp_min = tmp_min;
            }

            public String getUv_index() {
                return uv_index;
            }

            public void setUv_index(String uv_index) {
                this.uv_index = uv_index;
            }

            public String getVis() {
                return vis;
            }

            public void setVis(String vis) {
                this.vis = vis;
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

        public static class HourlyBean {
            /**
             * cloud : 11
             * cond_code : 101
             * cond_txt : 多云
             * dew : 22
             * hum : 73
             * pop : 7
             * pres : 998
             * time : 2020-05-16 13:00
             * tmp : 33
             * wind_deg : 174
             * wind_dir : 南风
             * wind_sc : 1-2
             * wind_spd : 5
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

        public static class LifestyleBean {
            /**
             * type : comf
             * brf : 较不舒适
             * txt : 白天天气多云，并且空气湿度偏大，在这种天气条件下，您会感到有些闷热，不很舒适。
             */

            private String type;
            private String brf;
            private String txt;

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getBrf() {
                return brf;
            }

            public void setBrf(String brf) {
                this.brf = brf;
            }

            public String getTxt() {
                return txt;
            }

            public void setTxt(String txt) {
                this.txt = txt;
            }
        }
    }
}
