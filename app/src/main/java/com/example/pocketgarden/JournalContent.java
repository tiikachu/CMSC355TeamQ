package com.example.pocketgarden;

import org.jetbrains.annotations.NotNull;

public class JournalContent {
    private final StringBuilder[] content;

    public JournalContent(){
        content = new StringBuilder[2];
    }

    public JournalContent(String title){
        content = new StringBuilder[2];
        content[0] = new StringBuilder(title);
    }

    public JournalContent(String title, String body){
        content = new StringBuilder[2];
        content[0] = new StringBuilder(title);
        content[1] = new StringBuilder(body);
    }

    @NotNull
    @Override
    public String toString(){
        return content[0].toString();
    }

    public void updateTitle(String s){
        content[0] = new StringBuilder(s);
    }

    public void updateContent(String s){
        content[1] = new StringBuilder(s);
    }
}
