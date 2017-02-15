package com.example.mooncat.clientmal;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class NavigationDrawerActivity extends AppCompatActivity
        implements  NavigationView.OnNavigationItemSelectedListener,
                    ViewPagerFragment.OnFragmentInteractionListener,
                    UserInfoFragment.OnFragmentInteractionListener {

    private FragmentManager fragmentManager;
    private String mUsername;
    private String mAnimeListXml;
    private String mMangaListXml;
    private Fragment uiFragment = null;
    private Fragment vpFragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer);
        mUsername = getIntent().getStringExtra("username");
        mAnimeListXml = "";
        mMangaListXml = "";
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        ((TextView)navigationView.getHeaderView(0).findViewById(R.id.header_username)).setText(mUsername);

        // TODO: implement the following in a better way
        navigationView.getMenu().getItem(0).setChecked(true);
        uiFragment = new UserInfoFragment();

        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.content_navigation_drawer, uiFragment)
                .addToBackStack(null)
                .commit();
        // Fetch data (lists + user info)
        fetchData();
    }

    public void fetchData(){
        RequestQueue queue = Volley.newRequestQueue(this);
        String urlAnime = Tools.searchUserAnimeListRequest(mUsername);
        String urlManga = Tools.searchUserMangaListRequest(mUsername);

        StringRequest stringAnimeRequest = new StringRequest(Request.Method.GET, urlAnime,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        mAnimeListXml = response;
                        ((UserInfoFragment) uiFragment).setAnimeValues(mAnimeListXml);

                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        queue.add(stringAnimeRequest);

        StringRequest stringMangaRequest = new StringRequest(Request.Method.GET, urlManga,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        mMangaListXml = response;
                        ((UserInfoFragment) uiFragment).setMangaValues(mMangaListXml);
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        queue.add(stringMangaRequest);
    }

    public void createVPFragment() {
        vpFragment = ViewPagerFragment.newInstance(mAnimeListXml, mMangaListXml);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation_drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if(id == R.id.nav_list || id == R.id.nav_user) { // TODO: create the second fragment
            switch (id) {
                case R.id.nav_list:
                    createVPFragment();
                    fragment = vpFragment;
                    break;
                case R.id.nav_user:
                    fragment = uiFragment;
                    break;
                default:
                    fragment = null;
            }
//
//            try {
//                fragment = (Fragment) fragmentClass.newInstance();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }

            fragmentManager.beginTransaction()
                    .replace(R.id.content_navigation_drawer, fragment)
                    .addToBackStack(null)
                    .commit();

        } else if (id == R.id.nav_settings) { // TODO: add the calls to the respective activities

        } else if (id == R.id.nav_about) {

        } else if (id == R.id.nav_logout) {
            Intent logout = new Intent(getApplicationContext(), UserSelectorActivity.class);
            startActivity(logout);
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
