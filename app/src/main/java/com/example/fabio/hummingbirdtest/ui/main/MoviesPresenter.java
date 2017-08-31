package com.example.fabio.hummingbirdtest.ui.main;

import javax.inject.Inject;

/**
 * Created by EUROCOM on 29/08/2017.
 */

public class MoviesPresenter implements MoviesContract.UserActionsListener{
    private MoviesContract.view view;

    //@Inject
    public MoviesPresenter(MoviesContract.view view) {
        this.view = view;
    }

    @Override
    public void findMoviesByPupularity() {

    }

    @Override
    public void findMoviesByKeyword(String movieName) {

    }

}
