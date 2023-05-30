package com.example.projetandroid;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MovieDAO {
    @Insert
    void insert(Movie movie);
    @Query("DELETE FROM movie")
    void deleteAll();
    @Query("SELECT * from movie ORDER BY titre ASC")
    List<Movie> getAllMovies();
    @Query("DELETE  from movie where movie_id= :movie_id")
    void Delete(String movie_id);
    @Query("SELECT * from movie where login= :login ORDER BY titre ASC")
    List<Movie> getAllMoviesbyLogin(String login);
}
