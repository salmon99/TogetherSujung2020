package com.example.togethersujung2020.ui.main;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.togethersujung2020.R;
import com.example.togethersujung2020.ui.login.Login;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.nio.channels.SelectableChannel;

public class SettingDeleteActivity extends AppCompatActivity {

    Button mDelete, mReturn;
    EditText mVerification;

    private FirebaseAuth firebaseAuth;
    private static final String TAG = "DeleteActivity";
    private FirebaseAuth mFirebaseAuth;
    private DatabaseReference firebaseDatabase;
    private FirebaseUser firebaseUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_delete);

        mDelete = findViewById(R.id.button5);
        mVerification = findViewById(R.id.editTextTextPassword);
        mReturn = findViewById(R.id.button4);


        //할래요 버튼
        mDelete.setOnClickListener(new View.OnClickListener() {//버튼 이벤트 처리
            @Override
            public void onClick(View view) {

                //파이어베이스 접근 설정
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                firebaseDatabase = FirebaseDatabase.getInstance().getReference();
                String uid = user.getUid();
                String Id = user.getEmail();

                final String verify = mVerification.getText().toString().trim();
               //Query pwd = firebaseDatabase.child(uid);

                if (verify.equals(Id)) {

                    final ProgressDialog mDialog = new ProgressDialog(SettingDeleteActivity.this);
                    mDialog.setMessage("계정 삭제중. . .");
                    mDialog.show();

                    user.delete()
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        mDialog.dismiss();
                                        Log.d(TAG, "User account deleted.");

                                        //탈퇴 완료시 화면을 빠져나감.
                                        Intent intent = new Intent(SettingDeleteActivity.this, Login.class);
                                        startActivity(intent);
                                        finish();
                                        Toast.makeText(SettingDeleteActivity.this, "탈퇴 성공!", Toast.LENGTH_SHORT).show();

                                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                        firebaseDatabase = FirebaseDatabase.getInstance().getReference();
                                        String uid = user.getUid();
                                        firebaseDatabase.child("Users").child(uid).removeValue();
                                    }
                                }
                            });

                } else {
                Toast.makeText(SettingDeleteActivity.this, "아이디가 틀렸습니다. 다시 입력해 주세요.", Toast.LENGTH_SHORT).show();
                return;
                }

            }
        });

        //안할래요 버튼
        mReturn.setOnClickListener(new View.OnClickListener() {//버튼 이벤트 처리
            @Override
            public void onClick(View view) {
                //finish();
                Intent intent = new Intent(
                        getApplicationContext(), // 현재 화면의 제어권자
                        SettingsActivity.class); // 다음 넘어갈 클래스 지정
                startActivity(intent); // 다음 화면으로 넘어간다

            }
        });

    }
}
