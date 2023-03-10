package com.example.projetandroid;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "userMovie",primaryKeys = {"Userid","MovieId"})
public class UserMovie {
    @ColumnInfo
    @NonNull
    public int Userid;
    @ColumnInfo
    @NonNull
    public int MovieId;

    public UserMovie(int userid, int movieId) {
        Userid = userid;
        MovieId = movieId;
    }
    public UserMovie() {

    }

    public int getUserid() {
        return Userid;
    }

    public int getMovieId() {
        return MovieId;
    }
}
