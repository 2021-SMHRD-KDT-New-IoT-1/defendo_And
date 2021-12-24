package com.jhj.myapplication3;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
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
import com.google.gson.JsonArray;
import com.jhj.myapplication3.ui.main.ListVO;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class AllWorkerFragment extends Fragment {

    private View worker_view;
    private TextView worker_title;
    private ListView listview_worker;
    private ArrayList<ListVO> al = new ArrayList<>();
    private Button btn_back_medit;
    RequestQueue requestQueue;
    StringRequest stringRequest;
    JSONArray jsonArray;
    int i=0;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_all_worker, container, false);

        requestQueue = Volley.newRequestQueue(getContext());
        listview_worker = v.findViewById(R.id.listview_worker);

        String dept = getArguments().getString("dept");
        Gson gson = new Gson();




        String select_url = "http://59.0.147.241:8085/project_dependo/AndroidSelectAll";
        stringRequest = new StringRequest(Request.Method.POST, select_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getContext(),response,Toast.LENGTH_SHORT).show();

                try {
                    jsonArray = new JSONArray(response);
                    for (; i < jsonArray.length(); i++) {
                        al.add(gson.fromJson(jsonArray.get(i).toString(),ListVO.class));
                    }
                    ChatAdapter adapter = new ChatAdapter(getContext(), R.layout.all_worker_list, al);
                    listview_worker.setAdapter(adapter);
                } catch (JSONException jsonException) {
                    jsonException.printStackTrace();
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
                params.put("dept", dept);
                return params;
            }
        };
        requestQueue.add(stringRequest);


        return v;
    }
}