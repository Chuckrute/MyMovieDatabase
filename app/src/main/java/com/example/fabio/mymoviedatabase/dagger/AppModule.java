package com.example.fabio.mymoviedatabase.dagger;

import android.content.Context;

import com.example.fabio.mymoviedatabase.apis.DatabaseAPI;
import com.example.fabio.mymoviedatabase.apis.IDatabaseAPI;
import com.example.fabio.mymoviedatabase.apis.IMoviesRequestsApi;
import com.example.fabio.mymoviedatabase.apis.MoviesRequestsApi;

import javax.inject.Singleton;

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

    @Provides
    @Singleton
    public IDatabaseAPI providesDatabaseAPI() {
        return new DatabaseAPI();
    }

    @Provides
    @Singleton
    public Context provideContext() {
        return mContext;
    }



}
