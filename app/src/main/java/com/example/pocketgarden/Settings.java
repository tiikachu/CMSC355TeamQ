package com.example.pocketgarden;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import androidx.appcompat.app.AppCompatActivity;

public class Settings extends AppCompatActivity implements View.OnClickListener {
    private CheckBox never, everyday;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        never = ((CheckBox)findViewById(R.id.never));
        everyday = ((CheckBox)findViewById(R.id.everyday));
        Button button = ((Button) findViewById(R.id.button2));
        button.setOnClickListener(this);
        load();
    }
    @Override
    public void onClick(View v) {
        save("CHECKBOX", never.isChecked());
        save("CHECKBOX", everyday.isChecked());
    }

    private void load () {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        boolean cbValue = sp.getBoolean("CHECKBOX", false);
        never.setChecked(cbValue);

    }

    private void save (String key, boolean value) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor edit = sp.edit();
        edit.putBoolean(key, value);
        edit.apply();
    }
}
