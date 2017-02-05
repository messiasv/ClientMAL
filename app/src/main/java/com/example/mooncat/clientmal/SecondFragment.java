package com.example.mooncat.clientmal;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.LinkedList;


/**
 * A simple {@link Fragment} subclass.
 */
public class SecondFragment extends Fragment {
    LinkedList<Manga> mangaList;
    ArrayAdapter<Manga> adapter;
    protected String mUsername;

    // newInstance constructor for creating fragment with arguments
    public static SecondFragment newInstance(String username) {
        SecondFragment secondFragment = new SecondFragment();
        final Bundle args = new Bundle();
        args.putString("username", username);
        secondFragment.setArguments(args);
        return  secondFragment;
    }

    // Store instance variables based on arguments passed
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mangaList = new LinkedList<>();
        mUsername = getArguments().getString("username");

        RequestQueue queue = Volley.newRequestQueue(getContext());
        String url = "https://myanimelist.net/malappinfo.php?u=" + mUsername + "&status=all&type=manga";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            mangaList.addAll(MangaParserXML.parseList(response));
                            adapter.notifyDataSetChanged();
                        } catch (XmlPullParserException | IOException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        queue.add(stringRequest);
    }

    // Inflate the view for the fragment based on layout XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        ListView listView = (ListView) view.findViewById(R.id.listview);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), MangaViewActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("title", ((Manga)parent.getItemAtPosition(position)).getTitle());
                bundle.putString("status", ((Manga)parent.getItemAtPosition(position)).getStatus());
                bundle.putString("type", ((Manga)parent.getItemAtPosition(position)).getType());
                bundle.putString("image", ((Manga)parent.getItemAtPosition(position)).getImage());
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        adapter = new MangaAdapter(getActivity(), R.layout.support_simple_spinner_dropdown_item, mangaList);
        listView.setAdapter(adapter);
        return view;
    }

}
