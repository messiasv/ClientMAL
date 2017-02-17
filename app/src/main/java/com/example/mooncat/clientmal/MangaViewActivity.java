package com.example.mooncat.clientmal;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class MangaViewActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    NetworkImageView mImageView;
    Manga mManga;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manga_view);
        mManga = (Manga) getIntent().getSerializableExtra("manga");
        setElements();
        findViewById(R.id.mangaViewDelete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setEnabled(false);
                mangaRequest(2);
            }
        });

        findViewById(R.id.mangaViewAdd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setEnabled(false);
                mangaRequest(0);
            }
        });
    }

    void setElements() {
        ((TextView) findViewById(R.id.mangaViewTitle)).setText(mManga.getTitle());
        ((TextView) findViewById(R.id.mangaViewStatus)).append(mManga.getStatus());
        ((TextView) findViewById(R.id.mangaViewType)).append(mManga.getType());
        ((TextView) findViewById(R.id.mangaViewVolumes))
                .append(mManga.getMyReadVolumes()
                        +" / "  +
                        (mManga.getVolumes().equals("0")?"?":mManga.getVolumes()));
        ((TextView) findViewById(R.id.mangaViewChapters))
                .append(mManga.getMyReadChapters()
                        +" / "  +
                        (mManga.getChapters().equals("0")?"?":mManga.getChapters()));
        Log.i("manga", "|" + mManga.getId()+"|");
        ((TextView) findViewById(R.id.mangaViewSynonyms)).append(mManga.getSynonyms());
        ((TextView) findViewById(R.id.mangaViewStart))
                .append(
                        Objects.equals(mManga.getStart(),
                                "0000-00-00") ?
                                "?" : mManga.getStart());
        ((TextView) findViewById(R.id.mangaViewEnd))
                .append(
                        Objects.equals(mManga.getEnd(),
                                "0000-00-00") ?
                                "?" : mManga.getEnd());

        Spinner score_spinner = (Spinner) findViewById(R.id.mangaViewMyScoreValue);
        score_spinner.setOnItemSelectedListener(this);
        setDefaultScorePrompt(score_spinner);
        Spinner status_spinner = (Spinner) findViewById(R.id.mangaViewMyStatusValue);
        status_spinner.setOnItemSelectedListener(this);
        setDefaultStatusPrompt(status_spinner);
        
        mImageView = (NetworkImageView) findViewById(R.id.mangaViewImage);
        ImageDownloader.downloadImage(mManga.getImage(),this,mImageView);
    }

    // mode: 0_add 1_update 2_delete
    void mangaRequest(int mode) {
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());

        String url = "";
        switch (mode) {
            case 0:
                try {
                    url = Tools.AddManga(mManga.getId());
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                break;
            case 2:
                url = Tools.DeleteManga(mManga.getId());
        }

        StringRequest deleteMangaRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(MangaViewActivity.this, response, Toast.LENGTH_LONG).show();
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();

                String creds = Tools.readFile(getApplicationContext(), "creds");
                params.put("Authorization", creds);
                return params;
            }
        };
        queue.add(deleteMangaRequest);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String test = adapterView.getItemAtPosition(i).toString();
        switch(test){
            case "Select":
                mManga.setMyScore("0");
                break;
            case "1 (Appalling)":
                mManga.setMyScore("1");
                break;
            case "2 (Horrible)":
                mManga.setMyScore("2");
                break;
            case "3 (Very Bad)":
                mManga.setMyScore("3");
                break;
            case "4 (Bad)":
                mManga.setMyScore("4");
                break;
            case "5 (Average)":
                mManga.setMyScore("5");
                break;
            case "6 (Fine)":
                mManga.setMyScore("6");
                break;
            case "7 (Good)":
                mManga.setMyScore("7");
                break;
            case "8 (Very Good)":
                mManga.setMyScore("8");
                break;
            case "9 (Great)":
                mManga.setMyScore("9");
                break;
            case "10 (Masterpiece)":
                mManga.setMyScore("10");
                break;
            case "Reading":
                mManga.setMyStatus("1");
                break;
            case "Completed":
                mManga.setMyStatus("2");
                break;
            case "On-Hold":
                mManga.setMyStatus("3");
                break;
            case "Dropped":
                mManga.setMyStatus("4");
                break;
            case "Plan to Read":
                mManga.setMyStatus("6");
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
       
    }
    
    public void setDefaultScorePrompt(Spinner spinner){
        if(mManga.getMyStatus().equals("")) {
            spinner.setEnabled(false);
        } else {
            switch (mManga.getMyScore()) {
                case "0":
                    spinner.setSelection(0);
                    break;
                case "1":
                    spinner.setSelection(1);
                    break;
                case "2":
                    spinner.setSelection(2);
                    break;
                case "3":
                    spinner.setSelection(3);
                    break;
                case "4":
                    spinner.setSelection(4);
                    break;
                case "5":
                    spinner.setSelection(5);
                    break;
                case "6":
                    spinner.setSelection(6);
                    break;
                case "7":
                    spinner.setSelection(7);
                    break;
                case "8":
                    spinner.setSelection(8);
                    break;
                case "9":
                    spinner.setSelection(9);
                    break;
                case "10":
                    spinner.setSelection(10);
                    break;
            }
        }
    }

    public void setDefaultStatusPrompt(Spinner spinner) {
        if(mManga.getMyStatus().equals("")) {
            spinner.setEnabled(false);
        } else {
            switch (Integer.parseInt(mManga.getMyStatus())) {
                case 1:
                    spinner.setSelection(0);
                    break;
                case 2:
                    spinner.setSelection(1);
                    break;
                case 3:
                    spinner.setSelection(2);
                    break;
                case 4:
                    spinner.setSelection(3);
                    break;
                case 6:
                    spinner.setSelection(4);
                    break;
            }
        }
    }
}
