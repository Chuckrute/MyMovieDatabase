package com.example.fabio.hummingbirdtest.apis;

import rx.Observable;

/**
 * Created by EUROCOM on 30/08/2017.
 */

public interface IMoviesRequestsApi {

    public Observable<String> getMoviesByPopularity(int index);
    public Observable<String> getMoviesByName(String name);
}
