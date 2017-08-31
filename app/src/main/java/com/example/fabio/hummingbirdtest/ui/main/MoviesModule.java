package com.example.fabio.hummingbirdtest.ui.main;

import javax.inject.Named;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

/**
 * Created by EUROCOM on 30/08/2017.
 */

@Module
public abstract class MoviesModule {

    @Binds
    abstract MainActivity provideMainActivity(MainActivity mainActivity);

    @Provides @Named("someId") static String provideSomeId(MainActivity mainActivity) {
        return mainActivity.someId;
    }

}
