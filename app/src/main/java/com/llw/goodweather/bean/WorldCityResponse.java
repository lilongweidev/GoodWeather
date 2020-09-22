package com.llw.goodweather.bean;

import java.util.List;

/**
 * 世界城市返回实体
 *
 * @author llw
 */
public class WorldCityResponse {
    /**
     * code : 200
     * topCityList : [{"name":"喀布尔","id":"6BA3","lat":"34.53277","lon":"69.16583","adm2":"喀布尔","adm1":"喀布尔省","country":"阿富汗","tz":"Asia/Kabul","utcOffset":"+04:30","isDst":"0","type":"city","rank":"20","fxLink":"http://hfx.link/1xq31"},{"name":"Ghaziabad","id":"8E836","lat":"34.31499","lon":"70.76899","adm2":"Ghaziabad","adm1":"楠格哈尔省","country":"阿富汗","tz":"Asia/Kabul","utcOffset":"+04:30","isDst":"0","type":"city","rank":"600","fxLink":"http://hfx.link/1xqa1"},{"name":"Herat","id":"E5CDA","lat":"34.37385","lon":"62.17918","adm2":"Herat","adm1":"赫拉特省","country":"阿富汗","tz":"Asia/Kabul","utcOffset":"+04:30","isDst":"0","type":"city","rank":"31","fxLink":"http://hfx.link/1xqd1"},{"name":"坎大哈","id":"B5722","lat":"31.60777","lon":"65.70527","adm2":"坎大哈","adm1":"坎大哈省","country":"阿富汗","tz":"Asia/Kabul","utcOffset":"+04:30","isDst":"0","type":"city","rank":"31","fxLink":"http://hfx.link/1xq21"},{"name":"马扎里沙里夫","id":"C5C86","lat":"36.70000","lon":"67.11666","adm2":"Mazar-i-Sharif","adm1":"巴尔赫省","country":"阿富汗","tz":"Asia/Kabul","utcOffset":"+04:30","isDst":"0","type":"city","rank":"31","fxLink":"http://hfx.link/1xq11"},{"name":"贾拉拉巴德","id":"575B","lat":"34.43027","lon":"70.45277","adm2":"贾拉拉巴德","adm1":"Nangarhar","country":"阿富汗","tz":"Asia/Kabul","utcOffset":"+04:30","isDst":"0","type":"city","rank":"41","fxLink":"http://hfx.link/1xqe1"},{"name":"法扎巴德","id":"46291","lat":"37.09999","lon":"70.56700","adm2":"法扎巴德","adm1":"巴达赫尚省","country":"阿富汗","tz":"Asia/Kabul","utcOffset":"+04:30","isDst":"0","type":"city","rank":"51","fxLink":"http://hfx.link/1xq71"},{"name":"Khost","id":"AD93F","lat":"33.33333","lon":"69.91666","adm2":"Khost","adm1":"霍斯特省","country":"阿富汗","tz":"Asia/Kabul","utcOffset":"+04:30","isDst":"0","type":"city","rank":"75","fxLink":"http://hfx.link/1xqf1"},{"name":"加兹尼","id":"EA283","lat":"33.54916","lon":"68.42333","adm2":"加兹尼","adm1":"加兹尼省","country":"阿富汗","tz":"Asia/Kabul","utcOffset":"+04:30","isDst":"0","type":"city","rank":"51","fxLink":"http://hfx.link/1xqb1"},{"name":"Farah","id":"A05D4","lat":"32.34361","lon":"62.11944","adm2":"Farah","adm1":"法拉省","country":"阿富汗","tz":"Asia/Kabul","utcOffset":"+04:30","isDst":"0","type":"city","rank":"51","fxLink":"http://hfx.link/1xq81"},{"name":"Sinay","id":"964D8","lat":"33.37900","lon":"65.01699","adm2":"Sinay","adm1":"古尔省","country":"阿富汗","tz":"Asia/Kabul","utcOffset":"+04:30","isDst":"0","type":"city","rank":"65","fxLink":"http://hfx.link/1xq61"},{"name":"安德科伊","id":"611D5","lat":"36.92499","lon":"65.11000","adm2":"安德科伊","adm1":"法利亚布省","country":"阿富汗","tz":"Asia/Kabul","utcOffset":"+04:30","isDst":"0","type":"city","rank":"55","fxLink":"http://hfx.link/1xq41"},{"name":"巴米扬","id":"450C6","lat":"34.81999","lon":"67.81099","adm2":"巴米扬","adm1":"巴米扬省","country":"阿富汗","tz":"Asia/Kabul","utcOffset":"+04:30","isDst":"0","type":"city","rank":"45","fxLink":"http://hfx.link/1xq51"},{"name":"贾巴奥斯沙拉吉","id":"71A7D","lat":"35.09799","lon":"69.20700","adm2":"贾巴奥斯沙拉吉","adm1":"帕尔旺省","country":"阿富汗","tz":"Asia/Kabul","utcOffset":"+04:30","isDst":"0","type":"city","rank":"55","fxLink":"http://hfx.link/1xqm1"},{"name":"黑拉坦","id":"40B4E","lat":"37.17200","lon":"67.33699","adm2":"黑拉坦","adm1":"巴尔赫省","country":"阿富汗","tz":"Asia/Kabul","utcOffset":"+04:30","isDst":"0","type":"city","rank":"65","fxLink":"http://hfx.link/1xqc1"},{"name":"塔林科","id":"5ED87","lat":"32.62400","lon":"65.86299","adm2":"塔林科","adm1":"乌鲁兹甘省","country":"阿富汗","tz":"Asia/Kabul","utcOffset":"+04:30","isDst":"0","type":"city","rank":"71","fxLink":"http://hfx.link/1xqn1"},{"name":"加德兹","id":"99F95","lat":"33.59799","lon":"69.22399","adm2":"加德兹","adm1":"帕克蒂亚省","country":"阿富汗","tz":"Asia/Kabul","utcOffset":"+04:30","isDst":"0","type":"city","rank":"61","fxLink":"http://hfx.link/1xq91"},{"name":"扎兰季","id":"C08A9","lat":"30.98399","lon":"61.84199","adm2":"扎兰季","adm1":"尼姆鲁兹省","country":"阿富汗","tz":"Asia/Kabul","utcOffset":"+04:30","isDst":"0","type":"city","rank":"71","fxLink":"http://hfx.link/1xqo1"},{"name":"昆都士","id":"362EC","lat":"36.72888","lon":"68.85694","adm2":"昆都士","adm1":"昆都士省","country":"阿富汗","tz":"Asia/Kabul","utcOffset":"+04:30","isDst":"0","type":"city","rank":"41","fxLink":"http://hfx.link/1xqg1"},{"name":"普勒`阿拉姆","id":"9AA42","lat":"33.99100","lon":"69.02200","adm2":"普勒`阿拉姆","adm1":"洛加尔省","country":"阿富汗","tz":"Asia/Kabul","utcOffset":"+04:30","isDst":"0","type":"city","rank":"61","fxLink":"http://hfx.link/1xqi1"}]
     * refer : {"sources":["heweather.com"],"license":["commercial license"]}
     */

    private String code;
    private ReferBean refer;
    private List<TopCityListBean> topCityList;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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
         * name : 喀布尔
         * id : 6BA3
         * lat : 34.53277
         * lon : 69.16583
         * adm2 : 喀布尔
         * adm1 : 喀布尔省
         * country : 阿富汗
         * tz : Asia/Kabul
         * utcOffset : +04:30
         * isDst : 0
         * type : city
         * rank : 20
         * fxLink : http://hfx.link/1xq31
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
