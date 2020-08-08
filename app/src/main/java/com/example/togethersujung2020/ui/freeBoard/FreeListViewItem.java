package com.example.togethersujung2020.ui.freeBoard;

import android.app.Activity;
import android.graphics.drawable.Drawable;

import androidx.core.content.ContextCompat;

import com.example.togethersujung2020.R;

public class FreeListViewItem {
    private Drawable iconDrawable ;
    private String titleStr ;
    private String descStr ;
    private String commentNum;

    public void setIcon(Drawable icon) {
        iconDrawable = icon ;
    }
    public void setIcon(){}
    public void setTitle(String title) { titleStr = title ; }
    public void setDesc(String desc) {
        descStr = desc ;
    }
    public void setComment(String comment){commentNum=comment;}
    public Drawable getIcon() {
        return this.iconDrawable ;
    }
    public String getTitle() {
        return this.titleStr ;
    }
    public String getDesc() {
        return this.descStr ;
    }
    public String getComment(){return this.commentNum;}
}
