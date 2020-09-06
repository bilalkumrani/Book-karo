package com.example.barberbookingapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class LoginActivity extends Activity
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
        setContentView(R.layout.login_activity);

        FirebaseApp.initializeApp(this);
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

        Boolean MasterFlag=true;
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        //userName Validation
        input_username = (EditText)findViewById(R.id.username_login);
        awesomeValidation.addValidation(this, R.id.username_login, RegexTemplate.NOT_EMPTY, R.string.invalid_name);
        if(awesomeValidation.validate())
        {
            username = input_username.getText().toString().trim();
            if (username.contains(" "))
            {
                MasterFlag=false;
                input_username.setError("username should not contains spaces");
            }
            else
            {
                //////////////////username  AVAILABLE HERE//////////////////////////

            }

        }
        else
        {
            MasterFlag=false;
            input_username.setError("Invalid");
        }

        input_pass = (EditText)findViewById(R.id.password_login);
        pass = input_pass.getText().toString().trim();

        //////PASSWORD AVAILABLE HERE///////

        if(MasterFlag)
        {
            final LodingDialogue lodingDialogue = new LodingDialogue(this,"Please wait..!!");
            lodingDialogue.startLoadingDialoge();

            Log.e("We are"," sining in service provider account");
            final FirebaseDatabase rootNode= FirebaseDatabase.getInstance();
            final DatabaseReference reference;
            reference = rootNode.getReference("ServiceProvider");
            reference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot)
                {

                    if( SinIn(reference,dataSnapshot))
                    {
                        Log.e("We are"," done sining in");

                       splash.tempData.setUserName(username);
                       splash.tempData.setPassword(pass);

                        lodingDialogue.dismiss();
                       Intent openServiceProviderActivity = new Intent(LoginActivity.this,ServiceProviderMainActivity.class);
                       startActivity(openServiceProviderActivity);

                    }
                    else
                    {
                        Log.e("We are"," sin in faild");
                        Toast.makeText(getApplicationContext(),"Wrong Pass or userName",Toast.LENGTH_LONG).show();
                        input_pass.setError("Invalid");
                        input_username.setError("Invalid");
                        lodingDialogue.dismiss();
                        //TODO: show error message
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Log.e("We are"," error in accessing");
                    //Here will be pop up if got any error while accessing database.
                }
            });



        }





    }

    private void customer_function() {

        Boolean MasterFlag = true;
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        //userName Validation
        input_username = (EditText)findViewById(R.id.username_login);
        awesomeValidation.addValidation(this, R.id.username_login, RegexTemplate.NOT_EMPTY, R.string.invalid_name);
        if(awesomeValidation.validate())
        {
            username = input_username.getText().toString().trim();
            if (username.contains(" "))
            {
                MasterFlag=false;
                input_username.setError("username should not contains spaces");
            }
            else
            {
                //////////////////username  AVAILABLE HERE//////////////////////////

            }

        }
        else
        {
            MasterFlag=false;
            input_username.setError("Invalid");
        }

        input_pass = (EditText)findViewById(R.id.password_login);
        pass = input_pass.getText().toString().trim();

        //////PASSWORD AVAILABLE HERE///////
        if(MasterFlag)
        {
            final LodingDialogue lodingDialogue = new LodingDialogue(this,"Please wait..!!");
            lodingDialogue.startLoadingDialoge();
            Log.e("We are"," sining in customer account");
            final FirebaseDatabase rootNode= FirebaseDatabase.getInstance();
            final DatabaseReference reference;
            reference = rootNode.getReference("Customers");
            reference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot)
                {

                    if( SinIn(reference,dataSnapshot))
                    {
                        Log.e("We are"," done sining in");
                        //TODO: Open customer dash board
                        Toast.makeText(getApplicationContext(),"Welcome",Toast.LENGTH_LONG).show();
                        lodingDialogue.dismiss();
                    }
                    else
                    {
                        Log.e("We are"," sin in faild");
                        Toast.makeText(getApplicationContext(),"Wrong Pass or userName",Toast.LENGTH_LONG).show();
                        input_pass.setError("Invalid");
                        input_username.setError("Invalid");
                        lodingDialogue.dismiss();
                        //TODO: show error message
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Log.e("We are"," error in accessing");
                    //Here will be pop up if got any error while accessing database.
                }
            });



        }

    }







    public void sign_up(View view)
    {
     to_signup = new Intent(LoginActivity.this, registration_form.class);
     startActivity(to_signup);
    }


    public void forget_pass(View view)
    {
        to_forget_password = new Intent(LoginActivity.this, Forget_password.class);
        startActivity(to_forget_password);

    }
    public boolean SinIn(DatabaseReference reference, DataSnapshot dataSnapshot)
    {
        try {

            int count=0;
            for (DataSnapshot dsp : dataSnapshot.getChildren())
            {


                if(username.equals(dsp.child("PersonalInfo").child("UserName").getValue().toString())&&pass.equals(dsp.child("PersonalInfo").child("Password").getValue().toString()))
                {
                    //TODO: Get personal info
                    splash.tempData.setServiceProviderName(dsp.child("PersonalInfo").child("Name").getValue().toString());
                    splash.tempData.setCustomerName(dsp.child("PersonalInfo").child("Name").getValue().toString());
                    splash.tempData.setNodeNo(""+count);
                    return true;
                }
                count++;
            }

            return  false;
        }
        catch (Exception ex)
        {
            Log.e("We are",ex.toString());
            return  false;
        }

    }

}