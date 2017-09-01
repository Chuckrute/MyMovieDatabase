package com.example.fabio.mymoviedatabase;

import android.app.Application;

import com.example.fabio.mymoviedatabase.dagger.AppComponent;
import com.example.fabio.mymoviedatabase.dagger.AppModule;
import com.example.fabio.mymoviedatabase.dagger.DaggerAppComponent;
//import com.example.fabio.mymoviedatabase.dagger.DaggerAppComponent;


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
