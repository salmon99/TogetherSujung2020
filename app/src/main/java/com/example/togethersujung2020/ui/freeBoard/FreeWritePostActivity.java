package com.example.togethersujung2020.ui.freeBoard;

import android.os.Bundle;
import android.view.Menu;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.togethersujung2020.R;

public class FreeWritePostActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_freewritepost);
        //메뉴바에 '<' 버튼이 생긴다.(두개는 항상 같이다닌다)
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) { //액션바 메뉴 표시하기
        ActionBar ab = getSupportActionBar() ;
        ab.setTitle("자유게시판 새 글 작성") ;
        getMenuInflater().inflate(R.menu.freewritepost_actions, menu);
        return true;
    }
}
