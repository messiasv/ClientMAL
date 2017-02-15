package com.example.mooncat.clientmal;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

public class MangaViewActivity extends AppCompatActivity implements ImageDownloader.Listener {

    ImageView mImageView;
    Manga mManga;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manga_view);
        mManga = (Manga) getIntent().getSerializableExtra("manga");
        setElements();
    }

    void setElements() {
        ((TextView) findViewById(R.id.mangaViewTitle)).setText(mManga.getTitle());
        ((TextView) findViewById(R.id.mangaViewStatus)).append(mManga.getStatus());
        ((TextView) findViewById(R.id.mangaViewType)).append(mManga.getType());
        ((TextView) findViewById(R.id.mangaViewVolumes))
                .append(mManga.getMyReadVolumes()
                        +" / "  +
                        (mManga.getVolumes().equals("0")?"?":mManga.getVolumes()));
        ((TextView) findViewById(R.id.mangaViewChapters))
                .append(mManga.getMyReadChapters()
                        +" / "  +
                        (mManga.getChapters().equals("0")?"?":mManga.getChapters()));
        Log.i("manga", "|" + mManga.getSynonyms()+"|");
        ((TextView) findViewById(R.id.mangaViewSynonyms)).append(mManga.getSynonyms());
        ((TextView) findViewById(R.id.mangaViewStart))
                .append(
                        Objects.equals(mManga.getStart(),
                                "0000-00-00") ?
                                "?" : mManga.getStart());
        ((TextView) findViewById(R.id.mangaViewEnd))
                .append(
                        Objects.equals(mManga.getEnd(),
                                "0000-00-00") ?
                                "?" : mManga.getEnd());
        ((TextView) findViewById(R.id.mangaViewMyScore)).append(mManga.getMyScore());
        ((TextView) findViewById(R.id.mangaViewMyStatus)).append(mManga.getMyStatus());

        new ImageDownloader(this).execute(mManga.getImage());
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
