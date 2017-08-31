package com.example.fabio.hummingbirdtest.dagger;

import android.app.Activity;

import com.example.fabio.hummingbirdtest.ui.main.MainActivity;

import dagger.Binds;
import dagger.Module;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;

/**
 * Created by EUROCOM on 30/08/2017.
 */

@Module
public abstract class BuildersModule {
    @Binds
    @IntoMap
    @ActivityKey(MainActivity.class)
    abstract AndroidInjector.Factory<? extends Activity> bindMainActivityInjectorFactory(AppSubComponent.Builder builder);

    // Add another builder binding here
}
