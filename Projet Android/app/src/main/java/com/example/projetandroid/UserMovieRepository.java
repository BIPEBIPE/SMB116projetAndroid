package com.example.projetandroid;

import android.content.Context;

import java.util.List;

public class UserMovieRepository {
    private final UserMovieDao userMovieDao;

    public UserMovieRepository(Context c) {
        MovieDatabase database=MovieDatabase.getDatabase(c);
        this.userMovieDao=database.userMovieDao();
    }

    public void addUserMovie(UserMovie userMovie){
        this.userMovieDao.insert(userMovie);
    }

    public void deleteUserMovie(String userid,String movieId){
        this.userMovieDao.deleteUserMovie(userid,movieId);
    }

    public List<UserMovie> getAllMoviesbyUser(String userid){
        return this.userMovieDao.getAllMoviesbyUser(userid);
    }
}
