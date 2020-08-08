package com.example.togethersujung2020.ui.freeBoard;

public class FreeBoard {
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
