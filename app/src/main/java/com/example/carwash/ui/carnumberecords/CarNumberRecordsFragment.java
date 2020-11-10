package com.example.carwash.ui.carnumberecords;

import android.graphics.Color;
import android.graphics.ColorFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.carwash.R;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CarNumberRecordsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CarNumberRecordsFragment extends Fragment {
     PieChart pie;
    float rainfall[] ={98.8f,123.8f,161.6f,24.2f,52f,35.4f,13.8f,73.4f,203.4f,240.4f,240.2f,159.7f};
    String monthName[] = {"Jan","Feb","Mar","April","May","June","July",
            "Aug","Sep","Oct","Nov","Dec"};


        // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CarNumberRecordsFragment() {
        // Required empty public constructor
    }


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CarNumberRecordsFragment.
     */


    // TODO: Rename and change types and number of parameters
    public static CarNumberRecordsFragment newInstance(String param1, String param2) {
        CarNumberRecordsFragment fragment = new CarNumberRecordsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);




        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_car_number_records, container, false);

        pie = (PieChart)view.findViewById(R.id.pie);
        List<PieEntry> pieEntries = new ArrayList<>();
        for (int i = 0; i < monthName.length; i++) {
            pieEntries.add(new PieEntry(rainfall[i], monthName[i]));

            PieDataSet PiedataSet = new PieDataSet(pieEntries,"");
            PiedataSet.setColors(ColorTemplate.JOYFUL_COLORS);
            PiedataSet.setValueTextColor(Color.BLACK);
            PiedataSet.setValueTextSize(10f);
            PieData data = new PieData(PiedataSet);

            // Design Pie
            pie.setCenterText("Total Numbers of Cars ");
            pie.setCenterTextRadiusPercent(80);
            pie.setData(data);
            pie.setHoleRadius(70);
            pie.highlightValues(null);
            pie.setTransparentCircleRadius(1);
            pie.setRotationEnabled(false);
            pie.animateY(800);




        }return view;
}
}
    
