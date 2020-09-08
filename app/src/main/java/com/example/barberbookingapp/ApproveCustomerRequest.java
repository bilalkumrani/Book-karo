package com.example.barberbookingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class ApproveCustomerRequest extends AppCompatActivity {

    TextInputEditText Name,Request,Phone,RequestDate;
    ArrayList<NewRequestDataHolder>  customerData;
    int RequestIndexInList;
    Button confirm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.approve_request_portal);

        RequestIndexInList = getIntent().getIntExtra("RequestIndexInList", 0);;
        Log.e("RequestIndex is", String.valueOf(RequestIndexInList));
       customerData = NewRequests_Main_ServiceProvider.newRequetsBtnDetails;

       Name = findViewById(R.id.Name);
       Phone = findViewById(R.id.mobile);
       Request = findViewById(R.id.Request);
       RequestDate = findViewById(R.id.RequestDate);
       confirm = findViewById(R.id.confirmRequest);
       Name.setText("Name : "+customerData.get(RequestIndexInList).getCustomerName());
       Phone.setText("Mobile : "+customerData.get(RequestIndexInList).getPhone());
       Request.setText("Requested for: "+customerData.get(RequestIndexInList).getServiceChoosed());
       RequestDate.setText("Requested on : "+customerData.get(RequestIndexInList).getRequestTimeDate());

       Name.setEnabled(false);
       Phone.setEnabled(false);
       Request.setEnabled(false);
       RequestDate.setEnabled(false);


    }
    public void Confirm_Request(View view)
    {

            Log.e("We are", "in serviced");
            final FirebaseDatabase rootNode = FirebaseDatabase.getInstance();
            final DatabaseReference reference;
            Log.e("Node",splash.tempData.getNodeNo());
            reference = rootNode.getReference("ServiceProvider").child(splash.tempData.getNodeNo()).child("Requests").child(customerData.get(RequestIndexInList).getRequestIndex());
            reference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    Log.e("here",customerData.get(RequestIndexInList).getRequestIndex());

                    Date c = Calendar.getInstance().getTime();
                    System.out.println("Current time => " + c);

                    SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
                    String formattedDate = df.format(c);

                    reference.child("Status").setValue("Approved");
                    reference.child("AppointmentTimeDate").setValue(formattedDate);
                    confirm.setEnabled(false);
                    confirm.setText("Request Accepted");
                }



                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });





    }

}