package com.jhj.myapplication3;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.transition.Transition;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Switch;
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
import com.jhj.myapplication3.ui.main.AttendanceVO;
import com.jhj.myapplication3.ui.main.MemberVO;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class worker_list_pageFragment extends Fragment {
    private TextView tv_id;
    private TextView tv_tel;
    private TextView tv_dept;
    private TextView tv_hell;
    private TextView tv_heaven;
    private TextView tv_date;
    private TextView education_view;
    private Button btn_remove_list;
    private Button btn_modify_list;
    private long now;
    private Date date;
    private TextView sunday;
    private TextView monday;
    private TextView tuesday;
    private TextView wendsday;
    private TextView thursday;
    private TextView friday;
    private TextView saturday;
    private TextView isLock;
    private Button btn_back_workerlist;



    Gson gson;

    private RequestQueue requestQueue;
    private StringRequest stringRequest;
    private StringRequest stringRequest2;
    private StringRequest stringRequest3;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_worker_list_page, container, false);

        String id = getArguments().getString("id");
        tv_id = v.findViewById(R.id.tv_id);
        tv_tel = v.findViewById(R.id.tv_tel);
        tv_dept = v.findViewById(R.id.tv_dept);
        tv_hell = v.findViewById(R.id.tv_hell);
        tv_heaven = v.findViewById(R.id.tv_heaven);
        tv_date = v.findViewById(R.id.tv_date);
        btn_remove_list = v.findViewById(R.id.btn_remove_list);
        btn_modify_list = v.findViewById(R.id.btn_modify_list);
        education_view = v.findViewById(R.id.education_view);
        sunday = v.findViewById(R.id.sunday);
        monday = v.findViewById(R.id.monday);
        tuesday = v.findViewById(R.id.tuesday);
        wendsday = v.findViewById(R.id.wendsday);
        thursday = v.findViewById(R.id.thursday);
        friday = v.findViewById(R.id.friday);
        saturday = v.findViewById(R.id.saturday);
        isLock = v.findViewById(R.id.isLock);
        btn_back_workerlist=v.findViewById(R.id.btn_back_workerlist);

        now = System.currentTimeMillis();
        date = new Date(now);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String getTime = sdf.format(date);
        String month = getTime.substring(5,7);
        String day = getTime.substring(8,10);
        tv_date.setText(month+"월 "+day+"일");


        Calendar calendar = Calendar.getInstance();
        int j = calendar.get(Calendar.DAY_OF_WEEK);
        int resultDate = Integer.parseInt(day)-j;
        for(int i=1;i<=7;i++){
            resultDate+=1;
            if(resultDate>31){
                resultDate=1;
            }

            if(i==1){
                sunday.setText(resultDate+"일");
            }
            else if(i==2){
                monday.setText(resultDate+"일");
            }
            else if(i==3){
                tuesday.setText(resultDate+"일");
            } else if (i == 4) {
                wendsday.setText(resultDate+"일");
            }
            else if(i==5){
                thursday.setText(resultDate+"일");
            }
            else if (i==6){
                friday.setText(resultDate+"일");
            }
            else if (i==7){
                saturday.setText(resultDate+"일");
            }
        }
        requestQueue = Volley.newRequestQueue(getContext());

        String member_url = "http://59.0.147.241:8085/project_dependo/AndroidMember";
        gson = new Gson();

        stringRequest = new StringRequest(Request.Method.POST, member_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                MemberVO vo = gson.fromJson(response, MemberVO.class);
                tv_id.setText(vo.getWorker_id()+" 근로자님");
                tv_tel.setText(vo.getWorker_phone());
                tv_dept.setText(vo.getWorker_dept());
                if(vo.getEducation().equals("Y")){
                    Drawable img = getContext().getResources().getDrawable( R.drawable.icon_size_happay );
                    img.setBounds( 0, 0, 105, 60 );
                    education_view.setCompoundDrawables(null,img,null,null);
                }
                else{
                    Drawable img = getContext().getResources().getDrawable( R.drawable.icon_size_shock );
                    img.setBounds( 0, 0, 105, 0 );
                    education_view.setCompoundDrawables(null,img,null,null);

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
                params.put("id", id);
                return params;
            }
        };
        requestQueue.add(stringRequest);

        String select_url = "http://59.0.147.241:8085/project_dependo/AndroidSelect";
       stringRequest2 = new StringRequest(Request.Method.POST, select_url, new Response.Listener<String>() {
           @Override
           public void onResponse(String response) {
               if(!response.equals("null")) {
                   AttendanceVO vo = gson.fromJson(response, AttendanceVO.class);
                   String startTime = vo.getStart_time();
                   String start_Time = startTime.substring(11, 16);
                   tv_hell.setText(start_Time);
                   String endTime = vo.getEnd_time();
                   String end_Time = endTime.substring(11, 16);
                   tv_heaven.setText(end_Time);
                   String check = vo.getAtt_type();
                   if (check.equals("Y")) {
                       Drawable img2 = getContext().getResources().getDrawable(R.drawable.icon_size_happay);
                       img2.setBounds(0, 0, 105, 60);
                       isLock.setCompoundDrawables(null, img2, null, null);
                   } else if (check.equals("N")) {
                       Drawable img2 = getContext().getResources().getDrawable(R.drawable.icon_size_shock);
                       img2.setBounds(0, 0, 105, 0);
                       isLock.setCompoundDrawables(null, img2, null, null);
                   }
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
               HashMap<String, String> params = new HashMap<>();
               params.put("id",id);
               params.put("date",getTime);
               return params;
           }
       };
       requestQueue.add(stringRequest2);

       btn_remove_list.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
               builder.setTitle("삭제하시겠습니까?");
               String url = "http://59.0.147.241:8085/project_dependo/AndroidDelete";

               stringRequest3 = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                   @Override
                   public void onResponse(String response) {
                       if(response.equals("1")){
                           Toast.makeText(getContext(), "삭제 완료되었습니다.", Toast.LENGTH_SHORT).show();
                           FragmentManager manager = getActivity().getSupportFragmentManager();
                           manager.beginTransaction().remove(worker_list_pageFragment.this).commit();
                           manager.popBackStack();
                       }
                       else if(response.equals("0")){
                           Toast.makeText(getContext(),"삭제 실패하였습니다.",Toast.LENGTH_SHORT).show();
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
                       params.put("id",id);
                       return params;
                   }
               };

               builder.setPositiveButton("cancle", null);
               builder.setNegativeButton("ok", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialog, int which) {
                       // RadioButton 체크한거 푸는 명령어~~
                       requestQueue.add(stringRequest3);
                   }
               });
               builder.create().show();
           }
       });

       btn_modify_list.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
               worker_edit_Fragment fm = new worker_edit_Fragment();
               Bundle bundle = new Bundle();
               bundle.putString("id",id);
               fm.setArguments(bundle);
               transaction.replace(R.id.list_frame,fm);
               transaction.commit();
           }
       });

       btn_back_workerlist.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               FragmentManager manager = getActivity().getSupportFragmentManager();
               manager.beginTransaction().remove(worker_list_pageFragment.this).commit();
               manager.popBackStack();
           }
       });




        // Inflate the layout for this fragment
        return v;
    }
}