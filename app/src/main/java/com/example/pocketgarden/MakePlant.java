package com.example.pocketgarden;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import plant.*;

public class MakePlant extends AppCompatActivity {

    private EditText plantName;
    private EditText plantAge;
    private CheckBox indoor;
    private CheckBox potted;

    private int ageInput;
    private int intervalInput;
    private boolean indoorInput;
    private boolean pottedInput;
    private String nameInput;
    private String imageURL;

    public static final String SHARED_PREFS = "com.pocketgarden.plants";
    public static final String TEXT = "text";

    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_plant);

        Button submit = findViewById(R.id.submit);
        plantName = findViewById(R.id.plantName);
        plantAge = findViewById(R.id.plantAge);
        EditText interval = findViewById(R.id.wateringInt);
        indoor = findViewById(R.id.indoorcheck);
        potted = findViewById(R.id.potted);


        submit.setOnClickListener(view -> {
            nameInput = plantName.getText().toString();
            ageInput = Math.max(Integer.parseInt(plantAge.getText().toString()), 0);

            getData(nameInput);

            indoorInput = indoor.isChecked();
            pottedInput = potted.isChecked();
            String inputtedInterval = interval.getText().toString();
            if(isInteger(inputtedInterval)){
                intervalInput = Integer.parseInt(inputtedInterval);
            }

            PlantObject newPlant = new PlantObject(nameInput, ageInput, intervalInput, null, indoorInput, pottedInput,  imageURL);
            saveData(newPlant);
            loadData();
        });


    }

    public void getData(String nameInput){
        Runnable runnable = () -> {
            GetPlantInfo request = new GetPlantInfo(getApplicationContext());
            String JSON = request.getCommonNameInfo(nameInput);
            String[] makePlantInfo = request.parseJSON(JSON);
            String[] precipRange = makePlantInfo[1].split("-");
            int[] precips = {Integer.parseInt(precipRange[0], Integer.parseInt(precipRange[1]))};
            decideRange(precips);
            imageURL = makePlantInfo[2];
        };new Thread(runnable).start();
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

        TextView textView = findViewById(R.id.textView);
        textView.setText(name);
    }

    public void updateView(){
        TextView textView = findViewById(R.id.textView);
        textView.setText(name);
    }

    public boolean isInteger(String str) {
        if (str == null) {
            return false;
        }
        int length = str.length();
        if (length == 0) {
            return false;
        }
        int i = 0;
        if (str.charAt(0) == '-') {
            if (length == 1) {
                return false;
            }
            i = 1;
        }
        for (; i < length; i++) {
            char c = str.charAt(i);
            if (c < '0' || c > '9') {
                return false;
            }
        }
        return true;
    }

}