package com.example.fabio.mymoviedatabase.ui.main;

import com.example.fabio.mymoviedatabase.apis.IDatabaseAPI;
import com.example.fabio.mymoviedatabase.apis.IMoviesRequestsApi;
import com.example.fabio.mymoviedatabase.data.Movie;
import com.example.fabio.mymoviedatabase.data.MovieResults;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;

import io.realm.RealmList;
import rx.Observable;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;

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
        Mockito.when(mRequestsApi.getMoviesByName(anyString(),anyInt())).thenReturn(Observable.just(result));
        Mockito.when(mDatabaseAPI.isValid(result)).thenReturn(true);
        mPresenter = new MoviesPresenter(view, mDatabaseAPI, mRequestsApi);
    }

    @Test
    public void loadDatabaseTest() throws Exception{
        InOrder inOrder = Mockito.inOrder(mDatabaseAPI, view, mRequestsApi);
        mPresenter.loadDatabase(false);
        inOrder.verify(mDatabaseAPI).openDatabase();
        inOrder.verify(mDatabaseAPI).getResults();
        inOrder.verify(view).showMovieList(result.getResults());
        inOrder.verify(mRequestsApi).getMoviesByMinRate(anyInt(),anyInt());
        inOrder.verify(view).hideLoadingDialog();
        inOrder.verify(mDatabaseAPI).updateDatabase(result);
        inOrder.verify(view).showMovieList(result.getResults());

    }

    @Test
    public void loadDatabaseTest2() throws Exception{
        InOrder inOrder = Mockito.inOrder(mDatabaseAPI, view, mRequestsApi);
        Mockito.when(mDatabaseAPI.getResults()).thenReturn(null);

        mPresenter.loadDatabase(false);
        inOrder.verify(mDatabaseAPI).openDatabase();
        inOrder.verify(mDatabaseAPI).getResults();
        inOrder.verify(view).showLoadingDialog();
        inOrder.verify(mRequestsApi).getMoviesByMinRate(anyInt(),anyInt());
        inOrder.verify(view).hideLoadingDialog();
        inOrder.verify(mDatabaseAPI).updateDatabase(result);
        inOrder.verify(view).showMovieList(result.getResults());

    }

    @Test
    public void findMoviesByMinRate() throws Exception{
        InOrder inOrder = Mockito.inOrder(mDatabaseAPI, view, mRequestsApi);
        mPresenter.findMoviesByMinRate(1);
        inOrder.verify(mRequestsApi).getMoviesByMinRate(1,5);
        inOrder.verify(view).hideLoadingDialog();
        inOrder.verify(mDatabaseAPI).updateDatabase(result);
        inOrder.verify(view).showMovieList(result.getResults());
    }

    @Test
    public void findMoviesByKeyword() throws Exception{
        InOrder inOrder = Mockito.inOrder(mDatabaseAPI, view, mRequestsApi);
        mPresenter.findMoviesByKeyword("MOCK KEY",1);
        inOrder.verify(mRequestsApi).getMoviesByName("MOCK KEY", 1);
        inOrder.verify(view).hideLoadingDialog();
        inOrder.verify(mDatabaseAPI).updateDatabase(result);
        inOrder.verify(view).showMovieList(result.getResults());
    }

    @Test
    public void findMoviesByKeyword2() throws Exception{
        InOrder inOrder = Mockito.inOrder(mDatabaseAPI, view, mRequestsApi);
        mPresenter.findMoviesByKeyword("",1);
        inOrder.verify(mRequestsApi).getMoviesByMinRate(1,5);
        inOrder.verify(view).hideLoadingDialog();
        inOrder.verify(mDatabaseAPI).updateDatabase(result);
        inOrder.verify(view).showMovieList(result.getResults());
    }
}
