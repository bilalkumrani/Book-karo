package com.example.barberbookingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class Service_Provider_Account_Details extends AppCompatActivity {

    TextView name,username,noOfServedServices,ratings;
    int positive = 0,negetive=0,totalServed=0;
    LodingDialogue lodingDialogue;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.service_provider_account_info);

        name = (TextView) findViewById(R.id.Name);
        username = (TextView) findViewById(R.id.userName);
        noOfServedServices = (TextView) findViewById(R.id.noOfServedServices);
        ratings = (TextView) findViewById(R.id.averageRating);
        loadData();
        lodingDialogue = new LodingDialogue(this,"Loading your data");
        lodingDialogue.startLoadingDialoge();
        name.setText(splash.tempData.getServiceProviderName());
        username.setText(splash.tempData.getUserName());


    }

    public void reload(View view)
    {
        lodingDialogue.startLoadingDialoge();
        positive=0;
        negetive=0;
        totalServed=0;

        loadData();
    }
    public int avgRatingFormula(int positive,int negetive, int totalServed)
    {
        return 0;
    }
    public void loadData()
    {
        Log.e("We are", "in serviced");
        final FirebaseDatabase rootNode = FirebaseDatabase.getInstance();
        final DatabaseReference reference;
        Log.e("Node",splash.tempData.getNodeNo());
        reference = rootNode.getReference("ServiceProvider").child(splash.tempData.getNodeNo()).child("Requests");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                boolean flag = true;

                for (DataSnapshot dsp : dataSnapshot.getChildren()) {

                    if("Serviced".equals(dsp.child("Status").getValue().toString()))
                    {
                        if("+".equals(dsp.child("Rating").getValue().toString()))positive++;
                        if("-".equals(dsp.child("Rating").getValue().toString()))negetive++;

                        totalServed++;
                        Log.e("here",totalServed+" : "+positive+" : "+negetive);
                    }
                    noOfServedServices.setText(totalServed+"");
                    ratings.setText(positive+"+ & "+negetive+" - ");

                }
                lodingDialogue.dismiss();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }


}