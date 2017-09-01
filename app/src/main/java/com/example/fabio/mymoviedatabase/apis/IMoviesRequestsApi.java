package com.example.fabio.mymoviedatabase.apis;

import com.example.fabio.mymoviedatabase.data.MovieResults;

import rx.Observable;

/**
 * Created by EUROCOM on 30/08/2017.
 */

public interface IMoviesRequestsApi {

    public Observable<MovieResults> getMoviesByPopularity(int index);

    Observable<MovieResults> getMoviesByName(String name, int index);
}
