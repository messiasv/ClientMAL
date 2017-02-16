package com.example.mooncat.clientmal;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;

import java.util.List;


class AnimeAdapter extends ArrayAdapter<Anime> {

    private NetworkImageView mImage;
    AnimeAdapter(Context context, int resource, List<Anime> objects) {
        super(context, resource, objects);
    }

    @Override
    @NonNull
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View root = inflater.inflate(R.layout.item, null);
        Anime anime = getItem(position);
        TextView title = (TextView) root.findViewById(R.id.title);
        TextView status = (TextView) root.findViewById(R.id.status);
        TextView type = (TextView) root.findViewById(R.id.type);
        mImage = (NetworkImageView) root.findViewById(R.id.imageView);

        if (anime != null) {
            title.setText(anime.getTitle());
            status.setText(anime.getStatus());
            type.setText(anime.getType());
            ImageDownloader.downloadImage(anime.getImage(), this.getContext(), mImage);
        }
        return root;
    }
}
