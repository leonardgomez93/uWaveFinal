package com.example.leonardgomez.uwavefinal.uwavechat;

/**
 * Created by chrisoung on 3/30/18.
 */
import com.example.leonardgomez.uwavefinal.R;
import com.example.leonardgomez.uwavefinal.uwavechat.*;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import org.json.JSONException;
import org.json.JSONObject;

public class Login extends AppCompatActivity {
    Button registerButton, loginButton;
    TextView guestUser;
    EditText username;
    EditText password;
    String user;
    String pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        guestUser = (TextView) findViewById(R.id.guest_user);
        loginButton = (Button) findViewById(R.id.loginButton);
        registerButton = (Button) findViewById(R.id.registerButton);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this, Register.class));
            }
        });

        guestUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this, Guest.class));
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user = username.getText().toString();
                pass = password.getText().toString();

                if (user.equals("")) {
                    username.setError("Field can't be blank.");
                } else if (pass.equals("")) {
                    password.setError("Field can't be blank.");
                } else {
                    String url = "https://uwave-198615.firebaseio.com/users.json";
                    /*originally user.json*/
                    final ProgressDialog pd = new ProgressDialog(Login.this);
                    pd.setMessage("Loading...");
                    pd.show();

                    StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String s) {

                            if (s.equals("null")) {
                                Toast.makeText(Login.this, "User not found.", Toast.LENGTH_LONG).show();
                            } else {

                                try {
                                    JSONObject obj = new JSONObject(s);
                                    if (!obj.has(user)) {
                                        Toast.makeText(Login.this, "User not found.", Toast.LENGTH_LONG).show();

                                    }
                                    else if (obj.getJSONObject(user).getString("password").equals(pass)) {

                                        UserDetails.username = user;
                                        UserDetails.password = pass;
                                        startActivity(new Intent(Login.this, Users.class));
                                        Toast.makeText(Login.this,"Login successful! Welcome back, " + UserDetails.username + "!",Toast.LENGTH_LONG).show();


                                    }
                                    else {
                                        Toast.makeText(Login.this, "Incorrect password.", Toast.LENGTH_LONG).show();
                                    }
                                }

                                catch (JSONException e) {
                                    e.printStackTrace();

                                }
                            }

                            pd.dismiss();
                        }

                        }, new Response.ErrorListener() {

                        @Override
                        public void onErrorResponse(VolleyError volleyError) {
                            System.out.println("" + volleyError);
                            pd.dismiss();
                        }
                    });


                    RequestQueue rQueue = Volley.newRequestQueue(Login.this);
                    rQueue.add(request);
                }

            }
        });
    }
}
