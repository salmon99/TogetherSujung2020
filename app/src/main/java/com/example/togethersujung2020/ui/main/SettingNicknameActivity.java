package com.example.togethersujung2020.ui.main;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.togethersujung2020.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class SettingNicknameActivity extends AppCompatActivity {

    private static final String TAG = "SettingNicknameActivity";
    ImageButton mBack;
    private FirebaseUser firebaseUser;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_nickname);

        mBack = findViewById(R.id.imageButton1);

        //현재 로그인한 사용자 불러오기
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        firebaseDatabase = FirebaseDatabase.getInstance().getReference();

            //String sungbok = r_btn1.getText().toString().trim();
            //해쉬맵 테이블을 파이어베이스 데이터베이스에 저장
            //HashMap<Object,String> hashMap = new HashMap<>();

           // hashMap.put("location", sungbok);

            //FirebaseDatabase database = FirebaseDatabase.getInstance();
            //DatabaseReference reference = database.getReference("Users");

            UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                .setDisplayName("Jane Q. User")
                .setPhotoUri(Uri.parse("https://example.com/jane-q-user/profile.jpg"))
                .build();

        user.updateProfile(profileUpdates)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "User profile updated.");
                        }
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
