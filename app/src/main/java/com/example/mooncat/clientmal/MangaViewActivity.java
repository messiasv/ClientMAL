package com.example.mooncat.clientmal;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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

public class MangaViewActivity extends AppCompatActivity{

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
        ((TextView) findViewById(R.id.mangaViewMyScore)).append(mManga.getMyScore());
        ((TextView) findViewById(R.id.mangaViewMyStatus)).append(mManga.getMyStatus());

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
}
