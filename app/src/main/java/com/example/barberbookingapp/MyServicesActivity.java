package com.example.barberbookingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicMarkableReference;

public class MyServicesActivity extends AppCompatActivity {
    public static ArrayList<ServiceTitleDetials> serviceTitleDetials;
    ListView listView;
    static CustomListAdapter adapter;
    String UserName, Password;
    LodingDialogue lodingDialogue;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_services);
        listView = findViewById(R.id.dataListView);
        serviceTitleDetials = new  ArrayList<>();
        lodingDialogue = new LodingDialogue(this,"Updating Status.. Please wait");
        UserName = splash.tempData.getUserName();
        Password = splash.tempData.getPassword();
        textView = findViewById(R.id.ServiceTitle);
        loadData();
    }
    public void createService(View view)
    {
        if(textView.getText().toString().length()>0) {
            AddService(textView.getText().toString());
        }
        else
        {
            Toast.makeText(getApplicationContext(),"Please write name of service",Toast.LENGTH_LONG).show();
        }

    }
    public void loadData() {
        serviceTitleDetials.clear();
        Log.e("We are", "in new");
        DatabaseReference request;
        final FirebaseDatabase rootNode = FirebaseDatabase.getInstance();
        request = rootNode.getReference("ServiceProvider").child(splash.tempData.getNodeNo()).child("Services");
        request.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.e("here", "looking for services");
                for (DataSnapshot dsp : dataSnapshot.getChildren()) {
                    Log.e("here serivce : ", dsp.child("ServiceName").getValue().toString());
                    Log.e("Service index", dsp.child("Index").getValue().toString());
                    serviceTitleDetials.add(new ServiceTitleDetials(dsp.child("ServiceName").getValue().toString(), dsp.child("ServiceStatus").getValue().toString(), dsp.child("Index").getValue().toString()));
                }

                Log.e("Here", "Seting data into listview");
                adapter = new CustomListAdapter(getApplicationContext(), R.layout.my_servcies_items_layout, serviceTitleDetials);
                listView.setAdapter(adapter);
                ServiceProviderMainActivity.lodingDialogue.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    public class CustomListAdapter extends ArrayAdapter<ServiceTitleDetials> {
        ArrayList<ServiceTitleDetials> serviceTitleDetials;
        Context context;
        int resources;
        LayoutInflater layoutInflater;
        Button actionBtn;
        public CustomListAdapter(Context context, int resource, ArrayList<ServiceTitleDetials> serviceTitleDetials) {
            super(context, resource, serviceTitleDetials);
            this.serviceTitleDetials = serviceTitleDetials;
            this.context = context;
            this.resources = resource;
            layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = layoutInflater.inflate(resources, null);
            }

            final Button topicBtn = convertView.findViewById(R.id.topicDisplay);
            actionBtn = convertView.findViewById(R.id.AddRemoveServiceBtn);
            final ServiceTitleDetials requestListItem = getItem(position);

            final boolean flag;
            if (requestListItem.getServiceStatus().equals("ON")) {
                topicBtn.setText("You give service of  : " + requestListItem.getServiceName());
                actionBtn.setText("Remove");
                //;
                flag=false;
            } else {
                actionBtn.setText("Add");
                topicBtn.setText("You can give service: " + requestListItem.getServiceName());
                flag=true;
                //
            }
            actionBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    if (flag)
                    {

                        UpdateStatus("ON",requestListItem.getIndex());

                    }
                    else
                    {
                        UpdateStatus("NO",requestListItem.getIndex());
                    }
                }
            });


            return convertView;
        }

        public void UpdateStatus(final String status,final String Index) {
            lodingDialogue.startLoadingDialoge();
            Log.e("Final index",Index);
            Log.e("We are", "in serviced");
            final FirebaseDatabase rootNode = FirebaseDatabase.getInstance();
            final DatabaseReference reference;
            Log.e("Node", splash.tempData.getNodeNo());
            reference = rootNode.getReference("ServiceProvider").child(splash.tempData.getNodeNo()).child("Services").child(Index+"");
            reference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    Log.e("here", "Updating service status");


                    reference.child("ServiceStatus").setValue(status);
                    lodingDialogue.dismiss();
                    finish();
                    startActivity(getIntent());
                }


                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });


        }



    }
    public void AddService(final String service) {
        lodingDialogue.startLoadingDialoge();



        final FirebaseDatabase rootNode = FirebaseDatabase.getInstance();
        final DatabaseReference reference;
        Log.e("Node", splash.tempData.getNodeNo());
        reference = rootNode.getReference("ServiceProvider").child(splash.tempData.getNodeNo()).child("Services");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @SuppressLint("LongLogTag")
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                Log.e("Last index of service is  ",""+dataSnapshot.getChildrenCount());
                reference.child(""+dataSnapshot.getChildrenCount()).child("ServiceName").setValue(service);
                reference.child(""+dataSnapshot.getChildrenCount()).child("ServiceStatus").setValue("NO");
                reference.child(""+dataSnapshot.getChildrenCount()).child("Index").setValue(dataSnapshot.getChildrenCount());

                finish();
                startActivity(getIntent());
       }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

}