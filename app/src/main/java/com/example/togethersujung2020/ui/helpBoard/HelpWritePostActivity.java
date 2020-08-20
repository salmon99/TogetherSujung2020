package com.example.togethersujung2020.ui.helpBoard;

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
import com.example.togethersujung2020.ui.infoBoard.InfoWritePostActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class HelpWritePostActivity extends AppCompatActivity {
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
        editContent.setHint("주변 사람에게 도움을 요청하거나 도움을 준다는 내용의 글을 작성해주세요.");
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) { //액션바 메뉴 표시하기
        ActionBar ab = getSupportActionBar() ;
        ab.setTitle("도움게시판 새 글 작성") ;
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
                DatabaseReference database = FirebaseDatabase.getInstance().getReference();
                FreeBoard board =new FreeBoard(editTitle.getText().toString(),editContent.getText().toString());
                database.getRef().child("helpboard").push().setValue(board);
                HelpWritePostActivity.Post post = new HelpWritePostActivity.Post(editTitle.getText().toString(),"도움");
                database.getRef().child(userId).child("myPost").push().setValue(post);
                finish();
                Toast.makeText(this, "새로운 글을 등록했습니다.", Toast.LENGTH_SHORT).show();
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
