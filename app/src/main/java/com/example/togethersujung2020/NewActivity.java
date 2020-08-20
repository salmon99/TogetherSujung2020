package com.example.togethersujung2020;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class NewActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);

        Bundle extras = getIntent().getExtras();

        String title = extras.getString("title");
        String date = extras.getString("date");
        String content = extras.getString("content");

        TextView textview = (TextView) findViewById((R.id.textView10));

        String str = title + '\n' + date + '\n' + content;
        textview.setText(str);
    }
}
