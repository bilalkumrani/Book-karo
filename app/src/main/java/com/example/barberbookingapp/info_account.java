package com.example.barberbookingapp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class info_account extends Activity
{
    TextView userName,name,noOFAvailedServ,PendingServ;
    LodingDialogue lodingDialogue;
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.info_account);
        name  = findViewById(R.id.Name);
        userName = findViewById(R.id.userName);
        PendingServ = findViewById(R.id.averageRating);
        noOFAvailedServ  = findViewById(R.id.noOfServedServices);
        name.setText(splash.tempData.getCustomerName());
        userName.setText(splash.tempData.getCustomerUserName());
        lodingDialogue = new LodingDialogue(this,"Wait.. We are loading your profile");
        lodingDialogue.startLoadingDialoge();
        loadData();
    }

    public void reload(View view)
    {
        Intent openAdminportal = new Intent(info_account.this,update_customer_data.class);
        startActivity(openAdminportal);
    }

    public void refersh(View  view)
    {
        finish();
        startActivity(getIntent());

    }



    public void loadData()
    {
        Log.e("We are", "in servicedand loading daata for customer");
        final FirebaseDatabase rootNode = FirebaseDatabase.getInstance();
        final DatabaseReference reference;
        Log.e("Node",splash.tempData.getNodeNo());
        reference = rootNode.getReference("ServiceProvider");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                boolean flag = true;



                for (DataSnapshot dsp : dataSnapshot.getChildren()) {


                       final DatabaseReference requests;
                       requests = dsp.getRef().child("Requests");
                       requests.addListenerForSingleValueEvent(new ValueEventListener() {
                           @SuppressLint("LongLogTag")
                           @Override
                           public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                               int pend=0;
                               int avialed=0;
                               for (DataSnapshot snapshot: dataSnapshot.getChildren())
                               {
                                   Log.e("Searching the customer for for account ",snapshot.child("CustomerName").getValue().toString());
                                   String CustomerPhone = snapshot.child("Phone").getValue().toString();
                                   String status = snapshot.child("Status").getValue().toString();
                                  if (CustomerPhone.equals(splash.tempData.getCustomerPhone())) {
                                      if ("Serviced".equals(status)) {
                                          Log.e("SErviced", "Here");
                                          avialed++;

                                      } else if ("New".equals(status)) {
                                          Log.e("pending", "here");
                                          pend++;
                                      }
                                  }
                               }

                               noOFAvailedServ.setText(""+avialed);
                               PendingServ.setText(""+pend);
                               lodingDialogue.dismiss();
                           }

                           @Override
                           public void onCancelled(@NonNull DatabaseError databaseError) {

                           }
                       });

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }


}
