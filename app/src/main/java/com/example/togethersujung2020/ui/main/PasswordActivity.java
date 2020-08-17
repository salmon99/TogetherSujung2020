package com.example.togethersujung2020.ui.main;

import android.os.Bundle;
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

         final String password = ((EditText)findViewById(R.id.newPassword)).getText().toString();
         final String passwordCheck = ((EditText)findViewById(R.id.newPassword1)).getText().toString();

        Button changeBtn = findViewById(R.id.changePassword);
        changeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (password.length() > 0 && passwordCheck.length() > 0) {
                    if (password.equals(passwordCheck)) {
                        Toast.makeText(PasswordActivity.this, "변경되었습니다!", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(PasswordActivity.this, "비밀번호가 일치하지 않습니다!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(PasswordActivity.this, "새 비밀번호를 입력해 주세요!", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}
