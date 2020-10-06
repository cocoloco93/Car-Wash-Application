package com.example.carwash.ui.auth;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.carwash.MainActivity;
import com.example.carwash.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

import androidx.appcompat.app.AppCompatActivity;

public class SignInActivity extends AppCompatActivity {

    EditText emid;
    EditText pwd;
    private Button b_sign_in;
    private TextView tv_sign_up;

    private String login_url;
    int code;
    InputStream code_err;
    SharedPreferences sharedpreferences;
    public static final String mypreference = "mypref";
    public static final String Name = "nameKey";
    public static final String Email = "emailKey";
    public static final String TokenKey = "token";
    String Token;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        login_url = getString(R.string.login_api_url);


        emid = (EditText) findViewById(R.id.edtextGmail);
        pwd = (EditText) findViewById(R.id.edtextPassword);


        b_sign_in = (Button) findViewById(R.id.btmEditShop);
        tv_sign_up = (TextView) findViewById(R.id.textSignUp);

        b_sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attemptSignIn();

            }
        });

        tv_sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveToActivitySignUp();

            }
        });


        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);




        sharedpreferences = getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);

        if (sharedpreferences.contains(Name)) {
            emid.setText(sharedpreferences.getString(Name, ""));
        }
        if (sharedpreferences.contains(Email)) {
            pwd.setText(sharedpreferences.getString(Email, ""));
        }
        if (sharedpreferences.contains(Token)) {
            Token = sharedpreferences.getString(TokenKey,"");
        }

        if (sharedpreferences.contains(Name)) {
            attemptSignIn();
        }

        emid.setError(null);
        pwd.setError(null);




    };

        private void moveToActivitySignUp(){
            Intent intent =new Intent(SignInActivity.this, SignUpActivity.class);
            startActivity(intent);
    }


    private void attemptSignIn() {


        // Store values at the time of the login attempt.
        final String text_username = emid.getText().toString();
        final String text_password = pwd.getText().toString();

        emid.setError(null);
        pwd.setError(null);

        // Check for a valid email address.
        if (TextUtils.isEmpty(text_username)) {
            emid.setError(getString(R.string.error_field_required));
        } else if (TextUtils.isEmpty(text_password)) {
            pwd.setError(getString(R.string.error_field_required));
        } else {

            int status = 0;


            try {
                URL url = new URL(login_url);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("POST");
                con.setRequestProperty("Content-Type", "application/json; charset=utf-8");
                con.connect();


                JSONObject cred = new JSONObject();
                cred.put("username", text_username);
                cred.put("password", text_password);


                String jsonInputString = cred.toString();

                String text1234 = "{\n" +
                        "    \"username\": \"" + text_username + "\",\n" +
                        "    \"password\": \"" + text_password + "\"\n" +
                        "}";


                Log.v("JSONSTring", jsonInputString);
                Log.v("JSONSTring123", text1234);

                DataOutputStream os = new DataOutputStream(con.getOutputStream());
                os.writeBytes(jsonInputString);
                os.flush();
                os.close();

                code = con.getResponseCode();
                Log.v("Error_code", String.valueOf(code));

                code_err = con.getErrorStream();


                if (code == 200) {

                    try (BufferedReader br = new BufferedReader(
                            new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8))) {
                        StringBuilder response = new StringBuilder();
                        String responseLine = null;
                        while ((responseLine = br.readLine()) != null) {
                            response.append(responseLine.trim());
                        }
                        System.out.println(response.toString());
                        Log.v("Login123", response.toString());

                        JSONObject answer = new JSONObject(response.toString());
                        final String token = answer.getString("token");

                        Log.v("msg", token);

                        // Use the Builder class for convenient dialog construction
                        AlertDialog.Builder builder = new AlertDialog.Builder(this);
                        builder.setTitle("App Title");
                        builder.setMessage("You are successfully logged in");
                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // You don't have to do anything here if you just
                                // want it dismissed when clicked


                                if (code == 200) {
                                    String n = text_username;
                                    String e = text_password;
                                    String t = token;

                                    Log.v("Message_Token", token);



                                    if (sharedpreferences.getString("Token", "").equals(token)) {

                                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                        startActivity(intent);
                                        Log.v("Message1234", token);

                                    } else {
                                        SharedPreferences.Editor editor = sharedpreferences.edit();
                                        editor.putString(Name, n);
                                        editor.putString(Email, e);
                                        editor.putString(TokenKey, t);
                                        editor.apply();

                                        Log.v("Editor_Name", Objects.requireNonNull(sharedpreferences.getString(Name, "")));
                                        Log.v("Editor_Token", Objects.requireNonNull(sharedpreferences.getString(TokenKey, "")));


                                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                        finish();
                                        startActivity(intent);
                                        Log.v("Message123", token);
                                    }
                                }
                            }
                        });

                        builder.show();


                    }

                } else {

                    throw new IOException(String.valueOf(con.getResponseCode()));

                }


            } catch (IOException | JSONException ie) {

                ie.getMessage();

                Log.e("Error_msg", Objects.requireNonNull(ie.getMessage()));

                if(ie.getMessage().equals("404")) {
                    emid.setError("User not found");
                } else if(ie.getMessage().equals("401")) {
                    pwd.setError("Wrong Password");
                }
            }


        }

    }



    private void attemptSignIn1() {


        // Store values at the time of the login attempt.
        final String text_username = emid.getText().toString();
        final String text_password = pwd.getText().toString();

        // Check for a valid email address.
        if (TextUtils.isEmpty(text_username)) {
            emid.setError(getString(R.string.error_field_required));
        } else if (TextUtils.isEmpty(text_password)) {
            pwd.setError(getString(R.string.error_field_required));
        } else {

            int status = 0;

            try {
                URL url = new URL(login_url);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("POST");
                con.setRequestProperty("Content-Type", "application/json; charset=utf-8");
                con.connect();


                JSONObject cred = new JSONObject();
                cred.put("username", text_username);
                cred.put("password", text_password);


                String jsonInputString = cred.toString();

                String text1234 = "{\n" +
                        "    \"username\": \"" + text_username + "\",\n" +
                        "    \"password\": \"" + text_password + "\"\n" +
                        "}";


                Log.v("JSONSTring", jsonInputString);
                Log.v("JSONSTring123", text1234);

                DataOutputStream os = new DataOutputStream(con.getOutputStream());
                os.writeBytes(jsonInputString);
                os.flush();
                os.close();

                code = con.getResponseCode();
                Log.v("Error_code", String.valueOf(code));

                code_err = con.getErrorStream();

                try (BufferedReader br = new BufferedReader(
                        new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8))) {
                    StringBuilder response = new StringBuilder();
                    String responseLine = null;
                    while ((responseLine = br.readLine()) != null) {
                        response.append(responseLine.trim());
                    }
                    System.out.println(response.toString());
                    Log.v("Login123", response.toString());

                    JSONObject answer = new JSONObject(response.toString());
                    final String token = answer.getString("token");

                    Log.v("msg", token);

                    // Use the Builder class for convenient dialog construction
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("App Title");
                    builder.setMessage("You are successfully logged in");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // You don't have to do anything here if you just
                            // want it dismissed when clicked


                            if (code == 200) {
                                String n = text_username;
                                String e = text_password;
                                String t = token;


                                if (sharedpreferences.getString("Token", "").equals(token)) {

                                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                    startActivity(intent);
                                    Log.v("Message123", token);

                                } else {
                                    SharedPreferences.Editor editor = sharedpreferences.edit();
                                    editor.putString(Name, n);
                                    editor.putString(Email, e);
                                    editor.putString(Token, t);
                                    editor.apply();

                                    Log.v("Editor_Name", Objects.requireNonNull(sharedpreferences.getString(Name, "")));

                                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                    finish();
                                    startActivity(intent);
                                    Log.v("Message123", token);
                                }
                            }
                        }
                    });

                    builder.show();


                }

            } catch (Exception ex) {

                ex.printStackTrace();

            }

        }

        }
    }



