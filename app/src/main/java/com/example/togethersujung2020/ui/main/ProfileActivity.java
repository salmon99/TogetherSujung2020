package com.example.togethersujung2020.ui.main;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.ListFragment;

import com.example.togethersujung2020.R;
import com.example.togethersujung2020.ui.freeBoard.FreeBoard;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {
    FragmentManager fragmentManager = getSupportFragmentManager();
    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

    //TextView mEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //뒤로가기 버튼

        final TextView loc = (TextView) findViewById(R.id.profileplace);
        final TextView name = (TextView) findViewById(R.id.profilename);
        final TextView email = (TextView) findViewById(R.id.profile_email);

        TabLayout tab = findViewById(R.id.profile_tab);

        tab.post(new Runnable() {
            @Override
            public void run() {
                fragmentTransaction = fragmentManager.beginTransaction();
            }
        });

        tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int pos = tab.getPosition();
                changeView(pos);
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                // do nothing
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                // do nothing
            }
        });

        //사용자 정보 가져오기
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
           // String name1 = user.getDisplayName();
            String email1 = user.getEmail();
            String uid = user.getUid();

            boolean emailVerified = user.isEmailVerified();

            final DatabaseReference database= FirebaseDatabase.getInstance().getReference();
            database.child("Users").child(uid).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String location = dataSnapshot.child("location").getValue(String.class);
                    String name2 = dataSnapshot.child("nickname").getValue(String.class);
                    loc.setText(location);
                    name.setText(name2);
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
            email.setText(email1);
        }
    }

    private void changeView(int index) {
        switch (index) {
            case 0:
                fragmentTransaction = fragmentManager.beginTransaction();
                profile_content fragment1 = new profile_content();
                fragmentTransaction.add(R.id.profile_fragment, fragment1);
                fragmentTransaction.commit();
                break;
            case 1:
                fragmentTransaction = fragmentManager.beginTransaction();
                profile_comment fragment2 = new profile_comment();
                fragmentTransaction.add(R.id.profile_fragment, fragment2);
                fragmentTransaction.commit();
                break;
            case 2:
                fragmentTransaction = fragmentManager.beginTransaction();
                profile_scrap fragment3 = new profile_scrap();
                fragmentTransaction.add(R.id.profile_fragment, fragment3);
                fragmentTransaction.commit();
                break;
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) { //액션바 메뉴 표시하기
        ActionBar ab = getSupportActionBar() ;
        ab.setTitle("프로필 정보") ;
        getMenuInflater().inflate(R.menu.profile_actions, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) { //액션바 실행하기
        switch (item.getItemId()) {
            case R.id.settings:
                Intent settings = new Intent(this, SettingsActivity.class);
                startActivity(settings);
                Toast.makeText(this, "환경설정 버튼을 클릭했습니다.", Toast.LENGTH_SHORT).show();
                return true;
            case android.R.id.home: //뒤로가기 버튼 클릭시 동작
                finish();
                Toast.makeText(this, "뒤로가기 버튼을 클릭했습니다.", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onClick(View v) {

    }
}
