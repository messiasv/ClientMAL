package com.example.mooncat.clientmal;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class UserLoginActivity extends AppCompatActivity {

    private static final String FILENAME = "creds";
    private static final String USERNAME = "username";
    private String mUsername;
    private EditText usernameEntry;
    private EditText passwordEntry;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);

        usernameEntry = (EditText) findViewById(R.id.MAL_login);
        passwordEntry = (EditText) findViewById(R.id.MAL_password);
        loginButton = (Button) findViewById(R.id.login_button);

        ((CheckBox) findViewById(R.id.check_box_show_passwd)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    passwordEntry.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    passwordEntry.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setEnabled(false);
                mUsername = usernameEntry.getText().toString();
                verifyCredentials(encodeCredentials(mUsername, passwordEntry.getText().toString()));
                passwordEntry.setText("");
            }
        });
    }

    private String encodeCredentials(final String username, final String password) {
        String creds = String.format("%s:%s", username, password);
        String auth = "Basic " + Base64.encodeToString(creds.getBytes(), Base64.NO_WRAP);
        return auth;
    }

    protected void verifyCredentials(final String creds) {
        RequestQueue queue = Volley.newRequestQueue(this);


        String url;
        url = Tools.verifyCredentialsRequest();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        FileOutputStream fos;
                        FileOutputStream fosU;
                        try {
                            fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);
                            fos.write(creds.getBytes());
                            fos.close();
                            fosU = openFileOutput(USERNAME, Context.MODE_PRIVATE);
                            fosU.write(mUsername.getBytes());
                            fosU.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        Intent intent = new Intent(getApplicationContext(), NavigationDrawerActivity.class);
                        intent.putExtra("username", mUsername);
                        startActivity(intent);
                        finish();
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                loginButton.setEnabled(true);
                Toast.makeText(UserLoginActivity.this,
                        R.string.incorrect_creds,
                        Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();
                params.put("Authorization", creds);
                return params;
            }
        };
        queue.add(stringRequest);
    }
}
