package com.example.fabio.mymoviedatabase.dagger;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by EUROCOM on 04/09/2017.
 */
@Singleton
@Component(modules = {TestModule.class})
public interface TestApplicationComponent extends AppComponent {
}
