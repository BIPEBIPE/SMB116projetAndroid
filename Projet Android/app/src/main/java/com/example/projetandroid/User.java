package com.example.projetandroid;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "user")
public class User {
    @PrimaryKey(autoGenerate = true)
    int id;
    @ColumnInfo(name = "login")
    String login;
    @ColumnInfo(name = "pwd")
    String pwd;
    @ColumnInfo(name = "birthdate")
    long birthDate;

    public User(String login,String pwd) {
        this.login=login;
        this.pwd=pwd;
    }

    public int getUserId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPwd() {
        return pwd;
    }

    public long getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(long birthDate) {
        this.birthDate = birthDate;
    }
}
