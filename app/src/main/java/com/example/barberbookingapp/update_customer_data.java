package com.example.barberbookingapp;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

import androidx.annotation.Nullable;

import com.google.android.material.textfield.TextInputEditText;

public class update_customer_data extends Activity
{
    TextInputEditText name;
    TextInputEditText phone;
    TextInputEditText password;
    TextInputEditText new_password;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.edit_data);
    }
    public void data_display(View view)
    {

        name = findViewById(R.id.username_service);
        phone = findViewById(R.id.phone_service);

        password = findViewById(R.id.password_service);
        new_password = findViewById(R.id.confirm_password_service);
        Log.d("check", "display_data: "+name.getText()+" "+phone.getText()+" "+password.getText()+" "+new_password.getText());
    }


}
