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
public class MangaListFragment extends Fragment {
    LinkedList<Manga> mangaList;
    ArrayAdapter<Manga> adapter;
    protected String mUsername;
    protected String mMangaListXml;

    // newInstance constructor for creating fragment with arguments
    public static MangaListFragment newInstance(String username, String mangaListXml) {
        MangaListFragment mangaListFragment = new MangaListFragment();
        final Bundle args = new Bundle();
        args.putString("username", username);
        args.putString("mangalist", mangaListXml);
        mangaListFragment.setArguments(args);
        return mangaListFragment;
    }

    // Store instance variables based on arguments passed
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mangaList = new LinkedList<>();
        mUsername = getArguments().getString("username");
        mMangaListXml = getArguments().getString("mangalist");

        try {
            mangaList.addAll(ParserXML.parseUserMangaList(mMangaListXml));
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
                Intent intent = new Intent(getActivity(), MangaViewActivity.class);
                intent.putExtra("manga", (Manga) parent.getItemAtPosition(position));
                startActivity(intent);
            }
        });
        adapter = new MangaAdapter(getActivity(), R.layout.support_simple_spinner_dropdown_item, mangaList);
        listView.setAdapter(adapter);
        return view;
    }
}
