package com.adoregeek.rxdemo;

import android.app.Application;

import dagger.Module;
import dagger.Provides;

/**
 * Created by David on 2016/5/31.
 */
@Module
public class ApplicationMoudle {
    Application mApplication;

    public ApplicationMoudle(Application mApplication) {
        this.mApplication = mApplication;
    }

    @Provides
    Application providesApplication() {
        return mApplication;
    }
}
