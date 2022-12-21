package com.llw.goodweather.db.bean;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

/**
 * 我的城市数据实体
 */
@Entity
public class MyCity {

    @NonNull
    @PrimaryKey
    private String cityName;

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    @Ignore
    public MyCity(String cityName) {
        this.cityName = cityName;
    }

    public MyCity() {}
}
