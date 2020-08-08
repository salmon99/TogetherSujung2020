package com.example.togethersujung2020.ui.ttingBoard;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.togethersujung2020.R;
import com.example.togethersujung2020.ui.freeBoard.FreeListViewAdapter;

public class TtingActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tting);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //뒤로가기 버튼 추가

        ListView listview ;
        FreeListViewAdapter adapter;
    }
}
