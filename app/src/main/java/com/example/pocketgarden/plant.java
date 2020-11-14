package com.example.pocketgarden;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import plant.*;

public class plant extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant);

        PlantObject my_plant_2 = new PlantObject("abc", 1, 1, null, true, true);



        TextView plantName = (TextView) findViewById(R.id.plantName);
        plantName.setText(my_plant_2.getName());
    }
}