package com.example.pocketgarden;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

import plant.*;

public class make_plant extends AppCompatActivity {

    private Button submit;
    private EditText plantName;
    private EditText plantAge;
    private EditText interval;
    private CheckBox indoor;
    private CheckBox potted;

    private int ageInput;
    private int intervalInput;
    private boolean indoorInput;
    private boolean pottedInput;
    private String nameInput;

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String TEXT = "text";

    private String name;





    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_plant);

        submit = (Button) findViewById(R.id.submit);
        plantName = (EditText) findViewById(R.id.plantName);
        plantAge = (EditText) findViewById(R.id.plantAge);
        interval = (EditText) findViewById(R.id.wateringint);
        indoor = (CheckBox) findViewById(R.id.indoorcheck);
        potted = (CheckBox) findViewById(R.id.potted);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nameInput = plantName.getText().toString();
                if (Integer.parseInt(plantAge.getText().toString()) > 0) {
                    ageInput = Integer.parseInt(plantAge.getText().toString());
                } else {
                    ageInput = 0;
                }

                if (Integer.parseInt(interval.getText().toString()) > 0) {
                    intervalInput = Integer.parseInt(interval.getText().toString());
                } else {
                    intervalInput = 0;
                }

                indoorInput = indoor.isChecked();
                pottedInput = potted.isChecked();

                PlantObject newPlant = new PlantObject(nameInput, ageInput, intervalInput, null, indoorInput, pottedInput);
                saveData(newPlant);
                loadData();


                //TextView textView = (TextView) findViewById(R.id.textView);
                //textView.setText("Plant name: " + newPlant.getName() + "\nPlant age:" + newPlant.getAge() + "\nWatering interval: " + newPlant.getInterval() + "\nIndoor: " + indoorInput + "\nPotted: " + pottedInput);
            }
        });


    }

    public void saveData(PlantObject plantIn){
        SharedPreferences mPref = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor edit = mPref.edit();
        edit.putString(TEXT, plantIn.getName());

        edit.apply();
        Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();
    }

    public void loadData(){
        SharedPreferences mPref = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);

        name = mPref.getString(TEXT,"");

        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText(name);
    }

    public void updateView(){
        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText(name);
    }

}