package com.e.popularmoviesudacity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.e.popularmoviesudacity.model.Reviews;

import java.util.List;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.reviewHolder> {

    private TextView authorName, reviewText;
    private List<Reviews> reviewsList;

    ReviewAdapter(List<Reviews> reviewsList) {
        this.reviewsList = reviewsList;
    }

    void setReviewsList(List<Reviews> reviewsList){
        this.reviewsList = reviewsList;
        notifyDataSetChanged();

    }
    @NonNull
    @Override
    public ReviewAdapter.reviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.review_item_layout,parent,false);
        return new reviewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewAdapter.reviewHolder holder, int position) {
        holder.bind(reviewsList.get(position));

    }

    @Override
    public int getItemCount() {
        return reviewsList.size();
    }

    class reviewHolder extends RecyclerView.ViewHolder{

        reviewHolder(@NonNull View itemView) {
            super(itemView);

            //cache the review item layout IDs here
            authorName = itemView.findViewById(R.id.author_name);
            reviewText = itemView.findViewById(R.id.author_review);
        }

        private void bind(Reviews review){
            authorName.setText(review.getAuthor());
            reviewText.setText(review.getContent());
        }
    }
}
