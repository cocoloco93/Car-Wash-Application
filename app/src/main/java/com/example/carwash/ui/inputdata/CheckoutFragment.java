package com.example.carwash.ui.inputdata;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.carwash.R;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

public class CheckoutFragment extends Fragment {

    public CheckoutFragment() {

    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_check_out, container, false);

        final TextView textView = root.findViewById(R.id.text_home);


        final Button b1 = root.findViewById(R.id.btmSetUp);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveToActivityCheckOut(v);

            }
        });

        return root;
    }

    private void moveToActivityCheckOut(View v) {
        Navigation.findNavController(v).navigate(R.id.nav_dashboard);

    }
}