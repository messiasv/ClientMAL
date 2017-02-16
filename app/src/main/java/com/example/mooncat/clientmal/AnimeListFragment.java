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

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.Serializable;
import java.util.LinkedList;


/**
 * A simple {@link Fragment} subclass.
 */
public class AnimeListFragment extends Fragment {
    LinkedList<Anime> animeList;
    ArrayAdapter<Anime> adapter;
    protected String  mUsername;
    protected String  mAnimeListXml;
    protected int mParserToUse;

    // newInstance constructor for creating fragment with arguments
    public static AnimeListFragment newInstance(String username, String animeListXml, int parserToUse) {
        AnimeListFragment animeListFragment = new AnimeListFragment();
        final Bundle args = new Bundle();
        args.putString("username", username);
        args.putString("animelist", animeListXml);
        args.putInt("parser", parserToUse);
        animeListFragment.setArguments(args);
        return animeListFragment;
    }

    // Store instance variables based on arguments passed
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        animeList = new LinkedList<>();
        mUsername = getArguments().getString("username");
        mAnimeListXml = getArguments().getString("animelist");
        mParserToUse = getArguments().getInt("parser");

        switch (mParserToUse) {
            case 0:
                try {
                    animeList.addAll(ParserXML.parseUserAnimeList(mAnimeListXml));
                } catch (XmlPullParserException | IOException e) {
                    e.printStackTrace();
                }
                break;
            case 1:
                try {
                    animeList.addAll(ParserXML.parseAnimeNameList(mAnimeListXml));
                } catch (XmlPullParserException | IOException e) {
                    e.printStackTrace();
                }
                break;
        }
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
                Intent intent = new Intent(getActivity(), AnimeViewActivity.class);
                intent.putExtra("anime", ((Anime)parent.getItemAtPosition(position)));
                startActivity(intent);
            }
        });
        adapter = new AnimeAdapter(getActivity(), R.layout.support_simple_spinner_dropdown_item, animeList);
        listView.setAdapter(adapter);
        return view;
    }
}
