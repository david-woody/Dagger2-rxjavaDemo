package com.adoregeek.rxdemo.functions.weather;

import com.adoregeek.rxdemo.BuildConfig;

import java.util.concurrent.TimeUnit;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2016/6/1.
 */
@Module
public class WeatherApiProducerModule {

    @Provides
    OkHttpClient providesOkHttpClient() {
        OkHttpClient.Builder okClient = new OkHttpClient().newBuilder();
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            okClient.addInterceptor(logging);
        }
        okClient.connectTimeout(60 * 1000, TimeUnit.MILLISECONDS)
                .readTimeout(60 * 1000, TimeUnit.MILLISECONDS);
        return okClient.build();
    }

    @Provides
    Retrofit providesRetrofitAdapter(OkHttpClient okHttpClient) {
        Retrofit.Builder retrofitAdapter = new Retrofit.Builder();
        retrofitAdapter.client(okHttpClient)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://api.openweathermap.org/data/2.5/");
        return retrofitAdapter.build();
    }

    @Provides
    WeatherApiService providesWeatherApiService(Retrofit retrofit) {
        return retrofit.create(WeatherApiService.class);
    }

    @Provides
    WeatherManager providesWeatherManager(WeatherApiService weatherApiService) {
        return new WeatherManager(weatherApiService);
    }


}
