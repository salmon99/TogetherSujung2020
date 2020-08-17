package com.example.togethersujung2020.ui.register;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;


import androidx.annotation.IdRes;
import androidx.appcompat.app.AppCompatActivity;

import com.example.togethersujung2020.R;

public class Registersub2 extends AppCompatActivity {

    private RadioButton r_btn1, r_btn2;
    private RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_nickname);  // layout xml 과 자바파일을 연결
        // end onCreate()

        ImageButton c = (ImageButton) findViewById(R.id.imageButton3);
        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(
                        getApplicationContext(), // 현재 화면의 제어권자
                        Registersub3.class); // 다음 넘어갈 클래스 지정
                startActivity(intent); // 다음 화면으로 넘어간다
            }
        });

        //라디오 버튼 설정
        r_btn1 = (RadioButton) findViewById(R.id.radioButton1);
        r_btn2 = (RadioButton) findViewById(R.id.radioButton2);
        r_btn1.setOnClickListener(radioButtonClickListener);
        r_btn2.setOnClickListener(radioButtonClickListener);


        //라디오 그룹 설정
        radioGroup = (RadioGroup) findViewById(R.id.radioButtonGroup);
        radioGroup.setOnCheckedChangeListener(radioGroupButtonChangeListener);
    }

    //라디오 버튼 클릭 리스너
    RadioButton.OnClickListener radioButtonClickListener = new RadioButton.OnClickListener() {
        @Override
        public void onClick(View view) {
            Toast.makeText(Registersub2.this, "r_btn1 : " + r_btn1.isChecked() + "r_btn2 : " + r_btn2.isChecked(), Toast.LENGTH_SHORT).show();
        }
    };

    //라디오 그룹 클릭 리스너
    RadioGroup.OnCheckedChangeListener radioGroupButtonChangeListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
            if (i == R.id.radioButton1) {
                Toast.makeText(Registersub2.this, "성북구 지역을 선택했습니다.", Toast.LENGTH_SHORT).show();
            } else if (i == R.id.radioButton2) {
                Toast.makeText(Registersub2.this, "강북구 지역을 선택했습니다.", Toast.LENGTH_SHORT).show();
            }
        }
    };
}



