package com.llw.goodweather.bean;

public class WorldCountryBean {


    private String countryName;
    private String countryCode;

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public WorldCountryBean(String countryName, String countryCode) {
        this.countryName = countryName;
        this.countryCode = countryCode;
    }
}
