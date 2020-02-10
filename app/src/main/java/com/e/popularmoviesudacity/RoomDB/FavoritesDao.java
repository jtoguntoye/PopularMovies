package com.e.popularmoviesudacity.RoomDB;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.e.popularmoviesudacity.model.Movie;

import java.util.List;

@Dao
public interface FavoritesDao {

    @Query("SELECT * FROM favorites_table")
    LiveData<List<Movie>>getAllFavorites();


    @Query("SELECT id FROM favorites_table where  id=:movieID")
    LiveData<Integer>getFavorite(int movieID);

    @Insert(onConflict=OnConflictStrategy.IGNORE )
    void insertFavorite(Movie favoriteMovie);

    @Delete
    void deleteFromFavorite(Movie favoriteMovie);

}
