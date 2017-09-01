package com.example.fabio.hummingbirdtest.ui.main;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.WindowManager;
import android.widget.EditText;

import com.example.fabio.hummingbirdtest.App;
import com.example.fabio.hummingbirdtest.BaseActivity;
import com.example.fabio.hummingbirdtest.R;
import com.example.fabio.hummingbirdtest.data.Movie;
import com.example.fabio.hummingbirdtest.ui.main.di.MoviesModule;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class MainActivity extends BaseActivity implements MoviesContract.view{

    @Inject
    MoviesContract.UserActionsListener mPresenter;

    private MoviesRecyclerViewAdapter adapter;
    private RecyclerView rvMovies;
    private EditText etMovieName;
    private LinearLayoutManager mLayoutManager;
    private int listPage = 1;
    private boolean isLoading = false;

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
                mPresenter.findMoviesByKeyword(s.toString(), listPage);
            }
        });
        mPresenter.findMoviesByPopularity(1);
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
                    mPresenter.findMoviesByKeyword(etMovieName.getText().toString(), listPage);

                }
            }
        });
    }
}
