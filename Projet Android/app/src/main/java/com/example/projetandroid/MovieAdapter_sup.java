package com.example.projetandroid;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class MovieAdapter_sup extends RecyclerView.Adapter<MovieAdapter_sup.MovieViewHolder>{
    List<MovieAPI> movieList;
    MovieListener movieListener;

    public MovieAdapter_sup(List<MovieAPI> movies, MovieListener listener) {
        this.movieList = movies;
        this.movieListener = listener;
    }

    @NonNull
    @Override
    public MovieAdapter_sup.MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater  = LayoutInflater.from(parent.getContext());
        View contactView = inflater.inflate(R.layout.movie_item_sup, parent, false);
        //movie_item_sup
        MovieAdapter_sup.MovieViewHolder viewHolder = new MovieAdapter_sup.MovieViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MovieAdapter_sup.MovieViewHolder holder, int position) {
        // Retrieve the correct data for that position
        MovieAPI data = movieList.get(position);
        // Add the data to the view
        holder.bind(data.Id);
    }

    @Override
    public int getItemCount() {
        // Return the number of data items to display
        return movieList.size();
    }

    class MovieViewHolder extends RecyclerView.ViewHolder {
        TextView wordTextView;
        TextView titreTextView;
        ImageView affiche;
        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            wordTextView = (TextView) itemView.findViewById(R.id.word_textview);
            titreTextView = (TextView) itemView.findViewById(R.id.titre);
            affiche = (ImageView) itemView.findViewById(R.id.affiche);
        }

        public void bind(String id) {
            wordTextView.setText(id);
            RequestQueue queue = Volley.newRequestQueue(itemView.getContext());
            String url = "https://api.themoviedb.org/3/movie/"+id+"?api_key=187d564fea6925a69feacfe93dcc5530";
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject postObj = new JSONObject(response);
                        String titre = postObj.getString("original_title");
                        titreTextView.setText(titre);
                        JSONObject postjson = postObj.getJSONObject("belongs_to_collection");
                        String src= postObj.getString("poster_path");//postjson.getString("poster_path");
                        src="https://image.tmdb.org/t/p/w300/"+src;
                        Log.e("e",src);
                        Picasso.with(itemView.getContext()).load(src).into(affiche);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }}, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });
            queue.add(stringRequest);

            titreTextView.setOnClickListener((view) -> movieListener.onWordSelected(id));
            affiche.setOnClickListener((view) -> movieListener.onWordSelected(id));
        }
    }
}
