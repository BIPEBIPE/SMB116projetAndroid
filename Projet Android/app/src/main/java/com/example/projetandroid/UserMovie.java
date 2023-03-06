package com.example.projetandroid;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "userMovie",primaryKeys = {"Userid","MovieId"})
public class UserMovie {
    int Userid;
    int MovieId;

    public UserMovie(int userid, int movieId) {
        Userid = userid;
        MovieId = movieId;
    }

    public int getUserid() {
        return Userid;
    }

    public int getMovieId() {
        return MovieId;
    }
}
