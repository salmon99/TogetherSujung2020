package com.example.togethersujung2020.ui.main;

import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.togethersujung2020.R;

public class LocationActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_location);

        final RadioGroup rg = (RadioGroup) findViewById(R.id.radioGroup);
        Button changebtn = findViewById(R.id.changeLocation);
        changebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(LocationActivity.this, "변경되었습니다!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
