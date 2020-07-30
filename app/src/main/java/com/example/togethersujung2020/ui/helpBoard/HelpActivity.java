package com.example.togethersujung2020.ui.helpBoard;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.togethersujung2020.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class HelpActivity extends AppCompatActivity {
    private Button sendbt;

    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        sendbt = (Button) findViewById(R.id.button);

        sendbt.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                databaseReference.child("message").push().setValue("2");
            }
        });

    }
}
