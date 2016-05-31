package com.adoregeek.rxdemo;

import android.app.Application;

/**
 * Created by David on 2016/5/31.
 */
public class RxApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        setUpComponent();
    }

    private void setUpComponent() {

    }
}
