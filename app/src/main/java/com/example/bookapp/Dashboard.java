package com.example.bookapp;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.example.bookapp.databinding.ActivityDasboardBinding;

public class Dashboard extends AppCompatActivity {

    //view binding
    private ActivityDasboardBinding binding;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        binding = ActivityDasboardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //Handle loginBtn click, start login screen
        binding.loginBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(Dashboard.this,LoginActivity.class));
            }
        });


        //Handle skipBtn click, start continue login screen
        binding.skipBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(Dashboard.this,DashboardUserActivity.class));
            }
        });


    }
}