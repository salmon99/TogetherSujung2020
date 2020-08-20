package com.example.togethersujung2020.ui.ttingBoard;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.togethersujung2020.R;
import com.example.togethersujung2020.ui.freeBoard.FreeBoard;
import com.example.togethersujung2020.ui.freeBoard.FreeListViewAdapter;
import com.example.togethersujung2020.ui.freeBoard.FreeWritePostActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TtingWritePostActivity extends AppCompatActivity {
    final String userId ="김수정";
    EditText editTitle;
    EditText editContent;
    FreeListViewAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_freewritepost);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //뒤로가기 버튼 추가
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        editTitle = (EditText) findViewById(R.id.editTextTitle);
        editContent = (EditText) findViewById(R.id.editTextContents);

        editTitle.setHint("제목을 입력해주세요.");
        editContent.setHint("띵 하고 싶은 [음식(물건)], 나누고 싶은 [인원 수](ex) 2명), [가격], [거래 장소]의 내용을 포함해서 글을 작성해주세요.");
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) { //액션바 메뉴 표시하기
        ActionBar ab = getSupportActionBar() ;
        ab.setTitle("띵게시판 새 글 작성") ;
        getMenuInflater().inflate(R.menu.freewritepost_actions, menu);
        return true;
    }
    public class Post{
        public String title;
        public String board;
        public Post(){}
        public Post(String title,String board){
            this.title = title;
            this.board = board;
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) { //액션바 실행하기
        switch (item.getItemId()) {
            case android.R.id.home: //뒤로가기 버튼 클릭시 동작
                finish();
                return true;
            case R.id.recordPost: //등록 버튼 클릭시 동작
                Intent postview = this.getIntent();
                final String div1 = postview.getStringExtra("div");
                DatabaseReference database = FirebaseDatabase.getInstance().getReference();
                TtingBoard board =new TtingBoard(editTitle.getText().toString(),editContent.getText().toString(),div1); //todo 한식 말고 string 받아와서 출력하도록 고치기
                database.getRef().child("ttingboard").push().setValue(board);
                TtingWritePostActivity.Post post = new TtingWritePostActivity.Post(editTitle.getText().toString(),"띵");
                database.getRef().child(userId).child("myPost").push().setValue(post);
                finish();
                Toast.makeText(this, "새로운 글을 등록했습니다.", Toast.LENGTH_SHORT).show();
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}