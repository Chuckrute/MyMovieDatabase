package com.example.fabio.hummingbirdtest.ui.main;

import com.example.fabio.hummingbirdtest.ui.main.MainActivity;
import com.example.fabio.hummingbirdtest.ui.main.MoviesModule;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

/**
 * Created by EUROCOM on 30/08/2017.
 */

@Subcomponent(modules = { MoviesModule.class })
public interface AppSubComponent extends AndroidInjector<MainActivity> {
    
    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<MainActivity> {
    }

    void inject(MainActivity view);
}
