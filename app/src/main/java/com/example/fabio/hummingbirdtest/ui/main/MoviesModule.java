package com.example.fabio.hummingbirdtest.ui.main;

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

}
