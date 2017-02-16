package com.example.mooncat.clientmal;

import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class SearchActivity extends AppCompatActivity implements UserInfoFragment.OnFragmentInteractionListener{

    private String mAnimeResults;
    private String mMangaResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        mAnimeResults = "";
        mMangaResults = "";

        findViewById(R.id.search_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String search = ((TextView)findViewById(R.id.search_bar)).getText().toString();
                search(search);
            }
        });
    }

    void search(final String search) {
        RequestQueue queue = Volley.newRequestQueue(this);
        String urlAnime = "";
        String urlManga = "";

        try {
            urlAnime = Tools.searchAnime(search);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        StringRequest searchAnimeRequest = new StringRequest(Request.Method.GET, urlAnime,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        mAnimeResults = response;
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
        queue.add(searchAnimeRequest);

        try {
            urlManga = Tools.searchManga(search);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        StringRequest searchMangaRequest = new StringRequest(Request.Method.GET, urlManga,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        mMangaResults = response;
                        displaySearchResults(mAnimeResults,mMangaResults);
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
        queue.add(searchMangaRequest);

    }

    void displaySearchResults(String animeResults, String mangaResults) {
        Fragment vpFragment = ViewPagerFragment.newInstance(animeResults, mangaResults, 1);
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.search_results, vpFragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
