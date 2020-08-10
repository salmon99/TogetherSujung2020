package com.example.togethersujung2020.ui.freeBoard;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FreeBoard {
    final DatabaseReference database= FirebaseDatabase.getInstance().getReference();
    String title="";
    String content="";

    FreeBoard(){}

    FreeBoard(String title, String content){
        this.title=title;
        this.content=content;
    }

    public String getTitle(){return title;}
    public String getContent(){return content;}
}
