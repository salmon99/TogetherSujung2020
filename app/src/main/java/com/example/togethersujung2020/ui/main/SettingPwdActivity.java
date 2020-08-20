package com.example.togethersujung2020.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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

public class SettingPwdActivity extends AppCompatActivity {

    private static final String TAG = "SettingNicPwdActivity";
    ImageButton mBack;
    EditText mPastPwd, mCurrentPwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_pwd);

        mBack = findViewById(R.id.imageButton3);

        //데베에서 불러오기 다시 설정!

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String newPassword = "SOME-SECURE-PASSWORD";

        user.updatePassword(newPassword)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "User password updated.");
                        }
                    }
                });

        //뒤로가기 버튼
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SettingPwdActivity.this, SettingsActivity.class));
            }
        });
    }
}