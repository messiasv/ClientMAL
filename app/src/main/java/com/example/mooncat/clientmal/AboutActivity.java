package com.example.mooncat.clientmal;


import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        CardView aboutCardView = (CardView) findViewById(R.id.about_card);
        CardView openSourceLicensesCardView = (CardView) findViewById(R.id.open_source_licenses_card);

        aboutCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayLicensesAlertDialog(R.layout.dialog_licenses,
                        getString(R.string.app_name),
                        "file:///android_asset/about_card.html");
            }
        });
        openSourceLicensesCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayLicensesAlertDialog(R.layout.dialog_licenses,
                        getString(R.string.action_licenses),
                        "file:///android_asset/open_source_licenses.html");
            }
        });
    }

    private void displayLicensesAlertDialog(int layout, String title, String url) {
        WebView view = (WebView) LayoutInflater.from(this).inflate(layout, null);
        view.loadUrl(url);
        AlertDialog mAlertDialog = new AlertDialog.Builder(this, R.style.Theme_AppCompat_Light_Dialog_Alert)
                .setTitle(title)
                .setView(view)
                .setPositiveButton(android.R.string.ok, null)
                .show();
    }
}
