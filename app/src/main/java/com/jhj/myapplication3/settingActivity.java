package com.jhj.myapplication3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Date;

public class settingActivity extends AppCompatActivity {
    private Button btn_modification;
    private Button btn_logout;
    private Button button_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        btn_modification = findViewById(R.id.btn_modification);
        btn_logout = findViewById(R.id.btn_logout);
        button_back = findViewById(R.id.button_back);


        String id = getIntent().getStringExtra("id");

        btn_modification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                AdminEditFragment fm = new AdminEditFragment();
                Bundle bundle = new Bundle();
                bundle.putString("id", id);
                fm.setArguments(bundle);
                transaction.replace(R.id.admin_view, fm);
                transaction.commit();
                //0x7f0a00e1
            }
        });
        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }


        });
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(settingActivity.this,loginActivity.class);
                startActivity(intent);
            }
        });
    }


}