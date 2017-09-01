package com.example.fabio.hummingbirdtest.ui.main.di;

import com.example.fabio.hummingbirdtest.ui.main.MoviesContract;
import com.example.fabio.hummingbirdtest.ui.main.MoviesPresenter;

import javax.inject.Named;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

/**
 * Created by EUROCOM on 30/08/2017.
 */

@Module
public class MoviesModule {

    private MoviesContract.view view;

    public MoviesModule(MoviesContract.view view) {
        this.view = view;
    }

    @Provides
    public MoviesContract.UserActionsListener providesMoviePresenter() {
        return new MoviesPresenter(view);
    }

}
