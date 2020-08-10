package com.example.togethersujung2020.ui.freeBoard;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.togethersujung2020.R;
import com.example.togethersujung2020.ui.main.ProfileActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class FreePostViewActivity extends AppCompatActivity {
TextView title;
TextView content;
TextView key;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_freepostview);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //뒤로가기 버튼 추가

        title =(TextView)findViewById(R.id.title);
        content=(TextView)findViewById(R.id.content);
        key = (TextView)findViewById(R.id.key);

        Intent postview = this.getIntent();
        String title1 = postview.getStringExtra("title");
        String content1 = postview.getStringExtra("content");
        String commentNum1 = postview.getStringExtra("commentNum");
        String key1=postview.getStringExtra("postkey");

        title.setText(title1);
        content.setText(content1);
        key.setText(key1); //todo 나중에 키값 보여주는 건 지우기
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) { //액션바 메뉴 표시하기
        ActionBar ab = getSupportActionBar() ;
        ab.setTitle("같이수정 자유게시판") ;
        getMenuInflater().inflate(R.menu.freepostview_actions, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) { //액션바 실행하기
        switch (item.getItemId()) {
            case R.id.ScrapPost: //todo 스크랩 기능 구현
                Toast.makeText(FreePostViewActivity.this, "글 스크랩 버튼을 클릭했습니다.", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.RemovePost:
                Intent postview = this.getIntent();
                String content1 = postview.getStringExtra("content");
                String key1 = postview.getStringExtra("postkey");
                DatabaseReference database= FirebaseDatabase.getInstance().getReference();
                Query query = database.child("freeboard").child(key1).orderByChild("content").equalTo(content1);
                query.getRef().removeValue();
                finish();
                Toast.makeText(FreePostViewActivity.this, "글 삭제 버튼을 클릭했습니다.", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.ModifyPost: //todo 글 수정 기능 구현
                Toast.makeText(FreePostViewActivity.this, "글 수정 버튼을 클릭했습니다.", Toast.LENGTH_SHORT).show();
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
