package com.example.fabio.mymoviedatabase.apis;

import com.example.fabio.mymoviedatabase.data.MovieResults;
import com.google.gson.Gson;

import java.util.Locale;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;
import rx.schedulers.Schedulers;

/**
 * Created by EUROCOM on 30/08/2017.
 */

public class MoviesRequestsApi implements IMoviesRequestsApi{
    private static Gson gson;
    private static EndPoints endPoints;
    public static Retrofit retrofit = null;
    public static final String BASE_URL = "https://api.themoviedb.org/3/";
    public static final String API_KEY = "d9b919d2a956a8db41c97f533abf5443";

    @Override
    public Observable<MovieResults> getMoviesByMinRate(int index, int minVoteAverage) {
        return endPoints.getMoviesByPopularity(API_KEY, Locale.getDefault().getDisplayLanguage(), index, minVoteAverage);
    }

    @Override
    public Observable<MovieResults> getMoviesByName(String name, int index) {
        return endPoints.getMoviesByName(API_KEY, name);
    }

    public MoviesRequestsApi(){
        if(gson == null) {

            RxJavaCallAdapterFactory rxAdapter = RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io());
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(rxAdapter)
                    .build();
        }
        if(endPoints == null) {
            endPoints = retrofit.create(EndPoints.class);
        }
    }

    public interface EndPoints {

        @GET("discover/movie")
        Observable<MovieResults> getMoviesByPopularity(@Query("api_key") String key, @Query("language") String language, @Query("page") int page, @Query("vote_average.gte") int minVoteAverage);

        @GET("search/movie")
        Observable<MovieResults> getMoviesByName(@Query("api_key") String key, @Query("query") String query);

    }
}
