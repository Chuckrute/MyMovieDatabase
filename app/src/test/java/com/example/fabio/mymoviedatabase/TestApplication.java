package com.example.fabio.mymoviedatabase;

//import com.example.fabio.mymoviedatabase.dagger.DaggerTestApplicationComponent;

import com.example.fabio.mymoviedatabase.dagger.DaggerTestApplicationComponent;
import com.example.fabio.mymoviedatabase.dagger.TestApplicationComponent;
import com.example.fabio.mymoviedatabase.dagger.TestModule;

/**
 * Created by EUROCOM on 04/09/2017.
 */

public class TestApplication extends App {

    public static TestApplicationComponent component;

    @Override
    public void onCreate() {
        super.onCreate();


        component = createComponent();

    }

    @Override protected TestApplicationComponent createComponent() {
        return DaggerTestApplicationComponent.builder()
                .testModule(new TestModule(this))
                .build();
    }

}
