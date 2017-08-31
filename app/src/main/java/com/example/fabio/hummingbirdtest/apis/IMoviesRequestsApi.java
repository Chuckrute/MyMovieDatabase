package com.example.fabio.hummingbirdtest.apis;

import com.example.fabio.hummingbirdtest.data.Movie;
import com.example.fabio.hummingbirdtest.data.MovieResults;

import java.util.ArrayList;

import rx.Observable;

/**
 * Created by EUROCOM on 30/08/2017.
 */

public interface IMoviesRequestsApi {

    public Observable<MovieResults> getMoviesByPopularity(int index);

    Observable<MovieResults> getMoviesByName(String name, int index);
}
