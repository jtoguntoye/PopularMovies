package com.e.popularmoviesudacity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.e.popularmoviesudacity.model.Videos;

import java.util.List;

//trailer adapter with support for trailer click to open video in a browser
public class trailerAdapter extends RecyclerView.Adapter<trailerAdapter.trailerViewHolder> {

    private List<Videos> videosList;
    private TextView trailerName;
    private trailerClickHandler mTrailerClickHandler;

    public trailerAdapter(List<Videos> videos, trailerClickHandler trailerClickHandler) {
        videosList = videos;
        mTrailerClickHandler  = trailerClickHandler;
    }

    void setVideosList(List<Videos> videosList){
        this.videosList = videosList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public trailerAdapter.trailerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
//inflate the list_item layout here
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.trailer_item_layout,parent, false);

        return new trailerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull trailerViewHolder holder, int position) {
        holder.bind(videosList.get(position));

    }


    @Override
    public int getItemCount() {
        return videosList.size();
    }

    public class  trailerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public trailerViewHolder(@NonNull View itemView) {
            super(itemView);
        trailerName=itemView.findViewById(R.id.trailer_name);

        //create an itemClickListener in the viewHolder constructor to handle the click event of the
        // trailer recyclerView item
        itemView.setOnClickListener(this);
        }



        @Override
        public void onClick(View view) {
            mTrailerClickHandler.onMovieClickListener(getAdapterPosition());

        }

        public void bind(Videos videos){
            trailerName.setText(videos.getName());

        }
    }

    //create an interface to handle item click on the recycler view items
    public  interface  trailerClickHandler{
        void onMovieClickListener(int position);

    }
}
