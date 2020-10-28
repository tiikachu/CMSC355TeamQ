package com.example.pocketgarden;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewNavBar();
    }

    protected void viewNavBar(){

        var navController = findNavController(R.id.fragNavHost);
        bottomNavView.setupWithNavController(navController);
    }




}