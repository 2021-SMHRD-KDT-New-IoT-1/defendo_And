package com.jhj.myapplication3;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class joinActivity extends AppCompatActivity {
    private EditText join_id;
    private EditText join_pw;
    private EditText join_name;
    private EditText join_tel;
    private EditText join_aff;
    private EditText join_pw2;
    private Button btn_idCheck;
    private Button btn_joinUs;
    private Button btn_back;
    private TextView check_pw;
    private String password ="";

    RequestQueue requestQueue;
    StringRequest stringRequest;
    StringRequest stringRequest2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);
        join_id = findViewById(R.id.join_id);
        join_pw = findViewById(R.id.join_pw);
        join_pw2 = findViewById(R.id.join_pw2);
        join_name = findViewById(R.id.join_name);
        join_tel = findViewById(R.id.join_tel);
        join_aff = findViewById(R.id.join_aff);
        check_pw = findViewById(R.id.check_pw);

        btn_idCheck = findViewById(R.id.btn_idCheck);
        btn_joinUs = findViewById(R.id.btn_joinUs);
        btn_back = findViewById(R.id.btn_back);
        join_pw2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(join_pw.getText().toString().equals(join_pw2.getText().toString())){
                    check_pw.setText("비밀번호가 일치합니다.");
                    password = join_pw2.getText().toString();
                }
                else{
                    check_pw.setText("비밀번호가 일치하지 않습니다.");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        requestQueue = Volley.newRequestQueue(getApplicationContext());

        String id_check = "http://project-student.ddns.net/dependo/AndroidIdCheck";

        stringRequest = new StringRequest(Request.Method.POST, id_check, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equals("false")) {
                    Toast.makeText(joinActivity.this, "회원가입이 가능한 아이디 입니다.", Toast.LENGTH_SHORT).show();
                } else if (response.equals("true")) {
                    Toast.makeText(joinActivity.this, "중복된 아이디 입니다.", Toast.LENGTH_SHORT).show();
                    join_id.setText("");
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();
                params.put("id", join_id.getText().toString());
                return params;
            }
        };

        btn_idCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestQueue.add(stringRequest);
            }
        });

        String join = "http://project-student.ddns.net/dependo/AndroidJoin";
        stringRequest2 = new StringRequest(Request.Method.POST, join, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Toast.makeText(joinActivity.this, response, Toast.LENGTH_SHORT).show();
                if (response.equals("1")) {
                    Toast.makeText(joinActivity.this, "회원가입성공", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(joinActivity.this, loginActivity.class);
                    startActivity(intent);
                } else if (response.equals("0")) {
                    Toast.makeText(joinActivity.this, "회원가입실패", Toast.LENGTH_SHORT).show();

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();
                params.put("id", join_id.getText().toString());
                params.put("pw", password);
                params.put("name", join_name.getText().toString());
                params.put("tel", join_tel.getText().toString());
                params.put("dept", join_aff.getText().toString());
                params.put("check_manager","Y");
                return params;
            }
        };

        btn_joinUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(password==""){
                    Toast.makeText(joinActivity.this,"비밀번호를 확인해주세요.",Toast.LENGTH_SHORT).show();
                }
                else {
                    requestQueue.add(stringRequest2);
                }

            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(joinActivity.this, loginActivity.class);
                startActivity(intent);
            }
        });


    }
}