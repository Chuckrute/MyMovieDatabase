package com.example.fabio.mymoviedatabase.dagger;

import com.example.fabio.mymoviedatabase.TestApplication;
import com.example.fabio.mymoviedatabase.apis.IDatabaseAPI;
import com.example.fabio.mymoviedatabase.apis.IMoviesRequestsApi;

import org.mockito.Mockito;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by EUROCOM on 04/09/2017.
 */
@Module
public class TestModule {
    public TestApplication application;

    public TestModule(TestApplication application) {
        this.application = application;
    }

    @Provides
    @Singleton
    IDatabaseAPI provideDatabaseAPI() {
        IDatabaseAPI databaseAPI = Mockito.mock(IDatabaseAPI.class);
        return databaseAPI;
    }

    @Provides
    @Singleton
    IMoviesRequestsApi provideIMoviesRequestsApi() {
        IMoviesRequestsApi moviesRequestsApi = Mockito.mock(IMoviesRequestsApi.class);

        return moviesRequestsApi;
    }

}
