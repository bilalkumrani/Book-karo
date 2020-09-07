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

public class Frag_4 extends Fragment {
    View v;
    private RecyclerView myrecyclerview;
    private List<Services> lstServices;
    ArrayList <AcceptedRequesDetails> acceptedRequesDetails;


    public Frag_4() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.frag_4,container,false);
        myrecyclerview = (RecyclerView)v.findViewById(R.id.complete_recyclerview);

        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        lstServices = new ArrayList<>();
        acceptedRequesDetails= new ArrayList<>();

    }

    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            Log.e("Here","Refreshing frag 4");
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
                                if(CN.equals(splash.tempData.getCustomerName()) && Phone.equals(splash.tempData.getCustomerPhone()) && Status.equals("Approved"))
                                {
                                    lstServices.add(new Services(values.child("ServiceChoosed").getValue().toString(),"Your request sent to : "+finalServiceProvciderName1+" is accepted and got appointment on date:  "+values.child("AppointmentTimeDate").getValue().toString(),R.drawable.complete));
                                    acceptedRequesDetails.add(new AcceptedRequesDetails(values.child("ServiceChoosed").getValue().toString(),values.child("RequestIndex").getValue().toString(), finalServiceProvciderName1, finalServiceProvciderIndex, finalServiceProvciderLocation, finalServiceProvciderAvgRating));

                                }

                            }

                            RecyclerViewAdapeter_For_Frag_4 recyclerAdapter = new RecyclerViewAdapeter_For_Frag_4(getContext(),lstServices,acceptedRequesDetails);
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
