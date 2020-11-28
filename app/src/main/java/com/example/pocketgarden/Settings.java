package com.example.pocketgarden;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.appcompat.app.AppCompatActivity;

public class Settings extends AppCompatActivity {
    private CheckBox never, everyday;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);


        never = ((CheckBox) findViewById(R.id.never));
        everyday = ((CheckBox) findViewById(R.id.everyday));

        SharedPreferences preferences = getSharedPreferences("com.pocketgarden.settings", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        never.setChecked(preferences.getBoolean("checked", false));
        never.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                editor.putBoolean("checked", isChecked);
                editor.apply();
            }
        });

        everyday.setChecked(preferences.getBoolean("checked", false));
        everyday.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                editor.putBoolean("checked", isChecked);
                editor.apply();
            }
        });


    }

}
//    @Override
//    public void onClick(View v) {
//        save("CHECKBOX", never.isChecked());
//        save("CHECKBOX", everyday.isChecked());
//    }
//
//    private void load () {
//        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
//        boolean cbValue = sp.getBoolean("CHECKBOX", false);
//        if (cbValue) {
//            never.setChecked(true);
//        }
//        else {
//            never.setChecked(false);
//        }
//
//    }
//
//    private void save (String key, boolean value) {
//        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
//        SharedPreferences.Editor edit = sp.edit();
//        edit.putBoolean(key, value);
//        edit.apply();
//    }
//}
