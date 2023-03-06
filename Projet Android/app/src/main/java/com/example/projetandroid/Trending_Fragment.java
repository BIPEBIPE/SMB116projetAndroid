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

import java.util.ArrayList;
import java.util.Arrays;
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
        movieRepository.deleteAll();
        List<Movie> movies= movieRepository.getAllMovies();
        if(!ExisteMovie(movies,"Avatar the way of water")){
            movieRepository.addMovie(new Movie("Avatar the way of water"));
        }
        if(!ExisteMovie(movies,"Babylon")){
            movieRepository.addMovie(new Movie("Babylon"));
        }
        if(!ExisteMovie(movies,"Asterix et Obélix")){
            movieRepository.addMovie(new Movie("Asterix et Obélix"));
        }
        if(!ExisteMovie(movies,"OSS 117")){
            movieRepository.addMovie(new Movie("OSS 117"));
        }
        movies= movieRepository.getAllMovies();

        RecyclerView recyclerView = v.findViewById(R.id.recyclerview_trending);
        MovieAdapter adapter = new MovieAdapter(movies,Movie->{
            NavController navController= Navigation.findNavController(v);
            Bundle bundle = new Bundle();
            bundle.putString("titreMovie", Movie);
            navController.navigate(R.id.action_fragment_trending_to_fragment_detail_movie,bundle);
        });

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return v;
    }

    private boolean ExisteMovie(List<Movie> list,String titre ){
        boolean exist=false;
        for (Movie a: list) {
            if(a.titre==titre){
                exist=true;
            }
        }
        return exist;
    }
}