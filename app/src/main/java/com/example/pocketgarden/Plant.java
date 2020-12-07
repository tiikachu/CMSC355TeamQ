package com.example.pocketgarden;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import plant.PlantObject;

public class Plant extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant);

        PlantObject my_plant_2 = new PlantObject("Sample Plant", 1, 1, null, true, true, "https://bs.floristic.org/image/o/473e2ed33e13f12e5424fff21996c7476520dc4d");


        TextView plantName = findViewById(R.id.plantName);
        plantName.setText("Plant name is " + my_plant_2.getName());

        TextView plantAge = findViewById(R.id.plantAge);
        plantAge.setText("This Plant is " + my_plant_2.getAge() + " weeks old.");

        TextView plantInt = findViewById(R.id.wateringint);
        plantInt.setText("Water this Plant every " + my_plant_2.getInterval() + " day(s).");

        TextView plantIn = findViewById(R.id.Indoor);
        if (my_plant_2.isIndoor()) {
            plantIn.setText(R.string.Indoor_plant_msg);
        } else {
            plantIn.setText(R.string.outdoor_plant_msg);
        }

        TextView planted = findViewById(R.id.potted);
        if (my_plant_2.isPotted()) {
            planted.setText(R.string.potted_msg);
        } else {
            planted.setText(R.string.not_potted_msg);
        }


        //Drawable myDrawable = getResources().getDrawable(R.drawable.Plant);
        //ImageView iv = (ImageView) findViewById(R.id.plantPhoto);
        //iv.setImageDrawable(myDrawable);
        imgReqs imgReq = new imgReqs();
        Thread thread = new Thread(imgReq);
        imgReq.setImageURL(my_plant_2.getImgURL());
        thread.start();

        ImageView iv = findViewById(R.id.plantPhoto);
        iv.setImageBitmap(imgReq.getMyBitmap());

    }

    static class imgReqs implements Runnable {
        Bitmap myBitmap;
        String imageURL;

        @Override
        public void run() {
            URL urlConnection;
            HttpURLConnection connection;
            InputStream finalInput = null;
            try {
                urlConnection = new URL(imageURL);
                connection = (HttpURLConnection) urlConnection.openConnection();
                InputStream input;
                input = connection.getInputStream();
                finalInput = input;
            } catch (IOException e) {
                e.printStackTrace();
            }

            myBitmap = BitmapFactory.decodeStream(finalInput);
        }

        public Bitmap getMyBitmap() {
            return myBitmap;
        }

        public void setImageURL(String s) {
            imageURL = s;
        }
    }


}