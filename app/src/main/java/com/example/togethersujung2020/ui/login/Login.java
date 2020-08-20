package com.example.togethersujung2020.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.togethersujung2020.ui.main.MainActivity;
import com.example.togethersujung2020.R;
import com.example.togethersujung2020.ui.register.RegisterActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    Button mLoginBtn;
    Button mRegisterBtn;
    EditText mEmailText, mPasswordText;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        firebaseAuth = FirebaseAuth.getInstance();
        //버튼 등록하기
        mRegisterBtn = findViewById(R.id.registerBtn);
        mLoginBtn = findViewById(R.id.loginBtn);
        mEmailText = findViewById(R.id.username);
        mPasswordText = findViewById(R.id.password);


        //가입 버튼이 눌리면
        mRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //intent함수를 통해 register액티비티 함수를 호출한다.
                startActivity(new Intent(Login.this, RegisterActivity.class));

            }
        });


        //로그인 버튼이 눌리면
        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mEmailText.getText().toString().trim();
                String pwd = mPasswordText.getText().toString().trim();
                firebaseAuth.signInWithEmailAndPassword(email,pwd)
                        .addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    Intent intent = new Intent(Login.this, MainActivity.class);
                                    startActivity(intent);
                                }else{
                                    Toast.makeText(Login.this,"로그인이 안되는 이유? " +
                                            "\n 1. 아이디나 비밀번호가 틀립니다. " +
                                            "\n 2. 이메일 인증이 아직 완료되지 않았습니다. " +
                                            "\n 3. 유효하지 않는 이메일입니다. ",Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

            }
        });
    }
}
