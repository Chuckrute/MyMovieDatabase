package com.example.fabio.hummingbirdtest.dagger;

import android.content.Context;

import com.example.fabio.hummingbirdtest.CustomApplication;
import com.example.fabio.hummingbirdtest.dagger.AppSubComponent;

import dagger.Module;
import dagger.Provides;

/**
 * Created by EUROCOM on 30/08/2017.
 */
@Module(subcomponents = { AppSubComponent.class})
public class AppModule {

    @Provides
    Context provideContext(CustomApplication application) {
        return application.getApplicationContext();
    }


}
