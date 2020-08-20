package com.example.togethersujung2020.ui.main;

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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.HashMap;
import java.util.Map;

public class SettingPwdActivity extends AppCompatActivity {

    private static final String TAG = "SettingNicPwdActivity";
    ImageButton mBack;
    Button mChange;
    EditText mPastPwd, mNewPwd;
    private FirebaseAuth mFirebaseAuth;
    private DatabaseReference firebaseDatabase;
    private FirebaseUser firebaseUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_pwd);

        mBack = findViewById(R.id.imageButton3);
        //뒤로가기 버튼
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SettingPwdActivity.this, SettingsActivity.class));
            }
        });

        //비밀번호 버튼 등록
        //mPastPwd = findViewById(R.id.editTextTextPassword2);
        mNewPwd = findViewById(R.id.editTextTextPassword3);
        mChange = findViewById(R.id.buttonChange2);


        mChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //파이어베이스 접근 설정
                FirebaseUser user = mFirebaseAuth.getInstance().getCurrentUser();
                mFirebaseAuth = FirebaseAuth.getInstance();
                firebaseDatabase = FirebaseDatabase.getInstance().getReference();

                //비밀번호 가져오기
               //Query pwd = firebaseDatabase.child("uid").equalTo("pwd");

                //final String pastPwd = mPastPwd.getText().toString().trim();
                String newPwd = mNewPwd.getText().toString().trim();

                //if (pastPwd.equals(pwd)) {

                final ProgressDialog mDialog = new ProgressDialog(SettingPwdActivity.this);
                mDialog.setMessage("바꾸는 중입니다...");
                mDialog.show();

                user.updatePassword(newPwd)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        mDialog.dismiss();
                                        Log.d(TAG, "User password updated.");

                                        //데이터베이스에도 업데이트
                                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                        firebaseDatabase = FirebaseDatabase.getInstance().getReference("Users");
                                        String uid = user.getUid();
                                        String newPwd = mNewPwd.getText().toString().trim();

                                        DatabaseReference changeRef = firebaseDatabase.child(uid);
                                        Map<String, Object> nameUpdate = new HashMap<>();
                                        nameUpdate.put("pwd", newPwd);
                                        changeRef.updateChildren(nameUpdate);

                                        Intent intent = new Intent(SettingPwdActivity.this, SettingsActivity.class);
                                        startActivity(intent);
                                        finish();
                                        Toast.makeText(SettingPwdActivity.this, "비밀번호 변경 성공!", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                }
                //else {
                 //   Toast.makeText(SettingPwdActivity.this, "비밀번호가 틀렸습니다. 다시 입력해 주세요.", Toast.LENGTH_SHORT).show();
                 //   return;
                //}
            //}
        });
    }
}