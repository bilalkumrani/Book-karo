package com.example.barberbookingapp.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.barberbookingapp.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment_Main_ServiceProvider extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    private PageViewModel_Main_ServiceProvider pageViewModelMainServiceProvider;

    public static PlaceholderFragment_Main_ServiceProvider newInstance(int index) {
        PlaceholderFragment_Main_ServiceProvider fragment = new PlaceholderFragment_Main_ServiceProvider();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageViewModelMainServiceProvider = ViewModelProviders.of(this).get(PageViewModel_Main_ServiceProvider.class);
        int index = 1;
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
        }
        pageViewModelMainServiceProvider.setIndex(index);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.main_activity_service_provider_layout, container, false);
        final TextView textView = root.findViewById(R.id.section_label);
        pageViewModelMainServiceProvider.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}