package com.example.carwash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ServiceCheckOut extends AppCompatActivity {
    private Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_check_out);
        b1 = (Button) findViewById(R.id.btmSetUp);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveToActivityE();

            }
        });
    }

    ;

    private void moveToActivityE() {
        Intent intent = new Intent(ServiceCheckOut.this, ExpenseData.class);
        startActivity(intent);
    }
}