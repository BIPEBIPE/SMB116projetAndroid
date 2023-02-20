package com.example.projetandroid;

import android.app.Application;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Trending_Fragment extends Fragment {

    public Trending_Fragment() {
        // Required empty public constructor
    }

    public static Trending_Fragment newInstance(String param1, String param2) {
        Trending_Fragment fragment = new Trending_Fragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_trending, container, false);

        MovieRepository movieRepository =new MovieRepository(getContext());
        movieRepository.addMovie(new Movie("Avatar the way of water"));
        movieRepository.addMovie(new Movie("Babylon"));
        movieRepository.addMovie(new Movie("Asterix et Obélix"));
        movieRepository.addMovie(new Movie("OSS 117"));
        List<Movie> movies= movieRepository.getAllMovies();

        RecyclerView recyclerView = v.findViewById(R.id.recyclerview_trending);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        MovieAdapter adapter = new MovieAdapter(movies,Movie->{
            NavController navController= Navigation.findNavController(v);
            navController.navigate(R.id.action_fragment_trending_to_fragment_detail_movie);
        });
        recyclerView.setAdapter(adapter);

        for(int i=0;i<movies.size();i++){
            Log.e("error",movies.get(i).titre);
        }

        return v;
    }
}