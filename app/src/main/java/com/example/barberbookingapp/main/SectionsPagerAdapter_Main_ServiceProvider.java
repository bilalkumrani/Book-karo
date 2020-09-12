package com.example.barberbookingapp.main;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.barberbookingapp.R;
import com.example.barberbookingapp.ApprovedRequests_Main_ServiceProvider;
import com.example.barberbookingapp.NewRequests_Main_ServiceProvider;
import com.example.barberbookingapp.ServicedRequests_Main_ServiceProvider;


/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter_Main_ServiceProvider extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tab_text_1, R.string.tab_text_2,R.string.tab_text_3};
    private final Context mContext;

    public SectionsPagerAdapter_Main_ServiceProvider(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {

        Fragment fragment = null;

        switch (position)
        {
            case 0:

                fragment = new NewRequests_Main_ServiceProvider();

                break;
            case 1:

                fragment = new ServicedRequests_Main_ServiceProvider();
                break;

            case 2:
                fragment = new ApprovedRequests_Main_ServiceProvider();
                break;

        }

        return  fragment;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        // Show 2 total pages.
        return 3;
    }
}