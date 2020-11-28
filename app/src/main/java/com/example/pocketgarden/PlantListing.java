package com.example.pocketgarden;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class PlantListing extends AppCompatActivity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant_listing);

        listView = (ListView) findViewById(R.id.listView);

        ArrayList<String> nameList = new ArrayList<>();

        //These names need to be added in a for loop length master list
        nameList.add("1");


        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,nameList);

        listView.setAdapter(adapter);

        //Make an onclick for each one that goes to that list


        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener((new View.OnClickListener(){
            @Override
            public void onClick(View view){
                openPlant();
            }
        }));

        Button newPlant = (Button) findViewById(R.id.newplant);
        newPlant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMakePlant();
            }
        });
    }

    public void openPlant(){
        Intent intent = new Intent(this, Plant.class);
        startActivity(intent);
    }

    public void openMakePlant(){
        Intent intent = new Intent(this, MakePlant.class);
        startActivity(intent);
    }

}