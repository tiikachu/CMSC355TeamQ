package com.example.pocketgarden;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class PlantListing extends AppCompatActivity {


    static ArrayList<Plant> plantList = new ArrayList<>();
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant_listing);

        ListView listView = findViewById(R.id.listView);


    }

    public void openMakePlant(View v){
        Intent intent = new Intent(getApplicationContext(), MakePlant.class);
        startActivity(intent);
    }

}