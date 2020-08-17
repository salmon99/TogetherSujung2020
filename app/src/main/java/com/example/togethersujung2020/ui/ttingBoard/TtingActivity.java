package com.example.togethersujung2020.ui.ttingBoard;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.togethersujung2020.R;
import com.example.togethersujung2020.ui.freeBoard.FreeListViewAdapter;
import com.google.android.material.tabs.TabLayout;

public class TtingActivity extends AppCompatActivity {
    ListView listview ;
    FreeListViewAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tting);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //뒤로가기 버튼 추가

        ViewPager vp = findViewById(R.id.viewpager); //화면을 슬라이드 해서 넘기는 뷰페이저
        com.example.togethersujung2020.ui.ttingBoard.VPAdapter adapter = new com.example.togethersujung2020.ui.ttingBoard.VPAdapter(getSupportFragmentManager());
        vp.setAdapter(adapter);

        //뷰페이저와 탭 연동
        TabLayout tab = findViewById(R.id.tting_tab);
        tab.setupWithViewPager(vp);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) { //액션바 메뉴 표시하기
        ActionBar ab = getSupportActionBar() ;
        ab.setTitle("띵 게시판") ;
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) { //액션바 실행하기
        switch (item.getItemId()) {
            case android.R.id.home: //뒤로가기 버튼 클릭시 동작
                finish();
                Toast.makeText(this, "뒤로가기 버튼을 클릭했습니다.", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
