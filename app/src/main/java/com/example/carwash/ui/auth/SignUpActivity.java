package com.example.carwash.ui.auth;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.carwash.MainActivity;
import com.example.carwash.R;
import com.example.carwash.ui.gettingstart.ShopCreation;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

import androidx.appcompat.app.AppCompatActivity;

public class SignUpActivity extends AppCompatActivity {
    private Button b1;
    EditText edemail;
    EditText edpassword;
    EditText edcompassword;
    Button btsignup;
    String signup_url;

    SharedPreferences sharedpreferences;
    public static final String mypreference = "mypref";
    public static final String Name = "nameKey";
    public static final String Email = "emailKey";
    public static final String TokenKey = "token";
    String Token;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        edemail = (EditText) findViewById(R.id.edtextGmail);
        edpassword = (EditText) findViewById(R.id.edtextPassword);
        edcompassword = (EditText) findViewById(R.id.edtextComPassword);
        btsignup = (Button) findViewById(R.id.btmEditShop);

        signup_url = getString(R.string.create_user_url);

        btsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attemptSignUp();

            }
        });

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        sharedpreferences = getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);

    }

    private void moveToActivitySignUp() {
        Intent intent = new Intent(SignUpActivity.this, ShopCreation.class);
        startActivity(intent);
    }

    private void attemptSignUp() {

        // Store values at the time of the login attempt.
        final String text_username = edemail.getText().toString();
        final String text_password = edpassword.getText().toString();

        try {
            URL url = new URL(signup_url);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json");
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
            os.writeBytes(text1234);
            os.flush();
            os.close();

            final int code = con.getResponseCode();
            Log.v("code", String.valueOf(code));

            if (code == 504 || code == 406) {

                // Use the Builder class for convenient dialog construction
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("App Title");
                builder.setMessage("User already exist" + " \nCode : " + code);
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // You don't have to do anything here if you just
                        // want it dismissed when clicked
                    }
                });

                builder.show();

            } else if (code == 200) {

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
                    builder.setMessage("User is successfully created");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // You don't have to do anything here if you just
                            // want it dismissed when clicked


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
                    });

                    builder.show();

//                    // Use the Builder class for convenient dialog construction
//                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
//                    builder.setTitle("App Title");
//                    builder.setMessage("User is successfully created");
//                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                        public void onClick(DialogInterface dialog, int id) {
//                            // You don't have to do anything here if you just
//                            // want it dismissed when clicked
//                                Intent intent = new Intent(getApplicationContext(), ShopCreation.class);
//                                startActivity(intent);
//                        }
//                    });
//
//                    builder.show();

                }

            }





        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

}