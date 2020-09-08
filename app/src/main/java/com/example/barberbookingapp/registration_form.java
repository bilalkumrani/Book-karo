package com.example.barberbookingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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
    LocalStorage localStorage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_form);

        localStorage = new LocalStorage(this);

    }

    //when register as a customer pressed
    public void register_customer(View view)
    {
        Boolean MasterFlag = true;
        input_name = (EditText)findViewById(R.id.Name_service);

        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        //start name validating
       awesomeValidation.addValidation(this, R.id.Name_service, "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", R.string.invalid_name);
       if(awesomeValidation.validate())
       {
           name = input_name.getText().toString().trim();
           ///////Name AVAILABLE HERE///////////////////////

       }
       else
       {
           MasterFlag=false;
           input_name.setError("Please enter Valid Name");
       }

       //userName Validation
        input_username = (EditText)findViewById(R.id.Name);
       awesomeValidation.addValidation(this, R.id.Name, RegexTemplate.NOT_EMPTY, R.string.invalid_name);
       if(awesomeValidation.validate())
       {
           userName = input_username.getText().toString().trim();
           if (userName.contains(" "))
           {
               MasterFlag=false;
               input_username.setError("username should not contain space");
           }

           else
               {
               //////////////////////////////////USERNAME AVAILABLE HERE//////////////////////

           }

       }
       else
       {
           MasterFlag=false;
           input_username.setError("Invalid");
       }


        //password validation
        input_pass1 = (EditText)findViewById(R.id.mobile);
        awesomeValidation.addValidation(this, R.id.mobile, RegexTemplate.NOT_EMPTY, R.string.invalid_pass);

        if (awesomeValidation.validate())
       {
           input_pass2 = (EditText)findViewById(R.id.RequestDate);
           pass1 = input_pass1.getText().toString().trim();
           pass2 = input_pass2.getText().toString().trim();
           if (pass1.contains(" "))
           {
               MasterFlag=false;
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
                   MasterFlag=false;
                   input_pass2.setError("Password didn't match");
               }

           }
       }
       else
       {
           MasterFlag=false;
         input_pass1.setError("Please enter a Valid Paasword");
       }

       //phone validation
        input_phone = (EditText)findViewById(R.id.Request);
        awesomeValidation.addValidation(this, R.id.Request, "^[+]?[0-9]{10,13}$", R.string.invalid);
        ;
        if(awesomeValidation.validate())
        {
            phone = input_phone.getText().toString().trim();

            //////phone_no AVAILABLE HERE/////////////////////////////
        }
        else
        {
            MasterFlag=false;
            input_phone.setError("Invalid");
        }

        if(MasterFlag)
        {
            final LodingDialogue lodingDialogue = new LodingDialogue(this,"Creating Customer Account. Please wait..!!");
            lodingDialogue.startLoadingDialoge();

            Log.e("We are"," creating customer account");
            final FirebaseDatabase rootNode= FirebaseDatabase.getInstance();
            final DatabaseReference reference;
            reference = rootNode.getReference("Customers");
            reference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot)
                {
                  if( UploadCustomerData(reference,dataSnapshot))
                  {
                      //localStorage.InsertAccount(userName,pass1,"Customer");

                      //TODO:Get personal info
                      splash.tempData.setUserName(userName);
                      splash.tempData.setCustomerUserName(userName);
                      splash.tempData.setPassword(pass1);
                      splash.tempData.setServiceProviderName(name);
                      splash.tempData.setCustomerName(name);
                      splash.tempData.setCustomerPhone(phone);
                      splash.tempData.setServiceProviderLocation("Sukkur");
                      lodingDialogue.dismiss();
                      Intent openServiceProviderActivity = new Intent(registration_form.this,CustomerDashBorad_MainActivity.class);
                      startActivity(openServiceProviderActivity);
                      finish();

                      //TODO: Open cutomer dash board
                  }
                  else
                  {
                      lodingDialogue.dismiss();
                      //TODO: show error message
                  }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                    //Here will be pop up if got any error while accessing database.
                }
            });


        }


    }

    //when register as service provider pressed
    public void register_service(View view)
    {
        Boolean MasterFlag = true;
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
            MasterFlag=false;
            input_name.setError("Invalid");
        }

        //userName Validation
        input_username = (EditText)findViewById(R.id.Name);
        awesomeValidation.addValidation(this, R.id.Name, RegexTemplate.NOT_EMPTY, R.string.invalid_name);
        if(awesomeValidation.validate())
        {
            userName = input_username.getText().toString().trim();
            if (userName.contains(" "))
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


        //password validation
        input_pass1 = (EditText)findViewById(R.id.mobile);
        awesomeValidation.addValidation(this, R.id.mobile, RegexTemplate.NOT_EMPTY, R.string.invalid_name);

        if (awesomeValidation.validate())
        {
            input_pass2 = (EditText)findViewById(R.id.RequestDate);
            pass1 = input_pass1.getText().toString().trim();
            pass2 = input_pass2.getText().toString().trim();
            if (pass1.contains(" "))
            {
                MasterFlag=false;
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
                    MasterFlag=false;
                    input_pass2.setError("Password didn't match");
                }

            }
        }
        else
        {
            MasterFlag=false;
            input_pass1.setError("Please enter a Valid Paasword");
        }

        //phone validation
        input_phone = (EditText)findViewById(R.id.Request);
        awesomeValidation.addValidation(this, R.id.Request, "^[+]?[0-9]{10,13}$", R.string.invalid);
        ;
        if(awesomeValidation.validate())
        {
            phone = input_phone.getText().toString().trim();
        }
        else
        {
            MasterFlag=false;
            input_phone.setError("Invalid");
        }


        if(MasterFlag)
        {
            final LodingDialogue lodingDialogue = new LodingDialogue(this,"Creating Service Provider Account. Please wait..!!");
            lodingDialogue.startLoadingDialoge();

            Log.e("We are"," creating vendor account account");
            final FirebaseDatabase rootNode= FirebaseDatabase.getInstance();
            final DatabaseReference reference;
            reference = rootNode.getReference("ServiceProvider");
            reference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot)
                {

                    if( UploadServiceProviderData(reference,dataSnapshot))
                    {
/*
                        localStorage.deleteAll(userName);
                        localStorage.InsertAccount(userName,pass1,"ServiceProvider");
                        lodingDialogue.dismiss();

                       LocalStorage localStorage = new LocalStorage(registration_form.this);
                        Cursor result = localStorage.getAll();
                        if (result.getCount() == 0) {
                            //showMessage("Error","No data found");
                            return;
                        }
                        else
                        {
                            String user = result.getString(0).toString();
                            String pass = result.getString(1).toString();
                            Toast.makeText(getApplicationContext(),user+" : "+pass,Toast.LENGTH_SHORT).show();
                            Log.e("Here is ",user+" : "+pass);
                        }
                        */
                        //TODO:Get personal info
                        splash.tempData.setUserName(userName);
                        splash.tempData.setPassword(pass1);
                        splash.tempData.setServiceProviderName(name);
                        splash.tempData.setCustomerName(name);
                        splash.tempData.setServiceProviderLocation("Sukkur");
                        lodingDialogue.dismiss();
                        Intent openServiceProviderActivity = new Intent(registration_form.this,ServiceProviderMainActivity.class);
                        startActivity(openServiceProviderActivity);
                        finish();
                    }
                    else
                    {
                        lodingDialogue.dismiss();
                        //TODO: show error message
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                    //Here will be pop up if got any error while accessing database.
                }
            });


        }

    }

    public boolean UploadCustomerData(DatabaseReference reference,DataSnapshot dataSnapshot)
    {
        try {

            if(Validate(reference,dataSnapshot))
            {

                int counter=0;
                for (DataSnapshot dsp : dataSnapshot.getChildren())
                {
                    counter = Integer.parseInt(dsp.child("Index").getValue().toString());
                }
                splash.tempData.setNodeNo(""+counter);
                splash.tempData.setCustomerName(name);
                counter++;

                reference.child((""+(counter))).child("PersonalInfo").child("Name").setValue(name);
                reference.child((""+(counter))).child("PersonalInfo").child("UserName").setValue(userName);
                reference.child((""+(counter))).child("PersonalInfo").child("Phone").setValue(phone);
                reference.child((""+(counter))).child("PersonalInfo").child("Password").setValue(pass1);
                reference.child((""+(counter))).child("PersonalInfo").child("MyLocation").setValue("Sukkur");

                reference.child((""+(counter))).child("Requests").child("0").child("RequestIndex").setValue(("0"));


                reference.child((""+(counter))).child("Index").setValue((counter)+"");

                Toast.makeText(getApplicationContext(),"Registered :"+counter,Toast.LENGTH_LONG).show();
                return  true;
            }
            else
            {
                return  false;
            }

        }
        catch (Exception ex)
        {
            return  false;
        }
    }

    public boolean UploadServiceProviderData(DatabaseReference reference,DataSnapshot dataSnapshot)
    {
        try {

            if(Validate(reference,dataSnapshot))
            {

                int counter=0;
                for (DataSnapshot dsp : dataSnapshot.getChildren())
                {
                    counter = Integer.parseInt(dsp.child("Index").getValue().toString());
                }

                //TODO: Set node no:

                counter++;
                splash.tempData.setNodeNo(""+counter);

                //TODO:Personal data

                reference.child((""+(counter))).child("Index").setValue((counter)+"");


                reference.child((""+(counter))).child("PersonalInfo").child("Name").setValue(name);
                reference.child((""+(counter))).child("PersonalInfo").child("UserName").setValue(userName);
                reference.child((""+(counter))).child("PersonalInfo").child("Phone").setValue(phone);
                reference.child((""+(counter))).child("PersonalInfo").child("Password").setValue(pass1);
                reference.child((""+(counter))).child("PersonalInfo").child("MyLocation").setValue("Sukkur");
                reference.child((""+(counter))).child("PersonalInfo").child("AvgRating").setValue("New");
                reference.child((""+(counter))).child("PersonalInfo").child("Likes").setValue("0");
                reference.child((""+(counter))).child("PersonalInfo").child("Dislikes").setValue("0");

                //TODO: Default requests

                reference.child((""+(counter))).child("Requests").child("0").child("RequestIndex").setValue(("0"));
                reference.child((""+(counter))).child("Requests").child("0").child("AppointmentTimeDate").setValue(("12/12/2020"));
                reference.child((""+(counter))).child("Requests").child("0").child("Phone").setValue(("0000000000"));
                reference.child((""+(counter))).child("Requests").child("0").child("Rating").setValue(("New"));
                reference.child((""+(counter))).child("Requests").child("0").child("RequestTimeDate").setValue(("12/12/2020"));
                reference.child((""+(counter))).child("Requests").child("0").child("ServiceChoosed").setValue(("Hair"));
                reference.child((""+(counter))).child("Requests").child("0").child("Status").setValue(("Default"));
                reference.child((""+(counter))).child("Requests").child("0").child("Payment").setValue(("0"));
                reference.child((""+(counter))).child("Requests").child("0").child("CustomerName").setValue(("test"));


                //TODO:Service 1

                reference.child((""+(counter))).child("Services").child("0").child("ServiceName").setValue("Hair");
                reference.child((""+(counter))).child("Services").child("0").child("ServiceStatus").setValue("NO");
                reference.child((""+(counter))).child("Services").child("0").child("Index").setValue("0");


                //TODO:Service 2

                reference.child((""+(counter))).child("Services").child("1").child("ServiceName").setValue("Beard");
                reference.child((""+(counter))).child("Services").child("1").child("ServiceStatus").setValue("NO");
                reference.child((""+(counter))).child("Services").child("1").child("Index").setValue("1");


                //TODO:Service 3

                reference.child((""+(counter))).child("Services").child("2").child("ServiceName").setValue("Facial");
                reference.child((""+(counter))).child("Services").child("2").child("ServiceStatus").setValue("NO");
                reference.child((""+(counter))).child("Services").child("2").child("Index").setValue("2");





                Toast.makeText(getApplicationContext(),"Registered :"+counter,Toast.LENGTH_LONG).show();
                return  true;
            }
            else
            {
                return  false;
            }

        }
        catch (Exception ex)
        {
            return  false;
        }
    }
    public boolean Validate(DatabaseReference reference, DataSnapshot dataSnapshot)
    {
        try {

            for (DataSnapshot dsp : dataSnapshot.getChildren())
            {


                if(userName.equals(dsp.child("PersonalInfo").child("UserName").getValue().toString()))
                {
                    input_username.setError("Already taken");


                    return false;
                }
                if(phone.equals(dsp.child("PersonalInfo").child("Phone").getValue().toString()))
                {
                    input_phone.setError("This number is already in use");
                    return  false;
                }

            }

            return  true;
        }
        catch (Exception ex)
        {
            Log.e("We are",ex.toString());
            return  true;
        }

    }

}