package com.example.barberbookingapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class pop_up extends Activity
{
    String TAG = "pop_up";
    Button directionbtn;


    String location,ServiceProviderIndex;

    String name;
    String detail;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dailog);
        getIncomingIntent();

        directionbtn = (Button)findViewById(R.id.directionbtn);
        directionbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(pop_up.this,directions.class);
                intent.putExtra("ServiceProviderLocation",location);

                startActivity(intent);
            }
        });


    }
    public void BookKaro(View view)
    {
        Log.e("SEr Index",ServiceProviderIndex);

        PlaceRequest();
    }
    public void on_click(View view)
    {
        Log.d("check", "on_click: is called");
    }
    private void getIncomingIntent()
    {
        Log.d(TAG, "getIncomingIntent: ");
        if(getIntent().hasExtra("Name")&&getIntent().hasExtra("Detail"))
        {
             name = getIntent().getStringExtra("Name");
             detail = getIntent().getStringExtra("Detail");
            location = getIntent().getStringExtra("ServiceProviderLocation");
            ServiceProviderIndex = getIntent().getStringExtra("ServiceProviderIndex");
            Log.d(TAG, "getIncomingIntent: "+name+" "+detail);
            setData(name,detail);
        }

    }
    public void setData(String name, String detail)
    {
        TextView name_field = findViewById(R.id.name_in_service);
        TextView detail_field = findViewById(R.id.Detail_in_service);
        name_field.setText(name);
        detail_field.setText(detail);
    }
    public void PlaceRequest()
    {
        final LodingDialogue lodingDialogue = new LodingDialogue(this,"Placing your request.. Please wait");
        lodingDialogue.startLoadingDialoge();
        Log.e("We are", "placing ordeer ");
        final FirebaseDatabase rootNode = FirebaseDatabase.getInstance();
        final DatabaseReference reference;
        Log.e("Node",splash.tempData.getNodeNo());
        reference = rootNode.getReference("ServiceProvider").child(ServiceProviderIndex).child("Requests");
         reference.addListenerForSingleValueEvent(new ValueEventListener() {
             @Override
             public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                 Log.e("Request index", String.valueOf(dataSnapshot.getChildrenCount()));

                 reference.child(String.valueOf(dataSnapshot.getChildrenCount())).child("RequestIndex").setValue((String.valueOf(dataSnapshot.getChildrenCount())));
                 reference.child(String.valueOf(dataSnapshot.getChildrenCount())).child("AppointmentTimeDate").setValue(("-"));
                 reference.child(String.valueOf(dataSnapshot.getChildrenCount())).child("Phone").setValue((splash.tempData.getCustomerPhone()));
                 reference.child(String.valueOf(dataSnapshot.getChildrenCount())).child("Rating").setValue(("+"));
                 reference.child(String.valueOf(dataSnapshot.getChildrenCount())).child("CustomerName").setValue((splash.tempData.getCustomerName()));

                 String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
                 String currentTime = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());
                 reference.child(String.valueOf(dataSnapshot.getChildrenCount())).child("RequestTimeDate").setValue((currentDate+" on "+currentTime));
                 reference.child(String.valueOf(dataSnapshot.getChildrenCount())).child("ServiceChoosed").setValue((name));
                 reference.child(String.valueOf(dataSnapshot.getChildrenCount())).child("Status").setValue(("New"));


                 lodingDialogue.dismiss();
             }

             @Override
             public void onCancelled(@NonNull DatabaseError databaseError) {

             }
         });




    }


}
