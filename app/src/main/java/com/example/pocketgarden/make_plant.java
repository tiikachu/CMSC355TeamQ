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
    private String imageURL;

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

                getData(nameInput);

                indoorInput = indoor.isChecked();
                pottedInput = potted.isChecked();

                PlantObject newPlant = new PlantObject(nameInput, ageInput, intervalInput, null, indoorInput, pottedInput,  imageURL);
                saveData(newPlant);
                loadData();


                //TextView textView = (TextView) findViewById(R.id.textView);
                //textView.setText("Plant name: " + newPlant.getName() + "\nPlant age:" + newPlant.getAge() + "\nWatering interval: " + newPlant.getInterval() + "\nIndoor: " + indoorInput + "\nPotted: " + pottedInput);
            }
        });


    }

    public void getData(String nameInput){
        Runnable runnable = new Runnable(){
            @Override
            public void run() {
                GetPlantInfo request = new GetPlantInfo(getApplicationContext());
                String JSON = request.getCommonNameInfo(nameInput);
                String[] makePlantInfo = request.parseJSON(JSON);
                String[] precipRange = makePlantInfo[1].split("-");
                int[] precips = {Integer.parseInt(precipRange[0], Integer.parseInt(precipRange[1]))};
                decideRange(precips);
                imageURL = makePlantInfo[2];
            }
        };
        new Thread(runnable).start();
    }

    private void decideRange(int[] precips){
        int avg = (precips[0] + precips[1]) / 2;
        double inchAvg = avg / 25.4 / 365;

        if(inchAvg > 1)
            intervalInput = 1;
        else if(inchAvg > 0.75)
            intervalInput = 2;
        else if(inchAvg > 0.6)
            intervalInput = 3;
        else if(inchAvg > 0.5)
            intervalInput = 4;
        else if(inchAvg > 0.4)
            intervalInput = 5;
        else if(inchAvg > 0.3)
            intervalInput = 6;
        else
            intervalInput = 7;
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