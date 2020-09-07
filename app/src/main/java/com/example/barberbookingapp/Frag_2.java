package com.example.barberbookingapp;



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
        RecyclerViewAdapeter recyclerAdapter = new RecyclerViewAdapeter(getContext(),lstServices,serviceAvailbleForCustomers);
        myrecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        myrecyclerview.setAdapter(recyclerAdapter);
        return v;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        serviceAvailbleForCustomers = new ArrayList<>();

        lstServices = new ArrayList<>();

        lstServices.add(new Services("Request 1","For what service",R.drawable.request));
        lstServices.add(new Services("Request 2","For what service",R.drawable.request));
        lstServices.add(new Services("Request 3","For what service",R.drawable.request));
        lstServices.add(new Services("Request 4","For what service",R.drawable.request));
        lstServices.add(new Services("Request 1","For what service",R.drawable.request));
        lstServices.add(new Services("Request 2","For what service",R.drawable.request));
        lstServices.add(new Services("Request 3","For what service",R.drawable.request));
        lstServices.add(new Services("Request 4","For what service",R.drawable.request));
        lstServices.add(new Services("Request 1","For what service",R.drawable.request));
        lstServices.add(new Services("Request 2","For what service",R.drawable.request));
        lstServices.add(new Services("Request 3","For what service",R.drawable.request));
        lstServices.add(new Services("Request 4","For what service",R.drawable.request));


    }
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            Log.e("Here","Refreshing requests" );

        }
    }


}
