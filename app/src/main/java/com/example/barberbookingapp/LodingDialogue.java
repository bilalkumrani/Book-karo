package com.example.barberbookingapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;


public class LodingDialogue {
     Activity activity;
     AlertDialog alertDialog;
     String message;
     TextView textView;

    LodingDialogue(Activity activity, String message)
    {
        this.message=message;
        this.activity=activity;
    }
    void startLoadingDialoge()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        LayoutInflater layoutInflater  =activity.getLayoutInflater();

        View view = layoutInflater.inflate(R.layout.loading_dialoge,null);
        textView = view.findViewById(R.id.loadingMsg);
        textView.setText(message);
        builder.setView(view);

        builder.setCancelable(false);
        alertDialog = builder.create();
        alertDialog.show();
    }
    void dismiss()
    {
        alertDialog.dismiss();
    }
}
