package com.example.mooncat.clientmal;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private EditText usernameEntry;
    private Button sendButton;
    private String username;

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usernameEntry = (EditText) findViewById(R.id.MAL_login);
        sendButton = (Button) findViewById(R.id.send_button);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.this.setUsername(MainActivity.this.usernameEntry.getText().toString());
                new UsernameCheckTask().execute(MainActivity.this.username);
                Log.i(TAG, "username: " + username);
            }
        });
    }

    private class UsernameCheckTask extends AsyncTask {

        @Override
        protected Object doInBackground(Object[] params) {
            try {
                HttpURLConnection hUC = (HttpURLConnection) (new URL("https://myanimelist.net/profile/" + username)).openConnection();
                hUC.setRequestMethod("HEAD");
                hUC.connect();
                final int responseCode = hUC.getResponseCode();
                Log.d(TAG, "response code: " + responseCode);
                if(responseCode == HttpURLConnection.HTTP_OK) {
                    Log.i(TAG, "User exists やった !!");
                    return true;
                } else {
                    Log.i(TAG, "User doesn't exist !!");
                    publishProgress("username not found");
                    return false;
                }
            } catch (Exception e) {
                e.printStackTrace();
                Log.wtf(TAG,"HTTP mad error !!!");
                return false;
            }
        }

        @Override
        protected void onProgressUpdate(Object[] values) {
            Toast.makeText(MainActivity.this, (String)values[0], Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void onPostExecute(Object result) {
            if((boolean)result) {
                Intent intent = new Intent(getApplicationContext(), ViewPagerActivity.class);
                intent.putExtra("username", username);
                startActivity(intent);
                finish();
            }
        }
    }
}
