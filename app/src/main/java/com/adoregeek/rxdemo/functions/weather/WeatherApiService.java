package com.adoregeek.rxdemo.functions.weather;

import android.graphics.Bitmap;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2016/6/1.
 */
public interface WeatherApiService {
    @GET("weather")
    Observable<WeatherResponse> getWeather(@Query("id") String place, @Query("APPID") String appid);

    static String BaseImageUrl = "http://openweathermap.org/img/w/%1$s.png";
}
