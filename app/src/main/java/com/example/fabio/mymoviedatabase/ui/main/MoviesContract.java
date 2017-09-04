package com.example.fabio.mymoviedatabase.ui.main;

import com.example.fabio.mymoviedatabase.data.Movie;

import java.util.List;

/**
 * Created by EUROCOM on 29/08/2017.
 */

public interface MoviesContract {

    interface view{
        void showMovieList(List<Movie> movies);
        public void makeFailureDialogBox();

        void makeFailureToast();
    }

    interface UserActionsListener{
        void findMoviesByMinRate(int index);
        void findMoviesByKeyword(String movieName, int index);
    }
}
