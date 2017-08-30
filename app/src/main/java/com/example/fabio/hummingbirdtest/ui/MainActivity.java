package com.example.fabio.hummingbirdtest.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.fabio.hummingbirdtest.R;
import com.example.fabio.hummingbirdtest.data.Movie;

import java.util.List;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements MoviesContract.view{

        @Inject MoviesContract.UserActionsListener mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void showMovieList(List<Movie> movie) {

    }
}
