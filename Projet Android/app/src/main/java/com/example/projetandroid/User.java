package com.example.projetandroid;

import android.graphics.Bitmap;

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
    String birthDate;
    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    byte[] img_profile;

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

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public byte[] getImg_profile() {
        return img_profile;
    }

    public void setImg_profile(byte[] img_profile) {
        this.img_profile = img_profile;
    }
}
