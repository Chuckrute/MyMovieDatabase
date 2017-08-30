package com.example.fabio.hummingbirdtest.dagger;

import com.example.fabio.hummingbirdtest.data.Movie;
import com.example.fabio.hummingbirdtest.ui.MainActivity;
import com.example.fabio.hummingbirdtest.ui.MoviesContract;
import com.example.fabio.hummingbirdtest.ui.MoviesPresenter;

import java.util.ArrayList;
import java.util.List;

import dagger.Module;
import dagger.Provides;

/**
 * Created by EUROCOM on 30/08/2017.
 */

@Module
public class AppModule {

    @Provides
    public Movie provideMovie() {
        return new Movie();
    }
    @Provides
    public List<Movie> provideMovieList() {
        return new ArrayList<Movie>();
    }

    @Provides
    public  MoviesContract.UserActionsListener providePresenter(MoviesContract.view view)
    {
        return new MoviesPresenter(view);
    }

}
