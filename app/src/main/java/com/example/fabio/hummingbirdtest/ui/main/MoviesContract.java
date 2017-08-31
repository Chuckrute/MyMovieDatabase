package com.example.fabio.hummingbirdtest.ui.main;

import com.example.fabio.hummingbirdtest.data.Movie;

import java.util.List;

/**
 * Created by EUROCOM on 29/08/2017.
 */

public interface MoviesContract {

    interface view{
        void showMovieList(List<Movie> movies);

        public void makeFailureDialogBox();

        public void showLoadingDialog();
        public void hideLoadingDialog();
    }

    interface UserActionsListener{
        void findMoviesByPopularity(int index);
        void findMoviesByKeyword(String movieName, int index);
    }
}
