package com.example.pocketgarden;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }



    protected void viewNavBar(){

        var navController = findNavController(R.id.fragNavHost);
        bottomNavView.setupWithNavController(navController);
    }




}