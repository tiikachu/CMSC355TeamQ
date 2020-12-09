package com.example.pocketgarden;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class PlantCare extends AppCompatActivity implements View.OnClickListener {

    public CardView plant1, plant2, plant3, plant4;
    TextView textView1, textView2, textView3, textView4;
    String plantId;
    String id;
    boolean clicked;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.plantcare);

        plant1 = findViewById(R.id.plant1);
        plant2 = findViewById(R.id.plant2);
        plant3 = findViewById(R.id.plant3);
        plant4 = findViewById(R.id.plant4);

        textView1 = findViewById(R.id.plant1text);
        textView2 = findViewById(R.id.plant2text);
        textView3 = findViewById(R.id.plant3text);
        textView4 = findViewById(R.id.plant4text);



        plant1.setOnClickListener(this);
        plant2.setOnClickListener(this);
        plant3.setOnClickListener(this);
        plant4.setOnClickListener(this);

        
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.plant1:
                Toast.makeText(this, getPlantId(textView1), Toast.LENGTH_SHORT).show();
                intent = new Intent(this, Notifications.class);
                //plantId = getPlantId(textView1);
                break;
            case R.id.plant2:
                Toast.makeText(this, getPlantId(textView2), Toast.LENGTH_SHORT).show();
                intent = new Intent(this, Notifications.class);
                //plantId = getPlantId(textView2);
                break;
            case R.id.plant3:
                Toast.makeText(this, getPlantId(textView3), Toast.LENGTH_SHORT).show();
                intent = new Intent(this, Notifications.class);
              //  plantId = getPlantId(textView3);
                break;
            case R.id.plant4:
               Toast.makeText(this, getPlantId(textView4), Toast.LENGTH_SHORT).show();
                intent = new Intent(this, Notifications.class);
                //plantId = getPlantId(textView4);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + v.getId());
        }
        startActivity(intent);
    }

    public String getPlantId(TextView tw) {
        return (String) tw.getText();
    }



}