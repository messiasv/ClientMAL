package com.example.mooncat.clientmal;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.Objects;

public class AnimeViewActivity extends AppCompatActivity implements ImageDownloader.Listener {

    ImageView mImageView;
    Anime mAnime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anime_view);
        mAnime = (Anime) getIntent().getSerializableExtra("anime");
        try {
            setElements();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void setElements() throws IOException {
        ((TextView) findViewById(R.id.animeViewTitle)).setText(mAnime.getTitle());
        ((TextView) findViewById(R.id.animeViewStatus)).append(mAnime.getStatus());
        ((TextView) findViewById(R.id.animeViewType)).append(mAnime.getType());
        ((TextView) findViewById(R.id.animeViewEpisodes)).append(mAnime.getMyWatchedEpisodes() + " / " + (Objects.equals(mAnime.getEpisodes(), "0") ?"?":mAnime.getEpisodes()) );
        ((TextView) findViewById(R.id.animeViewSynonyms)).append(mAnime.getSynonyms());
        ((TextView) findViewById(R.id.animeViewStart)).append(Objects.equals(mAnime.getStart(), "0000-00-00") ?"?":mAnime.getStart());
        ((TextView) findViewById(R.id.animeViewEnd)).append(Objects.equals(mAnime.getEnd(), "0000-00-00") ?"?":mAnime.getEnd());
        ((TextView) findViewById(R.id.animeViewMyScore)).append(mAnime.getMyScore());
        ((TextView) findViewById(R.id.animeViewMyStatus)).append(mAnime.getMyStatus());

        new ImageDownloader(this).execute(mAnime.getImage());
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
