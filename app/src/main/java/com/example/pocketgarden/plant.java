package com.example.pocketgarden;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.module.AppGlideModule;
import com.bumptech.glide.module.LibraryGlideModule;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import plant.*;

public class plant extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant);

        PlantObject my_plant_2 = new PlantObject("Sample Plant", 1, 1, null, true, true, "https://bs.floristic.org/image/o/473e2ed33e13f12e5424fff21996c7476520dc4d");



        TextView plantName = (TextView) findViewById(R.id.plantName);
        plantName.setText("Plant name is " + my_plant_2.getName());

        TextView plantAge = (TextView) findViewById(R.id.plantAge);
        plantAge.setText("This plant is " + my_plant_2.getAge() + " weeks old.");

        TextView plantInt = (TextView) findViewById(R.id.wateringint);
        plantInt.setText("Water this plant every " + my_plant_2.getInterval() + " day(s).");

        TextView plantIn = (TextView) findViewById(R.id.Indoor);
        if(my_plant_2.isIndoor()){
            plantIn.setText(R.string.Indoor_plant_msg);
        }
        else{
            plantIn.setText(R.string.outdoor_plant_msg);
        }

        TextView planted = (TextView) findViewById(R.id.potted);
        if(my_plant_2.isPotted()){
            planted.setText(R.string.potted_msg);
        }
        else {
            planted.setText(R.string.not_potted_msg);
        }


        getImg(my_plant_2.getImgURL());

        //Drawable myDrawable = getResources().getDrawable(R.drawable.plant);
        //ImageView iv = (ImageView) findViewById(R.id.plantPhoto);
        //iv.setImageDrawable(myDrawable);




    }


    public void getImg(String imageURL){

        Runnable runnable = new Runnable(){
            @Override
            public void run() {


                URL urlConnection = null;
                try {
                    urlConnection = new URL(imageURL);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                HttpURLConnection connection = null;
                try {
                    connection = (HttpURLConnection) urlConnection.openConnection();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                connection.setDoInput(true);
                try {
                    connection.connect();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                InputStream input = null;
                try {
                    input = connection.getInputStream();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                InputStream finalInput = input;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Bitmap myBitmap = BitmapFactory.decodeStream(finalInput);
                        ImageView iv = (ImageView) findViewById(R.id.plantPhoto);
                        iv.setImageBitmap(myBitmap);


                    }
                });


            }
        }; new Thread(runnable).start();
    }





}