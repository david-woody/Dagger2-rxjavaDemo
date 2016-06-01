package com.adoregeek.rxdemo.functions.weather;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/6/1.
 */
public class WeatherManager {
    public WeatherApiService weatherApiService;

    public WeatherManager(WeatherApiService weatherApiService) {
        this.weatherApiService = weatherApiService;
    }

    public Observable<WeatherResponse> getWeatherByCityId(String cityId) {
        return weatherApiService.getWeather(cityId, "aaacbd36b8f88a65d3c0aa4e8294a8a0")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }


}
