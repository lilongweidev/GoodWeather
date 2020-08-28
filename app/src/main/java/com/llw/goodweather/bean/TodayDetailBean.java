package com.llw.goodweather.bean;

public class TodayDetailBean {
    private int icon;
    private String value;
    private String name;

    public TodayDetailBean(int icon,String value,String name) {
        this.icon = icon;
        this.value = value;
        this.name = name;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
