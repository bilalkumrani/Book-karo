package com.example.barberbookingapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.barberbookingapp.main.SectionsPagerAdapter_Main_ServiceProvider;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ServiceProviderMainActivity extends AppCompatActivity {
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    static LodingDialogue lodingDialogue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity_service_provider_layout);
        SectionsPagerAdapter_Main_ServiceProvider sectionsPagerAdapterMainServiceProvider = new SectionsPagerAdapter_Main_ServiceProvider(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapterMainServiceProvider);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        Toast.makeText(getApplicationContext(),splash.tempData.getUserName()+splash.tempData.getPassword(),Toast.LENGTH_SHORT).show();
        lodingDialogue = new LodingDialogue(this,"Fetching Data. Please wait");

    }
    public void openMyAccount(View view)
    {
        Intent openMyAccount = new Intent(ServiceProviderMainActivity.this,Service_Provider_Account_Details.class);
        startActivity(openMyAccount);

    }
}