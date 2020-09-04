package com.example.barberbookingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;

public class Forget_password extends AppCompatActivity {
    private EditText input_mobile;
    private String mobile_no;
    AwesomeValidation awesomeValidation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_forget_password);
    }


    public void send_otp(View view)
    {
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        input_mobile = (EditText)findViewById(R.id.mobile_no_field);
        awesomeValidation.addValidation(this, R.id.mobile_no_field, "^[+]?[0-9]{10,13}$", R.string.invalid);
        if (awesomeValidation.validate())
        {
            mobile_no = input_mobile.getText().toString().trim();

            ////////////MOBILE NO AVAILABLE HERE
            Toast.makeText(
                    getApplicationContext(),
                    "SUCCESS",
                    Toast.LENGTH_SHORT
            ).show();
        }
        else
        {
            input_mobile.setError("Invalid");
        }
    }
}