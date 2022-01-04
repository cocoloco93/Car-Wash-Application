package com.example.carwash.ui.auth;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.carwash.R;
import com.example.carwash.ui.dashboard.HomeViewModel;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;



public class SignOutFragment extends Fragment {

    private HomeViewModel homeViewModel;

    SharedPreferences sharedpreferences;
    public static final String mypreference = "mypref";
    public static final String Name = "nameKey";
    public static final String Email = "emailKey";
    public static final String TokenKey = "token";
    String Token;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_sign_out, container, false);


        sharedpreferences = this.requireContext().getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);

        attemptSignOut();

        return root;
    }

    public void attemptSignOut() {


        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("App Title");
        builder.setMessage("You will be logged out");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // You don't have to do anything here if you just
                // want it dismissed when clicked

                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putString(Name, "");
                editor.putString(Email, "");
                editor.putString(Token, "");
                editor.apply();

                String a = sharedpreferences.getString(Name,"");
                assert a != null;
                Log.v("Editor_Signout",a);

                String t = sharedpreferences.getString(TokenKey,"");
                assert t != null;
                Log.v("Editor_Signout_Token",t);


                Intent intent = new Intent(getContext(), SignInActivity.class);
                requireActivity().finish();
                startActivity(intent);
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        builder.show();



    }

}