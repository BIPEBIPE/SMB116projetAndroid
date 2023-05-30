package com.example.projetandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Enumeration;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView navView;
    public static boolean Connected = false;
    public static String Login ="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeBottomNavigationView();
    }

    private void initializeBottomNavigationView() {
        navView = findViewById(R.id.bottom_navigation);
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        NavController navController = navHostFragment.getNavController();
        NavigationUI.setupWithNavController(navView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = this.getMenuInflater();
        //inflater.inflate(R.menu.bottom_menu,menu);
        //Toast.makeText(getApplicationContext(),"onCreateOptionsMenu",Toast.LENGTH_SHORT).show();
        return true;
    }
}