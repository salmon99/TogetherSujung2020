package com.example.togethersujung2020.ui.main;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
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

import java.util.HashMap;
import java.util.Map;

public class SettingLocationActivity extends AppCompatActivity {

    ImageButton mBack;
    EditText mLocation;
    Button mChange;
    private FirebaseUser firebaseUser;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference firebaseDatabase;

    private static final String TAG = "SettingLocationActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_location);

        mBack = findViewById(R.id.imageButton2);
        mLocation = findViewById(R.id.editTextTextLocation);
        mChange = findViewById(R.id.buttonChange2);

        mChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                firebaseDatabase = FirebaseDatabase.getInstance().getReference("Users");
                String uid = user.getUid();
                final String newLocation = mLocation.getText().toString().trim();

                final ProgressDialog mDialog = new ProgressDialog(SettingLocationActivity.this);
                mDialog.setMessage("지역 변경중. . .");
                mDialog.show();

                DatabaseReference changeRef = firebaseDatabase.child(uid);
                Map<String, Object> nameUpdate = new HashMap<>();
                nameUpdate.put("location", newLocation);
                changeRef.updateChildren(nameUpdate)
                        .addOnCompleteListener(SettingLocationActivity.this, new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    mDialog.dismiss();
                                    Intent intent = new Intent(SettingLocationActivity.this, SettingsActivity.class);
                                    startActivity(intent);
                                    finish();
                                    Toast.makeText(SettingLocationActivity.this, "지역 변경 성공!", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

            }
        });


        //뒤로가기 버튼
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SettingLocationActivity.this, SettingsActivity.class));
            }
        });
    }
}
