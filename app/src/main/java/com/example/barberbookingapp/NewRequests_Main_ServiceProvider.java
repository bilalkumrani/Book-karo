package com.example.barberbookingapp;

import android.app.Activity;
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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class NewRequests_Main_ServiceProvider extends Fragment {
   public  static ArrayList<NewRequestDataHolder> newRequetsBtnDetails;
    ListView listView;
    static CustomListAdapter adapter;
    String UserName,Password;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.newrequests_service_provider_layout,container,false);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            Log.e("Here","Refreshing new requests");
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
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                newRequetsBtnDetails.clear();
                boolean flag=true;
                int count = 0;
                for (DataSnapshot dsp : dataSnapshot.getChildren())
                {
                    if(UserName.equals(dsp.child("PersonalInfo").child("UserName").getValue().toString()))
                    {
                        Log.e("Here","Logged in");
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
                            Log.e("Customer name",dsp.child("CustomerName").getValue().toString());
                            if("New".equals(dsp.child("Status").getValue().toString()))
                            {
                                newRequetsBtnDetails.add(new NewRequestDataHolder(dsp.child("CustomerName").getValue().toString(), dsp.child("RequestTimeDate").getValue().toString(), dsp.child("ServiceChoosed").getValue().toString(),dsp.child("Phone").getValue().toString(),dsp.child("RequestIndex").getValue().toString()));
                            }
                        }

                        Log.e("Here","Seting data into listview");
                        adapter = new CustomListAdapter(getActivity().getApplicationContext(),R.layout.new_request_data_holder_layout, newRequetsBtnDetails);
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

    public void onViewCreated(View view, Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        listView = view.findViewById(R.id.dataListView);
        newRequetsBtnDetails = new ArrayList<>();

        ServiceProviderMainActivity.lodingDialogue.startLoadingDialoge();

        UserName = splash.tempData.getUserName();
        Password = splash.tempData.getPassword();



    }

    public class CustomListAdapter extends ArrayAdapter<NewRequestDataHolder>
    {
        ArrayList<NewRequestDataHolder> newRequetsBtnDetails;
        Context context;
        int resources;
        LayoutInflater layoutInflater;

        public CustomListAdapter(Context context, int resource, ArrayList<NewRequestDataHolder> newRequetsBtnDetails)
        {
            super(context, resource, newRequetsBtnDetails);
            this.newRequetsBtnDetails = newRequetsBtnDetails;
            this.context = context;
            this.resources = resource;
            layoutInflater = (LayoutInflater)context.getSystemService (Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = layoutInflater.inflate(resources, null);
            }

            final Button topicBtn = convertView.findViewById(R.id.topicDisplay);
            final Button open = convertView.findViewById(R.id.newRequestDetails);
            final NewRequestDataHolder requestListItem = getItem(position);

            topicBtn.setText(requestListItem.getCustomerName()+" Has requested for "+requestListItem.getServiceChoosed());

            open.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent openApprovalPortal = new Intent(getActivity(),ApproveCustomerRequest.class);
                    openApprovalPortal.putExtra("RequestIndexInList",position);
                    startActivity(openApprovalPortal);
                }
            });

            return convertView;
        }
    }


}
