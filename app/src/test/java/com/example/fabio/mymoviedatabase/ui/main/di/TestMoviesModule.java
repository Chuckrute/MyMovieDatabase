package com.example.fabio.mymoviedatabase.ui.main.di;

import com.example.fabio.mymoviedatabase.ui.main.MoviesContract;
import com.example.fabio.mymoviedatabase.ui.main.MoviesPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by EUROCOM on 05/09/2017.
 */

@Module
public class TestMoviesModule {

    private MoviesContract.view view;

    public TestMoviesModule(MoviesContract.view view) {
        this.view = view;
    }

    @Provides
    public MoviesContract.UserActionsListener providesMoviePresenter() {
        return new MoviesPresenter(view);
    }

}
