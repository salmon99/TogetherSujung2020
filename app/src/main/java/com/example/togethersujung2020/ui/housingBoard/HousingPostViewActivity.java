package com.example.togethersujung2020.ui.housingBoard;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.togethersujung2020.R;
import com.example.togethersujung2020.ui.freeBoard.FreeComment;
import com.example.togethersujung2020.ui.freeBoard.FreeCommentListViewAdapter;
import com.example.togethersujung2020.ui.freeBoard.FreeCommentListViewItem;
import com.example.togethersujung2020.ui.infoBoard.InfoModifyPostActivity;
import com.example.togethersujung2020.ui.infoBoard.InfoPostViewActivity;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.List;

public class HousingPostViewActivity extends AppCompatActivity {
    final String userId ="김수정";
    final String area ="성북구";
    EditText editComment;
    private ListView listView;
    private FreeCommentListViewAdapter adapter;
    private FirebaseDatabase mDatabase;
    private DatabaseReference mReference;
    private ChildEventListener mChild;

    List<Object> Array = new ArrayList<Object>();
    ArrayList<FreeCommentListViewItem> commentItems=new ArrayList<>();

    public class Scrap {
        public String title;
        public String board;

        public Scrap() {
        }

        public Scrap(String key, String board) {
            this.title = key;
            this.board = board;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_freepostview);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //뒤로가기 버튼 추가
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN); //입력창이 자판에 가리지 않도록 하기

        adapter = new FreeCommentListViewAdapter(); //여기서부터 댓글 리스트뷰

        listView = (ListView) findViewById(R.id.listview_comment);
        listView.setAdapter(adapter);

        final TextView title = (TextView) findViewById(R.id.title);
        final TextView content = (TextView) findViewById(R.id.content);
        final TextView postkey = (TextView) findViewById(R.id.key);
        final TextView userName = (TextView) findViewById(R.id.userId);

        initDatabase();

        Intent postview = this.getIntent();
        final String title1 = postview.getStringExtra("title");
        final String content1 = postview.getStringExtra("content");
        final String key1 = postview.getStringExtra("postkey");

        title.setText(title1);
        content.setText(content1);
        postkey.setText(area); //todo 나중에 키값 보여주는 건 지우기
        userName.setText(userId);


        editComment = (EditText) findViewById(R.id.edit_comment);

        Button free_board = (Button) findViewById(R.id.button_comment); //자유게시판 버튼 누르면 화면 이동
        free_board.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference database = FirebaseDatabase.getInstance().getReference();
                FreeComment comment =new FreeComment(editComment.getText().toString());

                Query query = database.child("housingboard").child(key1).orderByChild("content").equalTo(content1);
                query.getRef().push().setValue(comment); //댓글 데이터베이스에 추가
                HousingPostViewActivity.Scrap scrap = new HousingPostViewActivity.Scrap(editComment.getText().toString(),title1); //내가 쓴 댓글
                database.getRef().child(userId).child("myComment").push().setValue(scrap);
                Toast.makeText(HousingPostViewActivity.this, "새 댓글을 등록했습니다.", Toast.LENGTH_SHORT).show();

                editComment.setText(null); //댓글 입력창 초기화
                adapter.notifyDataSetChanged();
            }
        });

        mReference = mDatabase.getReference("housingboard").child(key1); // 변경값을 확인할 child 이름
        mReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) { //나는 이게 진짜 왜 돌아가는지 모르겠다...
                for (DataSnapshot messageData : dataSnapshot.getChildren()) {
                    String msg2 = messageData.getValue().toString();
                    Array.add(msg2);
                    adapter.addItem(msg2);
                }
                adapter.notifyDataSetChanged();
                listView.setSelection(adapter.getCount() - 1);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        mReference.addChildEventListener(mChild);
    }
    private void initDatabase() {

        mDatabase = FirebaseDatabase.getInstance();

        mReference = mDatabase.getReference("log");
        mReference.child("log").setValue("check");

        mChild = new ChildEventListener() {

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        mReference.addChildEventListener(mChild);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mReference.removeEventListener(mChild);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) { //액션바 메뉴 표시하기
        ActionBar ab = getSupportActionBar() ;
        ab.setTitle("같이수정 주거게시판") ;
        getMenuInflater().inflate(R.menu.freepostview_actions, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) { //액션바 실행하기
        final DatabaseReference database= FirebaseDatabase.getInstance().getReference();
        Intent postview = this.getIntent();
        String key1="";
        String title1="";
        String content1="";
        key1 = postview.getStringExtra("postkey");
        title1 = postview.getStringExtra("title");
        content1 = postview.getStringExtra("content");
        switch (item.getItemId()) { //todo 스크랩, 삭제, 수정 기능 목록에서 버튼 만들어서 사용하기
            case R.id.ScrapPost:
                HousingPostViewActivity.Scrap scrap = new HousingPostViewActivity.Scrap(title1,"주거");
                database.getRef().child(userId).child("scrap").push().setValue(scrap); //게시글 키 push
                Toast.makeText(HousingPostViewActivity.this, "글 스크랩 버튼을 클릭했습니다.", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.RemovePost:
                final Query query = database.child("housingboard").child(key1).orderByChild("content").equalTo(content1);
                query.getRef().removeValue();
                finish();
                Toast.makeText(HousingPostViewActivity.this, "글 삭제 버튼을 클릭했습니다.", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.ModifyPost:
                Intent modifyPost = new Intent(this, HousingModifyPostActivity.class);
                modifyPost.putExtra("title_before",title1);
                modifyPost.putExtra("content_before",content1);
                modifyPost.putExtra("postkey",key1);
                startActivity(modifyPost);
                Toast.makeText(HousingPostViewActivity.this, "글 수정 버튼을 클릭했습니다.", Toast.LENGTH_SHORT).show();
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
