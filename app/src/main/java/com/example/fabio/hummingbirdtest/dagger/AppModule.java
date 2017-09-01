package com.example.fabio.hummingbirdtest.dagger;

import android.app.Application;
import android.content.Context;

import com.example.fabio.hummingbirdtest.ui.main.AppSubComponent;
import com.example.fabio.hummingbirdtest.ui.main.MainActivity;
import com.example.fabio.hummingbirdtest.ui.main.MoviesContract;
import com.example.fabio.hummingbirdtest.ui.main.MoviesPresenter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by EUROCOM on 30/08/2017.
 */
@Module(subcomponents = {AppSubComponent.class})
public class AppModule {

    @Provides
    @Singleton
    Context provideContext(Application application) {
        return application.getApplicationContext();
    }
    @Provides
    MoviesContract.view provideView(MoviesContract.view activity) {
        return activity;
    }
}
