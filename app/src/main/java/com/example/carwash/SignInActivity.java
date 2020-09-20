package com.example.carwash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SignInActivity extends AppCompatActivity {
    private Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        b1 = (Button) findViewById(R.id.btmEditShop);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveToActivitySignUp();

            }
        });
    };
        private void moveToActivitySignUp(){
            Intent intent =new Intent(SignInActivity.this, SignUpActivity.class);
            startActivity(intent);
        }


    }

