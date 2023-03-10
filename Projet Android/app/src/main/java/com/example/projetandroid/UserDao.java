package com.example.projetandroid;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDao {
    @Insert
    void insert(User user);

    @Query("SELECT * from user ORDER BY login ASC")
    List<User> getAllUser();

    @Query("SELECT * from user where login = :login ")
    User getUser(String login);

    @Query("SELECT * from user where login = :login and pwd = :pwd")
    User Connexion(String login, String pwd);
}
