package com.example.fabio.mymoviedatabase.ui.main;

import com.example.fabio.mymoviedatabase.apis.IDatabaseAPI;
import com.example.fabio.mymoviedatabase.apis.IMoviesRequestsApi;
import com.example.fabio.mymoviedatabase.data.Movie;
import com.example.fabio.mymoviedatabase.data.MovieResults;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import io.realm.RealmList;
import rx.Observable;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by EUROCOM on 04/09/2017.
 */

public class MoviesPresenterTest {

    @Mock IMoviesRequestsApi mRequestsApi;
    @Mock IDatabaseAPI mDatabaseAPI;
    @Mock MoviesContract.view view;
    MoviesPresenter mPresenter;
    private MovieResults result;

    @Before
    public void setup() {
        view = mock(MoviesContract.view.class);
        mRequestsApi = mock(IMoviesRequestsApi.class);
        result = new MovieResults();
        result.setPage(1);
        result.setTotalPages(1);
        result.setTotalResults(3);
        Movie movie1 = new Movie();
        Movie movie2 = new Movie();
        Movie movie3 = new Movie();
        result.setResults(new RealmList<>(movie1, movie2, movie3));
        mDatabaseAPI = Mockito.mock(IDatabaseAPI.class);
        Mockito.when(mDatabaseAPI.getResults()).thenReturn(result);
        Mockito.when(mRequestsApi.getMoviesByMinRate(anyInt(),anyInt())).thenReturn(Observable.just(result));
        Mockito.when(mDatabaseAPI.isValid(result)).thenReturn(true);
        mPresenter = new MoviesPresenter(view, mDatabaseAPI, mRequestsApi);
    }

    @Test
    public void loadDatabaseTest() throws Exception{
        mPresenter.loadDatabase(false);
        verify(mDatabaseAPI).openDatabase();
        verify(mDatabaseAPI).getResults();
        verify(view).showMovieList(result.getResults());

    }
}
