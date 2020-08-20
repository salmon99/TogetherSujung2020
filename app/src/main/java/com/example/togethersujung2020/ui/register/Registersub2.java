package com.example.togethersujung2020.ui.register;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;


import androidx.annotation.IdRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.Guideline;

import com.example.togethersujung2020.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class Registersub2 extends AppCompatActivity {

    EditText t_name;
    Button b_name;
    private RadioButton r_btn1, r_btn2;
    private RadioGroup radioGroup;
    private FirebaseAuth mFirebaseAuth;
    private DatabaseReference firebaseDatabase;
    private FirebaseUser firebaseUser;

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

        //닉네임 텍스트, 버튼 설정
        t_name = (EditText) findViewById(R.id.editTextTextName);
        b_name = (Button) findViewById(R.id.button);
        b_name.setOnClickListener(nameButtonClickListener);

        //라디오 버튼 설정
        r_btn1 = (RadioButton) findViewById(R.id.radioButton1);
        r_btn2 = (RadioButton) findViewById(R.id.radioButton2);
        r_btn1.setOnClickListener(radioButtonClickListener);
        r_btn2.setOnClickListener(radioButtonClickListener);


        //라디오 그룹 설정
        radioGroup = (RadioGroup) findViewById(R.id.radioButtonGroup);
        radioGroup.setOnCheckedChangeListener(radioGroupButtonChangeListener);
    }


    //닉네임 저장 버튼 클릭 리스너
    Button.OnClickListener nameButtonClickListener = new Button.OnClickListener() {
        @Override
        public void onClick(View view) {

            //파이어베이스 접근 설정
            FirebaseUser user = mFirebaseAuth.getInstance().getCurrentUser();
            mFirebaseAuth = FirebaseAuth.getInstance();
            firebaseDatabase = FirebaseDatabase.getInstance().getReference();

            String nickname = t_name.getText().toString().trim();
            //해쉬맵 테이블을 파이어베이스 데이터베이스에 저장
            HashMap<Object,String> hashMap = new HashMap<>();

            hashMap.put("nickname",nickname);

            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference reference = database.getReference("Users");
            //reference.child(uid).setValue(hashMap);
        }
    };


    //라디오 버튼 클릭 리스너
    RadioButton.OnClickListener radioButtonClickListener = new RadioButton.OnClickListener() {
        @Override
        public void onClick(View view) {

            //파이어베이스 접근 설정
            FirebaseUser user = mFirebaseAuth.getCurrentUser();
            mFirebaseAuth = FirebaseAuth.getInstance();
            firebaseDatabase = FirebaseDatabase.getInstance().getReference();

            if (r_btn1.isChecked()){
                // 성북구 선택했을 때
                String sungbok = r_btn1.getText().toString().trim();
                //해쉬맵 테이블을 파이어베이스 데이터베이스에 저장
                HashMap<Object,String> hashMap = new HashMap<>();

                hashMap.put("location", sungbok);

                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference reference = database.getReference("Users");

            } else{
                // 강북구 선택했을 때
                String gangbok = r_btn2.getText().toString().trim();
                //해쉬맵 테이블을 파이어베이스 데이터베이스에 저장
                HashMap<Object,String> hashMap = new HashMap<>();

                hashMap.put("location", gangbok);

                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference reference = database.getReference("Users");

            }
            //Toast.makeText(Registersub2.this, "r_btn1 : " + r_btn1.isChecked() + "r_btn2 : " + r_btn2.isChecked(), Toast.LENGTH_SHORT).show();
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



