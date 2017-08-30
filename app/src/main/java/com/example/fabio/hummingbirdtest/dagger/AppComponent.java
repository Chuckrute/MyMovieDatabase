package com.example.fabio.hummingbirdtest.dagger;

import com.example.fabio.hummingbirdtest.ui.MainActivity;
import com.example.fabio.hummingbirdtest.ui.MoviesContract;

import dagger.Component;

/**
 * Created by EUROCOM on 30/08/2017.
 */


@Component(modules = {AppModule.class})
interface AppComponent {
    public void inject(MoviesContract.view view);

    public void inject(MoviesContract.UserActionsListener presenter);
}
