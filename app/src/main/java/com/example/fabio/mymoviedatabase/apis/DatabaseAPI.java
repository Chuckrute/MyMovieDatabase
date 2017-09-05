package com.example.fabio.mymoviedatabase.apis;

import android.content.Context;

import com.example.fabio.mymoviedatabase.data.MovieResults;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

/**
 * Created by EUROCOM on 04/09/2017.
 */

public class DatabaseAPI implements IDatabaseAPI{
    private Realm realmUI;
    RealmConfiguration myConfig;
    private Context ctx;
    private String MOVIE_RESULTS = "MOVIE_RESULTS";

    public DatabaseAPI(Context ctx){
        this.ctx = ctx;
        openDatabase();
    }

    public void openDatabase(){
        Realm.init(ctx);
        realmUI = Realm.getDefaultInstance();
        myConfig = new RealmConfiguration.Builder()
                .inMemory()
                .build();
    }

    public MovieResults getResults(){
        RealmResults<MovieResults> results = realmUI.where(MovieResults.class).findAll();
        if(results.size() == 0){
            //no data created.
            return null;
        }
        MovieResults result = results.first();

        return result;
    }

    public void updateDatabase(MovieResults results){
        realmUI.beginTransaction();
        RealmResults<MovieResults> oldResults = realmUI.where(MovieResults.class).findAll();
        oldResults.deleteAllFromRealm();
        MovieResults movieResults = realmUI.copyToRealmOrUpdate(results);

        realmUI.commitTransaction();
    }

    public void close(){
        realmUI.close();
    }

    @Override
    public boolean isValid(MovieResults movies) {
        return movies.isValid();
    }

}
