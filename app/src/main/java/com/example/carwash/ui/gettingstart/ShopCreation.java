package com.example.carwash.ui.gettingstart;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.carwash.R;

import androidx.appcompat.app.AppCompatActivity;

public class ShopCreation extends AppCompatActivity {

    private Button b1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_creation);

        b1 = (Button) findViewById(R.id.btmSetUp);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveToActivityServiceCreation();

            }
        });
    }

    private void moveToActivityServiceCreation() {
        Intent intent = new Intent(ShopCreation.this, ServiceCreation.class);
        startActivity(intent);
    }
}