package com.example.pocketgarden;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

import static android.content.ContentValues.TAG;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try{
        setContentView(R.layout.activity_main);}
        catch(Exception e){
            Log.e(TAG, "onCreateView", e);
            throw e;
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