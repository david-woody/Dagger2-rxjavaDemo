package com.adoregeek.rxdemo.functions.weather;

import javax.inject.Inject;

import dagger.Component;

/**
 * Created by Administrator on 2016/6/1.
 */
@Component(modules = WeatherApiProducerModule.class)
public interface WeatherComponent {
    void inject(WeatherActivity activity);
}
