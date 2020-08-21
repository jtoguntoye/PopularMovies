package com.e.popularmoviesudacity.RoomDB;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.e.popularmoviesudacity.model.Movie;
import com.e.popularmoviesudacity.model.Reviews;
import com.e.popularmoviesudacity.model.Videos;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Movie.class}, version = 1)
public abstract class FavoritesDB extends RoomDatabase {

    //create an abstract getter method for each DAO
    public  abstract  FavoritesDao favoritesDao();

    private static volatile FavoritesDB INSTANCE;
    private static int NUM_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUM_OF_THREADS);

    //instantiate the RoomDatabase using a singleton pattern
    public static FavoritesDB getDatabaseInstance(final Context context){
        if(INSTANCE ==null){
            synchronized (FavoritesDB.class) {
                if(INSTANCE==null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            FavoritesDB.class,"favorites_database")
                            .build();
                }
            }
        }
    return INSTANCE;
    }
}
