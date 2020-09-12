package com.example.barberbookingapp;



import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Frag_2 extends Fragment {
    View v;
    private RecyclerView myrecyclerview;
    private List<Services> lstServices;

    ArrayList <ServiceAvailbleForCustomer> serviceAvailbleForCustomers;


    public Frag_2() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.frag_2,container,false);

        myrecyclerview = (RecyclerView)v.findViewById(R.id.recyclerview);

        return v;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        serviceAvailbleForCustomers = new ArrayList<>();

        lstServices = new ArrayList<>();



    }
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            Log.e("Here","Refreshing requests" );

            loadData();
        }
    }

    public void loadData()
    {
        Log.e("We are","in new");

        final FirebaseDatabase rootNode= FirebaseDatabase.getInstance();
        final DatabaseReference reference;
        reference = rootNode.getReference("ServiceProvider");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @SuppressLint("LongLogTag")
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                lstServices.clear();

                DatabaseReference services;

                String ServiceProvciderName, ServiceProvciderIndex, ServiceProvciderLocation, ServiceProvciderAvgRating;

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    ServiceProvciderName = snapshot.child("PersonalInfo").child("Name").getValue().toString();
                    ServiceProvciderIndex = snapshot.child("Index").getValue().toString();
                    ServiceProvciderLocation = snapshot.child("PersonalInfo").child("MyLocation").getValue().toString();
                    ServiceProvciderAvgRating = snapshot.child("PersonalInfo").child("AvgRating").getValue().toString();

                    services = snapshot.child("Requests").getRef();

                    final String finalServiceProvciderName1 = ServiceProvciderName;
                    final String finalServiceProvciderIndex = ServiceProvciderIndex;
                    final String finalServiceProvciderLocation = ServiceProvciderLocation;
                    final String finalServiceProvciderAvgRating = ServiceProvciderAvgRating;

                    services.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            Log.e("inside inner loop","finding peniding");


                            for (DataSnapshot  values : dataSnapshot.getChildren())
                            {
                             Log.e("CName",   values.child("CustomerName").getValue().toString());
                             Log.e("CPHOen",values.child("Phone").getValue().toString());

                             String CN = values.child("CustomerName").getValue().toString();
                             String Phone = values.child("Phone").getValue().toString();
                             String Status = values.child("Status").getValue().toString();
                             if(CN.equals(splash.tempData.getCustomerName()) && Phone.equals(splash.tempData.getCustomerPhone()) && Status.equals("New"))
                             {
                                 lstServices.add(new Services(values.child("ServiceChoosed").getValue().toString(),"Requested to Service provider named : "+finalServiceProvciderName1+" on date: "+values.child("RequestTimeDate").getValue().toString(),R.drawable.request));
                             }

                            }

                            RecyclerViewAdapeter_For_Frag_2 recyclerAdapter = new RecyclerViewAdapeter_For_Frag_2(getContext(),lstServices,serviceAvailbleForCustomers);
                            myrecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
                            myrecyclerview.setAdapter(recyclerAdapter);

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
