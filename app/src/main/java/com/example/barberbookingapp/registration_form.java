package com.example.barberbookingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;

public class registration_form extends AppCompatActivity {
    private String name;
    private String userName;
    private String phone;
    private String pass1;
    private String pass2;
    AwesomeValidation awesomeValidation;
    EditText input_name;
    EditText input_username;
    EditText input_phone;
    EditText input_pass1;
    EditText input_pass2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_registration_form);
    }

    //when register as a customer pressed
    public void register_customer(View view)
    {
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        //start name validating
       input_name = (EditText)findViewById(R.id.Name_service);
       awesomeValidation.addValidation(this, R.id.Name_service, "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", R.string.invalid_name);
       if(awesomeValidation.validate())
       {
           name = input_name.getText().toString().trim();
           ///////Name AVAILABLE HERE///////////////////////

       }
       else
       {
           input_name.setError("Please enter Valid Name");
       }

       //userName Validation
        input_username = (EditText)findViewById(R.id.username_service);
       awesomeValidation.addValidation(this, R.id.username_service, RegexTemplate.NOT_EMPTY, R.string.invalid_name);
       if(awesomeValidation.validate())
       {
           userName = input_username.getText().toString().trim();
           if (userName.contains(" "))
           {
               input_username.setError("username should not contain space");
           }
           else {
               //////////////////////////////////USERNAME AVAILABLE HERE//////////////////////

           }

       }
       else
       {
           input_username.setError("Invalid");
       }


        //password validation
        input_pass1 = (EditText)findViewById(R.id.password_service);
        awesomeValidation.addValidation(this, R.id.password_service, RegexTemplate.NOT_EMPTY, R.string.invalid_pass);

        if (awesomeValidation.validate())
       {
           input_pass2 = (EditText)findViewById(R.id.confirm_password_service);
           pass1 = input_pass1.getText().toString().trim();
           pass2 = input_pass2.getText().toString().trim();
           if (pass1.contains(" "))
           {
               input_pass1.setError("Password should not contain spaces");
           }
           else
           {
               if(pass1.equals(pass2))
               {
                   ///pass1   pass2 /// AVAILABLE HERE///////////

               }
               else
               {
                   input_pass2.setError("Password didn't match");
               }

           }
       }
       else
       {
         input_pass1.setError("Please enter a Valid Paasword");
       }

       //phone validation
        input_phone = (EditText)findViewById(R.id.phone_service);
        awesomeValidation.addValidation(this, R.id.phone_service, "^[+]?[0-9]{10,13}$", R.string.invalid);
        ;
        if(awesomeValidation.validate())
        {
            phone = input_phone.getText().toString().trim();

            //////phone_no AVAILABLE HERE/////////////////////////////
        }
        else
        {
            input_phone.setError("Invalid");
        }




    }

    //when register as service provider pressed
    public void register_service(View view)
    {
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        //start name validating
        input_name = (EditText)findViewById(R.id.Name_service);
        awesomeValidation.addValidation(this, R.id.Name_service, "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", R.string.invalid_name);
        if(awesomeValidation.validate())
        {
            name = input_name.getText().toString().trim();
        }
        else
        {
            input_name.setError("Invalid");
        }

        //userName Validation
        input_username = (EditText)findViewById(R.id.username_service);
        awesomeValidation.addValidation(this, R.id.username_service, RegexTemplate.NOT_EMPTY, R.string.invalid_name);
        if(awesomeValidation.validate())
        {
            userName = input_username.getText().toString().trim();
            if (userName.contains(" "))
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


        //password validation
        input_pass1 = (EditText)findViewById(R.id.password_service);
        awesomeValidation.addValidation(this, R.id.password_service, RegexTemplate.NOT_EMPTY, R.string.invalid_name);

        if (awesomeValidation.validate())
        {
            input_pass2 = (EditText)findViewById(R.id.confirm_password_service);
            pass1 = input_pass1.getText().toString().trim();
            pass2 = input_pass2.getText().toString().trim();
            if (pass1.contains(" "))
            {
                input_pass1.setError("Password should not contain spaces");
            }
            else
            {
                if(pass1.equals(pass2))
                {
                    //pass1 ////pass2 /// AVAILABBLE HERE/////////////////////////

                }
                else
                {
                    input_pass2.setError("Password didn't match");
                }

            }
        }
        else
        {
            input_pass1.setError("Please enter a Valid Paasword");
        }

        //phone validation
        input_phone = (EditText)findViewById(R.id.phone_service);
        awesomeValidation.addValidation(this, R.id.phone_service, "^[+]?[0-9]{10,13}$", R.string.invalid);
        ;
        if(awesomeValidation.validate())
        {
            phone = input_phone.getText().toString().trim();
        }
        else
        {
            input_phone.setError("Invalid");
        }


    }
}