package com.example.barberbookingapp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class update_customer_data extends Activity
{
    TextInputEditText name;
    TextInputEditText password;

    LodingDialogue lodingDialogue;
    Button update;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.edit_data);
        name = findViewById(R.id.username_service);
        password = findViewById(R.id.phone_service);


        update = findViewById(R.id.update);
        name.setText(splash.tempData.getCustomerName());
        password.setText(splash.tempData.getPassword());

        lodingDialogue  = new LodingDialogue(this,"Please wait we are updating your profile");

    }
    public void upadate(View view)
    {
        lodingDialogue.startLoadingDialoge();
        loadData();
        Log.e("upadnig btn ","here");
    }
    public void loadData()
    {
        Log.e("We are", "in servicedand loading daata for customer");
        final FirebaseDatabase rootNode = FirebaseDatabase.getInstance();
        final DatabaseReference reference;
        Log.e("Node",splash.tempData.getNodeNo());

        reference = rootNode.getReference("Customers").child(splash.tempData.getNodeNo()).child("PersonalInfo");

        reference.child("Name").setValue(name.getText().toString());
        reference.child("Password").setValue(password.getText().toString());

        splash.tempData.setCustomerName(name.getText().toString());
        update.setText("Your data is updated");


        lodingDialogue.dismiss();
        Log.e("updated cutomers","done");


    }



}