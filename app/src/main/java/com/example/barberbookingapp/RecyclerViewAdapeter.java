package com.example.barberbookingapp;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewAdapeter extends RecyclerView.Adapter<RecyclerViewAdapeter.MyViewHolder> {
    Context mcontext;
    List<Services> mData;
    Dialog mydialog;

    List<ServiceAvailbleForCustomer> serviceAvailbleForCustomers;

    public RecyclerViewAdapeter(Context mcontext, List<Services> mData,List<ServiceAvailbleForCustomer>  serviceAvailbleForCustomers) {
        this.mcontext = mcontext;
        this.mData = mData;
        this.serviceAvailbleForCustomers=serviceAvailbleForCustomers;
    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View v;
        v = LayoutInflater.from(mcontext).inflate(R.layout.services_list,parent,false);
        final MyViewHolder vHolder = new MyViewHolder(v);

          /*
        mydialog = new Dialog(mcontext);
        mydialog.setContentView(R.layout.dailog);
        mydialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));




        vHolder.item_service.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {


                //(this, MainActivity.class);


                dailog_name_tv.setText(mData.get(vHolder.getAdapterPosition()).getName());
                dailog_desc_tv.setText(mData.get(vHolder.getAdapterPosition()).getDesc());
                dailoge_image.setImageResource(mData.get(vHolder.getAdapterPosition()).getPhoto());

                mydialog.show();


            }
        });





         */
        return vHolder;


    }


    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position)
    {

        holder.tv_name.setText(mData.get(position).getName());
        holder.tv_disc.setText(mData.get(position).getDesc());
        holder.img.setImageResource(mData.get(position).getPhoto());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {



            Log.d("check", "onClick: clicked on: " +"img");

            //TODO: we are going to fly

            Intent intent = new Intent(mcontext, pop_up.class);
            intent.putExtra("Name",mData.get(holder.getAdapterPosition()).getName());
            intent.putExtra("Detail",mData.get(holder.getAdapterPosition()).getDesc());
            intent.putExtra("ServiceProviderLocation",serviceAvailbleForCustomers.get(holder.getAdapterPosition()).getServiceProvciderLocation());
            intent.putExtra("ServiceProviderIndex",serviceAvailbleForCustomers.get(holder.getAdapterPosition()).getServiceProvciderIndex());

            mcontext.startActivity(intent);


            mcontext.startActivity(intent);
        }
    });

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }



    public static class MyViewHolder extends RecyclerView.ViewHolder{
       private TextView tv_name;
       private TextView tv_disc;
       private ImageView img;
       private LinearLayout item_service;

        public MyViewHolder(@NonNull View itemView)
        {
            super(itemView);
            item_service = (LinearLayout)itemView.findViewById(R.id.service_list_id);
            tv_name = (TextView)itemView.findViewById(R.id.services_name);
            tv_disc = (TextView)itemView.findViewById(R.id.services_desc);
            img = (ImageView) itemView.findViewById(R.id.img_services);
        }
    }



}
