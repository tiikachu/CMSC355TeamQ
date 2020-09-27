package com.example.pocketgarden;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ScrollView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    /**
     * Opens journal notes editor
     * @param view
     */
    public void showJournalClick(View view){
        Intent intent = new Intent(getApplicationContext(), ShowJournals.class);
        startActivity(intent);
    }

}