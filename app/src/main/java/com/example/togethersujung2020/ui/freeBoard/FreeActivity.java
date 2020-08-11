package com.example.togethersujung2020.ui.freeBoard;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.togethersujung2020.R;
import com.example.togethersujung2020.ui.housingBoard.HousingActivity;
import com.example.togethersujung2020.ui.main.MainActivity;
import com.example.togethersujung2020.ui.main.ProfileActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FreeActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_free);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //뒤로가기 버튼 추가

        ListView listview ;
        FreeListViewAdapter adapter;

        // Adapter 생성
        adapter = new FreeListViewAdapter() ;

        // 리스트뷰 참조 및 Adapter달기
        listview = (ListView) findViewById(R.id.listview1);
        listview.setAdapter(adapter);

        //todo: 데이터를 데이터베이스로부터 받아와야함

        // 첫 번째 아이템 추가.
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.profile),
                "제목어쩌구", "뭔가의 내용","5") ;
        // 두 번째 아이템 추가.
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.user),
                "제목제목", "글내용 어쩌구","0") ;
        // 세 번째 아이템 추가.
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.user2),
                "제목!!", "글내용어쩌구","3") ;
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                // get item
                FreeListViewItem item = (FreeListViewItem) parent.getItemAtPosition(position) ;

                String titleStr = item.getTitle() ;
                String descStr = item.getDesc() ;
                Drawable iconDrawable = item.getIcon() ;
                String commentNumber = item.getComment();

                // TODO : use item data.
            }
        }) ;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) { //액션바 메뉴 표시하기
        ActionBar ab = getSupportActionBar() ;
        ab.setTitle("같이수정 자유게시판") ;
        getMenuInflater().inflate(R.menu.freeactionbar_actions, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) { //액션바 실행하기
        switch (item.getItemId()){
            case R.id.newPost:
                Intent newPost = new Intent(this,FreeWritePostActivity.class);
                startActivity(newPost);
                Toast.makeText(FreeActivity.this, "새 글 등록 버튼을 클릭했습니다.", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.profile:
                Intent profile = new Intent(this, ProfileActivity.class);
                startActivity(profile);
                Toast.makeText(FreeActivity.this, "프로필 버튼을 클릭했습니다.", Toast.LENGTH_SHORT).show();
                return true;
            case android.R.id.home: //뒤로가기 버튼 클릭시 동작
                finish();
                Toast.makeText(this, "뒤로가기 버튼을 클릭했습니다.", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}