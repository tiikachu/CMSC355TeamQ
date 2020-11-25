package com.example.pocketgarden;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
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

        ListView listView = findViewById(R.id.listView);

        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("com.pocketgarden.journal.notes", Context.MODE_PRIVATE);
        Set<String> tempSet = sharedPref.getStringSet("notes", null);

        if(tempSet != null && tempSet.size() != journal.size()){
            journal.addAll(tempSet);}
        else if(journal.size() == 0){
            journal.add("");
        }
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, journal);
        listView.setAdapter(arrayAdapter);

        /*
        Create intent to jump to journal editor class
         */
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Intent intent = new Intent(getApplicationContext(), JournalEditor.class);
                intent.putExtra("noteID", position); //to tell us which row of listView was tapped
                startActivity(intent);
            }
        });

        /*
        Create intent to delete arrayAdapter
         */
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id)
            {
                new AlertDialog.Builder(ShowJournals.this)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Delete?")
                        .setMessage("Are you sure you want to delete this note?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which)
                            {
                                journal.remove(position);
                                arrayAdapter.notifyDataSetChanged();
                            }
                        })

                        .setNegativeButton("No", null)
                        .show();
                /*
                save changes
                 */
                SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("com.pocketgarden.journal.notes", Context.MODE_PRIVATE);
                HashSet<String> set = new HashSet<>(ShowJournals.journal);
                sharedPreferences.edit().putStringSet("notes", set).apply();

                return true;
            }


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