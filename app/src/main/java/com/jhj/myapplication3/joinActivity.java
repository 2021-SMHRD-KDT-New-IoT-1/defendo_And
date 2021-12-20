package com.jhj.myapplication3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class joinActivity extends AppCompatActivity {
    private ImageView logo2;
    private TextView join_title;
    private EditText join_id;
    private EditText join_pw;
    private EditText join_name;
    private EditText join_tel;
    private EditText join_dept;
    private Button btn_idcheck2;
    private Button btn_join2;
    private Button btn_back2;
    private View join_view;
    private CheckBox checkBox_admin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        logo2 = findViewById(R.id.logo2);
        join_title = findViewById(R.id.join_title);
        //
        join_id = findViewById(R.id.join_id);
        join_pw = findViewById(R.id.join_pw);
        join_name = findViewById(R.id.edit_mid);
        join_tel = findViewById(R.id.edit_wid);
        join_dept = findViewById(R.id.join_dept);
        //
        btn_idcheck2 = findViewById(R.id.btn_idcheck2);
        btn_join2 = findViewById(R.id.btn_join2);
        btn_back2 = findViewById(R.id.btn_back2);
        join_view = findViewById(R.id.join_view);
        checkBox_admin = findViewById(R.id.checkBox_admin);
    }
}