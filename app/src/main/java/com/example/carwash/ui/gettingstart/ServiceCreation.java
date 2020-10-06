package com.example.carwash.ui.gettingstart;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.carwash.R;

import androidx.appcompat.app.AppCompatActivity;

public class ServiceCreation extends AppCompatActivity {

    private Button b1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_creation);

        b1 = (Button) findViewById(R.id.btmCreate);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveToActivityOtherItemCreation();

            }
        });
    }

    private void moveToActivityOtherItemCreation() {
        Intent intent = new Intent(ServiceCreation.this, OtherItemCreation.class);
        startActivity(intent);
    }

    }