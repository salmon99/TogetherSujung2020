//커뮤니티 메인 화면

package com.example.togethersujung2020.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.togethersujung2020.R;
import com.example.togethersujung2020.ui.freeBoard.FreeActivity;
import com.example.togethersujung2020.ui.freeBoard.FreeWritePostActivity;
import com.example.togethersujung2020.ui.helpBoard.HelpActivity;
import com.example.togethersujung2020.ui.housingBoard.HousingActivity;
import com.example.togethersujung2020.ui.infoBoard.InfoActivity;
import com.example.togethersujung2020.ui.tradingBoard.TradeActivity;
import com.example.togethersujung2020.ui.ttingBoard.TtingActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.profile);

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
    public boolean onCreateOptionsMenu(Menu menu) { //액션바 메뉴 표시하기
        ActionBar ab = getSupportActionBar() ;
        ab.setTitle("같이수정") ;
        getMenuInflater().inflate(R.menu.mainactionbar_actions, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) { //액션바 실행하기
        switch (item.getItemId()){
            case R.id.bell:
                Intent newPost = new Intent(this, NoticeActivity.class);
                startActivity(newPost);
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
