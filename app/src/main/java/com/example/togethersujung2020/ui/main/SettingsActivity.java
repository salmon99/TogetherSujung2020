package com.example.togethersujung2020.ui.main;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.togethersujung2020.R;
import com.example.togethersujung2020.ui.login.LoginActivity;
import com.example.togethersujung2020.ui.login.Login;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SettingsActivity extends AppCompatActivity {
    private final int GET_GALLERY_IMAGE = 200;

    Button mLocationchange, mNamechange, mPwdchange, mLogout, mUserdelete;

    //firebase auth object
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    //private FirebaseAuth mFirebaseAuth;
    //private DatabaseReference firebaseDatabase;

    private static final String TAG = "SettingsActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //뒤로가기 버튼 추가

        Button nameBtn = findViewById(R.id.profile_name);
        nameBtn.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SettingsActivity.this, NicknameActivity.class);
                startActivity(intent);
            }
        });

        Button locaBtn = findViewById(R.id.profile_location);
        locaBtn.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LocationActivity.class);
                startActivity(intent);
            }
        });

        Button passwdBtn = findViewById(R.id.profile_password);
        passwdBtn.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PasswordActivity.class);
                startActivity(intent);
            }
        });

        Button changePf = findViewById(R.id.profile_photo);
        changePf.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                // 기본 갤러리로 접근
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType(android.provider.MediaStore.Images.Media.CONTENT_TYPE);
                startActivityForResult(intent, GET_GALLERY_IMAGE);
            }
        });

        Button logOut = findViewById(R.id.profile_logout);
        logOut.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(SettingsActivity.this);
                builder.setTitle("로그아웃");
                builder.setMessage("로그아웃하시겠습니까?");
                builder.setNegativeButton("예", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(intent);
                    }
                });
                builder.setPositiveButton("아니오", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.create().show();
            }
        });

        Button exit = findViewById(R.id.profile_exit);
        exit.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(SettingsActivity.this);
                builder.setTitle("회원 탈퇴");
                builder.setMessage("정말 탈퇴하시겠습니까?");
                builder.setNegativeButton("예", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(intent);
                        // + 개인정보 데이터 삭제!!
                    }
                });
                builder.setPositiveButton("아니오", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.create().show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        setContentView(R.layout.activity_profile);

        ImageView profile = findViewById(R.id.profile);

        if (requestCode == GET_GALLERY_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null) {

            Uri selectedImageUri = data.getData();
            profile.setImageURI(selectedImageUri);

        }

    } // 갤러리 뜨게는 했는데 오류 나는 것 같기도 함...

        //findViewById(R.id.btn_start).setOnClickListener(mClickListener);

        mLocationchange = findViewById(R.id.profile_location);
        mNamechange = findViewById(R.id.profile_name);
        mPwdchange = findViewById(R.id.profile_password);
        mLogout = findViewById(R.id.profile_logout);
        mUserdelete = findViewById(R.id.profile_exit);

        //파이어베이스 접근 설정
        //FirebaseUser user = mFirebaseAuth.getCurrentUser();
        //mFirebaseAuth = FirebaseAuth.getInstance();
        //firebaseDatabase = FirebaseDatabase.getInstance().getReference();

        //현재 로그인한 사용자 가져오기
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();


        if (user != null) {
            // User is signed in

            //위치 변경
            mLocationchange.setOnClickListener(new View.OnClickListener() {//버튼 이벤트 처리
                @Override
                public void onClick(View view) {
                    //intent함수를 통해 register액티비티 함수를 호출한다.
                    startActivity(new Intent(SettingsActivity.this, SettingNicknameActivity.class));
                }
            });


            //닉네임 변경
            mNamechange.setOnClickListener(new View.OnClickListener() {//버튼 이벤트 처리
                @Override
                public void onClick(View view) {
                    //intent함수를 통해 register액티비티 함수를 호출한다.
                    startActivity(new Intent(SettingsActivity.this, SettingNicknameActivity.class));
                }
            });



            //비밀번호 변경
            mPwdchange.setOnClickListener(new View.OnClickListener() {//버튼 이벤트 처리
                @Override
                public void onClick(View view) {
                    //intent함수를 통해 register액티비티 함수를 호출한다.
                    startActivity(new Intent(SettingsActivity.this, SettingPwdActivity.class));
                }
            });


            //로그아웃
            mLogout.setOnClickListener(new View.OnClickListener() {//버튼 이벤트 처리
                @Override
                public void onClick(View view) {

                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                    firebaseAuth.signOut();

                    Toast.makeText(getApplicationContext(), "로그아웃 되었습니다", Toast.LENGTH_SHORT).show();
                    //버튼 클릭시 Toast 메세지"버튼 클릭 성공" 출력

                    //finish();
                    Intent intent = new Intent(
                            getApplicationContext(), // 현재 화면의 제어권자
                            Login.class); // 다음 넘어갈 클래스 지정
                    startActivity(intent); // 다음 화면으로 넘어간다
                }
            });
            //회원 탈퇴
            mUserdelete.setOnClickListener(new View.OnClickListener() {//버튼 이벤트 처리
                @Override
                public void onClick(View view) {
                    //intent함수를 통해 register액티비티 함수를 호출한다.
                    startActivity(new Intent(SettingsActivity.this, SettingDeleteActivity.class));
                }
            });
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) { //액션바 메뉴 표시하기
        ActionBar ab = getSupportActionBar();
        ab.setTitle("환경 설정");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) { //액션바 실행하기
        switch (item.getItemId()) {
            case android.R.id.home: //뒤로가기 버튼 클릭시 동작
                finish();
                Toast.makeText(this, "뒤로가기 버튼을 클릭했습니다.", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
