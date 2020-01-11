package com.e.popularmoviesudacity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.e.popularmoviesudacity.model.Movie;


import java.util.List;

public class moviesAdapter extends RecyclerView.Adapter<moviesAdapter.movieViewHolder> {

    private List<Movie> movieList;
    private  movieClickHandler mClickHandler;

    public moviesAdapter(List<Movie> movieList, movieClickHandler clickHandler) {
        this.movieList = movieList;
        mClickHandler = clickHandler;
    }

    void setAdapterMovieList(List<Movie> movieList){
        this.movieList = movieList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public movieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //inflate the list_item layout here
        View itemView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.movies_list_item,parent, false);

        return new movieViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull movieViewHolder holder, int position) {
        holder.bind(movieList.get(position));

    }

    @Override
    public int getItemCount() {
     return  movieList.size();

    }

    //create inner viewHolder class
    public class movieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final ImageView imageView;
        private String IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w185";

        public movieViewHolder(@NonNull View itemView) {
            super(itemView);
            //get the list item from layout here
            imageView = itemView.findViewById(R.id.movie_poster);

            //create an itemClickListener in the viewHolder constructor to handle the click event of the
            // recyclerView item
            itemView.setOnClickListener(this);

        }

        private void bind(Movie movie){
            Glide.with(itemView)
                    .load(IMAGE_BASE_URL+movie.getMoviePosterPath())
                    .apply(RequestOptions.placeholderOf(R.color.colorPrimary))
                    .into(imageView);
        }

        @Override
        public void onClick(View view) {
            mClickHandler.onMovieClickListener(getAdapterPosition());

        }
    }


    //create an interface to handle item click on the recycler view items
    public  interface  movieClickHandler{
        void onMovieClickListener(int position);

    }
}
