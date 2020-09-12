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

public class ApprovedRequests_Main_ServiceProvider extends Fragment {
    static  ArrayList<ApprovedRequestDataHolder> approvedRequestBtnDetails;
    ListView listView;
    static CustomListAdapter adapter;
    private String UserName;
    private String Password;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.aprovedrequests_service_provider_layout,container,false);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            Log.e("Here","Refreshing Approved requests");
            loadData();
        }
    }
    public void onViewCreated(View view, Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        listView = view.findViewById(R.id.dataListView);
        approvedRequestBtnDetails = new ArrayList<>();



    }
    public void loadData()
    {
        UserName = splash.tempData.getUserName();
        Password = splash.tempData.getPassword();
        ServiceProviderMainActivity.lodingDialogue.startLoadingDialoge();

        final FirebaseDatabase rootNode= FirebaseDatabase.getInstance();
        final DatabaseReference reference;
        reference = rootNode.getReference("ServiceProvider");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                approvedRequestBtnDetails.clear();

                boolean flag=true;
                int count = 0;
                for (DataSnapshot dsp : dataSnapshot.getChildren())
                {
                    if(UserName.equals(dsp.child("PersonalInfo").child("UserName").getValue().toString()))
                    {
                        Log.e("Here","Logged in for approved");
                        break;
                    }
                    count++;
                }

                DatabaseReference request;
                request = rootNode.getReference("ServiceProvider").child(count+"").child("Requests");
                request.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                        for (DataSnapshot dsp : dataSnapshot.getChildren())
                        {
                            Log.e("requester name",dsp.child("CustomerName").getValue().toString());
                            if("Approved".equals(dsp.child("Status").getValue().toString()))
                            {
                                approvedRequestBtnDetails.add(new ApprovedRequestDataHolder(dsp.child("CustomerName").getValue().toString(), dsp.child("AppointmentTimeDate").getValue().toString(), dsp.child("ServiceChoosed").getValue().toString()));
                            }
                        }

                        Log.e("Here","Seting data into listview of approved");
                        adapter = new CustomListAdapter(getActivity().getApplicationContext(),R.layout.aprovedrequests_data_holder_layout, approvedRequestBtnDetails);
                        listView.setAdapter(adapter);
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

    public class CustomListAdapter extends ArrayAdapter<ApprovedRequestDataHolder>
    {
        ArrayList<ApprovedRequestDataHolder> approvedRequestDataHolders;
        Context context;
        int resources;
        LayoutInflater layoutInflater;

        public CustomListAdapter(Context context, int resource, ArrayList<ApprovedRequestDataHolder> approvedRequestDataHolders)
        {
            super(context, resource, approvedRequestDataHolders);
            this.approvedRequestDataHolders = approvedRequestDataHolders;
            this.context = context;
            this.resources = resource;
            layoutInflater = (LayoutInflater)context.getSystemService (Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = layoutInflater.inflate(resources, null);
            }

            final Button topicBtn = convertView.findViewById(R.id.topicDisplay);
            final ApprovedRequestDataHolder approvedListItem = getItem(position);

            topicBtn.setText("Request of "+approvedListItem.getCustomerName()+" is approved for date: "+approvedListItem.getAppointmentTimeData());

            return convertView;
        }
    }

}
