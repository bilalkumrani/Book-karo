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

public class Frag_1 extends Fragment {

    View v;
    private RecyclerView myrecyclerview;
    private List<Services> lstServices;
    ArrayList <ServiceAvailbleForCustomer> serviceAvailbleForCustomers;
    String UserName,Password;
    public Frag_1() {
    }

    

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.frag_1,container,false);
        myrecyclerview = (RecyclerView)v.findViewById(R.id.service_recyclerview);

        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        lstServices = new ArrayList<>();
        serviceAvailbleForCustomers = new ArrayList<>();

         }
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            Log.e("Here","Refreshing Services ");
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
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                lstServices.clear();

                DatabaseReference services;

                String ServiceProvciderName,ServiceProvciderIndex,ServiceProvciderLocation,ServiceProvciderAvgRating;

                for (DataSnapshot snapshot: dataSnapshot.getChildren())
                {
                    ServiceProvciderName = snapshot.child("PersonalInfo").child("Name").getValue().toString();
                    ServiceProvciderIndex = snapshot.child("Index").getValue().toString();
                    ServiceProvciderLocation = snapshot.child("PersonalInfo").child("MyLocation").getValue().toString();
                    ServiceProvciderAvgRating = snapshot.child("PersonalInfo").child("AvgRating").getValue().toString();

                    services = snapshot.child("Services").getRef();

                    final String finalServiceProvciderName1 = ServiceProvciderName;
                    final String finalServiceProvciderIndex = ServiceProvciderIndex;
                    final String finalServiceProvciderLocation = ServiceProvciderLocation;
                    final String finalServiceProvciderAvgRating = ServiceProvciderAvgRating;
                    services.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            Log.e("inside inner loop","bb");
                            for (DataSnapshot snapshot:dataSnapshot.getChildren())
                            {
                                Log.e("inside inner loop","bb ejeiwojew");

                                String service = snapshot.child("ServiceName").getValue().toString();
                                String status = snapshot.child("ServiceStatus").getValue().toString();
                                if(status.equals("ON"))
                                {

                                    serviceAvailbleForCustomers.add(new ServiceAvailbleForCustomer(service, finalServiceProvciderName1, finalServiceProvciderIndex, finalServiceProvciderLocation, finalServiceProvciderAvgRating));

                                    Log.e("Ser ",service);




                                    if (service=="Hair")
                                    {
                                        lstServices.add(new Services(service,"Service provided by "+finalServiceProvciderName1+", He has rating :( "+finalServiceProvciderAvgRating+" ) ",R.drawable.hair));

                                        Log.e("In hair","");
                                    }
                                    else if (service.equals("Beard"))
                                    {

                                        Log.e("In beard","");
                                        lstServices.add(new Services("Beard","Service provided by "+finalServiceProvciderName1+", He has rating :( "+finalServiceProvciderAvgRating+" ) ",R.drawable.beard));
                                    }
                                    else if (service.equals("Facial"))
                                    {

                                        Log.e("In faical","");
                                        lstServices.add(new Services("Facial","Service provided by "+finalServiceProvciderName1+", He has rating :( "+finalServiceProvciderAvgRating+" ) ",R.drawable.barber));

                                    }
                                    else
                                    {

                                        Log.e("In facial" ,"");
                                        lstServices.add(new Services(service,"Service provided by "+finalServiceProvciderName1+", He has rating :( "+finalServiceProvciderAvgRating+" ) ",R.drawable.serviceee));
                                    }

                                    RecyclerViewAdapeter recyclerAdapter = new RecyclerViewAdapeter(getContext(),lstServices,serviceAvailbleForCustomers);
                                    myrecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
                                    myrecyclerview.setAdapter(recyclerAdapter);



                                }
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                        }


                    });
                }

                Log.e("Value of total service providers", String.valueOf(dataSnapshot.getChildrenCount()));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }


}
