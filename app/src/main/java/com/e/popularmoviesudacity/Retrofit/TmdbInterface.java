package com.e.popularmoviesudacity.Retrofit;

import com.e.popularmoviesudacity.model.MovieResponse;
import com.e.popularmoviesudacity.model.videoResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

//Interface to declare the Retrofit methods to interact with the API
public interface TmdbInterface {

    @GET("movie/popular")
    Call<MovieResponse> getPopularMovies(
            @Query("api_key") String apiKey
    );

    @GET("movie/{movie_id}/videos")
    Call<videoResponse> getVideo(
            @Path("movie_id") int movieID,
            @Query("api_key") String apiKey
    );

    @GET("movie/top_rated")
    Call<MovieResponse> getTopRated(
            @Query("api_key") String apiKey
    );
}
