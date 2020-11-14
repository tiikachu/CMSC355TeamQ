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

        PlantObject my_plant_2 = new PlantObject("Sample Plant", 1, 1, null, true, true);



        TextView plantName = (TextView) findViewById(R.id.plantName);
        plantName.setText("Plant name is " + my_plant_2.getName());

        TextView plantAge = (TextView) findViewById(R.id.plantAge);
        plantAge.setText("This plant is " + my_plant_2.getAge() + " weeks old.");

        TextView plantInt = (TextView) findViewById(R.id.wateringint);
        plantInt.setText("Water this plant every " + my_plant_2.getInterval() + " day(s).");

        TextView plantIn = (TextView) findViewById(R.id.Indoor);
        if(my_plant_2.isIndoor()){
            plantIn.setText("This is a indoor plant.");
        }
        else{
            plantIn.setText("This is a outdoor plant.");
        }

        TextView planted = (TextView) findViewById(R.id.potted);
        if(my_plant_2.isPotted()){
            planted.setText("This plant has been potted.");
        }
        else {
            planted.setText("This plant is not potted.");
        }




    }
}