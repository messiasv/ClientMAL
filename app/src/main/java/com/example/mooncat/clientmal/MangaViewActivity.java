package com.example.mooncat.clientmal;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MangaViewActivity extends AppCompatActivity implements ImageDownloader.Listener {

    ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manga_view);

        Bundle bundle = getIntent().getExtras();
        setElements(bundle);
    }

    void setElements(Bundle bundle) {
        ((TextView) findViewById(R.id.mangaViewTitle)).setText(bundle.getString("title"));
        ((TextView) findViewById(R.id.mangaViewStatus)).setText(bundle.getString("status"));
        ((TextView) findViewById(R.id.mangaViewType)).setText(bundle.getString("type"));
        new ImageDownloader(this).execute(bundle.getString("image"));
        mImageView = (ImageView) findViewById(R.id.mangaViewImage);
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
