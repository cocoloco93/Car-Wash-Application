package com.example.carwash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ExpenseData extends AppCompatActivity {
    private Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense_data);
        b1 = (Button) findViewById(R.id.btmCreate3);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveToActivityE();

            }
        });
    }

    ;

    private void moveToActivityE() {
        Intent intent = new Intent(ExpenseData.this, ExpenseCheckout.class);
        startActivity(intent);
    }
    }