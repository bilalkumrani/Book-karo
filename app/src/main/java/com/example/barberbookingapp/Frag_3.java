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

public class Frag_3 extends Fragment {
    View v;
    private RecyclerView myrecyclerview;
    private List<Services> lstFav;
    ArrayList <ServiceAvailbleForCustomer> serviceAvailbleForCustomers;

    public Frag_3() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.frag_3,container,false);
        myrecyclerview = (RecyclerView)v.findViewById(R.id.fav_recyclerview);
        RecyclerViewAdapeter recyclerAdapter = new RecyclerViewAdapeter(getContext(),lstFav,serviceAvailbleForCustomers);
        myrecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        myrecyclerview.setAdapter(recyclerAdapter);
        return v;


    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lstFav = new ArrayList<>();
        serviceAvailbleForCustomers = new ArrayList<>();

        lstFav.add(new Services("Hair","200",R.drawable.hair));
        lstFav.add(new Services("Beard","100",R.drawable.beard));
        lstFav.add(new Services("Facial","500",R.drawable.barber));
        lstFav.add(new Services("Other services","All categories",R.drawable.serviceee));



    }
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            Log.e("Here","Refreshing frag 3");

        }
    }

}
