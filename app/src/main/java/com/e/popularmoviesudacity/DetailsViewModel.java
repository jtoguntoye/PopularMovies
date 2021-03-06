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
    private LiveData<List<Videos>> videoList;
    private LiveData<List<Reviews>> reviewsList;

    public DetailsViewModel(Application application, int movieID) {
        super(application);
        moviesRepository = new MoviesRepository(application);
        movieDataSource = new movieDataSource();
        videoList = movieDataSource.getMovieTrailers(movieID);
        reviewsList = movieDataSource.getMovieReviews(movieID);
    }

   public LiveData<List<Videos>> getVideosList(){
     return  videoList;

    }

    public LiveData<List<Reviews>> getReviewList(){
        return reviewsList;
    }


    //setter method to insert a favorite to the database via the repository
    void insertToFavorites(Movie movie){
        moviesRepository.insertFavorite(movie);
    }

    void deleteFromFavorites(Movie movie){
        moviesRepository.deleteFavorite(movie);
    }



    public LiveData<Movie> getFavorite(int movieID){
        return moviesRepository.getFavorite(movieID);
    }
}
