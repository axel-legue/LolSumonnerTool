package com.legue.axel.lolsummonertool;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.SearchRecentSuggestions;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
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

    private Fragment currentFragment;
    private String fragmentTag;
    private FirebaseAnalytics mFirebaseAnalytics;
    SuperApplication application;
    //TODO : General : add a WorkManager for Database Insertion ?
    // TODO: 27/04/2019 Change ResponseBody with SummonerObject
    // TODO: 27/04/2019 Implement Mechanism for saving only 1 Profil in database
    // TODO: 27/04/2019  /lol/Match/v4/matchlists/by-account/{encryptedAccountId}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
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

        if (savedInstanceState == null) {
            displaySelectedScreen(R.id.nav_builds);
        } else {
            if (savedInstanceState.containsKey("frag")) {
                fragmentTag = savedInstanceState.getString("frag");
                currentFragment = getSupportFragmentManager().findFragmentByTag(fragmentTag);
                if (currentFragment != null) {
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fl_content, currentFragment, fragmentTag)
                            .commit();
                }
            }

        }

        adView = findViewById(R.id.ad_banner);

        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("frag", fragmentTag);
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

        switch (id) {
            case R.id.nav_builds:
                fragmentTag = Constants.DRAWER_ITEM_BUILD;
                Toast.makeText(application, getString(R.string.soon_available), Toast.LENGTH_SHORT).show();
                currentFragment = null;
                break;
            case R.id.nav_profil:
                fragmentTag = Constants.DRAWER_ITEM_PROFIL;
                currentFragment = new ProfilFragment();
                break;
            case R.id.nav_bans:
                fragmentTag = Constants.DRAWER_ITEM_BANS;
                // TODO : replace with correct fragment
                Toast.makeText(application, getString(R.string.soon_available), Toast.LENGTH_SHORT).show();
                currentFragment = null;
                break;
            case R.id.nav_wiki:
                fragmentTag = Constants.DRAWER_ITEM_WIKI;
                // TODO : replace with correct fragment
                currentFragment = new WikiFragment();
                break;
            case R.id.nav_feedback:
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        getString(R.string.scheme_mail), getString(R.string.ssp), null));
                startActivity(Intent.createChooser(emailIntent, "Feed Back..."));
                break;
            case R.id.nav_privacy:
                fragmentTag = Constants.DRAWER_ITEM_PRIVACY;
                // TODO : replace with correct fragment
                Toast.makeText(application, getString(R.string.soon_available), Toast.LENGTH_SHORT).show();
                currentFragment = null;
                break;
        }


        if (currentFragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.fl_content, currentFragment, fragmentTag)
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
                // TODO: 25/05/2019 Display Profil Information
                Snackbar.make(adView, R.string.request_succeed, Snackbar.LENGTH_LONG)
                        .show();
                break;


            case RetrofitConstants.ACTION_ERROR:
                Snackbar.make(adView, R.string.request_failed, Snackbar.LENGTH_LONG)
                        .show();
                break;
        }
        return true;
    });

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
