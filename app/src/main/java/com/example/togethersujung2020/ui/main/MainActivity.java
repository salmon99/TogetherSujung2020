//커뮤니티 메인 화면

package com.example.togethersujung2020.ui.main;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.togethersujung2020.CustomAdapter;
import com.example.togethersujung2020.NewActivity;
import com.example.togethersujung2020.R;
import com.example.togethersujung2020.User;
import com.example.togethersujung2020.ui.freeBoard.FreeActivity;
import com.example.togethersujung2020.ui.freeBoard.FreeWritePostActivity;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Dictionary;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<User> arrayList = new ArrayList<>();
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;

    interface ClickListener {
        void onClick(View view, int position);

        void onLongClick(View view, int position);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.profile);

        recyclerView = findViewById(R.id.recyclerView); // id 연결
        recyclerView.setHasFixedSize(true); // recyclerview 기존 성능 강화
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        arrayList = new ArrayList<>(); // User 객체를 담을 arraylist(adapter쪽으로)

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                ((LinearLayoutManager) layoutManager).getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                User user = arrayList.get(position);

                Intent intent = new Intent(getBaseContext(), NewActivity.class);
                intent.putExtra("title", user.getTitle());
                intent.putExtra("date", user.getDate());
                intent.putExtra("content", user.getContent());
                startActivity(intent);
            }

            @Override
            public void onLongClick(View view, int position) {
            }
        }));
        database = FirebaseDatabase.getInstance(); // Firebase database 연동
        databaseReference = database.getReference("User"); // DB 테이블 연결
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                arrayList.clear(); // 기존 배열 리스트가 존재하지 않게 초기화
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    User user = snapshot.getValue(User.class); // 만들어뒀던 User 객체에 데이터를 담는다
                    arrayList.add(user); // 담은 데이터들을 배열 리스트에 넣고 recyclerview로 보낼 준비
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        adapter = new CustomAdapter(arrayList, this);
        recyclerView.setAdapter(adapter); // recyclerview에 adapter 연결

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
            case R.id.bell:
                Intent notice = new Intent(this, NoticeActivity.class);
                startActivity(notice);
                Toast.makeText(MainActivity.this, "알림창 버튼을 클릭했습니다.", Toast.LENGTH_SHORT).show();
                return true;
            case android.R.id.home:
                Intent profile = new Intent(this, ProfileActivity.class);
                startActivity(profile);
                Toast.makeText(MainActivity.this, "프로필 버튼을 클릭했습니다.", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
