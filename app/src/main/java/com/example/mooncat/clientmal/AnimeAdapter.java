package com.example.mooncat.clientmal;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;


public class AnimeAdapter extends ArrayAdapter<Anime> implements ImageDownloader.Listener {

    private ImageView mImage;
    public AnimeAdapter(Context context, int resource, List<Anime> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View root = inflater.inflate(R.layout.item, null);
        Anime anime = getItem(position);
        TextView title = (TextView) root.findViewById(R.id.title);
        TextView status = (TextView) root.findViewById(R.id.status);
        TextView type = (TextView) root.findViewById(R.id.type);
        mImage = (ImageView) root.findViewById(R.id.imageView);
        System.out.println(mImage.toString());

        title.setText(anime.getTitle());
        status.setText(anime.getStatus());
        type.setText(anime.getType());
        new ImageDownloader(this).execute(anime.getImage());
        return root;
    }

    @Override
    public void onImageLoaded(Bitmap bitmap) {
        mImage.setImageBitmap(bitmap);
    }

    @Override
    public void onError() {
        Toast.makeText(getContext(), "Error Loading Image !", Toast.LENGTH_SHORT).show();
    }
}
