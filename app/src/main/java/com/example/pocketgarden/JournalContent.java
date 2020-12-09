package com.example.pocketgarden;

public class JournalContent {
    private StringBuilder[] content;

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

    public String toString(){
        return content[0].toString();
    }


}
