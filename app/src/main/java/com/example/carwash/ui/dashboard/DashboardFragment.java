package com.example.carwash.ui.dashboard;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.carwash.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import java.util.ArrayList;
import java.util.List;

public class DashboardFragment extends Fragment {
    LineChart lineChart;
    BarChart barChart;
    float rainfall[] ={98.8f,123.8f,161.6f,24.2f,52f,35.4f};
    String monthName[] = {"Jan","Feb","Mar","April","May","June"};


    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);

        lineChart = root.findViewById(R.id.linecart);
        barChart = root.findViewById(R.id.bar);
        LineDataSet lineDataSet = new LineDataSet(lineChartDataSet(),"data set");
        ArrayList<ILineDataSet> iLineDataSets = new ArrayList<>();
        iLineDataSets.add(lineDataSet);

        LineData lineData = new LineData(iLineDataSets);
        lineChart.setData(lineData);
        lineChart.invalidate();

        BarDataSet barDataSet = new BarDataSet(barchartDataSet(), "data set1");
        BarData barData = new BarData();
        barData.addDataSet(barDataSet);
        barChart.setData(barData);
        barChart.invalidate();

        setuppieChart(root);
        return root;
    }

    private ArrayList<Entry> lineChartDataSet(){
        ArrayList<Entry> dataSet = new ArrayList<Entry>();


        dataSet.add(new Entry(15,10));
        dataSet.add(new Entry(15,20));
        dataSet.add(new Entry(15,30));
        dataSet.add(new Entry(15,20));
        dataSet.add(new Entry(15,70));
        dataSet.add(new Entry(15,10));
        dataSet.add(new Entry(15,10));
        return dataSet;


    }

    private ArrayList<BarEntry> barchartDataSet() {
        ArrayList<BarEntry> dataSet = new ArrayList<>();


        dataSet.add(new BarEntry(0, 10));
        dataSet.add(new BarEntry(1, 10));
        dataSet.add(new BarEntry(2, 1));
        dataSet.add(new BarEntry(3, 10));
        dataSet.add(new BarEntry(4, 10));

        return dataSet;

    }
    private void setuppieChart(View V) {
        //Populating a list of PieEntries:
       /* List<PieEntry> pieEntries = new ArrayList<>();
        for (int i = 0; i < monthName.length; i++) {
            pieEntries.add(new PieEntry(rainfall[i], monthName[i]));
        }*/

        List<PieEntry> pieEntries = new ArrayList<>();
        for (int i = 0; i < monthName.length; i++) {
            pieEntries.add(new PieEntry(rainfall[i], monthName[i]));

            // Create Pie data


            PieDataSet PiedataSet = new PieDataSet(pieEntries,"");
            PiedataSet.setColors(new int[]{ Color.rgb(64, 89, 128), Color.rgb(149, 165, 124), Color.rgb(217, 184, 162),
                    Color.rgb(191, 134, 134), Color.rgb(179, 48, 80), Color.rgb(100, 100, 128)});
            PiedataSet.setValueTextColor(Color.BLACK);
            PiedataSet.setValueTextSize(10f);
            PieData data = new PieData(PiedataSet);

            // Design Pie
            PieChart pieChart = V.findViewById(R.id.pie);
            pieChart.setCenterText("Total Numbers of Cars ");
            pieChart.setData(data);
            pieChart.setHoleRadius(60);
            pieChart.highlightValues(null);
            pieChart.setTransparentCircleRadius(1);
            pieChart.setRotationEnabled(false);
            pieChart.animateY(800);


        }
    }
}