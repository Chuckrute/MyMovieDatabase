package com.example.fabio.mymoviedatabase.ui.main.di;

import com.example.fabio.mymoviedatabase.ui.main.MainActivity;

import dagger.Subcomponent;

/**
 * Created by EUROCOM on 30/08/2017.
 */

@Subcomponent
(modules = { MoviesModule.class })
public interface AppSubComponent {

    public void inject(MainActivity mainActivity);
}
