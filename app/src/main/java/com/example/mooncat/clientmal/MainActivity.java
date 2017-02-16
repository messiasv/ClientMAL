package com.example.mooncat.clientmal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String username = Tools.readFile(getApplicationContext(), "username");
        String credentials = Tools.readFile(getApplicationContext() ,"creds");
        if (Objects.equals(username, "") || Objects.equals(credentials, "")) {
            Intent intent = new Intent(getApplicationContext(), UserLoginActivity.class);
            startActivity(intent);
            finish();
        } else {
            Intent intent = new Intent(getApplicationContext(), NavigationDrawerActivity.class);
            intent.putExtra("username", username);
            startActivity(intent);
            finish();
        }
    }
}
