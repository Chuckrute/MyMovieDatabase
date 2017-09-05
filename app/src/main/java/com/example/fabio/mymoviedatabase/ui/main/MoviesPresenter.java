package com.example.fabio.mymoviedatabase.ui.main;

import com.example.fabio.mymoviedatabase.App;
import com.example.fabio.mymoviedatabase.apis.IDatabaseAPI;
import com.example.fabio.mymoviedatabase.apis.IMoviesRequestsApi;
import com.example.fabio.mymoviedatabase.data.Movie;
import com.example.fabio.mymoviedatabase.data.MovieResults;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Subscription;
import rx.functions.Action1;

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
    private boolean shouldOverrideData;
    private Action1<MovieResults> onSuccess;
    private Action1<Throwable> onError;

    @Inject
    public MoviesPresenter(MoviesContract.view view) {
        this.view = view;
        displayedMovies = new ArrayList<>();
        App.component.inject(this);
        onSuccess = movies -> {
            view.hideLoadingDialog();
            mDatabaseAPI.updateDatabase(movies);
            if (!shouldOverrideData) {
                displayedMovies.addAll(movies.getResults());
            } else {
                displayedMovies = movies.getResults();
            }
            view.showMovieList(displayedMovies);
        };

        onError = error -> {
            error.printStackTrace();
            view.makeFailureDialogBox();
        };
    }

    public MoviesPresenter(MoviesContract.view view, IDatabaseAPI api, IMoviesRequestsApi moviesRequestsApi) {
        this.view = view;
        this.mDatabaseAPI = api;
        this.mRequestsApi = moviesRequestsApi;
        displayedMovies = new ArrayList<>();

        onSuccess = movies -> {
            view.hideLoadingDialog();
            mDatabaseAPI.updateDatabase(movies);
            if (!shouldOverrideData) {
                displayedMovies.addAll(movies.getResults());
            } else {
                displayedMovies = movies.getResults();
            }
            view.showMovieList(displayedMovies);
        };

        onError = error -> {
            error.printStackTrace();
            view.makeFailureDialogBox();
        };
    }

    @Override
    public void loadDatabase(boolean orientationChanged){
        mDatabaseAPI.openDatabase();
        MovieResults movies = mDatabaseAPI.getResults();

        if(movies==null){
            view.showLoadingDialog();
            findMoviesByMinRate(1);
            return;
        }

        if(mDatabaseAPI.isValid(movies)) {
            if (!shouldOverrideData) {
                displayedMovies.addAll(movies.getResults());
            } else {
                displayedMovies = movies.getResults();
            }
            view.showMovieList(displayedMovies);
        }
        if(!orientationChanged) {
            findMoviesByMinRate(1);
        } else{
            view.showMovieList(displayedMovies);
        }
    }

    @Override
    public void findMoviesByMinRate(int index) {
        if(index > 1){
            this.shouldOverrideData = false;
        } else {
            this.shouldOverrideData = true;
        }

        subscription = mRequestsApi
        .getMoviesByMinRate(index, minRate)
        .subscribe(onSuccess,onError);
    }

    @Override
    public void findMoviesByKeyword(String movieName, final int index) {
        if (subscription != null && !subscription.isUnsubscribed()){
            subscription.unsubscribe();
        }

        if(movieName.isEmpty()){
            findMoviesByMinRate(index);
            return;
        }
        subscription = mRequestsApi
        .getMoviesByName(movieName, index)
        .subscribe(onSuccess,onError);

    }

    @Override
    public void closeDatabase(){
        mDatabaseAPI.close();
    }
}
