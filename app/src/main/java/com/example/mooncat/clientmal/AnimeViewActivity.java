package com.example.mooncat.clientmal;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
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

public class AnimeViewActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    NetworkImageView mImageView;
    Anime mAnime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anime_view);
        mAnime = (Anime) getIntent().getSerializableExtra("anime");
        setElements();

        findViewById(R.id.animeViewDelete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setEnabled(false);
                animeRequest(2);
            }
        });

        findViewById(R.id.animeViewAdd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setEnabled(false);
                animeRequest(0);
            }
        });
    }

    void setElements() {
        ((TextView) findViewById(R.id.animeViewTitle)).setText(mAnime.getTitle());
        ((TextView) findViewById(R.id.animeViewStatus)).append(mAnime.getStatus());
        ((TextView) findViewById(R.id.animeViewType)).append(mAnime.getType());

        EditText editText = (EditText)findViewById(R.id.animeViewMyEpisodes);
        editText.setText(mAnime.getMyWatchedEpisodes());

        ((TextView) findViewById(R.id.animeViewEpisodes)).append((mAnime.getEpisodes().equals("0") ? "?" : mAnime.getEpisodes()) );
        ((TextView) findViewById(R.id.animeViewSynonyms)).append(mAnime.getSynonyms());
        ((TextView) findViewById(R.id.animeViewStart)).append(mAnime.getStart().equals("0000-00-00") ? "?" : mAnime.getStart());
        ((TextView) findViewById(R.id.animeViewEnd)).append(mAnime.getEnd().equals("0000-00-00") ? "?" : mAnime.getEnd());

        Spinner score_spinner = (Spinner) findViewById(R.id.animeViewMyScoreValue);
        score_spinner.setOnItemSelectedListener(this);
        setDefaultScorePrompt(score_spinner);
        Spinner status_spinner = (Spinner) findViewById(R.id.animeViewMyStatusValue);
        status_spinner.setOnItemSelectedListener(this);
        setDefaultStatusPrompt(status_spinner);

        mImageView = (NetworkImageView) findViewById(R.id.animeViewImage);
        ImageDownloader.downloadImage(mAnime.getImage(),this,mImageView);
    }
    // mode: 0_add 1_update 2_delete
    void animeRequest(int mode) {
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());

        String url = "";
        switch (mode) {
            case 0:
                try {
                    url = Tools.AddAnime(mAnime.getId());
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                break;
            case 2:
                url = Tools.DeleteAnime(mAnime.getId());
        }
        StringRequest animeRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(AnimeViewActivity.this, response, Toast.LENGTH_LONG).show();
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
        queue.add(animeRequest);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
      String test = adapterView.getItemAtPosition(i).toString();
        switch(test){
            case "Select":
                mAnime.setMyScore("0");
                break;
            case "1 (Appalling)":
                mAnime.setMyScore("1");
                break;
            case "2 (Horrible)":
                mAnime.setMyScore("2");
                break;
            case "3 (Very Bad)":
                mAnime.setMyScore("3");
                break;
            case "4 (Bad)":
                mAnime.setMyScore("4");
                break;
            case "5 (Average)":
                mAnime.setMyScore("5");
                break;
            case "6 (Fine)":
                mAnime.setMyScore("6");
                break;
            case "7 (Good)":
                mAnime.setMyScore("7");
                break;
            case "8 (Very Good)":
                mAnime.setMyScore("8");
                break;
            case "9 (Great)":
                mAnime.setMyScore("9");
                break;
            case "10 (Masterpiece)":
                mAnime.setMyScore("10");
                break;
            case "Watching":
                mAnime.setMyStatus("1");
                break;
            case "Completed":
                mAnime.setMyStatus("2");
                break;
            case "On-Hold":
                mAnime.setMyStatus("3");
                break;
            case "Dropped":
                mAnime.setMyStatus("4");
                break;
            case "Plan to Watch":
                mAnime.setMyStatus("6");
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void setDefaultScorePrompt(Spinner spinner){
        if (mAnime.getMyScore().equals("")) {
            spinner.setEnabled(false);
        } else {
            switch (mAnime.getMyScore()) {
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
            }
        }
    }

    public void setDefaultStatusPrompt(Spinner spinner) {
        if (mAnime.getMyStatus().equals("")) {
            spinner.setEnabled(false);
        } else {
            switch (Integer.parseInt(mAnime.getMyStatus())) {
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
            }
        }
    }
}
