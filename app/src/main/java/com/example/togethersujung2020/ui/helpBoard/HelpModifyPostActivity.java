package com.example.togethersujung2020.ui.helpBoard;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.togethersujung2020.R;
import com.example.togethersujung2020.ui.infoBoard.InfoModifyPostActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class HelpModifyPostActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_freemodifypost);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //뒤로가기 버튼 추가

        final EditText title = (EditText) findViewById(R.id.ModifyTitle);
        final EditText content = (EditText) findViewById(R.id.ModifyContents);

        Intent postview = this.getIntent();
        final String title1 = postview.getStringExtra("title_before");
        final String content1 = postview.getStringExtra("content_before");
        final String key1 = postview.getStringExtra("postkey");

        title.setText(title1);
        content.setText(content1);

        Button free_board = (Button) findViewById(R.id.button_modify); //자유게시판 버튼 누르면 화면 이동
        free_board.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference database = FirebaseDatabase.getInstance().getReference();
                database.getRef().child("helpboard").child(key1).child("title").setValue(title.getText().toString());
                database.getRef().child("helpboard").child(key1).child("content").setValue(content.getText().toString());
                finish();
                Toast.makeText(HelpModifyPostActivity.this, "글 수정을 완료했습니다.", Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) { //액션바 메뉴 표시하기
        ActionBar ab = getSupportActionBar() ;
        ab.setTitle("도움게시판 글 수정") ;
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) { //액션바 실행하기
        switch (item.getItemId()) {
            case android.R.id.home: //뒤로가기 버튼 클릭시 동작
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
