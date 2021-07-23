package com.llw.goodweather.contract;

import android.annotation.SuppressLint;

import com.llw.goodweather.api.ApiService;
import com.llw.goodweather.bean.AirNowResponse;
import com.llw.goodweather.bean.DailyResponse;
import com.llw.goodweather.bean.HourlyResponse;
import com.llw.goodweather.bean.NewSearchCityResponse;
import com.llw.goodweather.bean.NowResponse;
import com.llw.goodweather.bean.SunMoonResponse;
import com.llw.mvplibrary.base.BasePresenter;
import com.llw.mvplibrary.base.BaseView;
import com.llw.mvplibrary.newnet.NetworkApi;
import com.llw.mvplibrary.newnet.observer.BaseObserver;

/**
 * 地图天气订阅器
 *
 * @author llw
 */
public class MapWeatherContract {

    public static class MapWeatherPresenter extends BasePresenter<IMapWeatherView> {


        /**
         * 搜索城市  V7版本中  需要把定位城市的id查询出来，然后通过这个id来查询详细的数据
         *
         * @param location 城市名
         */
        @SuppressLint("CheckResult")
        public void searchCity(String location) {//注意这里的4表示新的搜索城市地址接口
            ApiService service = NetworkApi.createService(ApiService.class, 4);//指明访问的地址
            service.newSearchCity(location, "exact")
                    .compose(NetworkApi.applySchedulers(new BaseObserver<NewSearchCityResponse>() {
                        @Override
                        public void onSuccess(NewSearchCityResponse newSearchCityResponse) {
                            if (getView() != null) {
                                getView().getNewSearchCityResult(newSearchCityResponse);
                            }
                        }

                        @Override
                        public void onFailure(Throwable e) {
                            if (getView() != null) {
                                getView().getDataFailed();
                            }
                        }
                    }));
        }


        /**
         * 实况天气  V7版本
         *
         * @param location 城市名
         */
        @SuppressLint("CheckResult")
        public void nowWeather(String location) {//这个3 表示使用新的V7API访问地址
            ApiService service = NetworkApi.createService(ApiService.class, 3);
            service.nowWeather(location).compose(NetworkApi.applySchedulers(new BaseObserver<NowResponse>() {
                @Override
                public void onSuccess(NowResponse nowResponse) {
                    if (getView() != null) {
                        getView().getNowResult(nowResponse);
                    }
                }

                @Override
                public void onFailure(Throwable e) {
                    if (getView() != null) {
                        getView().getDataFailed();
                    }
                }
            }));
        }

        /**
         * 24小时天气预报
         *
         * @param location 城市名
         */
        @SuppressLint("CheckResult")
        public void weatherHourly(String location) {//这个3 表示使用新的V7API访问地址
            ApiService service = NetworkApi.createService(ApiService.class, 3);
            service.hourlyWeather(location).compose(NetworkApi.applySchedulers(new BaseObserver<HourlyResponse>() {
                @Override
                public void onSuccess(HourlyResponse hourlyResponse) {
                    if (getView() != null) {
                        getView().getWeatherHourlyResult(hourlyResponse);
                    }
                }

                @Override
                public void onFailure(Throwable e) {
                    if (getView() != null) {
                        getView().getDataFailed();
                    }
                }
            }));
        }


        /**
         * 天气预报  V7版本   7d 表示天气的数据 为了和之前看上去差别小一些，这里先用七天的
         *
         * @param location 城市名
         */
        @SuppressLint("CheckResult")
        public void dailyWeather(String location) {//这个3 表示使用新的V7API访问地址
            ApiService service = NetworkApi.createService(ApiService.class, 3);
            service.dailyWeather("7d", location).compose(NetworkApi.applySchedulers(new BaseObserver<DailyResponse>() {
                @Override
                public void onSuccess(DailyResponse dailyResponse) {
                    if (getView() != null) {
                        getView().getDailyResult(dailyResponse);
                    }
                }

                @Override
                public void onFailure(Throwable e) {
                    if (getView() != null) {
                        getView().getDataFailed();
                    }
                }
            }));
        }

        /**
         * 当天空气质量
         *
         * @param location 城市名
         */
        @SuppressLint("CheckResult")
        public void airNowWeather(String location) {
            ApiService service = NetworkApi.createService(ApiService.class, 3);
            service.airNowWeather(location).compose(NetworkApi.applySchedulers(new BaseObserver<AirNowResponse>() {
                @Override
                public void onSuccess(AirNowResponse airNowResponse) {
                    if (getView() != null) {
                        getView().getAirNowResult(airNowResponse);
                    }
                }

                @Override
                public void onFailure(Throwable e) {
                    if (getView() != null) {
                        getView().getDataFailed();
                    }
                }
            }));
        }

        /**
         * 日出日落、月升月落
         *
         * @param location 城市名
         */
        @SuppressLint("CheckResult")
        public void getSunMoon(String location, String date) {
            ApiService service = NetworkApi.createService(ApiService.class, 3);
            service.getSunMoon(location, date).compose(NetworkApi.applySchedulers(new BaseObserver<SunMoonResponse>() {
                @Override
                public void onSuccess(SunMoonResponse sunMoonResponse) {
                    if (getView() != null) {
                        getView().getSunMoonResult(sunMoonResponse);
                    }
                }

                @Override
                public void onFailure(Throwable e) {
                    if (getView() != null) {
                        getView().getDataFailed();
                    }
                }
            }));
        }

    }

    public interface IMapWeatherView extends BaseView {
        //搜索城市返回城市id  通过id才能查下面的数据,否则会提示400  V7
        void getNewSearchCityResult(NewSearchCityResponse response);

        //实况天气
        void getNowResult(NowResponse response);

        //24小时天气预报
        void getWeatherHourlyResult(HourlyResponse response);

        //天气预报  7天
        void getDailyResult(DailyResponse response);

        //空气质量
        void getAirNowResult(AirNowResponse response);

        //太阳和月亮
        void getSunMoonResult(SunMoonResponse response);

        //错误返回
        void getDataFailed();


    }
}
