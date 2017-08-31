package com.example.fabio.hummingbirdtest.ui.main;

import android.util.Log;

import com.example.fabio.hummingbirdtest.apis.IMoviesRequestsApi;
import com.example.fabio.hummingbirdtest.apis.MoviesRequestsApi;
import com.example.fabio.hummingbirdtest.data.Movie;
import com.example.fabio.hummingbirdtest.data.MovieResults;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by EUROCOM on 29/08/2017.
 */

public class MoviesPresenter implements MoviesContract.UserActionsListener{
    private MoviesContract.view view;
    private IMoviesRequestsApi mRequestsApi;
    private Subscription subscription;
    private List<Movie> displayedMovies;

    public MoviesPresenter(MoviesContract.view view) {
        this.view = view;
        mRequestsApi = new MoviesRequestsApi();
        displayedMovies = new ArrayList<>();
    }

    @Override
    public void findMoviesByPopularity(final int index) {
        Log.w("Loading:","All movies page: "+index);
        subscription = mRequestsApi
        .getMoviesByPopularity(index)
        .subscribeOn(Schedulers.computation())
        .observeOn(AndroidSchedulers.mainThread())
        .unsubscribeOn(Schedulers.io())
        .subscribe(
            new Subscriber<MovieResults>() {
                @Override
                public void onCompleted() {

                }
                @Override
                public void onError(Throwable e) {
                    view.makeFailureDialogBox();
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
            findMoviesByPopularity(index);
            return;
        }
        Log.w("Loading:","Movies with text: "+movieName+" page: "+index);
        subscription = mRequestsApi
        .getMoviesByName(movieName, index)
        .subscribeOn(Schedulers.computation())
        .observeOn(AndroidSchedulers.mainThread())
        .unsubscribeOn(Schedulers.io())
        .subscribe(
                new Subscriber<MovieResults>() {
                    @Override
                    public void onCompleted() {

                    }
                    @Override
                    public void onError(Throwable e) {
                        view.makeFailureDialogBox();
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
