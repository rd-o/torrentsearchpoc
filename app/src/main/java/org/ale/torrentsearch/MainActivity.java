package org.ale.torrentsearch;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.ale.torrentsearch.search.SearchResult;
import org.ale.torrentsearch.search.SortOrder;
import org.ale.torrentsearch.search.TorrentSite;

import java.util.List;

import static android.app.SearchManager.QUERY;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);
        try {
            searchSite(TorrentSite.ThePirateBay);
        }catch (Exception e) {
            e.printStackTrace();
        }
        setContentView(R.layout.activity_main);
    }


    private void searchSite(TorrentSite torrentSite) throws Exception {
        // Set test user and password
        Context context =this.getApplicationContext();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        String QUERY = "nirvana";
        SortOrder ORDER = SortOrder.BySeeders;
        int RESULTS = 10;
        long THE_YEAR_2000 = 946684800000L;


        List<SearchResult> results = torrentSite.search(prefs, QUERY, ORDER, RESULTS);

        System.out.println("-------- size:" + results.size());

        for (SearchResult result : results) {
            System.out.println(result.getTitle());
            System.out.println(result.getTorrentUri().toString());
            System.out.println(result.getDetailsUrl());
            System.out.println(result.getSize());
            System.out.println(result.getAddedDate());
            System.out.println(result.getAddedDate().getTime());
            System.out.println(result.getSeeds());
            System.out.println(result.getLeechers());
        }
    }

}
