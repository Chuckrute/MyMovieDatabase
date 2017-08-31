package com.example.fabio.hummingbirdtest.ui.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.example.fabio.hummingbirdtest.R;
import com.example.fabio.hummingbirdtest.dagger.AppComponent;
import com.example.fabio.hummingbirdtest.data.Movie;

import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;


public class MainActivity extends AppCompatActivity implements MoviesContract.view{

    @Inject MoviesContract.UserActionsListener mPresenter;

    private MoviesRecyclerViewAdapter adapter;
    private RecyclerView rvMovies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvMovies = (RecyclerView) findViewById(R.id.rvMovies);
    }

    @Override
    public void showMovieList(List<Movie> movies) {
        adapter = new MoviesRecyclerViewAdapter(this, movies, this);
        rvMovies.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
