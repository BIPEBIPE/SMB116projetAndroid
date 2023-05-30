package com.example.projetandroid;

import android.content.Context;

import java.util.List;

public class UserRepository {
    private final UserDao userDAO;

    public UserRepository(Context c) {
        MovieDatabase database=MovieDatabase.getDatabase(c);
        this.userDAO=database.userDao();
    }

    public void addUser(User user){
        this.userDAO.insert(user);
    }

    public User getUser(String login){
        return this.userDAO.getUser(login);
    }

    public User Connexion(String login,String pwd){
        return this.userDAO.Connexion(login,pwd);
    }

    public List<User> getAllUsers(){
        return this.userDAO.getAllUser();
    }

    public void ChangeMDP(String login, String pwd){
        this.userDAO.ChangeMDP(login,pwd);
    }

    public void ChangePP(String login, byte[] img_profile){
        this.userDAO.ChangePP(login,img_profile);
    }
}
