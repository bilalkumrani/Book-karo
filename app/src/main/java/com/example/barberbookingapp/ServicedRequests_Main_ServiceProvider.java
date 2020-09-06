package com.example.barberbookingapp;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ServicedRequests_Main_ServiceProvider extends Fragment {
    static ArrayList<ServicedRequestDataHolder> servicedRequestBtnDetails;
    static ListView listView;
    static CustomListAdapter adapter;
    private String UserName;
    private String Password;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.e("We are","in services");

        return inflater.inflate(R.layout.servicedrequests_service_provider_layout, container, false);
    }
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            Log.e("Here","Refreshing Serviced requests page");
            loadData();
        }
    }

    public void loadData()
    {
        Log.e("We are", "in serviced");
        final FirebaseDatabase rootNode = FirebaseDatabase.getInstance();
        final DatabaseReference reference;
        reference = rootNode.getReference("ServiceProvider");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                servicedRequestBtnDetails.clear();

                boolean flag = true;
                int count = 0;
                for (DataSnapshot dsp : dataSnapshot.getChildren()) {
                    if (UserName.equals(dsp.child("PersonalInfo").child("UserName").getValue().toString())) {
                        Log.e("Here", "Logged in for serviced");
                        break;
                    }
                    count++;
                }

                DatabaseReference request;
                request = rootNode.getReference("ServiceProvider").child(count + "").child("Requests");
                request.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                        int counter=0;
                        for (DataSnapshot dsp : dataSnapshot.getChildren()) {
                            Log.e("requester name", dsp.child("CustomerName").getValue().toString());
                            if ("Serviced".equals(dsp.child("Status").getValue().toString())) {
                                servicedRequestBtnDetails.add(new ServicedRequestDataHolder(dsp.child("CustomerName").getValue().toString(), dsp.child("AppointmentTimeDate").getValue().toString(), dsp.child("ServiceChoosed").getValue().toString()));
                                counter++;
                            }
                        }

                        Log.e("Here", "Seting data into listview of serviced");
                        adapter = new CustomListAdapter(getActivity().getApplicationContext(), R.layout.serviced_requests_data_holder_layout, servicedRequestBtnDetails);
                        listView.setAdapter(adapter);
                        splash.tempData.setNoOfServedServices(""+counter);
                        ServiceProviderMainActivity.lodingDialogue.dismiss();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
    public void onViewCreated(View view, Bundle savedInstanceState) {

        listView = view.findViewById(R.id.dataListView);
        servicedRequestBtnDetails = new ArrayList<>();


        UserName = splash.tempData.getUserName();
        Password = splash.tempData.getPassword();


    }

    public class CustomListAdapter extends ArrayAdapter<ServicedRequestDataHolder> {
        ArrayList<ServicedRequestDataHolder> servicedRequestDataHolders;
        Context context;
        int resources;
        LayoutInflater layoutInflater;

        public CustomListAdapter(Context context, int resource, ArrayList<ServicedRequestDataHolder> servicedRequestDataHolders) {
            super(context, resource, servicedRequestDataHolders);
            this.servicedRequestDataHolders = servicedRequestBtnDetails;
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
            final ServicedRequestDataHolder serviced = getItem(position);

            topicBtn.setText("Request of " + serviced.getCustomerName() + " is approved for date: " + serviced.getAppointmentTimeData());

            return convertView;
        }
    }
}


