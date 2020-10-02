package com.example.carwash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class WorkOperation extends AppCompatActivity implements View.OnClickListener {
    private Button b1;
    private Button b2;
    private Button b3;
    private Button b4;
    private Button b5;
    private ImageButton i1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_operation);
        b1 = (Button) findViewById(R.id.btmEditShop);
        b2 = (Button) findViewById(R.id.btmCreatService);
        b3 = (Button) findViewById(R.id.btmEditService);
        b4 = (Button) findViewById(R.id.btmCreatItem);
        b5 = (Button) findViewById(R.id.btmEditItem);
        i1 = (ImageButton) findViewById(R.id.btmMenu);

        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
        b4.setOnClickListener(this);
        b5.setOnClickListener(this);
        i1.setOnClickListener(this);
    }
    @Override
    public void onClick(View v){
        if(v==b1){
            Intent b1Intent = new Intent(this, ShopCreation.class);
            startActivity(b1Intent);
        }
        else if (v==b2){
            Intent b2Intent = new Intent(this, ServiceCreation.class);
            startActivity(b2Intent);
        }
        else if (v==b3){
            Intent b3Intent = new Intent(this,ServiceList.class);
            startActivity(b3Intent);
        }
        else if (v==b4){
            Intent b4Intent = new Intent(this,OtherItemCreation.class);
            startActivity(b4Intent);
        }
        else if (v==b5){
            Intent b5Intent = new Intent(this,ItemList.class);
            startActivity(b5Intent);
        }
        else if (v==i1){
            Intent i1Intent = new Intent(this, InputDataActivity.class);
            startActivity(i1Intent);
        }
        }
    }