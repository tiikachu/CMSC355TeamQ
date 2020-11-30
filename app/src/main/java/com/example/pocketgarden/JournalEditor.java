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



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journal_editor);

        EditText e = (EditText) findViewById(R.id.EditText);

        ProcessThread p = new ProcessThread();
        p.setEditText(e);
        Thread thread = new Thread(p);
        thread.start();
        e.setText(p.getEditText());
    }

    public void goBack(View view){
        Intent intent = new Intent(getApplicationContext(), ShowJournals.class);
        startActivity(intent);
    }

    class ProcessThread implements Runnable{
        private EditText editText;
        private int noteID;

        @Override
        public void run() {
            editText = initialize();
            save(editText);
        }

        public EditText initialize(){
            Intent   intent   = getIntent();
            noteID = intent.getIntExtra("noteID", -1);  //default value is -1 (in case of intent error)

            if(noteID != -1) {
                editText.setText(ShowJournals.journal.get(noteID)); }

            else   {
                runOnUiThread(() -> {
                    ShowJournals.journal.add("");                // as initially, the note is empty
                    noteID = ShowJournals.journal.size() - 1;
                    ShowJournals.arrayAdapter.notifyDataSetChanged();
                });  }

            return editText;
        }

        public void save(EditText editText){
            editText.addTextChangedListener(new TextWatcher()
            {

                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {}

                @Override
                public void afterTextChanged(Editable s)
                {
                    ShowJournals.journal.set(noteID, String.valueOf(s));
                    ShowJournals.arrayAdapter.notifyDataSetChanged();

                    SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("com.pocketgarden.journal.notes", Context.MODE_PRIVATE);
                    HashSet<String> set = new HashSet<>(ShowJournals.journal);
                    sharedPreferences.edit().putStringSet("notes", set).apply();
                }
            });
        }

        public Editable getEditText(){
            return editText.getText();
        }

        public void setEditText(EditText e){
            editText = new EditText(getApplicationContext());
            editText.setText(e.getText());
        }
    }
}