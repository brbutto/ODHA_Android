package org.odha.adhaoregon;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class NewsActivity extends AppCompatActivity {

    ListView newsListView;
    ArrayAdapter<String> newsAdaptor;
    ProgressDialog newsLoadProgress;
    ArrayList<String> titleArray = new ArrayList<String>();
    ArrayList<String> subtitleArray = new ArrayList<String>();

    Elements newsTitles, newsDates, newsText;

    String title, date, text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        new getAnnouncements().execute();
    }

    private class getAnnouncements extends AsyncTask<String, Void, Void> {

        @Override
        protected Void doInBackground(String... arg) {

            try {
                // Connect to the ODHA announcements website
                Document doc = Jsoup.connect("http://www.odha.org/announcements.php").get();

                /*
                Get individual elements from announcement posts: Titles, dates, and the body text of each
                announcement. All text comes in without formatting or breaks and should be treated as plain
                text.
                */
                newsTitles = doc.getElementsByClass("entry-title");
                newsDates = doc.getElementsByClass("entry-meta");
                newsText = doc.getElementsByClass("entry-content");

                // Loop for iterating through each post to extract the needed text
                for (int posts = 0; posts < newsTitles.size(); posts++) {

                    titleArray.add(newsTitles.get(posts).text());
                    //subtitleArray.add(newsDates.get(posts).text());
                    //text = newsText.get(posts).text();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;

        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            newsListView = (ListView) findViewById(R.id.news_list_view);
            newsAdaptor = new ArrayAdapter<String>(NewsActivity.this, R.layout.news_list_item_layout, R.id.news_item_title, titleArray);
            newsListView.setAdapter(newsAdaptor);

        }

        private class NewsItems {
            private String newsTitle;
            private String newsSubtitle;
            private String newsText;

            public void setTitles (String title, String subtitle){
                newsTitle = title;
                newsTitle = subtitle;
            }
        }
    }


}
