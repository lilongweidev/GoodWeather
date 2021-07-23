package com.llw.goodweather.contract;

import android.annotation.SuppressLint;
import com.llw.goodweather.api.ApiService;
import com.llw.goodweather.bean.AirNowResponse;
import com.llw.goodweather.bean.DailyResponse;
import com.llw.goodweather.bean.HourlyResponse;
import com.llw.goodweather.bean.LifestyleResponse;
import com.llw.goodweather.bean.MinutePrecResponse;
import com.llw.goodweather.bean.NewSearchCityResponse;
import com.llw.goodweather.bean.NowResponse;
import com.llw.goodweather.bean.WarningResponse;
import com.llw.mvplibrary.base.BasePresenter;
import com.llw.mvplibrary.base.BaseView;
import com.llw.mvplibrary.newnet.NetworkApi;
import com.llw.mvplibrary.newnet.observer.BaseObserver;

/**
 * 天气订阅器
 *
 * @author llw
 */
public class WeatherContract {

    public static class WeatherPresenter extends BasePresenter<IWeatherView> {

        /***************** 以下为使用V7版本而新增的接口方法，后期将都会使用这些方法，请注意 ****************/

        /**
         * 搜索城市  V7版本中  需要把定位城市的id查询出来，然后通过这个id来查询详细的数据
         *
         * @param location 城市名
         */
        @SuppressLint("CheckResult")
        public void newSearchCity(String location) {//注意这里的4表示新的搜索城市地址接口
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
                        getView().getWeatherDataFailed();
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
                        getView().getWeatherDataFailed();
                    }
                }
            }));
        }

        /**
         * 逐小时预报（未来24小时）
         *
         * @param location 城市名
         */
        @SuppressLint("CheckResult")
        public void hourlyWeather(String location) {
            ApiService service = NetworkApi.createService(ApiService.class, 3);
            service.hourlyWeather(location).compose(NetworkApi.applySchedulers(new BaseObserver<HourlyResponse>() {
                @Override
                public void onSuccess(HourlyResponse hourlyResponse) {
                    if (getView() != null) {
                        getView().getHourlyResult(hourlyResponse);
                    }
                }

                @Override
                public void onFailure(Throwable e) {
                    if (getView() != null) {
                        getView().getWeatherDataFailed();
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
                        getView().getWeatherDataFailed();
                    }
                }
            }));
        }

        /**
         * 生活指数
         *
         * @param location 城市名  type中的"1,2,3,5,6,8,9,10"，表示只获取这8种类型的指数信息，同样是为了对应之前的界面UI
         */
        @SuppressLint("CheckResult")
        public void lifestyle(String location) {
            ApiService service = NetworkApi.createService(ApiService.class, 3);
            service.lifestyle("0", location)
                    .compose(NetworkApi.applySchedulers(new BaseObserver<LifestyleResponse>() {
                        @Override
                        public void onSuccess(LifestyleResponse lifestyleResponse) {
                            if (getView() != null) {
                                getView().getLifestyleResult(lifestyleResponse);
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
         * 城市灾害预警
         *
         * @param location 城市id
         */
        @SuppressLint("CheckResult")
        public void nowWarn(String location) {
            ApiService service = NetworkApi.createService(ApiService.class, 3);
            service.nowWarn(location).compose(NetworkApi.applySchedulers(new BaseObserver<WarningResponse>() {
                @Override
                public void onSuccess(WarningResponse warningResponse) {
                    if (getView() != null) {
                        getView().getNowWarnResult(warningResponse);
                    }
                }

                @Override
                public void onFailure(Throwable e) {
                    if (getView() != null) {
                        getView().getWeatherDataFailed();
                    }
                }
            }));
        }

        /**
         * 分钟级降水
         *
         * @param location 经纬度拼接字符串，使用英文逗号分隔,经度在前纬度在后
         */
        @SuppressLint("CheckResult")
        public void getMinutePrec(String location) {
            ApiService service = NetworkApi.createService(ApiService.class, 3);
            service.getMinutePrec(location).compose(NetworkApi.applySchedulers(new BaseObserver<MinutePrecResponse>() {
                @Override
                public void onSuccess(MinutePrecResponse minutePrecResponse) {
                    if (getView() != null) {
                        getView().getMinutePrecResult(minutePrecResponse);
                    }
                }

                @Override
                public void onFailure(Throwable e) {
                    if (getView() != null) {
                        getView().getWeatherDataFailed();
                    }
                }
            }));
        }


    }

    public interface IWeatherView extends BaseView {

        //天气数据获取错误返回
        void getWeatherDataFailed();


        /*                以下为V7版本新增               */

        //搜索城市返回城市id  通过id才能查下面的数据,否则会提示400  V7
        void getNewSearchCityResult(NewSearchCityResponse response);

        //实况天气
        void getNowResult(NowResponse response);

        //天气预报  7天
        void getDailyResult(DailyResponse response);

        //逐小时天气预报
        void getHourlyResult(HourlyResponse response);

        //空气质量
        void getAirNowResult(AirNowResponse response);

        //生活指数
        void getLifestyleResult(LifestyleResponse response);

        //灾害预警
        void getNowWarnResult(WarningResponse response);

        //分钟级降水
        void getMinutePrecResult(MinutePrecResponse response);

        //错误返回
        void getDataFailed();


    }
}
