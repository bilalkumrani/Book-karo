package com.example.barberbookingapp;

import android.os.Bundle;
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

public class Frag_History extends Fragment {

    View v;
    private RecyclerView myrecyclerview;
    private List<Services> lstServices;
    ArrayList <ServiceAvailbleForCustomer> serviceAvailbleForCustomers;

    public Frag_History() {
    }

    

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fraf_history,container,false);
        myrecyclerview = (RecyclerView)v.findViewById(R.id.service_recyclerview);
        RecyclerViewAdapeter recyclerAdapter = new RecyclerViewAdapeter(getContext(),lstServices,serviceAvailbleForCustomers);
        myrecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        myrecyclerview.setAdapter(recyclerAdapter);
        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        lstServices = new ArrayList<>();
        serviceAvailbleForCustomers = new ArrayList<>();
        lstServices.add(new Services("Beard","You can get all beard styles with discount offer ",R.drawable.beard));
        lstServices.add(new Services("Facial","Full facial mask including all other special stuff",R.drawable.barber));
    }
}
