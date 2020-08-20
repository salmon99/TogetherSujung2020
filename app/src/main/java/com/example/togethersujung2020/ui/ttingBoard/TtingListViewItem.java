package com.example.togethersujung2020.ui.ttingBoard;

public class TtingListViewItem {
    private String titleStr ;
    private String descStr ;
    private String divStr;
    private String postkey;

    public void setTitle(String title) { titleStr = title ; }
    public void setDesc(String desc) {
        descStr = desc ;
    }
    public void setDiv(String div){divStr=div;}
    public void setKey(String key){postkey=key;}

    public String getTitle() {
        return this.titleStr ;
    }
    public String getDesc() {
        return this.descStr ;
    }
    public String getDiv(){return this.divStr;}
    public String getKey(){return this.postkey;}
}
