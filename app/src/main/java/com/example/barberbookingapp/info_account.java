package com.example.barberbookingapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

public class info_account extends Activity
{
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.info_account);
    }

    public void Update_Customer_Method(View view)
    {
        Intent openAdminportal = new Intent(info_account.this,update_customer_data.class);
        startActivity(openAdminportal);
    }
}
