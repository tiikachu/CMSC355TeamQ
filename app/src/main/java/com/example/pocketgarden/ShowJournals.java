package com.example.pocketgarden;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class ShowJournals extends AppCompatActivity {

    static ArrayList<String> journal = new ArrayList<>();
    static ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_journals);

        ListView listView = findViewById(R.id.journalList);

        initialize(listView);
        setOnClick(listView);
        setLongClick(listView);

    }

    public void initialize(ListView listView){
        Runnable runnable = () -> {
            SharedPreferences sp = getApplicationContext().getSharedPreferences(
                    "com.pocketgarden.journal.notes",
                    Context.MODE_PRIVATE);
            Set<String> tempSet = sp.getStringSet("notes", null);
            if(tempSet != null && journal.size() != tempSet.size()){
                journal.addAll(tempSet);
            }
        }; new Thread(runnable).start();

        arrayAdapter = new ArrayAdapter<>(this,
                R.layout.listview_content_format,
                journal);
        listView.setAdapter(arrayAdapter);
    }

    public void setOnClick(ListView listView){
        listView.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(getApplicationContext(), JournalEditor.class);
            intent.putExtra("noteID",
                    position); //to tell us which row of listView was tapped
            startActivity(intent);
        });
    }

    public void setLongClick(ListView listView){
        listView.setOnItemLongClickListener((parent, view, position, id) -> {

            new AlertDialog.Builder(ShowJournals.this)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setTitle("Delete?")
                    .setMessage("Are you sure you want to delete this note?")
                    .setPositiveButton("Yes", (dialog, which) -> {
                        journal.remove(position);
                        arrayAdapter.notifyDataSetChanged();
                    })

                    .setNegativeButton("No", null)
                    .show();

            Runnable runnable = () ->{
            SharedPreferences sharedPreferences = getApplicationContext().
                    getSharedPreferences("com.pocketgarden.journal.notes",
                            Context.MODE_PRIVATE);
            HashSet<String> set = new HashSet<>(ShowJournals.journal);
            sharedPreferences.edit().putStringSet("notes", set).apply();};
            new Thread(runnable).start();

            return true;
        });
    }

    public void goBack(View v){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

    public void makeNewNote(View v){
        Intent intent = new Intent(getApplicationContext(), JournalEditor.class);
        startActivity(intent);
    }

}