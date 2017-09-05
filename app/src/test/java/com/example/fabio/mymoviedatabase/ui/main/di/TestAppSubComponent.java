package com.example.fabio.mymoviedatabase.ui.main.di;

import com.example.fabio.mymoviedatabase.ui.main.MainActivity;

import dagger.Subcomponent;

/**
 * Created by EUROCOM on 05/09/2017.
 */
@Subcomponent
        (modules = { TestMoviesModule.class })
public interface TestAppSubComponent {

    public void inject(MainActivity mainActivity);
}
