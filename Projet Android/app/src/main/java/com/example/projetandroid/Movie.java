package com.example.projetandroid;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "movie")
public class Movie {
    @PrimaryKey(autoGenerate = true)
    int id;
    @ColumnInfo(name = "titre")
    String titre;
    @ColumnInfo(name = "date")
    long date;
    @ColumnInfo(name = "image")
    String img;
    @ColumnInfo(name = "movie_id")
    String movie_id;
    @ColumnInfo(name = "login")
    String login;

    public int getMovieId() {
        return id;
    }

    public Movie(String titre) {
        this.titre = titre;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getMovie_id() {
        return movie_id;
    }

    public void setMovie_id(String movie_id) {
        this.movie_id = movie_id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
