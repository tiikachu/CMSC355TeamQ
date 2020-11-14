package com.example.pocketgarden;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

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

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            // connect my plants card to whatever class
            case R.id.My_Plants:
                intent = new Intent (this, plant.class );
                startActivity(intent);
                break;
            case R.id.Basics:
                intent = new Intent (this, Basics.class);
        }
    }
    /**
     * Opens journal notes editor
     *
     * @param view
     */
    public void journalClick(View view) {
        Intent intent = new Intent(this, ShowJournals.class);
        startActivity(intent);
    }

    public void openNotifications() {
        Intent intent = new Intent(this, Notifications.class);
        startActivity(intent);
    }

    public void openMyPlants () {
        Intent intent = new Intent(this, PlantDisplay.class);
        startActivity(intent);
    }


}