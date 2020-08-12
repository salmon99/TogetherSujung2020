package com.example.togethersujung2020.ui.freeBoard;

public class FreeCommentListViewItem {
    private String commentStr;
  //  private String nicknameStr;

    public void setComment(String comment){
        commentStr=comment;
    }
//    public void setNickname(String nickname){
//        commentStr=nickname;
//    }
    public String getComment(){
        return this.commentStr;
    }
//    public String getNickname(){
//        return this.nicknameStr;
//    }

}
