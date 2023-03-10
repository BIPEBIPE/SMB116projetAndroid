package com.example.projetandroid;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserMovieDao {
    @Insert
    void insert(UserMovie userMovie);

    @Query("DELETE FROM userMovie where userid = :userid and movieId= :movieId")
    void deleteUserMovie(String userid,String movieId);

    @Query("SELECT * from userMovie where userid = :userid")
    List<UserMovie> getAllMoviesbyUser(String userid);

}
