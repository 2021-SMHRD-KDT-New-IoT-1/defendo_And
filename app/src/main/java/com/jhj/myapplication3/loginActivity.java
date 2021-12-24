package com.jhj.myapplication3;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.jhj.myapplication3.ui.main.MemberVO;

import java.util.HashMap;
import java.util.Map;

public class loginActivity extends AppCompatActivity {

    EditText input_id;
    EditText input_pw;
    Button btn_login;
    Button btn_join;
    ImageView logo;
    Gson gson;

    RequestQueue requestQueue;
    StringRequest stringRequest;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        input_id = findViewById(R.id.input_id);
        input_pw = findViewById(R.id.input_pw);
        btn_join = findViewById(R.id.btn_join);
        btn_login = findViewById(R.id.btn_login);
        logo = findViewById(R.id.logo);




        requestQueue = Volley.newRequestQueue(getApplicationContext());

        gson=new Gson();

        String login_url="http://59.0.147.241:8085/project_dependo/AndroidLogin";

        stringRequest = new StringRequest(Request.Method.POST, login_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Toast.makeText(getApplicationContext(),response,Toast.LENGTH_SHORT).show();
                    MemberVO vo = gson.fromJson(response,MemberVO.class);
                    Intent intent = new Intent(loginActivity.this, MainActivity.class);
                    intent.putExtra("VO",vo);
                    startActivity(intent);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> params = new HashMap<>();
                params.put("id",input_id.getText().toString());
                params.put("pw",input_pw.getText().toString());
                return params;
            }
        };
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestQueue.add(stringRequest);
            }
        });
        btn_join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(loginActivity.this, joinActivity.class);
                startActivity(intent);
            }
        });
    }
}