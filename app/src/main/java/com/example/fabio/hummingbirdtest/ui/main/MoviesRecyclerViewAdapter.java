package com.example.fabio.hummingbirdtest.ui.main;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fabio.hummingbirdtest.R;
import com.example.fabio.hummingbirdtest.data.Movie;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by EUROCOM on 29/08/2017.
 */

public class MoviesRecyclerViewAdapter extends RecyclerView.Adapter<MoviesRecyclerViewAdapter.ViewHolder> {

    private List<Movie> movies;
    Context ctx;
    private HashMap<Integer,Target> targetImages;

    public MoviesRecyclerViewAdapter(MoviesContract.view view, List<Movie> movies, Context ctx) {
        this.movies = movies;
        this.ctx = ctx;
        targetImages = new HashMap<>();
    }

    @Override
    public MoviesRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from( parent.getContext() )
                .inflate(R.layout.movie_component, parent, false);
        MoviesRecyclerViewAdapter.ViewHolder viewHolder = new MoviesRecyclerViewAdapter.ViewHolder( view );
        return viewHolder;
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Movie movie = movies.get(position);
        holder.tvMovieTitle.setText(movie.getTitle());
        holder.tvMovieDescription.setText(movie.getOverview());

        targetImages.put(position, new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                holder.ivMovieImage.setImageBitmap(bitmap);
            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {

            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

            }
        });
        Picasso.with(ctx).load("https://image.tmdb.org/t/p/w500/"+movie.getPosterPath()).into(targetImages.get(position));
    }

    public void setMovieList(List<Movie> movieList) {
        this.movies = movieList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView ivMovieImage;
        private TextView tvMovieTitle;
        private TextView tvMovieDescription;

        public ViewHolder(View itemView) {
            super(itemView);

            ivMovieImage = (ImageView) itemView.findViewById(R.id.ivMovieImage);
            tvMovieTitle = (TextView) itemView.findViewById(R.id.tvMovieTitle);
            tvMovieDescription = (TextView) itemView.findViewById(R.id.tvMovieDescription);
        }
    }
}
