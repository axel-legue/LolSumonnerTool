package com.legue.axel.lolsummonertool;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.provider.SearchRecentSuggestions;
import androidx.annotation.NonNull;
import com.google.android.material.navigation.NavigationView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.SearchView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.legue.axel.lolsummonertool.database.SummonerToolDatabase;
import com.legue.axel.lolsummonertool.network.retrofit.RetrofitConstants;
import com.legue.axel.lolsummonertool.network.retrofit.RetrofitHelper;
import com.legue.axel.lolsummonertool.profil.ProfilFragment;
import com.legue.axel.lolsummonertool.utils.Utils;
import com.legue.axel.lolsummonertool.wiki.fragment.WikiFragment;

import butterknife.BindView;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private final String TAG = MainActivity.class.getName();
    @BindView(R.id.fl_content)
    FrameLayout flContent;

    AdView adView;

    private FirebaseAnalytics mFirebaseAnalytics;
    private Context mContext;
    SuperApplication application;

    //TODO : General : add a WorkManager for Database Insertion ?
    // TODO: 27/04/2019 Change ResponseBody with SummonerObject
    // TODO: 27/04/2019 Implement Mechanism for saving only 1 Profil in database
    // TODO: 27/04/2019  /lol/Match/v4/matchlists/by-account/{encryptedAccountId}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        application = (SuperApplication) this.getApplication();
        //    mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        displaySelectedScreen(R.id.nav_builds);

        adView = findViewById(R.id.ad_banner);

        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        handleIntent(intent);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        // Do something that differs the Activity's menu here
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setIconifiedByDefault(true);
        searchView.setMaxWidth(Utils.convertDpToPixel(300, this));
        if (searchManager != null) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        displaySelectedScreen(id);
        displayTitleName(id);
        return true;
    }

    private void displayTitleName(int id) {
        if (id != R.id.nav_profil) {
            getSupportActionBar().setTitle(getString(R.string.app_name));
        } else {
            getSupportActionBar().setTitle(null);
        }
    }

    private void displaySelectedScreen(int id) {
        Fragment fragment = null;

        switch (id) {
            case R.id.nav_builds:
                fragment = new WikiFragment();
                break;
            case R.id.nav_profil:
                fragment = new ProfilFragment();
                break;
            case R.id.nav_bans:
                // TODO : replace with correct fragment
                fragment = new WikiFragment();
                break;
            case R.id.nav_wiki:
                // TODO : replace with correct fragment
                fragment = new WikiFragment();
                break;
            case R.id.nav_feedback:
                // TODO : replace with correct fragment
                fragment = new WikiFragment();
                break;
            case R.id.nav_privacy:
                // TODO : replace with correct fragment
                fragment = new WikiFragment();
                break;
        }

        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.fl_content, fragment)
                    .commit();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }


    private void handleIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);

            SearchRecentSuggestions suggestions = new SearchRecentSuggestions(this,
                    ProfilSuggestionProvider.AUTHORITY, ProfilSuggestionProvider.MODE);
            suggestions.saveRecentQuery(query, null);

            findSummoner(query);

        }
    }

    private void findSummoner(String summonerName) {
        RetrofitHelper.getSummonerName(
                RetrofitConstants.ACTION_GET_SUMMONER,
                this,
                summonerName,
                summonerhandler,
                (SuperApplication) getApplication()
        );
    }

    private Handler summonerhandler = new Handler(msg -> {

        switch (msg.what) {
            case RetrofitConstants.ACTION_GET_SUMMONER:
                Log.i(TAG, "ACTION_GET_SUMMONER ");

                // Here we can only have 1 summoner save in database
                SummonerToolDatabase.getInstance(this).summonerDao().getSummoners().observe(this, summoners -> {
                    if (summoners != null && summoners.size() == 1) {
                        loadMatchesDetails(summoners.get(0).accountId);
                    }
                });

                break;
            case RetrofitConstants.ACTION_GET_SUMMONER_MACTHES:
                Log.i(TAG, "ACTION_GET_SUMMONER_MACTHES ");
                //todo replace with RxCode for chaining api calls
                //  loadMatchesDetails();
                break;

            case RetrofitConstants.ACTION_GET_MATCH_INFORMATIONS:
                Log.i(TAG, "ACTION_GET_MATCH_INFORMATIONS ");

                break;

            case RetrofitConstants.ACTION_ERROR:
                break;
        }
        return true;
    });

    private void loadSummonerMatches(String accountId) {
        RetrofitHelper.getSummonerMatches(
                RetrofitConstants.ACTION_GET_SUMMONER_MACTHES,
                this,
                accountId,
                10,
                0,
                summonerhandler,
                (SuperApplication) getApplication()
        );
    }

    private void loadMatchesDetails(String accountId) {
        RetrofitHelper.getSummonerMatches(
                RetrofitConstants.ACTION_GET_SUMMONER_MACTHES,
                this,
                accountId,
                10,
                0,
                summonerhandler,
                (SuperApplication) getApplication()
        );
    }


}
