package com.example.projetandroid;

import android.app.Application;
import android.content.Context;

//import androidx.room.Room;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MovieRepository {
    private final MovieDAO movieDAO;

    public MovieRepository(Context c) {
        MovieDatabase database=MovieDatabase.getDatabase(c);
        this.movieDAO=database.movieDao();
    }

    public void addMovie(Movie movie){
        this.movieDAO.insert(movie);
    }

    public void Delete(String movie_id){
        this.movieDAO.Delete(movie_id);
    }

    public void deleteAll(){this.movieDAO.deleteAll();}

    public List<Movie> getAllMovies(){
        return this.movieDAO.getAllMovies();
    }

    public List<Movie> getAllMoviesbyLogin(String login){
        return this.movieDAO.getAllMoviesbyLogin(login);
    }

}
