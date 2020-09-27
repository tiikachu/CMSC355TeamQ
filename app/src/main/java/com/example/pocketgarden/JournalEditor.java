package com.example.pocketgarden;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.util.HashSet;

public class JournalEditor extends AppCompatActivity {

    int noteID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journal_editor);

        EditText editText = (EditText)findViewById(R.id.EditText);
        Intent intent = getIntent();
        noteID = intent.getIntExtra("noteID", -1);  //default value is -1 (in case of intent error)

        if(noteID != -1)
        {
            editText.setText(ShowJournals.journal.get(noteID));
        }

        else
        {
            ShowJournals.journal.add("");                // as initially, the note is empty
            noteID = ShowJournals.journal.size() - 1;
            ShowJournals.arrayAdapter.notifyDataSetChanged();
        }

        editText.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                ShowJournals.journal.set(noteID, String.valueOf(s));
                ShowJournals.arrayAdapter.notifyDataSetChanged();

                SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("com.tanay.thunderbird.notes", Context.MODE_PRIVATE);
                HashSet<String> set = new HashSet<>(ShowJournals.journal);
                sharedPreferences.edit().putStringSet("notes", set).apply();
            }

            @Override
            public void afterTextChanged(Editable s)
            {

            }
        });
    }
}