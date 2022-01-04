package com.example.carwash.ui.gettingstart;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.carwash.MainActivity;
import com.example.carwash.R;

import androidx.appcompat.app.AppCompatActivity;

public class ManagerCreation extends AppCompatActivity {

    private Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_creation);

        b1 = (Button) findViewById(R.id.btmCreate4);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveToActivityMain();

            }
        });
    }

    private void moveToActivityMain() {
        Intent intent = new Intent(ManagerCreation.this, MainActivity.class);
        startActivity(intent);
    }
}