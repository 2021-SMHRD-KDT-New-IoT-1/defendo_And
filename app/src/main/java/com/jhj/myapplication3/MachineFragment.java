package com.jhj.myapplication3;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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


public class MachineFragment extends Fragment {
    private EditText m_id;
    private EditText w_id;
    private Button btn_register;
    private Button btn_mc_update;
    private FragmentTransaction fragmentTransaction;

    Gson gson;

    RequestQueue requestQueue;
    StringRequest stringRequest;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.fragment_machine, container, false);

        m_id = v.findViewById(R.id.m_id);
        w_id = v.findViewById(R.id.w_id);
        btn_register = v.findViewById(R.id.btn_modify);
        btn_mc_update = v.findViewById(R.id.btn_mc_update);



        requestQueue = Volley.newRequestQueue(getContext());

        gson = new Gson();

        String machine_url = "http://59.0.147.241:8085/project_dependo/AndroidHm";

        stringRequest = new StringRequest(Request.Method.POST, machine_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response=="1"){
                    Toast.makeText(getContext(),"등록이 완료되었습니다.", Toast.LENGTH_SHORT).show();
                }
                else if(response=="0"){
                    Toast.makeText(getContext(),"등록 실패하였습니다.",Toast.LENGTH_SHORT).show();
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
                HashMap<String,String>params = new HashMap<>();
                params.put("hm_id",m_id.getText().toString());
                params.put("worker_id",w_id.getText().toString());
                return params;
            }
        };
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestQueue.add(stringRequest);
            }
        });
        btn_mc_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                Machine2_Fragment fm = new Machine2_Fragment();
                transaction.replace(R.id.frameLayout2,fm);
                transaction.commit();
            }
        });




        return v;
    }
}