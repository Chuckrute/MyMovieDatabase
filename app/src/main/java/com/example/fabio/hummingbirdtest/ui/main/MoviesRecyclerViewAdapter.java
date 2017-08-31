package com.example.fabio.hummingbirdtest.ui.main;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.example.fabio.hummingbirdtest.data.Movie;

import java.util.List;

/**
 * Created by EUROCOM on 29/08/2017.
 */

public class MoviesRecyclerViewAdapter extends RecyclerView.Adapter {

    public MoviesRecyclerViewAdapter(MainActivity activity, List<Movie> movies, MainActivity activity1) {

    }

    @Override
    public MoviesRecyclerViewAdapter.MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    private class MovieViewHolder extends RecyclerView.ViewHolder{

        public MovieViewHolder(View itemView) {
            super(itemView);
        }
    }
}
