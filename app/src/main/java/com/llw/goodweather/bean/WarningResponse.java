package com.llw.goodweather.bean;

import java.util.List;

/**
 * 灾害预警返回实体
 *
 * @author llw
 */
public class WarningResponse {

    /**
     * code : 200
     * updateTime : 2020-08-14T10:10+08:00
     * fxLink : http://hfx.link/2fh5
     * warning : [{"id":"23062241600000_20200813132751","sender":"肇源县气象局","pubTime":"2020-08-13T13:28+08:00","title":"肇源县气象局发布大风蓝色预警[IV级/一般]","startTime":"2020-08-13T13:30+08:00","endTime":"2020-08-14T13:30+08:00","status":"active","level":"蓝色","type":"11B06","typeName":"大风","text":"肇源县气象台2020年8月13日13时28分发布大风蓝色预警信号：预计未来24小时肇源县受大风影响,平均风力可达5-6级，阵风可达7-8级，请有关单位和个人注意做好预防工作。防御指南:1.政府及相关部门按照职责做好防大风工作；2.关好门窗，加固围板、棚架、广告牌等易被风吹动的搭建物，妥善安置易受大风影响的室外物品，遮盖建筑物资；3.行人注意尽量少骑自行车，刮风时不要在广告牌、临时搭建物等下面逗留；4.有关部门和单位请注意森林、草原防火等防火，个人请注意室外和野外用火安全。　　　　　　　　　　　　　","related":""}]
     * refer : {"sources":["12379","Weather China"],"license":["no commercial use"]}
     */

    private String code;
    private String updateTime;
    private String fxLink;
    private ReferBean refer;
    private List<WarningBean> warning;

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

    public ReferBean getRefer() {
        return refer;
    }

    public void setRefer(ReferBean refer) {
        this.refer = refer;
    }

    public List<WarningBean> getWarning() {
        return warning;
    }

    public void setWarning(List<WarningBean> warning) {
        this.warning = warning;
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

    public static class WarningBean {
        /**
         * id : 23062241600000_20200813132751
         * sender : 肇源县气象局
         * pubTime : 2020-08-13T13:28+08:00
         * title : 肇源县气象局发布大风蓝色预警[IV级/一般]
         * startTime : 2020-08-13T13:30+08:00
         * endTime : 2020-08-14T13:30+08:00
         * status : active
         * level : 蓝色
         * type : 11B06
         * typeName : 大风
         * text : 肇源县气象台2020年8月13日13时28分发布大风蓝色预警信号：预计未来24小时肇源县受大风影响,平均风力可达5-6级，阵风可达7-8级，请有关单位和个人注意做好预防工作。防御指南:1.政府及相关部门按照职责做好防大风工作；2.关好门窗，加固围板、棚架、广告牌等易被风吹动的搭建物，妥善安置易受大风影响的室外物品，遮盖建筑物资；3.行人注意尽量少骑自行车，刮风时不要在广告牌、临时搭建物等下面逗留；4.有关部门和单位请注意森林、草原防火等防火，个人请注意室外和野外用火安全。
         * related :
         */

        private String id;
        private String sender;
        private String pubTime;
        private String title;
        private String startTime;
        private String endTime;
        private String status;
        private String level;
        private String type;
        private String typeName;
        private String text;
        private String related;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getSender() {
            return sender;
        }

        public void setSender(String sender) {
            this.sender = sender;
        }

        public String getPubTime() {
            return pubTime;
        }

        public void setPubTime(String pubTime) {
            this.pubTime = pubTime;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getTypeName() {
            return typeName;
        }

        public void setTypeName(String typeName) {
            this.typeName = typeName;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getRelated() {
            return related;
        }

        public void setRelated(String related) {
            this.related = related;
        }
    }
}
