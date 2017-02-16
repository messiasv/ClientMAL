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

class MangaAdapter extends ArrayAdapter<Manga> {

    private NetworkImageView mImage;

    MangaAdapter(Context context, int resource, List<Manga> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View root = inflater.inflate(R.layout.item, null);
        Manga manga = getItem(position);
        TextView title = (TextView) root.findViewById(R.id.title);
        TextView status = (TextView) root.findViewById(R.id.status);
        TextView type = (TextView) root.findViewById(R.id.type);
        mImage = (NetworkImageView) root.findViewById(R.id.imageView);

        if (manga != null) {
            title.setText(manga.getTitle());
            status.setText(manga.getStatus());
            type.setText(manga.getType());
            ImageDownloader.downloadImage(manga.getImage(),this.getContext(),mImage);
        }
        return root;
    }
}
