package com.example.togethersujung2020.ui.main;

import android.os.Bundle;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.togethersujung2020.R;

public class PasswordActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        Button chpswdbtn = findViewById(R.id.changePassword);
        chpswdbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(PasswordActivity.this, "변경되었습니다!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
