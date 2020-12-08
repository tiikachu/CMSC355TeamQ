package com.example.pocketgarden;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import java.util.HashSet;

public class JournalEditor extends AppCompatActivity {
    private EditText editText;
    private int noteID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journal_editor);

        editText = findViewById(R.id.EditText);
        initialize();
        save();
    }

    public void goBack(View view){
        Intent intent = new Intent(getApplicationContext(), ShowJournals.class);
        startActivity(intent);
    }

    public void initialize(){
        Intent intent = getIntent();
        noteID = intent.getIntExtra("noteID", -1);  //default value is -1 (in case of intent error)

        if(noteID != -1) {
            editText.setText(ShowJournals.journal.get(noteID));
        }

        else {
            ShowJournals.journal.add("");
            noteID = ShowJournals.journal.size() - 1;
            ShowJournals.arrayAdapter.notifyDataSetChanged();
        }
    }

    public void save(){
        editText.addTextChangedListener(new TextWatcher() {
            @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                ShowJournals.journal.set(noteID, String.valueOf(s));
                ShowJournals.arrayAdapter.notifyDataSetChanged();
            }
            @Override
            public void afterTextChanged(Editable s) {
                Runnable r = () -> {
                    SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("com.pocketgarden.journal.notes", Context.MODE_PRIVATE);
                    HashSet<String> set = new HashSet<>(ShowJournals.journal);
                    sharedPreferences.edit().putStringSet("notes", set).apply();
                };
                new Thread(r).start();

                }
            });
        }



}