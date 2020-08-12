package com.example.togethersujung2020.ui.freeBoard;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FreeComment {
    final DatabaseReference database= FirebaseDatabase.getInstance().getReference();
    String comment="";
  //  String nickname="";

    FreeComment(){}

    FreeComment(String comment){
        this.comment=comment;
   //     this.nickname=nickname;
    }

    public String getComment(){return this.comment;}
//    public String getNickname(){return nickname;}
}
