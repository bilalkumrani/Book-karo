package com.example.barberbookingapp;

import androidx.appcompat.app.AppCompatActivity;
import java.lang.Thread;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;

public class splash extends AppCompatActivity {

    public static TempData tempData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        tempData = new TempData();
        Thread td = new Thread(){

            public void run()
            {
                try {
                    sleep(2000);
                    finish();
                } catch (Exception ae) {
                    ae.printStackTrace();
                } finally {

                    Intent it = new Intent(splash.this, LoginActivity.class);
                    startActivity(it);
                }

            }
        };td.start();



    }
}