package com.example.carwash.ui.ShopInfo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.carwash.R;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

public class WorkOperationFragment extends Fragment implements View.OnClickListener {

    private Button b_editshop;
    private Button b_createservice;
    private Button b_editservice;
    private Button b_createitem;
    private Button b_edititem;
    private Button b_createmanager;
    private Button b_editemanager;

    public WorkOperationFragment() {
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_work_operation, container, false);

        b_editshop = (Button) root.findViewById(R.id.btmEditShop);
        b_createservice = (Button) root.findViewById(R.id.btmCreatService);
        b_editservice = (Button) root.findViewById(R.id.btmEditService);
        b_createitem = (Button) root.findViewById(R.id.btmCreatItem);
        b_edititem = (Button) root.findViewById(R.id.btmEditItem);
        b_createmanager = (Button) root.findViewById(R.id.btmCreatManager);
        b_editemanager = (Button) root.findViewById(R.id.btmEditManager);


        b_editshop.setOnClickListener(this);
        b_createservice.setOnClickListener(this);
        b_editservice.setOnClickListener(this);
        b_createitem.setOnClickListener(this);
        b_edititem.setOnClickListener(this);
        b_createmanager.setOnClickListener(this);
        b_editemanager.setOnClickListener(this);


        return root;
    }

    @Override
    public void onClick(View v) {
        if(v==b_editshop){
            Navigation.findNavController(v).navigate(R.id.nav_shop_creation);

        }
        else if (v==b_createservice){
            Navigation.findNavController(v).navigate(R.id.nav_service_creation);

        }
        else if (v==b_editservice){
            Navigation.findNavController(v).navigate(R.id.nav_service_edit);

        }
        else if (v==b_createitem){
            Navigation.findNavController(v).navigate(R.id.nav_other_items_creation);

        }
        else if (v==b_edititem){
            Navigation.findNavController(v).navigate(R.id.nav_other_items_list);

        }
        else if (v==b_createmanager) {
            Navigation.findNavController(v).navigate(R.id.nav_manager_creation);

        }
        else if (v==b_editemanager) {
            Navigation.findNavController(v).navigate(R.id.nav_manager_edit);

        }
    }
}