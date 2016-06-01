package com.adoregeek.rxdemo.functions.weather;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2016/6/1.
 */
public interface WeatherApiService {
    @GET("weather")
    Observable<WeatherResponse> getWeather(@Query("q") String place, @Query("APPID") String appid);
}
