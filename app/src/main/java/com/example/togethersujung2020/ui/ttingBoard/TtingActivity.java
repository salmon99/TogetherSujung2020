package com.example.togethersujung2020.ui.ttingBoard;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import com.example.togethersujung2020.R;
import com.example.togethersujung2020.ui.freeBoard.FreeActivity;
import com.example.togethersujung2020.ui.freeBoard.FreeBoard;
import com.example.togethersujung2020.ui.freeBoard.FreeListViewAdapter;
import com.example.togethersujung2020.ui.freeBoard.FreeListViewItem;
import com.example.togethersujung2020.ui.freeBoard.FreePostViewActivity;
import com.example.togethersujung2020.ui.main.profile_comment;
import com.example.togethersujung2020.ui.main.profile_content;
import com.example.togethersujung2020.ui.main.profile_scrap;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TtingActivity extends AppCompatActivity {
    FragmentManager fragmentManager = getSupportFragmentManager();
    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tting);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //뒤로가기 버튼 추가

        changeView(0);

        TabLayout tab = findViewById(R.id.tting_tab);
        tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int pos = tab.getPosition();
                changeView(pos);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                // do nothing
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                // do nothing
            }
        });
    }

    private void changeView(int index) {
        switch (index) {
            case 0:
                fragmentTransaction = fragmentManager.beginTransaction();
                tting_food fragment1 = new tting_food();
                fragmentTransaction.add(R.id.profile_fragment, fragment1);
                fragmentTransaction.commit();
                break;
            case 1:
                fragmentTransaction = fragmentManager.beginTransaction();
                tting_stuff fragment2 = new tting_stuff();
                fragmentTransaction.add(R.id.profile_fragment, fragment2);
                fragmentTransaction.commit();
                break;
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) { //액션바 메뉴 표시하기
        ActionBar ab = getSupportActionBar() ;
        ab.setTitle("같이수정 띵 게시판") ;
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
