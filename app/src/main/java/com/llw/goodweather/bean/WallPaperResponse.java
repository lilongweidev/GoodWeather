package com.llw.goodweather.bean;

import java.util.List;

/**
 * 壁纸列表返回实体
 *
 * @author llw
 */
public class WallPaperResponse {

    /**
     * msg : success
     * res : {"vertical":[{"preview":"http://img5.adesk.com/5e26fed9e7bce75ebd2c449d","thumb":"http://img5.adesk.com/5e26fed9e7bce75ebd2c449d?imageMogr2/thumbnail/!350x540r/gravity/Center/crop/350x540","img":"http://img5.adesk.com/5e26fed9e7bce75ebd2c449d?imageMogr2/thumbnail/!720x1280r/gravity/Center/crop/720x1280","views":0,"cid":["4ef0a35c0569795756000000"],"rule":"?imageMogr2/thumbnail/!$<Width>x$<Height>r/gravity/Center/crop/$<Width>x$<Height>","ncos":6,"rank":51169,"source_type":"vertical","tag":[],"url":[],"wp":"http://img5.adesk.com/5e26fed9e7bce75ebd2c449d","xr":false,"cr":false,"favs":830,"atime":1.580187618E9,"id":"5e26fed9e7bce75ebd2c449d","store":"qiniu","desc":""},{"preview":"http://img5.adesk.com/5ed61fd2e7bce75e7ef4c0ed","thumb":"http://img5.adesk.com/5ed61fd2e7bce75e7ef4c0ed?imageMogr2/thumbnail/!350x540r/gravity/Center/crop/350x540","img":"http://img5.adesk.com/5ed61fd2e7bce75e7ef4c0ed?imageMogr2/thumbnail/!720x1280r/gravity/Center/crop/720x1280","views":0,"cid":["4fb479f75ba1c65561000027"],"rule":"?imageMogr2/thumbnail/!$<Width>x$<Height>r/gravity/Center/crop/$<Width>x$<Height>","ncos":0,"rank":7860,"source_type":"vertical","tag":[],"url":[],"wp":"http://img5.adesk.com/5ed61fd2e7bce75e7ef4c0ed","xr":false,"cr":false,"favs":71,"atime":1.591337416E9,"id":"5ed61fd2e7bce75e7ef4c0ed","store":"qiniu","desc":""}]}
     * code : 0
     */

    private String msg;
    private ResBean res;
    private int code;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ResBean getRes() {
        return res;
    }

    public void setRes(ResBean res) {
        this.res = res;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public static class ResBean {
        private List<VerticalBean> vertical;

        public List<VerticalBean> getVertical() {
            return vertical;
        }

        public void setVertical(List<VerticalBean> vertical) {
            this.vertical = vertical;
        }

        public static class VerticalBean {
            /**
             * preview : http://img5.adesk.com/5e26fed9e7bce75ebd2c449d
             * thumb : http://img5.adesk.com/5e26fed9e7bce75ebd2c449d?imageMogr2/thumbnail/!350x540r/gravity/Center/crop/350x540
             * img : http://img5.adesk.com/5e26fed9e7bce75ebd2c449d?imageMogr2/thumbnail/!720x1280r/gravity/Center/crop/720x1280
             * views : 0
             * cid : ["4ef0a35c0569795756000000"]
             * rule : ?imageMogr2/thumbnail/!$<Width>x$<Height>r/gravity/Center/crop/$<Width>x$<Height>
             * ncos : 6
             * rank : 51169
             * source_type : vertical
             * tag : []
             * url : []
             * wp : http://img5.adesk.com/5e26fed9e7bce75ebd2c449d
             * xr : false
             * cr : false
             * favs : 830
             * atime : 1.580187618E9
             * id : 5e26fed9e7bce75ebd2c449d
             * store : qiniu
             * desc :
             */

            private String preview;
            private String thumb;
            private String img;
            private int views;
            private String rule;
            private int ncos;
            private int rank;
            private String source_type;
            private String wp;
            private boolean xr;
            private boolean cr;
            private int favs;
            private double atime;
            private String id;
            private String store;
            private String desc;
            private List<String> cid;
            private List<?> tag;
            private List<?> url;

            public String getPreview() {
                return preview;
            }

            public void setPreview(String preview) {
                this.preview = preview;
            }

            public String getThumb() {
                return thumb;
            }

            public void setThumb(String thumb) {
                this.thumb = thumb;
            }

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public int getViews() {
                return views;
            }

            public void setViews(int views) {
                this.views = views;
            }

            public String getRule() {
                return rule;
            }

            public void setRule(String rule) {
                this.rule = rule;
            }

            public int getNcos() {
                return ncos;
            }

            public void setNcos(int ncos) {
                this.ncos = ncos;
            }

            public int getRank() {
                return rank;
            }

            public void setRank(int rank) {
                this.rank = rank;
            }

            public String getSource_type() {
                return source_type;
            }

            public void setSource_type(String source_type) {
                this.source_type = source_type;
            }

            public String getWp() {
                return wp;
            }

            public void setWp(String wp) {
                this.wp = wp;
            }

            public boolean isXr() {
                return xr;
            }

            public void setXr(boolean xr) {
                this.xr = xr;
            }

            public boolean isCr() {
                return cr;
            }

            public void setCr(boolean cr) {
                this.cr = cr;
            }

            public int getFavs() {
                return favs;
            }

            public void setFavs(int favs) {
                this.favs = favs;
            }

            public double getAtime() {
                return atime;
            }

            public void setAtime(double atime) {
                this.atime = atime;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getStore() {
                return store;
            }

            public void setStore(String store) {
                this.store = store;
            }

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public List<String> getCid() {
                return cid;
            }

            public void setCid(List<String> cid) {
                this.cid = cid;
            }

            public List<?> getTag() {
                return tag;
            }

            public void setTag(List<?> tag) {
                this.tag = tag;
            }

            public List<?> getUrl() {
                return url;
            }

            public void setUrl(List<?> url) {
                this.url = url;
            }
        }
    }
}
