package com.example.togethersujung2020.ui.register;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.togethersujung2020.R;
import com.example.togethersujung2020.ui.login.Login;

public class Registersub3 extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_end);  // layout xml 과 자바파일을 연결
        // end onCreate()

        Button d = (Button) findViewById(R.id.button3);
        d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(
                        getApplicationContext(), // 현재 화면의 제어권자
                        Login.class); // 다음 넘어갈 클래스 지정
                startActivity(intent); // 다음 화면으로 넘어간다

           //     button3.setOnClickListener {
             //       finish();    // 액티비티 종료(로그아웃)
              //  } //todo 여기 오류나서 주석처리 해뒀습니다
            }
        });
    }
}

