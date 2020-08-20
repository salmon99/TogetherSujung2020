package com.example.togethersujung2020.ui.register;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.IdRes;
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
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    private static final String TAG = "RegisterEmailActivity";
    ImageButton nextbtn;
    Button mRegister1, mRegister2;
    EditText mEmailText, mPasswordText, mNameText;
    RadioButton r_btn1, r_btn2;
    RadioGroup radioGroup;
    private FirebaseAuth mFirebaseAuth;
    private DatabaseReference firebaseDatabase;
    private FirebaseUser firebaseUser;
    private View.OnClickListener radioButtonClickListener, radioGroupButtonChangeListener;
    static String campus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);  // layout xml 과 자바파일을 연결
        // end onCreate()


        //파이어베이스 접근 설정
        FirebaseUser user = mFirebaseAuth.getInstance().getCurrentUser();
        mFirebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance().getReference();

        //버튼, 텍스트 지정
        mEmailText = findViewById(R.id.EmailAddress);
        mPasswordText = findViewById(R.id.editTextPassword);
        mNameText = findViewById(R.id.editTextTextName);

        mRegister1 = findViewById(R.id.signinbutton1);
        //mRegister2 = findViewById(R.id.signinbutton1);


        //라디오 버튼 설정
        r_btn1 = (RadioButton) findViewById(R.id.radioButton1);
        r_btn2 = (RadioButton) findViewById(R.id.radioButton2);
        r_btn1.setOnClickListener(radioButtonClickListener);
        r_btn2.setOnClickListener(radioButtonClickListener);

        //라디오 그룹 설정
        radioGroup = (RadioGroup) findViewById(R.id.radioButtonGroup);
        radioGroup.setOnCheckedChangeListener((RadioGroup.OnCheckedChangeListener) radioGroupButtonChangeListener);

        RadioGroup.OnCheckedChangeListener radioGroupButtonChangeListener = new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                if (i == R.id.radioButton1) {
                    Toast.makeText(RegisterActivity.this, "성북구 지역을 선택했습니다.", Toast.LENGTH_SHORT).show();
                } else if (i == R.id.radioButton2) {
                    Toast.makeText(RegisterActivity.this, "강북구 지역을 선택했습니다.", Toast.LENGTH_SHORT).show();
                }
            }
        };




        //아이디 비번 등록버튼 클릭리스너   -->  firebase에 데이터를 저장한다.
        mRegister1.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {


                //가입 정보 가져오기
                final String email = mEmailText.getText().toString().trim();
                String pwd = mPasswordText.getText().toString().trim();
                String verification = "@sungshin.ac.kr";

                if (email.contains(verification)) {
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
                                String nickname = mNameText.getText().toString().trim();
                                String pwd = mPasswordText.getText().toString().trim();

                                if (r_btn1.isChecked()){
                                    // 성북구 선택했을 때
                                    campus = r_btn1.getText().toString().trim();

                                } else{
                                    // 강북구 선택했을 때
                                    campus = r_btn2.getText().toString().trim();
                                }


                                //해쉬맵 테이블을 파이어베이스 데이터베이스에 저장
                                final HashMap<Object, String> hashMap = new HashMap<>();

                                hashMap.put("uid", uid);
                                hashMap.put("email", email);
                                hashMap.put("nickname", nickname);
                                hashMap.put("pwd", pwd);
                                hashMap.put("location", campus);

                                FirebaseDatabase database = FirebaseDatabase.getInstance();
                                DatabaseReference reference = database.getReference("Users");
                                reference.child(uid).setValue(hashMap);

                            //가입이 이루어져을시 가입 화면을 빠져나감.
                                Intent intent = new Intent(RegisterActivity.this, Registersub.class);
                                startActivity(intent);
                                finish();
                                Toast.makeText(RegisterActivity.this, "회원가입에 성공하셨습니다.", Toast.LENGTH_SHORT).show();


                                //인증메일 보내기
                                mFirebaseAuth = FirebaseAuth.getInstance();
                                firebaseDatabase = FirebaseDatabase.getInstance().getReference();
                                mFirebaseAuth.useAppLanguage(); //해당기기의 언어 설정

                                user.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {                         //해당 이메일에 확인메일을 보냄
                                            FirebaseUser user = mFirebaseAuth.getInstance().getCurrentUser();
                                            Log.d(TAG, "Email sent.");
                                            Toast.makeText(RegisterActivity.this,
                                                    user.getEmail() +"로 인증 메일을 보냈습니다. \n메일함을 확인해주세요!",
                                                    Toast.LENGTH_SHORT).show();
                                        } else {                                             //메일 보내기 실패
                                            Log.e(TAG, "sendEmailVerification", task.getException());
                                            Toast.makeText(RegisterActivity.this,
                                                    "인증 메일 전송 실패",
                                                    Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                            } else {
                                mDialog.dismiss();
                                Toast.makeText(RegisterActivity.this, "이미 존재하는 이메일이거나 비밀번호가 6자 이하 입니다.", Toast.LENGTH_SHORT).show();
                                return;  //해당 메소드 진행을 멈추고 빠져나감.

                            }

                        }
                    });
                }
                //성신이메일이 아닐때?
                else {
                    Toast.makeText(RegisterActivity.this, "성신 이메일이 아닙니다. 공지사항을 확인해주세요.", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });

    }

    public boolean onSupportNavigateUp(){
        onBackPressed();; // 뒤로가기 버튼이 눌렸을시
        return super.onSupportNavigateUp(); // 뒤로가기 버튼
    }
}


