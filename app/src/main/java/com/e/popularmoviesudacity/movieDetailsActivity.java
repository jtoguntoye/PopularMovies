package com.e.popularmoviesudacity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.e.popularmoviesudacity.model.Movie;
import com.e.popularmoviesudacity.model.Videos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class movieDetailsActivity extends AppCompatActivity  implements trailerAdapter.trailerClickHandler{

    private TextView movieTitle, movieYear, movieRating, movieOverview;
    private RecyclerView tralerRecyclerView;
    private String posterPath;
    private String IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w185";
    private String YOU_TUBE_BASE_URL = "https://www.youtube.com/watch?v=";
    private ImageView moviePoster;
    private Movie mMovie;
    private int movieID;

    private trailerAdapter mTrailerAdapter;
    private List<Videos> videosList;
   // private  movieDataSource movieDataSource;
    public static final String ParceledMovie = "com.e.popularmovies.PARCELED_MOVIE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

    //movieDataSource= new movieDataSource();

        videosList = new ArrayList<>();
        mTrailerAdapter = new trailerAdapter(new ArrayList<>(), this);
        moviePoster = findViewById(R.id.detail_movie_poster);
        movieTitle = findViewById(R.id.detail_movie_title);
        movieYear = findViewById(R.id.release_year);
        movieRating = findViewById(R.id.movie_rating);
        movieOverview = findViewById(R.id.movie_overview);
        tralerRecyclerView = findViewById(R.id.trailer_recycler_View);

        tralerRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        tralerRecyclerView.setAdapter(mTrailerAdapter);

        readMoviesDetails();

        getMovieTrailerKeys(movieID);
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
                 .load(IMAGE_BASE_URL +mMovie.getMoviePosterPath())
                    .apply(RequestOptions.placeholderOf(R.color.colorPrimary))
                    .into(moviePoster);

        }
        else{
            Log.d("TAG DETAILS", "EMPTY EXTRA");
        }
    }

    //helper method to get movieTrailer keys from TMDB API
    private void getMovieTrailerKeys(int movieID) {

        detailsViewModelFactory factory = new detailsViewModelFactory(movieID);
     //get the viewModel class for the Details Activity to get the trailers for each movie
     detailsViewModel mDetailsActivityViewModel = ViewModelProviders.of(this, factory)
              .get(detailsViewModel.class);

     mDetailsActivityViewModel.getVideosList().observe(this, (List<Videos> videos) -> {
         mTrailerAdapter.setVideosList(videos);
         videosList= videos;
     });

    Log.d("DETAIL_TRAILER TAG", "size is:"+videosList.size());
     mTrailerAdapter.setVideosList(videosList);


    }

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
