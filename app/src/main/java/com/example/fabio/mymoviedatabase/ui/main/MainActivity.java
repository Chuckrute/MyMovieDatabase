package com.example.fabio.mymoviedatabase.ui.main;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
import android.support.test.espresso.IdlingResource;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.WindowManager;
import android.widget.EditText;

import com.example.fabio.mymoviedatabase.App;
import com.example.fabio.mymoviedatabase.BaseActivity;
import com.example.fabio.mymoviedatabase.R;
import com.example.fabio.mymoviedatabase.data.Movie;
import com.example.fabio.mymoviedatabase.ui.main.di.MoviesModule;
import com.example.fabio.mymoviedatabase.util.SimpleIdlingResource;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;
import javax.inject.Inject;

public class MainActivity extends BaseActivity implements MoviesContract.view{

    @Inject
    MoviesContract.UserActionsListener mPresenter;

    public MoviesRecyclerViewAdapter adapter;
    public RecyclerView rvMovies;
    private EditText etMovieName;
    private LinearLayoutManager mLayoutManager;
    private int listPage = 1;
    private boolean isLoading = false;
    private boolean orientationChanged = false;
    @Nullable
    private SimpleIdlingResource mIdlingResource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        App.component.movieModule(new MoviesModule(this)).inject(this);

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        rvMovies = (RecyclerView) findViewById(R.id.rvMovies);

        rvMovies.setHasFixedSize(true);
        adapter = new MoviesRecyclerViewAdapter(this, new ArrayList<Movie>(), this);
        rvMovies.setAdapter(adapter);

        mLayoutManager = new LinearLayoutManager(this);
        rvMovies.setLayoutManager(mLayoutManager);

        etMovieName = (EditText) findViewById(R.id.etMovieName);

        etMovieName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                listPage = 1;
                if (mIdlingResource != null) {
                    mIdlingResource.setIdleState(false);
                }
                mPresenter.findMoviesByKeyword(s.toString(), listPage);
            }
        });
        if (mIdlingResource != null) {
            mIdlingResource.setIdleState(false);
        }
        mPresenter.loadDatabase(orientationChanged);
        orientationChanged = false;
    }

    @Override
    public void showMovieList(List<Movie> movies) {
        adapter.setMovieList(movies);
        adapter.notifyDataSetChanged();
        isLoading = false;
        rvMovies.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if(isLoading){
                    return;
                }
                int visibleItemCount = mLayoutManager.getChildCount();
                int totalItemCount = mLayoutManager.getItemCount();
                int pastVisibleItems = mLayoutManager.findFirstVisibleItemPosition();
                if (pastVisibleItems + visibleItemCount >= totalItemCount) {
                    isLoading = true;
                    listPage++;
                    if (mIdlingResource != null) {
                        mIdlingResource.setIdleState(false);
                    }
                    mPresenter.findMoviesByKeyword(etMovieName.getText().toString(), listPage);
                }
            }
        });
        if (mIdlingResource != null) {
            mIdlingResource.setIdleState(true);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.closeDatabase();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        orientationChanged = true;
    }

    @VisibleForTesting
    @NonNull
    public IdlingResource getIdlingResource() {
        if (mIdlingResource == null) {
            mIdlingResource = new SimpleIdlingResource();
        }
        return mIdlingResource;
    }
}
