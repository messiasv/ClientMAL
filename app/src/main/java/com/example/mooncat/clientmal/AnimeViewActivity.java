package com.example.mooncat.clientmal;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
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

public class AnimeViewActivity extends AppCompatActivity {

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
        ((TextView) findViewById(R.id.animeViewEpisodes)).append(mAnime.getMyWatchedEpisodes() + " / " + (Objects.equals(mAnime.getEpisodes(), "0") ?"?":mAnime.getEpisodes()) );
        ((TextView) findViewById(R.id.animeViewSynonyms)).append(mAnime.getSynonyms());
        ((TextView) findViewById(R.id.animeViewStart)).append(Objects.equals(mAnime.getStart(), "0000-00-00") ?"?":mAnime.getStart());
        ((TextView) findViewById(R.id.animeViewEnd)).append(Objects.equals(mAnime.getEnd(), "0000-00-00") ?"?":mAnime.getEnd());

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
}
