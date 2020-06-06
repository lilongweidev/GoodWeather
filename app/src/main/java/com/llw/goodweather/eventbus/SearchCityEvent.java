package com.llw.goodweather.eventbus;

public class SearchCityEvent {

    public final String mLocation;
    public final String mCity;

    public SearchCityEvent(String location,String city) {
        this.mLocation = location;
        this.mCity = city;
    }
}
