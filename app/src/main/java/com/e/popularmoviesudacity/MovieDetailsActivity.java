package com.e.popularmoviesudacity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.e.popularmoviesudacity.model.Movie;
import com.e.popularmoviesudacity.model.Reviews;
import com.e.popularmoviesudacity.model.Videos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MovieDetailsActivity extends AppCompatActivity  implements TrailerAdapter.trailerClickHandler{

    private TextView movieTitle, movieYear, movieRating, movieOverview;
    private RecyclerView trailerRecyclerView, reviewRecyclerview;
    private String posterPath;
    private String IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w185";
    private String YOU_TUBE_BASE_URL = "https://www.youtube.com/watch?v=";
    private ImageView moviePoster;
    private Movie mMovie;
    private int movieID;
    private ImageButton favoriteButton;

    //initializing the favorites boolean to false
    private Boolean isFavorites =false;

    private TrailerAdapter mTrailerAdapter;
    private List<Videos> videosList;

    private List<Movie> favoriteList;

    private ReviewAdapter mReviewAdapter;


   // private  movieDataSource movieDataSource;
    public static final String ParceledMovie = "com.e.popularmovies.PARCELED_MOVIE";
    private detailsViewModelFactory factory;
    private DetailsViewModel mDetailsActivityViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);


        favoriteList = new ArrayList<>();
        videosList = new ArrayList<>();
        mTrailerAdapter = new TrailerAdapter(new ArrayList<>(), this);


        mReviewAdapter = new ReviewAdapter(new ArrayList<>());

        moviePoster = findViewById(R.id.detail_movie_poster);
        movieTitle = findViewById(R.id.detail_movie_title);
        movieYear = findViewById(R.id.release_year);
        movieRating = findViewById(R.id.movie_rating);
        movieOverview = findViewById(R.id.movie_overview);
        trailerRecyclerView = findViewById(R.id.trailer_recycler_View);
        reviewRecyclerview = findViewById(R.id.review_recycler_view);

        favoriteButton= findViewById(R.id.favorite_button);

        trailerRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        trailerRecyclerView.setAdapter(mTrailerAdapter);

        reviewRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        reviewRecyclerview.setAdapter(mReviewAdapter);

        readMoviesDetails();

      //get the viewModel class for the Details Activity to get the trailers and reviews for each movie
        factory = new detailsViewModelFactory(movieID);
        mDetailsActivityViewModel = ViewModelProviders.of(this, factory)
                .get(DetailsViewModel.class);

        getMovieTrailerKeys();
        getMovieReviews();

        addToFavorites();

    }

    private void addToFavorites() {

        favoriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            if(!isFavorites){
            favoriteList.add(mMovie);
            Log.d("favorites size:","size is:"+favoriteList.size());
                Log.d("movie Added:", mMovie.getTitle());
                favoriteButton.setImageResource(R.drawable.ic_star_24px);
                isFavorites =true;
            }
            else if(isFavorites){
                favoriteList.remove(mMovie);
                Log.d("movie Removed:", mMovie.getTitle());
                favoriteButton.setImageResource(R.drawable.ic_star_border_24px);
                isFavorites =false;
            }
            }
        });
    }


    //helper method to get the movie details and populate the child views and also get the videolist
    private void readMoviesDetails() {
        Intent movieIntent  = getIntent();
        mMovie = movieIntent.getParcelableExtra(ParceledMovie);
        if(mMovie !=null) {
            movieTitle.setText(mMovie.getTitle());
            movieYear.setText(mMovie.getReleaseDate().split("-")[0]);
            movieRating.setText(String.valueOf(mMovie.getMovieRating()));
            movieID = mMovie.getId();
            posterPath = mMovie.getMoviePosterPath();
            movieOverview.setText(mMovie.getMovieOverview());


            Glide.with(this)
                 .load(IMAGE_BASE_URL +posterPath)
                    .apply(RequestOptions.placeholderOf(R.color.colorPrimary))
                    .into(moviePoster);

        }
        else{
            Log.d("TAG DETAILS", "EMPTY EXTRA");
        }
    }

    //helper method to get movieTrailer keys from TMDB API
    private void getMovieTrailerKeys() {

     mDetailsActivityViewModel.getVideosList().observe(this, (List<Videos> videos) -> {
         mTrailerAdapter.setVideosList(videos);
         videosList= videos;
     });


    }

    //helper method to get the movie reviews
    private void getMovieReviews(){
        mDetailsActivityViewModel.getReviewList().observe(this, reviews -> {
           mReviewAdapter.setReviewsList(reviews);

        });
    }


    //implementing onClickListener for trailer item selection
    @Override
    public void onMovieClickListener(int position) {
        Intent trailerPlayIntent  = new Intent (Intent.ACTION_VIEW,
                Uri.parse(YOU_TUBE_BASE_URL +videosList.get(position).getKey()));

        try {
            startActivity(trailerPlayIntent);

        }catch (ActivityNotFoundException e){
            e.printStackTrace();
        }

    }
}
