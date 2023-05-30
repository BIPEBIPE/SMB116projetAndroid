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

    @Query("UPDATE user SET pwd= :pwd where login = :login ")
    void ChangeMDP(String login, String pwd);

    @Query("UPDATE user SET img_profile= :img_profile where login = :login ")
    void ChangePP(String login, byte[] img_profile);
}
