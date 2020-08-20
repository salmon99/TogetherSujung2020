package com.example.togethersujung2020.ui.main;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.togethersujung2020.R;
import com.example.togethersujung2020.ui.freeBoard.FreeActivity;
import com.example.togethersujung2020.ui.freeBoard.FreeWritePostActivity;
import com.example.togethersujung2020.ui.freeBoard.FreeBoard;
import com.example.togethersujung2020.ui.freeBoard.FreeListViewAdapter;
import com.example.togethersujung2020.ui.freeBoard.FreeListViewItem;
import com.example.togethersujung2020.ui.freeBoard.FreePostViewActivity;
import com.example.togethersujung2020.ui.helpBoard.HelpPostViewActivity;
import com.example.togethersujung2020.ui.housingBoard.HousingPostViewActivity;
import com.example.togethersujung2020.ui.infoBoard.InfoPostViewActivity;
import com.example.togethersujung2020.ui.tradingBoard.TradePostViewActivity;
import com.example.togethersujung2020.ui.ttingBoard.TtingPostViewActivity;
import com.google.firebase.FirebaseError;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class NoticeActivity extends AppCompatActivity {
    final String userId ="김수정";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final ListView listview ;
        final NoticeAdapter adapter;

        // Adapter 생성
        adapter = new NoticeAdapter() ;

        // 리스트뷰 참조 및 Adapter달기
        listview = (ListView) findViewById(R.id.listview1);
        listview.setAdapter(adapter);

        final DatabaseReference database= FirebaseDatabase.getInstance().getReference();
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot innerDataSanpShot : dataSnapshot.getChildren()) {
                    for (DataSnapshot innnerDataSanpShot : innerDataSanpShot.getChildren()) {
                        for (DataSnapshot innnnerDataSanpShot : innnerDataSanpShot.getChildren()) {
                            String info = innnnerDataSanpShot.child("comment").getValue(String.class);
                            if (info != null) {
                                FreeBoard board = innnerDataSanpShot.getValue(FreeBoard.class);
                                adapter.addItem(board.getTitle(), board.getContent(), innnerDataSanpShot.getKey(),innerDataSanpShot.getKey());
                                adapter.notifyDataSetChanged();
                            }
                        }
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                // get item
                FreeListViewItem item = (FreeListViewItem) parent.getItemAtPosition(position) ;

                String titleStr = item.getTitle() ;
                String descStr = item.getDesc() ;
                String commentNumber = item.getComment();
                String key=item.getKey();

                if(commentNumber.equals("freeboard")) {
                    Intent postview = new Intent(NoticeActivity.this, FreePostViewActivity.class);
                    postview.putExtra("title", titleStr);
                    postview.putExtra("content", descStr);
                    postview.putExtra("postkey", key);
                    startActivity(postview);
                }
                if(commentNumber.equals("infoboard")) {
                    Intent postview = new Intent(NoticeActivity.this, InfoPostViewActivity.class);
                    postview.putExtra("title", titleStr);
                    postview.putExtra("content", descStr);
                    postview.putExtra("postkey", key);
                    startActivity(postview);
                }
                if(commentNumber.equals("housingboard")) {
                    Intent postview = new Intent(NoticeActivity.this, HousingPostViewActivity.class);
                    postview.putExtra("title", titleStr);
                    postview.putExtra("content", descStr);
                    postview.putExtra("postkey", key);
                    startActivity(postview);
                }
                if(commentNumber.equals("ttingboard")) {
                    Intent postview = new Intent(NoticeActivity.this, TtingPostViewActivity.class);
                    postview.putExtra("title", titleStr);
                    postview.putExtra("content", descStr);
                    postview.putExtra("postkey", key);
                    startActivity(postview);
                }
                if(commentNumber.equals("tradeboard")) {
                    Intent postview = new Intent(NoticeActivity.this, TradePostViewActivity.class);
                    postview.putExtra("title", titleStr);
                    postview.putExtra("content", descStr);
                    postview.putExtra("postkey", key);
                    startActivity(postview);
                }
                if(commentNumber.equals("helpboard")) {
                    Intent postview = new Intent(NoticeActivity.this, HelpPostViewActivity.class);
                    postview.putExtra("title", titleStr);
                    postview.putExtra("content", descStr);
                    postview.putExtra("postkey", key);
                    startActivity(postview);
                }
            }
        }) ;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) { //액션바 메뉴 표시하기
        ActionBar ab = getSupportActionBar();
        ab.setTitle("알림창");
        return true;
    }

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
