package com.example.mooncat.clientmal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String username = readFile("username");
        String credentials = readFile("creds");
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

    String readFile(String filename) {
        StringBuffer fileContent = new StringBuffer("");
        FileInputStream fis;
        try {
            fis = openFileInput(filename);

            byte[] buffer = new byte[1024];
            int n;
            while ((n = fis.read(buffer)) != -1) {
                fileContent.append(new String(buffer, 0, n));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileContent.toString();
    }
}
