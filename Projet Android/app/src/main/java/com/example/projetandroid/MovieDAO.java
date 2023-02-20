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
}
