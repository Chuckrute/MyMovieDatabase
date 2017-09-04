package com.example.fabio.mymoviedatabase.ui.main;

import android.content.Context;
import android.util.Log;

import com.example.fabio.mymoviedatabase.App;
import com.example.fabio.mymoviedatabase.apis.IDatabaseAPI;
import com.example.fabio.mymoviedatabase.apis.IMoviesRequestsApi;
import com.example.fabio.mymoviedatabase.data.Movie;
import com.example.fabio.mymoviedatabase.data.MovieResults;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.realm.Realm;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by EUROCOM on 29/08/2017.
 */

public class MoviesPresenter implements MoviesContract.UserActionsListener{

    private MoviesContract.view view;
    @Inject public IMoviesRequestsApi mRequestsApi;
    @Inject public IDatabaseAPI mDatabaseAPI;
    private Subscription subscription;
    private List<Movie> displayedMovies;
    private int minRate = 5;

    public MoviesPresenter(MoviesContract.view view) {
        this.view = view;
        displayedMovies = new ArrayList<>();
        App.component.inject(this);


    }

    @Override
    public void findMoviesByMinRate(final int index) {
        subscription = mRequestsApi
        .getMoviesByMinRate(index, minRate)
        .subscribeOn(Schedulers.computation())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(
            new Subscriber<MovieResults>() {
                @Override
                public void onCompleted() {

                }
                @Override
                public void onError(Throwable e) {
                    Log.e("Retrofit error: ",e.getLocalizedMessage());
                    if(e instanceof IOException){
                        view.makeFailureDialogBox();
                    }
                }

                @Override
                public void onNext(MovieResults movies) {
                    if(index != 1){
                        displayedMovies.addAll(movies.getResults());
                    } else {
                        displayedMovies = movies.getResults();
                    }
                    view.showMovieList(displayedMovies);
                }
            }
        );

    }

    @Override
    public void findMoviesByKeyword(String movieName, final int index) {
        movieName = movieName.replace(" ","+");
        if (subscription != null && !subscription.isUnsubscribed()){
            subscription.unsubscribe();
        }

        if(movieName.isEmpty()){
            findMoviesByMinRate(index);
            return;
        }
        Log.w("Loading:","Movies with text: "+movieName+" page: "+index);
        subscription = mRequestsApi
        .getMoviesByName(movieName, index)
        .subscribeOn(Schedulers.computation())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(
                new Subscriber<MovieResults>() {
                    @Override
                    public void onCompleted() {

                    }
                    @Override
                    public void onError(Throwable e) {
                        if(e instanceof IOException) {
                            view.makeFailureToast();
                        }
                    }

                    @Override
                    public void onNext(MovieResults movies) {
                        if(index != 1){
                            displayedMovies.addAll(movies.getResults());
                        } else {
                            displayedMovies = movies.getResults();
                        }

                        view.showMovieList(movies.getResults());
                    }
                }
        );

    }

}
