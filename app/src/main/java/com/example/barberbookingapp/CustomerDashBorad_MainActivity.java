package com.example.barberbookingapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

public class CustomerDashBorad_MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        tabLayout = (TabLayout)findViewById(R.id.tablayout_id);
        viewPager = (ViewPager)findViewById(R.id.viewpager_id);
        adapter = new ViewPagerAdapter(getSupportFragmentManager());

        adapter.AddFragment(new Frag_1(),"");
        adapter.AddFragment(new Frag_2(),"");
        adapter.AddFragment(new Frag_3(),"");
        adapter.AddFragment(new Frag_4(),"");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setIcon(R.drawable.service);
        tabLayout.getTabAt(1).setIcon(R.drawable.request);
        tabLayout.getTabAt(2).setIcon(R.drawable.star);
        tabLayout.getTabAt(3).setIcon(R.drawable.complete);


     //   ActionBar actionBar = getSupportActionBar();
     //   assert actionBar != null;
     //   actionBar.setElevation(0);

    }
    public void check_About(View view)
    {
        Intent about = new Intent(CustomerDashBorad_MainActivity.this,info_account.class);
        startActivity(about);
    }
    public void check_History(View view)
    {
        Intent about = new Intent(CustomerDashBorad_MainActivity.this,History_data.class);
        startActivity(about);
    }
}