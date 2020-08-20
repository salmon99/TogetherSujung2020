package com.example.togethersujung2020.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.togethersujung2020.R;

public class SettingLocationActivity extends AppCompatActivity {

    ImageButton mBack;
    private static final String TAG = "SettingLocationActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_location);

        mBack = findViewById(R.id.imageButton2);

        //데베에서 t,f 불러와서 설정해야되는..

        //뒤로가기 버튼
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SettingLocationActivity.this, SettingsActivity.class));
            }
        });
    }
}
