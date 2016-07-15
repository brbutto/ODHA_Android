package org.odha.adhaoregon;

import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.support.customtabs.CustomTabsClient;
import android.support.customtabs.CustomTabsIntent;
import android.support.customtabs.CustomTabsServiceConnection;
import android.support.customtabs.CustomTabsSession;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.net.URL;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Get About Us button instance
        TextView aboutUsButton = (TextView) findViewById(R.id.about_odha_button);

        // OnClick listener for About Us button
        assert aboutUsButton != null;
        aboutUsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent aboutIntent = new Intent(HomeActivity.this, AboutActivity.class);

                // Go to About Us activity
                startActivity(aboutIntent);
            }
        });

        // Get Website button instance
        TextView websiteButton = (TextView) findViewById(R.id.odha_website_button);

        // OnClick listener for website button
        assert websiteButton != null;
        websiteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                Open ODHA website in browser using intents
                */
                /*
                Uri uri = Uri.parse("http://www.odha.org");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
                */

                String url = "http://www.odha.org";
                CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
                CustomTabsIntent customTabsIntent = builder.build();

                customTabsIntent.launchUrl(HomeActivity.this, Uri.parse(url));
            }
        });

        TextView newsButton = (TextView) findViewById(R.id.news_button);

        assert newsButton != null;
        newsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent newsIntent = new Intent(HomeActivity.this, NewsActivity.class);

                startActivity(newsIntent);
            }
        });
    }
}
