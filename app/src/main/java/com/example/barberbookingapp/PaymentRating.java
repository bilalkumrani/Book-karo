package com.example.barberbookingapp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.hardware.camera2.TotalCaptureResult;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class PaymentRating extends Activity {
    String TAG = "pop_up";
    Button directionbtn;

    TextView payment;

    Button bookKAro,statified,notStatified,pay;
    String rating;
    int flag;
    String location,ServiceProviderIndex,RequestIndex;

    String name;
    String detail;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.donerequest);
        getIncomingIntent();

        flag =0;

        bookKAro = findViewById(R.id.accountbtn);
        statified = findViewById(R.id.statifed);
        notStatified = findViewById(R.id.notStatisfied);
        pay = findViewById(R.id.paybtn);
        payment = findViewById(R.id.ToPayamount);

        directionbtn = (Button)findViewById(R.id.directionbtn);
        directionbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PaymentRating.this,directions.class);
                intent.putExtra("ServiceProviderLocation",location);

                startActivity(intent);
            }
        });

        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flag!=0)
                {
                    Payment(payment.getText().toString());
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Please give ratings",Toast.LENGTH_LONG).show();
                }
            }
        });

        statified.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag=1;
                like();
                Toast.makeText(getApplicationContext(),"Thank you for rating",Toast.LENGTH_LONG).show();

            }
        });
        notStatified.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag=-1;
                Dislike();
                Toast.makeText(getApplicationContext(),"Thank you for rating",Toast.LENGTH_LONG).show();

            }

        });

    }
    public void BookKaro(View view)
    {
        Log.e("SEr Index",ServiceProviderIndex);



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
            RequestIndex = getIntent().getStringExtra("RequestIndex");

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

    public void Payment(final String value)
    {
        final LodingDialogue lodingDialogue = new LodingDialogue(this,"Placing your request.. Please wait");
        lodingDialogue.startLoadingDialoge();
        Log.e("We are", "payment methods");
        final FirebaseDatabase rootNode = FirebaseDatabase.getInstance();
        final DatabaseReference reference;
        Log.e("Node",splash.tempData.getNodeNo());
        reference = rootNode.getReference("ServiceProvider").child(ServiceProviderIndex).child("Requests");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.e("Request index", String.valueOf(dataSnapshot.getChildrenCount()));

              reference.child(RequestIndex).child("Payment").setValue((value));
              reference.child(RequestIndex).child("Status").setValue(("Serviced"));

                pay.setEnabled(false);
                pay.setText("Paid");
                lodingDialogue.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




    }
    public void like()
    {
        final LodingDialogue lodingDialogue = new LodingDialogue(this,"Submitting your response.. Please wait");
        lodingDialogue.startLoadingDialoge();
        Log.e("We are", "payment methods");
        final FirebaseDatabase rootNode = FirebaseDatabase.getInstance();
        final DatabaseReference reference;
        Log.e("Node",splash.tempData.getNodeNo());
        reference = rootNode.getReference("ServiceProvider").child(ServiceProviderIndex);
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                final int[] likes = {0};
                final int[] dislikes = {0};
                final DatabaseReference personalInfoRoot = dataSnapshot.getRef().child("PersonalInfo");
                personalInfoRoot.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        Log.e("Likes",dataSnapshot.child("Likes").getValue().toString());
                        Log.e("DisLikes",dataSnapshot.child("Dislikes").getValue().toString());
                        likes[0] = Integer.parseInt(dataSnapshot.child("Likes").getValue().toString());
                        dislikes[0] = Integer.parseInt(dataSnapshot.child("Dislikes").getValue().toString());

                        personalInfoRoot.child("Likes").setValue((likes[0])+1);



                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                reference.child(RequestIndex).child("Rating").setValue(("+"));

                lodingDialogue.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




    }
    public void Dislike()
    {
        final LodingDialogue lodingDialogue = new LodingDialogue(this,"Submitting your response.. Please wait");
        lodingDialogue.startLoadingDialoge();
        Log.e("We are", "payment methods");
        final FirebaseDatabase rootNode = FirebaseDatabase.getInstance();
        final DatabaseReference reference;
        Log.e("Node",splash.tempData.getNodeNo());
        reference = rootNode.getReference("ServiceProvider").child(ServiceProviderIndex);
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                final int[] likes = {0};
                final int[] dislikes = {0};
               final DatabaseReference personalInfoRoot = dataSnapshot.getRef().child("PersonalInfo");
               personalInfoRoot.addListenerForSingleValueEvent(new ValueEventListener() {
                   @Override
                   public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                       Log.e("Likes",dataSnapshot.child("Likes").getValue().toString());
                       Log.e("DisLikes",dataSnapshot.child("Dislikes").getValue().toString());
                       likes[0] = Integer.parseInt(dataSnapshot.child("Likes").getValue().toString());
                       dislikes[0] = Integer.parseInt(dataSnapshot.child("Dislikes").getValue().toString());

                       personalInfoRoot.child("Dislikes").setValue((dislikes[0])+1);



                   }

                   @Override
                   public void onCancelled(@NonNull DatabaseError databaseError) {

                   }
               });

                reference.child(RequestIndex).child("Rating").setValue(("-"));

                lodingDialogue.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




    }
}