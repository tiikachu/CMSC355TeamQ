package com.example.pocketgarden;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.pocketgarden.weather.Weather;

import static android.content.ContentValues.TAG;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public CardView myPlants, basics, weather, plantCare, library, journal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try{
        setContentView(R.layout.activity_main);}
        catch(Exception e){
            Log.e(TAG, "onCreateView", e);
            throw e;
        }

        initialize();

    }

    @Override
    public void onClick(View view) {
        Intent intent;
        int id = view.getId();

        if(id == R.id.My_Plants)
            intent = new Intent(this, Plant.class);
        else if(id == R.id.Basics)
            intent = new Intent(this, Basics.class);
        else if(id== R.id.Weather)
            intent = new Intent(this, Weather.class);
        else if(id== R.id.PlantCare)
            intent = new Intent(this, PlantCare.class);
        else if(id== R.id.Library)
            intent = new Intent(this, PlantListing.class);
        else if(id== R.id.Journal)
            intent = new Intent(this, ShowJournals.class);
        else
            throw new IllegalStateException("Unexpected value: " + view.getId());

        startActivity(intent);
    }

    public void initialize(){
        myPlants = (CardView) findViewById(R.id.My_Plants);
        basics = (CardView) findViewById(R.id.Basics);
        weather = (CardView) findViewById(R.id.Weather);
        plantCare = (CardView) findViewById(R.id.PlantCare);
        library = (CardView) findViewById(R.id.Library);
        journal = (CardView) findViewById(R.id.Journal);

        myPlants.setOnClickListener(this);
        basics.setOnClickListener(this);
        weather.setOnClickListener(this);
        plantCare.setOnClickListener(this);
        library.setOnClickListener(this);
        journal.setOnClickListener(this);
    }
}