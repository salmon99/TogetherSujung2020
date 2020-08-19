package com.example.togethersujung2020.ui.ttingBoard;

public class TtingBoard  {
    String title="";
    String content="";
    String div="";

    TtingBoard(){}

    public TtingBoard(String title, String content,String div){
        this.title=title;
        this.content=content;
        this.div=div;
    }

    public String getTitle(){return title;}
    public String getContent(){return content;}
    public String getDiv(){return div;}
}
