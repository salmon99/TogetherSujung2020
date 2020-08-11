//커뮤니티 메인 화면

package com.example.togethersujung2020.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.togethersujung2020.CustomAdapter;
import com.example.togethersujung2020.R;
import com.example.togethersujung2020.User;
import com.example.togethersujung2020.ui.freeBoard.FreeActivity;
import com.example.togethersujung2020.ui.helpBoard.HelpActivity;
import com.example.togethersujung2020.ui.housingBoard.HousingActivity;
import com.example.togethersujung2020.ui.infoBoard.InfoActivity;
import com.example.togethersujung2020.ui.tradingBoard.TradeActivity;
import com.example.togethersujung2020.ui.ttingBoard.TtingActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<User> arrayList;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        arrayList = new ArrayList<>();

        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("User");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                arrayList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    User user = snapshot.getValue(User.class);
                    arrayList.add(user);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        }); // 공지 띄우려고 했는데 안 됨...!! 수정해야 함!!

        adapter = new CustomAdapter(arrayList, this);
        recyclerView.setAdapter(adapter);

        Button free_board = (Button) findViewById(R.id.free_board) ; //자유게시판 버튼 누르면 화면 이동
        free_board.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, FreeActivity.class) ;
                startActivity(intent) ;
            }
        });

        Button info_board = (Button) findViewById(R.id.info_board) ; //정보게시판 버튼 누르면 화면 이동
        info_board.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, InfoActivity.class) ;

                startActivity(intent) ;
            }
        });

        Button housing_board = (Button) findViewById(R.id.housing_board) ; //주거게시판 버튼 누르면 화면 이동
        housing_board.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, HousingActivity.class) ;

                startActivity(intent) ;
            }
        });

        Button tting_board = (Button) findViewById(R.id.tting_board) ; //띵게시판 버튼 누르면 화면 이동
        tting_board.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, TtingActivity.class) ;

                startActivity(intent) ;
            }
        });

        Button trading_board = (Button) findViewById(R.id.trading_board) ; //거래게시판 버튼 누르면 화면 이동
        trading_board.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, TradeActivity.class) ;

                startActivity(intent) ;
            }
        });

        Button help_board = (Button) findViewById(R.id.help_board) ; //도움게시판 버튼 누르면 화면 이동
        help_board.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, HelpActivity.class) ;

                startActivity(intent) ;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        ActionBar ab = getSupportActionBar();
        ab.setTitle("같이수정");
        getMenuInflater().inflate(R.menu.mainactionbar_actions, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_profile:
                Intent profile1 = new Intent(this, ProfileActivity.class);
                startActivity(profile1);
                Toast.makeText(MainActivity.this, "프로필 버튼을 클릭했습니다.", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_notice:
                Intent notice = new Intent(this, NoticeActivity.class);
                startActivity(notice);
                Toast.makeText(MainActivity.this, "알림창 버튼을 클릭했습니다.", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    } // 타이틀 바에 있는 아이콘들 연결시켰는데 이동 안 됨...
}

