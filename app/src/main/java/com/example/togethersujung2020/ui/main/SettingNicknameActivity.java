package com.example.togethersujung2020.ui.main;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
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
import com.example.togethersujung2020.ui.register.RegisterActivity;
import com.example.togethersujung2020.ui.register.Registersub;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.HashMap;
import java.util.Map;

public class SettingNicknameActivity extends AppCompatActivity {

    private static final String TAG = "SettingNicknameActivity";
    ImageButton mBack;
    EditText mName;
    Button mChange;
    private FirebaseUser firebaseUser;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_nickname);

        mBack = findViewById(R.id.imageButton1);
        mName = findViewById(R.id.editTextTextPersonName);
        mChange = findViewById(R.id.buttonChange1);

        mChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                firebaseDatabase = FirebaseDatabase.getInstance().getReference("Users");
                String uid = user.getUid();
                final String newName = mName.getText().toString().trim();

                final ProgressDialog mDialog = new ProgressDialog(SettingNicknameActivity.this);
                mDialog.setMessage("닉네임 변경중. . .");
                mDialog.show();

                DatabaseReference changeRef = firebaseDatabase.child(uid);
                Map<String, Object> nameUpdate = new HashMap<>();
                nameUpdate.put("nickname", newName);
                changeRef.updateChildren(nameUpdate)
                        .addOnCompleteListener(SettingNicknameActivity.this, new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    mDialog.dismiss();
                                    Intent intent = new Intent(SettingNicknameActivity.this, SettingsActivity.class);
                                    startActivity(intent);
                                    finish();
                                    Toast.makeText(SettingNicknameActivity.this, "닉네임 변경 성공!", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

            }
        });

        //뒤로가기 버튼
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SettingNicknameActivity.this, SettingsActivity.class));
            }
        });
    }
}
