package com.example.pocketgarden;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class PlantCare extends AppCompatActivity implements View.OnClickListener {

    public CardView plant1, plant2, plant3, plant4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.plantcare);

        plant1 = (CardView) findViewById(R.id.plant1);
        plant2 = (CardView) findViewById(R.id.plant2);
        plant3 = (CardView) findViewById(R.id.plant3);
        plant4 = (CardView) findViewById(R.id.plant4);
        
        plant1.setOnClickListener(this);
        plant2.setOnClickListener(this);
        plant3.setOnClickListener(this);
        plant4.setOnClickListener(this);
        
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.plant1:
            case R.id.plant2:
            case R.id.plant3:
            case R.id.plant4:
                intent = new Intent(this, Notifications.class);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + v.getId());
        }
        startActivity(intent);
        }
}