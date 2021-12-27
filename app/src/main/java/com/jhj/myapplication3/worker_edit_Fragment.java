package com.jhj.myapplication3;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class worker_edit_Fragment extends Fragment {
    private EditText edit_id;
    private EditText edit_pw;
    private EditText edit_pw2;
    private EditText edit_name;
    private EditText edit_tel;
    private EditText edit_dept;
    private Button btn_edit;
    private Button btn_back_edit;
    private TextView pw_check;
    private RequestQueue requestQueue;
    private StringRequest stringRequest;
    String password="";




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_worker_edit_, container, false);

        String id = getArguments().getString("id");

        edit_id = v.findViewById(R.id.edit_id);
        edit_pw = v.findViewById(R.id.edit_pw);
        edit_pw2 = v.findViewById(R.id.edit_pw2);
        edit_name = v.findViewById(R.id.edit_name);
        edit_tel = v.findViewById(R.id.edit_tel);
        edit_dept = v.findViewById(R.id.edit_dept);
        btn_edit = v.findViewById(R.id.btn_edit);
        btn_back_edit = v.findViewById(R.id.btn_back_edit);
        pw_check = v.findViewById(R.id.pw_check);

        edit_id.setText(id);




        edit_pw2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (edit_pw.getText().toString().equals(edit_pw2.getText().toString())) {
                    password = edit_pw2.getText().toString();
                    pw_check.setText("비밀번호가 일치합니다.");
                } else {
                    pw_check.setText("비밀번호가 일치하지 않습니다.");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        requestQueue = Volley.newRequestQueue(getContext());

        Gson gson = new Gson();

        String url = "http://project-student.ddns.net/dependo/AndroidUpdate";

        stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.equals("1")){
                    Toast.makeText(getContext(), "수정이 완료되었습니다.", Toast.LENGTH_SHORT).show();
                    FragmentManager manager = getActivity().getSupportFragmentManager();
                    manager.beginTransaction().remove(worker_edit_Fragment.this).commit();
                    manager.popBackStack();
                }
                else{
                    Toast.makeText(getContext(), "수정이 실패하였습니다.", Toast.LENGTH_SHORT).show();
                }

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
                params.put("id", edit_id.getText().toString());
                params.put("password", password);
                params.put("name", edit_name.getText().toString());
                params.put("tel", edit_tel.getText().toString());
                params.put("dept", edit_dept.getText().toString());
                return params;
            }
        };
        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestQueue.add(stringRequest);
            }
        });
        btn_back_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getActivity().getSupportFragmentManager();
                manager.beginTransaction().remove(worker_edit_Fragment.this).commit();
                manager.popBackStack();
            }
        });



        return v;
    }
}