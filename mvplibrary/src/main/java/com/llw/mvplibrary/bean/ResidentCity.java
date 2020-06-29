package com.llw.mvplibrary.bean;

import org.litepal.crud.LitePalSupport;

public class ResidentCity extends LitePalSupport {

    private int id;//编号

    private String location;//地区／城市名称

    private String parent_city;//该地区／城市的上级城市

    private String admin_area;//该地区／城市所属行政区域

    private String cnty;//该地区／城市所属国家名称

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
}
