package com.jhj.myapplication3;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class AllWorkerFragment extends Fragment {

    private View worker_view;
    private TextView worker_title;
    private ListView listview_worker;
    private ArrayList<String> al = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {





//        listview_worker=(ListView) getView().findViewById(R.id.listview_worker);


        /*ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(getActivity(),R.layout.all_worker_list,al);
        listview_worker.setAdapter(adapter);*/






        return inflater.inflate(R.layout.fragment_all_worker, container, false);
    }
}