package com.example.pocketgarden;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

}