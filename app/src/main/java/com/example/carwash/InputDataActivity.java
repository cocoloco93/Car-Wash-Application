package com.example.carwash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class InputDataActivity extends AppCompatActivity {
    private Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_data);
        b1 = (Button) findViewById(R.id.btmCreate2);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveToActivityS();

            }
        });
    };
    private void moveToActivityS(){
        Intent intent =new Intent(InputDataActivity.this, Checkout.class);
        startActivity(intent);
    }


}
