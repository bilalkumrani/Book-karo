package com.example.barberbookingapp;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class UpdateServiceProviderLocation extends AppCompatActivity {

    EditText yourlocation;
    Button btn_direction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_service_provider_location);


        yourlocation = (EditText)findViewById(R.id.yourlocation);
        btn_direction = (Button)findViewById(R.id.btn_direction);

        yourlocation.setText(splash.tempData.getServiceProviderLocation());
        //yourlocation.setText(loadLocation());

        btn_direction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String source = yourlocation.getText().toString().trim();
                yourlocation.setText(source);
                if(source.equals("")){
                    Toast.makeText(getApplicationContext(),"Enter new location", Toast.LENGTH_SHORT).show();

                }else{
                   updateLocation(source);
                }

            }

        });





    }

    public void updateLocation(final String source)
    {
        ServiceProviderMainActivity.lodingDialogue.startLoadingDialoge();
        Log.e("We are", "in serviced");
        final FirebaseDatabase rootNode = FirebaseDatabase.getInstance();
        final DatabaseReference reference;
        Log.e("Node",splash.tempData.getNodeNo());
        reference = rootNode.getReference("ServiceProvider").child(splash.tempData.getNodeNo()).child("PersonalInfo");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                Log.e("here",source);


                reference.child("MyLocation").setValue(source);
                btn_direction.setEnabled(false);
                btn_direction.setText("Location Updated");
                ServiceProviderMainActivity.lodingDialogue.dismiss();
            }



            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });





    }

}