package com.jhj.myapplication3;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MachineFragment extends Fragment {
    private View join_view;
    private TextView join_title;
    private TextView m_title1;
    private EditText m_id;
    private EditText w_id;
    private Button btn_register;
    private TextView m_title2;
    private EditText edit_mid;
    private EditText edit_wid;
    private Button btn_modify;
    private Button btn_back_medit;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.fragment_machine, container, false);

        join_view = v.findViewById(R.id.join_view);
        join_title = v.findViewById(R.id.join_title);
        m_title1 = v.findViewById(R.id.m_title1);
        m_id = v.findViewById(R.id.m_id);
        w_id = v.findViewById(R.id.w_id);
        btn_register = v.findViewById(R.id.btn_register);
        m_title2 = v.findViewById(R.id.m_title2);
        edit_mid = v.findViewById(R.id.edit_mid);
        edit_wid = v.findViewById(R.id.edit_wid);
        btn_modify = v.findViewById(R.id.btn_modify);
        btn_back_medit = v.findViewById(R.id.btn_back_medit);




        return inflater.inflate(R.layout.fragment_machine, container, false);
    }
}