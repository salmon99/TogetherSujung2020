/*package com.example.togethersujung2020.ui.register;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.togethersujung2020.R;

public class RegisterEmail1 extends AppCompatActivity implements View.OnClickListener {
    EditText authEmail;
    Button authBtn;

    LayoutInflater dialog; //LayoutInflater
    View dialogLayout; //layout을 담을 View
    Dialog authDialog; //dialog 객체

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_email); //없는 레이아웃 xml 파일을 참조하려고 해서 수정해봤습니다

        authEmail = (EditText) findViewById(R.id.editTextTextEmailAddress2); //없는 id를 참조하려고 해서 있는 걸로 수정해봤습니다
        authBtn = (Button) findViewById(R.id.authBtn);
        authBtn.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.authBtn:

                dialog = LayoutInflater.from(this);
                dialogLayout = dialog.inflate(R.layout.activity_register_password, null); // LayoutInflater를 통해 XML에 정의된 Resource들을 View의 형태로 반환 시켜 줌
                //inflate 할 xml 파일이 없는 것 같습니다
                authDialog = new Dialog(this); //Dialog 객체 생성
                authDialog.setContentView(dialogLayout); //Dialog에 inflate한 View를 탑재 하여줌
                authDialog.setCanceledOnTouchOutside(false); //Dialog 바깥 부분을 선택해도 닫히지 않게 설정함.
                authDialog.show(); //Dialog를 나타내어 준다.

        }

    }

}

 */
