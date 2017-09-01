package com.example.fabio.hummingbirdtest.dagger;

import android.content.Context;

import com.example.fabio.hummingbirdtest.apis.IMoviesRequestsApi;
import com.example.fabio.hummingbirdtest.apis.MoviesRequestsApi;

import dagger.Module;
import dagger.Provides;

/**
 * Created by EUROCOM on 30/08/2017.
 */
@Module
public class AppModule {

    private Context mContext;

    public AppModule(Context context) {
        this.mContext = context;
    }


    @Provides
    public IMoviesRequestsApi providesMovieRequestApi() {
        return new MoviesRequestsApi();
    }



}
