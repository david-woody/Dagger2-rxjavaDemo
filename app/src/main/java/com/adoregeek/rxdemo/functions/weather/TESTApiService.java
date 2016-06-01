package com.adoregeek.rxdemo.functions.weather;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by David on 2016/6/1.
 */
public interface TESTApiService {
    @GET("weather")
    Call<WeatherResponse> getWeather(@Query("q") String place, @Query("APPID") String appid);
}
