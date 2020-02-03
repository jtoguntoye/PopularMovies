package com.e.popularmoviesudacity;

import android.app.Application;

import androidx.lifecycle.LiveData;


import com.e.popularmoviesudacity.RoomDB.FavoritesDB;
import com.e.popularmoviesudacity.RoomDB.FavoritesDao;
import com.e.popularmoviesudacity.model.Movie;

import java.util.List;

public class MoviesRepository {

    private FavoritesDao mFavoritesDao;
    private LiveData<List<Movie>> mFavorites;

    public MoviesRepository(Application application) {

        FavoritesDB favoritesDB = FavoritesDB.getDatabaseInstance(application);
        mFavoritesDao = favoritesDB.favoritesDao();

    }

    LiveData<List<Movie>> getAllFavorites(){
       mFavorites= mFavoritesDao.getAllFavorites();
       return mFavorites;
    }

    void insertFavorite(Movie movie){
        FavoritesDB.databaseWriteExecutor.execute(() ->{
            mFavoritesDao.insertFavorite(movie);
        });
    }

    void deleteFavorite(Movie movie){
        FavoritesDB.databaseWriteExecutor.execute(() ->{
            mFavoritesDao.deleteFromFavorite(movie);
        });

    }


}
