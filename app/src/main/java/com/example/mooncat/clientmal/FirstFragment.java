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
public class FirstFragment extends Fragment {
    // Store instance variables
    private String title;
    private int page;
    LinkedList<Anime> animeList;
    ArrayAdapter<Anime> adapter;

    // newInstance constructor for creating fragment with arguments
    public static FirstFragment newInstance(int page, String title) {
        FirstFragment fragmentFirst = new FirstFragment();
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        args.putString("someTitle", title);
        fragmentFirst.setArguments(args);
        return fragmentFirst;
    }

    // Store instance variables based on arguments passed
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        page = getArguments().getInt("someInt", 0);
        title = getArguments().getString("someTitle");
        animeList = new LinkedList<>();

        RequestQueue queue = Volley.newRequestQueue(getContext());
        String url = "https://myanimelist.net/malappinfo.php?u=rinnetsu&status=all&type=anime";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        String result = response;
                        try {
                            animeList.addAll(AnimeParserXML.parseList(result));
                            for(Anime anime : animeList){
//                                System.out.println(anime.toString());
                            }

                            adapter.notifyDataSetChanged();
                        } catch (XmlPullParserException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
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
                Intent intent = new Intent(getActivity(), AnimeView.class);
                Bundle bundle = new Bundle();
                bundle.putString("title", ((Anime)parent.getItemAtPosition(position)).getTitle());
                bundle.putString("status", ((Anime)parent.getItemAtPosition(position)).getStatus());
                bundle.putString("type", ((Anime)parent.getItemAtPosition(position)).getType());
                bundle.putString("image", ((Anime)parent.getItemAtPosition(position)).getImage());
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        adapter = new AnimeAdapter(getActivity(), R.layout.support_simple_spinner_dropdown_item, animeList);
        listView.setAdapter(adapter);
//        TextView tvLabel = (TextView) view.findViewById(R.id.tvLabel); // TODO : change
//        tvLabel.setText(page + " -- " + title);
        return view;
    }
}
