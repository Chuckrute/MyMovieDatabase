package com.example.fabio.mymoviedatabase.apis;

import com.example.fabio.mymoviedatabase.data.MovieResults;

/**
 * Created by EUROCOM on 04/09/2017.
 */

public interface IDatabaseAPI {
    public MovieResults getResults();
    public void updateDatabase(MovieResults results);
    public void openDatabase();
    public void close();

    boolean isValid(MovieResults movies);
}
