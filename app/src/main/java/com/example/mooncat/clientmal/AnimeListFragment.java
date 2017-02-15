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
import java.util.LinkedList;


/**
 * A simple {@link Fragment} subclass.
 */
public class AnimeListFragment extends Fragment {
    LinkedList<Anime> animeList;
    ArrayAdapter<Anime> adapter;
    protected String  mUsername;
    protected String  mAnimeListXml;

    // newInstance constructor for creating fragment with arguments
    public static AnimeListFragment newInstance(String username, String animeListXml) {
        AnimeListFragment animeListFragment = new AnimeListFragment();
        final Bundle args = new Bundle();
        args.putString("username", username);
        args.putString("animelist", animeListXml);
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

        try {
            animeList.addAll(ParserXML.parseUserAnimeList(mAnimeListXml));
        } catch (XmlPullParserException | IOException e) {
            e.printStackTrace();
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
        return view;
    }
}
