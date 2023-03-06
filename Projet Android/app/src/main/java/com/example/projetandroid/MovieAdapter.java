package com.example.projetandroid;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder>{
    List<Movie> movieList;
    MovieListener movieListener;

    public MovieAdapter(List<Movie> movies, MovieListener listener) {
        this.movieList = movies;
        this.movieListener = listener;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater  inflater  = LayoutInflater.from(parent.getContext());
            View contactView = inflater.inflate(R.layout.movie_item, parent, false);
            MovieViewHolder viewHolder = new MovieViewHolder(contactView);
            return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        // Retrieve the correct data for that position
        Movie data = movieList.get(position);
        // Add the data to the view
        holder.bind(data.titre);
    }

    @Override
    public int getItemCount() {
        // Return the number of data items to display
        return movieList.size();
    }

    class MovieViewHolder extends RecyclerView.ViewHolder {
        TextView wordTextView;
        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            wordTextView = (TextView) itemView.findViewById(R.id.word_textview);
        }

        public void bind(String data) {
            wordTextView.setText(data);
            wordTextView.setOnClickListener((view) -> movieListener.onWordSelected(data));
        }
    }
}
