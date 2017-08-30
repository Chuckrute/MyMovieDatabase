package com.example.fabio.hummingbirdtest.ui;

import com.example.fabio.hummingbirdtest.data.Movie;

import java.util.List;

/**
 * Created by EUROCOM on 29/08/2017.
 */

public interface MoviesContract {

    interface view{
        void showMovieList(List<Movie> movie);
    }

    interface UserActionsListener{
        void findMoviesByPupularity();
        void findMoviesByKeyword( String movieName);
    }
}
