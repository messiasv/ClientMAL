package com.example.mooncat.clientmal;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

public class AnimeViewActivity extends AppCompatActivity implements ImageDownloader.Listener {

    ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anime_view);

        Bundle bundle = getIntent().getExtras();
        try {
            setElements(bundle);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void setElements(Bundle bundle) throws IOException {
        ((TextView) findViewById(R.id.animeViewTitle)).setText(bundle.getString("title"));
        ((TextView) findViewById(R.id.animeViewStatus)).setText(bundle.getString("status"));
        ((TextView) findViewById(R.id.animeViewType)).setText(bundle.getString("type"));
        new ImageDownloader(this).execute(bundle.getString("image"));
        mImageView = (ImageView) findViewById(R.id.animeViewImage);
    }

    @Override
    public void onImageLoaded(Bitmap bitmap) {
        mImageView.setImageBitmap(bitmap);
    }

    @Override
    public void onError() {
        Toast.makeText(this, "Error Loading Image !", Toast.LENGTH_SHORT).show();
    }
}
