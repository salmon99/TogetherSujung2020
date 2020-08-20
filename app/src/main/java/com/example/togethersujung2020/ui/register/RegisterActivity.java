package com.example.togethersujung2020.ui.register;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.togethersujung2020.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {

    private static final String TAG = "RegisterEmailActivity";
    ImageButton nextbtn;
    Button mEmailregister, mPwdregister;
    EditText mEmailText, mPasswordText;
    private FirebaseAuth mFirebaseAuth;
    private DatabaseReference firebaseDatabase;
    private FirebaseUser firebaseUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_email);  // layout xml 과 자바파일을 연결
        // end onCreate()


        nextbtn = findViewById(R.id.imageButton1);


        nextbtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(
                        getApplicationContext(), // 현재 화면의 제어권자
                        Registersub2.class); // 다음 넘어갈 클래스 지정
                startActivity(intent); // 다음 화면으로 넘어간다
            }
        });

        //파이어베이스 접근 설정
        FirebaseUser user = mFirebaseAuth.getInstance().getCurrentUser();
        mFirebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance().getReference();

        mEmailText = findViewById(R.id.EmailAddress);
        mEmailregister = findViewById(R.id.Emailbutton);

        mPasswordText = findViewById(R.id.editTextPassword);
        mPwdregister = findViewById(R.id.PWbutton);

        //mPasswordcheckText = findViewById(R.id.passwordcheckEdt);

        //파이어베이스 user 로 접근
        //가입버튼 클릭리스너   -->  firebase에 데이터를 저장한다.
        mPwdregister.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                //가입 정보 가져오기
                final String email = mEmailText.getText().toString().trim();
                String pwd = mPasswordText.getText().toString().trim();
                //String pwdcheck = mPasswordcheckText.getText().toString().trim();


                //if(pwd.equals(pwdcheck)) {
                Log.d(TAG, "등록 버튼 " + email + " , " + pwd);
                final ProgressDialog mDialog = new ProgressDialog(RegisterActivity.this);
                mDialog.setMessage("가입중입니다...");
                mDialog.show();

                //파이어베이스에 신규계정 등록하기
                mFirebaseAuth.createUserWithEmailAndPassword(email, pwd).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        //가입 성공시
                        if (task.isSuccessful()) {
                            mDialog.dismiss();

                            FirebaseUser user = mFirebaseAuth.getCurrentUser();
                            String email = user.getEmail();
                            String uid = user.getUid();
                            //String name = mName.getText().toString().trim();

                            //해쉬맵 테이블을 파이어베이스 데이터베이스에 저장
                            HashMap<Object,String> hashMap = new HashMap<>();

                            hashMap.put("uid",uid);
                            hashMap.put("email",email);

                            FirebaseDatabase database = FirebaseDatabase.getInstance();
                            DatabaseReference reference = database.getReference("Users");
                            reference.child(uid).setValue(hashMap);

                            //가입이 이루어져을시 가입 화면을 빠져나감.
                            //Intent intent = new Intent(RegisterActivity.this, Registersub3.class);
                            //startActivity(intent);
                            //finish();
                            //Toast.makeText(RegisterActivity.this, "회원가입에 성공하셨습니다.", Toast.LENGTH_SHORT).show();

                            } else {
                                mDialog.dismiss();
                                Toast.makeText(RegisterActivity.this, "이미 존재하는 이메일 입니다.", Toast.LENGTH_SHORT).show();
                                return;  //해당 메소드 진행을 멈추고 빠져나감.

                            }

                        }
                    });

                    //비밀번호 오류시
                }
                //else{
                    //Toast.makeText(RegisterActivity.this, "비밀번호가 틀렸습니다. 다시 입력해 주세요.", Toast.LENGTH_SHORT).show();
                    //return;
                //}
        });

    }

    public boolean onSupportNavigateUp(){
        onBackPressed();; // 뒤로가기 버튼이 눌렸을시
        return super.onSupportNavigateUp(); // 뒤로가기 버튼
    }
}

/*
        //파이어베이스 접근 설정
        user = mFirebaseAuth.getCurrentUser();
        mFirebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance().getReference();
        mFirebaseAuth.useAppLanguage(); //해당기기의 언어 설정



        user.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {                         //해당 이메일에 확인메일을 보냄
                    Log.d(TAG, "Email sent.");
                    Toast.makeText(Activity_SignUp.this,
                            "Verification email sent to " + user.getEmail(),
                            Toast.LENGTH_SHORT).show();
                } else {                                             //메일 보내기 실패
                    Log.e(TAG, "sendEmailVerification", task.getException());
                    Toast.makeText(Activity_SignUp.this,
                            "Failed to send verification email.",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

/*        //파이어베이스 접근 설정
        // user = firebaseAuth.getCurrentUser();
        firebaseAuth = FirebaseAuth.getInstance();
        //firebaseDatabase = FirebaseDatabase.getInstance().getReference();

        //파이어베이스 user 로 접근
        //확인버튼 클릭리스너   -->  인증 이메일 보내기
        mVerifyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                public void sendEmailVerification(){
                    // [START send_email_verification]
                    FirebaseAuth auth = FirebaseAuth.getInstance();
                    FirebaseUser user = auth.getCurrentUser();

                    user.sendEmailVerification()
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Log.d(TAG, "Email sent.");
                                        //가입 정보 저장하기
                                        final String email = mEmailText.getText().toString().trim();
                                    }
                                }
                            });
                    // [END send_email_verification]
                }
            }
        });
}
//이메일 등록하는

 */



