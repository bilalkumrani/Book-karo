package com.example.barberbookingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;


public class MainActivity extends Activity
{
    private String username;
    private String pass;
    EditText input_username;
    EditText input_pass;
    AwesomeValidation awesomeValidation;
    RadioGroup radioGroup;


   private Intent to_signup;
   private  Intent to_forget_password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

    }

    public void login(View view)
    {
        radioGroup = findViewById(R.id.r_btn_login);
        int checkid = radioGroup.getCheckedRadioButtonId();
        if(checkid==-1)
        {
            Toast.makeText(getApplicationContext(),"PLEASE CHECK BUTTON",Toast.LENGTH_SHORT).show();
        }
        else
        {
            switch (checkid)
            {
                case R.id.Customer_Radio:
                    customer_function();
                    break;
                case R.id.Service_Radio:
                    service_function();
            }
        }





    }

    private void service_function() {

        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        //userName Validation
        input_username = (EditText)findViewById(R.id.username_login);
        awesomeValidation.addValidation(this, R.id.username_login, RegexTemplate.NOT_EMPTY, R.string.invalid_name);
        if(awesomeValidation.validate())
        {
            username = input_username.getText().toString().trim();
            if (username.contains(" "))
            {
                input_username.setError("username should not contains spaces");
            }
            else
            {
                //////////////////username  AVAILABLE HERE//////////////////////////

            }

        }
        else
        {
            input_username.setError("Invalid");
        }

        input_pass = (EditText)findViewById(R.id.password_login);
        pass = input_pass.getText().toString().trim();

        //////PASSWORD AVAILABLE HERE///////






    }

    private void customer_function() {

        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        //userName Validation
        input_username = (EditText)findViewById(R.id.username_login);
        awesomeValidation.addValidation(this, R.id.username_login, RegexTemplate.NOT_EMPTY, R.string.invalid_name);
        if(awesomeValidation.validate())
        {
            username = input_username.getText().toString().trim();
            if (username.contains(" "))
            {
                input_username.setError("username should not contains spaces");
            }
            else
            {
                //////////////////username  AVAILABLE HERE//////////////////////////

            }

        }
        else
        {
            input_username.setError("Invalid");
        }

        input_pass = (EditText)findViewById(R.id.password_login);
        pass = input_pass.getText().toString().trim();

        //////PASSWORD AVAILABLE HERE///////

    }







    public void sign_up(View view)
    {
     to_signup = new Intent(MainActivity.this, registration_form.class);
     startActivity(to_signup);
    }


    public void forget_pass(View view)
    {
        to_forget_password = new Intent(MainActivity.this, Forget_password.class);
        startActivity(to_forget_password);

    }

}