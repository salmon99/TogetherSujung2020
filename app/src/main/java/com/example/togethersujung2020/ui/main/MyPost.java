package com.example.togethersujung2020.ui.main;

public class MyPost {
    String board="";
    String title="";

    MyPost(){}

    MyPost(String board, String title){
        this.board=board;
        this.title=title;
    }

    public String getBoard(){return board;}
    public String getTitle(){return title;}
}
