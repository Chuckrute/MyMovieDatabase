package com.example.fabio.hummingbirdtest;

import android.app.Activity;
import android.app.Application;

import com.example.fabio.hummingbirdtest.dagger.AppComponent;
import com.example.fabio.hummingbirdtest.dagger.AppModule;
import com.example.fabio.hummingbirdtest.dagger.DaggerAppComponent;
//import com.example.fabio.hummingbirdtest.dagger.DaggerAppComponent;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasDispatchingActivityInjector;


/**
 * Created by EUROCOM on 30/08/2017.
 */

public class App extends Application {

    public static AppComponent component;

    @Override
    public void onCreate() {
        super.onCreate();


        component = createComponent();
    }

    protected AppComponent createComponent() {
        return DaggerAppComponent.builder()
                .appModule(new AppModule(getBaseContext()))
                .build();
    }
}
