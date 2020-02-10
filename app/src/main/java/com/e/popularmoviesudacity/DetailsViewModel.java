package com.e.popularmoviesudacity;


import android.app.Application;
import android.content.Context;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;


import com.e.popularmoviesudacity.model.Movie;
import com.e.popularmoviesudacity.model.Reviews;
import com.e.popularmoviesudacity.model.Videos;

import java.net.Inet4Address;
import java.util.List;

// this viewModel class is used to get the video trailers for each movie from the data source class
//it extends AndroidViewModel since we need to get the context inside the viewModel
class DetailsViewModel extends AndroidViewModel {


    private final com.e.popularmoviesudacity.movieDataSource movieDataSource;

    private MoviesRepository moviesRepository;
    private int movieID;

    public DetailsViewModel(Application application, int movieID) {
        super(application);
        this.movieID = movieID;
        moviesRepository = new MoviesRepository(application);
        movieDataSource = new movieDataSource();

    }

   public LiveData<List<Videos>> getVideosList(){
     return  movieDataSource.getMovieTrailers(movieID);

    }

    public LiveData<List<Reviews>> getReviewList(){
        return movieDataSource.getMovieReviews(movieID);
    }


    //setter method to insert a favorite to the database via the repository
    void insertToFavorites(Movie movie){
        moviesRepository.insertFavorite(movie);
    }

    void deleteFromFavorites(Movie movie){
        moviesRepository.deleteFavorite(movie);
    }


    public LiveData<List<Movie>> getAllFavorites(){
        return moviesRepository.getAllFavorites();
    }

    public LiveData<Integer> getFavorite(int movieID){
        return moviesRepository.getFavorite(movieID);
    }
}
