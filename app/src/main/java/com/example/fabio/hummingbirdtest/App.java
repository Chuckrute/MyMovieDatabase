package com.example.fabio.hummingbirdtest;

import android.app.Activity;
import android.app.Application;


import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasDispatchingActivityInjector;


/**
 * Created by EUROCOM on 30/08/2017.
 */

public class App extends
        Application implements HasDispatchingActivityInjector {
    @Inject DispatchingAndroidInjector<Activity> dispatchingAndroidInjector;

    @Override
    public void onCreate() {
        super.onCreate();
//        DaggerAppComponent.builder()
//                .application((App)this)
//                .build()
//                .inject(this);
    }

    @Override
    public DispatchingAndroidInjector<Activity> activityInjector() {
        return dispatchingAndroidInjector;
    }
}
