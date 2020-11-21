package com.example.pocketgarden;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class plant_listing extends AppCompatActivity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant_listing);

        listView = (ListView) findViewById(R.id.listView);

        ArrayList<String> namelist = new ArrayList<>();

        //These names need to be added in a for loop length master list
        namelist.add("1");
        

        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,namelist);

        listView.setAdapter(adapter);

        //Make an onclick for each one that goes to that list


        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener((new View.OnClickListener(){
            @Override
            public void onClick(View view){
                openPlant();
            }
        }));

        Button newplant = (Button) findViewById(R.id.newplant);
        newplant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMakePlant();
            }
        });
    }

    public void openPlant(){
        Intent intent = new Intent(this, plant.class);
        startActivity(intent);
    }

    public void openMakePlant(){
        Intent intent = new Intent(this, make_plant.class);
        startActivity(intent);
    }

}