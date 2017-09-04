package com.example.fabio.mymoviedatabase.apis;

import io.realm.RealmConfiguration;

/**
 * Created by EUROCOM on 04/09/2017.
 */

public class DatabaseAPI implements IDatabaseAPI{
    RealmConfiguration myConfig;
    public DatabaseAPI(){
        myConfig = new RealmConfiguration.Builder()
        .inMemory()
        .build();


    }
}
