package com.example.carwash;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import com.example.carwash.ui.auth.SignInActivity;

import java.util.Objects;

import androidx.appcompat.app.AppCompatActivity;

public class GettingStartActivity extends AppCompatActivity {
    Button sub;
    Animation frombottom, fromtop;
    ImageView imv;

    SharedPreferences sharedpreferences;
    public static final String mypreference = "mypref";
    public static final String Name = "nameKey";
    public static final String Email = "emailKey";
    public static final String TokenKey = "token";
    String Token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gettingstart);

        sub = (Button) findViewById(R.id.splashbtm);
        imv = (ImageView) findViewById(R.id.splashlogo);



        frombottom = AnimationUtils.loadAnimation(this,R.anim.frombottom);
        fromtop = AnimationUtils.loadAnimation(this,R.anim.fromtop);

        sub.setAnimation(frombottom);
        imv.setAnimation(fromtop);

        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                goToMainActivity();

            }
        }) ;

        sharedpreferences = getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);


        Token = sharedpreferences.getString(TokenKey,"");

        assert Token != null;

        if (!Objects.equals(sharedpreferences.getString(TokenKey,""), "")) {
            sub.setVisibility(View.GONE);
        } 



        fromtop.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (!Objects.equals(sharedpreferences.getString(TokenKey,""), "")) {
                    Log.v("KeyT", Token);
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    finish();
                    startActivity(intent);
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });



    };


    private void goToMainActivity() {
        Intent intent = new Intent(GettingStartActivity.this, SignInActivity.class);
        finish();
        startActivity(intent);
    }

}