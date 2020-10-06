package com.example.carwash.ui.ShopInfo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.carwash.R;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class ServiceCreationFragment extends Fragment {


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_service_creation, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        return root;
    }
}