package com.jhj.myapplication3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class loginActivity extends AppCompatActivity {

    private EditText input_id;
    private EditText input_pw;
    private Button btn_login;
    private Button btn_join;
    private ImageView logo;
    private CheckBox checkBox;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        checkBox = findViewById(R.id.checkBox);
        input_id = findViewById(R.id.input_id);
        input_pw = findViewById(R.id.input_pw);
        btn_join = findViewById(R.id.btn_join);
        btn_login = findViewById(R.id.btn_login);
        logo = findViewById(R.id.logo);

        /*logo.setTranslationY(300);
        input_id.setTranslationY(300);
        input_pw.setTranslationY(300);
        btn_login.setTranslationY(300);
        btn_join.setTranslationY(300);*/



    }

    public void onclick(View v) {
        if (v.getId() == R.id.btn_login) {
            if (input_id.getText().toString().equals("jhg77") && input_pw.getText().toString().equals("1234")) {
                Toast toast = Toast.makeText(getApplicationContext(), "로그인 성공입니다.", Toast.LENGTH_SHORT);
                toast.show();
            } else {
                Toast toast = Toast.makeText(getApplicationContext(), "다시 입력해주세요.", Toast.LENGTH_SHORT);
                toast.show();
            }
        }
        btn_join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(loginActivity.this,joinActivity.class);

            }
        });
    }
}