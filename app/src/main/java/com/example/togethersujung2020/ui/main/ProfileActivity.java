package com.example.togethersujung2020.ui.main;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.ListFragment;

import com.example.togethersujung2020.R;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {
    FragmentManager fragmentManager = getSupportFragmentManager();
    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

public class ProfileActivity extends AppCompatActivity {

    //TextView mEmail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //뒤로가기 버튼

        changeView(0);

        TabLayout tab = findViewById(R.id.profile_tab);
        tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int pos = tab.getPosition();
                changeView(pos);
            }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //뒤로가기 버튼

        //사용자 정보 가져오기
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // Name, email address, and profile photo Url
            String name = user.getDisplayName();
            String email = user.getEmail();
            //Uri photoUrl = user.getPhotoUrl();

            // Check if user's email is verified
            boolean emailVerified = user.isEmailVerified();

            // The user's ID, unique to the Firebase project. Do NOT use this value to
            // authenticate with your backend server, if you have one. Use
            // FirebaseUser.getIdToken() instead.
            String uid = user.getUid();
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
