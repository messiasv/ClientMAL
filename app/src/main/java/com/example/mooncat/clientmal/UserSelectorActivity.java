package com.example.mooncat.clientmal;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
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

public class UserSelectorActivity extends AppCompatActivity {

    private static final String FILENAME = "creds";
    private EditText usernameEntry;
    private EditText passwordEntry;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_selector);

        usernameEntry = (EditText) findViewById(R.id.MAL_login);
        passwordEntry = (EditText) findViewById(R.id.MAL_password);
        loginButton = (Button) findViewById(R.id.login_button);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setEnabled(false);
                verifyCredentials(usernameEntry.getText().toString(), passwordEntry.getText().toString());
                passwordEntry.setText("");
            }
        });
    }

    protected void verifyCredentials(final String username, final String password) {
        RequestQueue queue = Volley.newRequestQueue(this);


        String url;
        url = Tools.verifyCredentialsRequest();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        FileOutputStream fos;
                        try {
                            fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);
                            String creds = String.format("%s:%s", username, password);
                            String auth = "Basic " + Base64.encodeToString(creds.getBytes(), Base64.NO_WRAP);
                            fos.write(auth.getBytes());
                            fos.close();
                            creds = null;
                            auth = null;
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        Intent intent = new Intent(getApplicationContext(), NavigationDrawerActivity.class);
                        intent.putExtra("username", username);
                        startActivity(intent);
                        finish();
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                loginButton.setEnabled(true);
                Toast.makeText(UserSelectorActivity.this,
                        R.string.incorrect_creds,
                        Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();
                String creds = String.format("%s:%s", username, password);
                String auth = "Basic " + Base64.encodeToString(creds.getBytes(), Base64.NO_WRAP);
                params.put("Authorization", auth);
                return params;
            }
        };
        queue.add(stringRequest);
    }
}
