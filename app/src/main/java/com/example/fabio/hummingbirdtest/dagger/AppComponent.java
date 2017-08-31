package com.example.fabio.hummingbirdtest.dagger;


import android.app.Application;

import dagger.BindsInstance;
import dagger.Component;

/**
 * Created by EUROCOM on 30/08/2017.
 */

@Component(modules = {
        AppModule.class,
        BuildersModule.class })
public interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance Builder application(Application application);
        AppComponent build();
    }
    void inject(Application app);
}
