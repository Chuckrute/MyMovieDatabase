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


    @Provides MoviesContract.UserActionsListener provideMoviesPresenter(MoviesContract.view mainActivity){
        return new MoviesPresenter(mainActivity);
    };

}
