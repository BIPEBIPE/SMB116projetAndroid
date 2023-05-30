package com.example.projetandroid;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Movie.class, User.class,UserMovie.class}, version = 8, exportSchema = false)
public abstract class MovieDatabase extends RoomDatabase {
    public abstract MovieDAO movieDao();
    public abstract UserDao userDao();
    public abstract UserMovieDao userMovieDao();
    private static MovieDatabase INSTANCE;

    public static MovieDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (MovieDatabase.class) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),MovieDatabase.class, "movie_database")
                        .fallbackToDestructiveMigration()
                        .allowMainThreadQueries()
                        .build();
            }
        }
        return INSTANCE;
    }
}
