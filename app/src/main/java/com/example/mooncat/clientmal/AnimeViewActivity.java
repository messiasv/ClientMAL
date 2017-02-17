package com.example.mooncat.clientmal;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
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
    EditText editEpisodes;

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

        findViewById(R.id.animeViewUpdate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAnime.setMyWatchedEpisodes(editEpisodes.getText().toString());
                view.setEnabled(false);
                animeRequest(1);
            }
        });

        findViewById(R.id.animeViewAdd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setEnabled(false);
                animeRequest(0);
            }
        });
        this.getSupportActionBar().setTitle(mAnime.getTitle());
    }

    void setElements() {
        ((TextView) findViewById(R.id.animeViewStatus)).append(mAnime.getStatus());
        ((TextView) findViewById(R.id.animeViewType)).append(mAnime.getType());

        editEpisodes = (EditText) findViewById(R.id.animeViewMyEpisodes);
        editEpisodes.setText(mAnime.getMyWatchedEpisodes());

        ((TextView) findViewById(R.id.animeViewEpisodes)).append((mAnime.getEpisodes().equals("0") ? "?" : mAnime.getEpisodes()) );
        if (!mAnime.getSynonyms().equals("")) {
            ((TextView) findViewById(R.id.animeViewSynonyms)).append(mAnime.getSynonyms());
        }
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

        if (!mAnime.getSynopsis().equals("")) {
            TextView engTitle = new TextView(this);
            engTitle.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            engTitle.setText("English title: " + mAnime.getEnglish());
            TextView synopsis = new TextView(this);
            synopsis.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            synopsis.setText("Synopsis: ");
            TextView synopsisValue = new TextView(this);
            synopsisValue.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            synopsisValue.setText(mAnime.getSynopsis());
            synopsisValue.setVerticalScrollBarEnabled(true);
            synopsisValue.setMovementMethod(new ScrollingMovementMethod());
            TextView score = new TextView(this);
            score.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            score.setText("Score: " + mAnime.getScore());

            ((LinearLayout) findViewById(R.id.animeViewLastContainer)).addView(engTitle);
            ((LinearLayout) findViewById(R.id.animeViewLastContainer)).addView(synopsis);
            ((LinearLayout) findViewById(R.id.animeViewLastContainer)).addView(synopsisValue);
            ((LinearLayout) findViewById(R.id.animeViewMainInfo)).addView(score);

            findViewById(R.id.animeViewUpdate).setVisibility(View.GONE);
            findViewById(R.id.animeViewDelete).setVisibility(View.GONE);
            findViewById(R.id.animeViewMyScoreLayout).setVisibility(View.GONE);
            findViewById(R.id.animeViewMyStatusLayout).setVisibility(View.GONE);
        } else {
            findViewById(R.id.animeViewAdd).setVisibility(View.GONE);
        }
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
            case 1 :
                setNextValues(mAnime);
                try {
                    url = Tools.UpdateAnime(mAnime.getId(), mAnime.getMyWatchedEpisodes(), mAnime.getMyStatus(), mAnime.getMyScore());
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

    public void setNextValues(Anime anime){
        if(!anime.getEpisodes().equals("0")) {
            if (anime.getMyStatus().equals("2")) {
                anime.setMyWatchedEpisodes(anime.getEpisodes());
            }else if(Integer.parseInt(anime.getMyWatchedEpisodes()) >= Integer.parseInt(anime.getEpisodes())){
                anime.setMyWatchedEpisodes(anime.getEpisodes());
            }
        }
    }
}
