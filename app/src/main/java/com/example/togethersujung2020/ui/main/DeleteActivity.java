package com.example.togethersujung2020.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.togethersujung2020.R;
import com.example.togethersujung2020.ui.login.Login;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class DeleteActivity extends AppCompatActivity {

    Button mDelete, mReturn;
    EditText mPwd;


    private FirebaseAuth firebaseAuth;
    private static final String TAG = "DeleteActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        mDelete = findViewById(R.id.button5);
        mPwd = findViewById(R.id.editTextTextPassword);
        mReturn = findViewById(R.id.button4);

        mDelete.setOnClickListener(new View.OnClickListener() {//버튼 이벤트 처리
            @Override
            public void onClick(View view) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                //String pwd = user.getDisplayName();

                user.delete()
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Log.d(TAG, "User account deleted.");
                                }
                            }
                        });

            }
        });

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
