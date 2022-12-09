package com.llw.goodweather.db.bean;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.util.List;

/**
 * 城市数据  省/直辖市  → 市  →  区/县
 */
@TypeConverters(CityConverter.class)
@Entity
public class Province {

    @PrimaryKey(autoGenerate = true)
    private int uid;
    private String provinceName;
    private List<City> cityList;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public List<City> getCityList() {
        return cityList;
    }

    public void setCityList(List<City> cityList) {
        this.cityList = cityList;
    }

    public Province() {}

    @Ignore
    public Province(String provinceName, List<City> cityList) {
        this.provinceName = provinceName;
        this.cityList = cityList;
    }

    public static class City {
        private String cityName;
        private List<Area> areaList;

        public String getCityName() {
            return cityName;
        }

        public void setCityName(String cityName) {
            this.cityName = cityName;
        }

        public List<Area> getAreaList() {
            return areaList;
        }

        public void setAreaList(List<Area> areaList) {
            this.areaList = areaList;
        }

        public City() {
        }

        public static class Area {
            private String areaName;

            public String getAreaName() {
                return areaName;
            }

            public void setAreaName(String areaName) {
                this.areaName = areaName;
            }

            public Area(String countyName) {
                this.areaName = countyName;
            }
        }
    }
}
