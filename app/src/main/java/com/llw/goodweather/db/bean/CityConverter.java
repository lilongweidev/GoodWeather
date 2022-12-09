package com.llw.goodweather.db.bean;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * 数据转换器
 */
public class CityConverter {

    @TypeConverter
    public List<Province.City> stringToObject(String value) {
        Type userListType = new TypeToken<ArrayList<Province.City>>() {}.getType();
        return new Gson().fromJson(value, userListType);
    }

    @TypeConverter
    public String objectToString(List<Province.City> list) {
        return new Gson().toJson(list);
    }
}
