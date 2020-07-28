package com.example.togethersujung2020.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.togethersujung2020.R;
import com.example.togethersujung2020.ui.freeBoard.FreeActivity;
import com.example.togethersujung2020.ui.freeBoard.FreeWritePostActivity;
import com.google.android.material.tabs.TabLayout;

import java.lang.reflect.Array;

public class ProfileActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ViewPager vp = findViewById(R.id.viewpager); //화면을 슬라이드 해서 넘기는 뷰페이저
        VPAdapter adapter = new VPAdapter(getSupportFragmentManager());
        vp.setAdapter(adapter);

        //뷰페이저와 탭 연동
        TabLayout tab = findViewById(R.id.profile_tab);
        tab.setupWithViewPager(vp);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) { //액션바 메뉴 표시하기
        ActionBar ab = getSupportActionBar() ;
        ab.setTitle("프로필 정보") ;
        getMenuInflater().inflate(R.menu.profile_actions, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) { //액션바 실행하기
        switch (item.getItemId()) {
            case R.id.settings:
                Intent settings = new Intent(this, SettingsActivity.class);
                startActivity(settings);
                Toast.makeText(this, "환경설정 버튼을 클릭했습니다.", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
