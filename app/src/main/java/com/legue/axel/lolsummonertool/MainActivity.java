package com.legue.axel.lolsummonertool;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.legue.axel.lolsummonertool.database.model.champion.Champion;
import com.legue.axel.lolsummonertool.network.ChampionDetailResponse;
import com.legue.axel.lolsummonertool.network.ChampionsResponse;
import com.legue.axel.lolsummonertool.retrofit.Constants;
import com.legue.axel.lolsummonertool.retrofit.RetrofitHelper;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private final String TAG = MainActivity.class.getName();
    private FirebaseAnalytics mFirebaseAnalytics;
    private SuperApplication application;
    private ChampionsResponse championsResponse;
    private ChampionDetailResponse championDetailResponse;

    private ImageView testPicasso;
    private Context context;

    //TODO : General : add relation in Models and add default Dao CRUD


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
//        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        testPicasso = findViewById(R.id.test_image);

        //TODO : testing purpose => update code and move it at a better place
        application = (SuperApplication) getApplication();
        loadChampions();
    }

    private void loadChampions() {
        //TODO : testing purpose => update code and move it at a better place
        RetrofitHelper.getChampions(
                Constants.ACTION_COMPLETE,
                championhandler,
                application);
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

        if (id == R.id.nav_builds) {
            // Handle the build action
        } else if (id == R.id.nav_profil) {

        } else if (id == R.id.nav_bans) {

        } else if (id == R.id.nav_wiki) {

        } else if (id == R.id.nav_feedback) {

        } else if (id == R.id.nav_privacy) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private Handler championhandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {

            switch (msg.what) {
                case Constants.ACTION_COMPLETE:

                    if (application.getChampionsResponse() != null) {
                        championsResponse = application.getChampionsResponse();
                        Log.i(TAG, "handleMessage: " + championsResponse);
                        printMap(championsResponse.getChampionList());
                        //TODO: clean code : Test Display Glide Image from ChampionResponse
                        Uri uri = Uri.parse("http://ddragon.leagueoflegends.com/cdn/9.5.1/img/champion/" + championDetailResponse.getImage().full);
                        Glide.with(context)
                                .load(uri)
                                .circleCrop()
                                .into(testPicasso);
                    }
                    break;

                case Constants.ACTION_ERROR:
                    break;
            }
            return true;
        }
    });

    // TODO : clean code : Test iterate LinkedHashMap
    private void printMap(HashMap hashMap) {
        Iterator it = hashMap.entrySet().iterator();
        while (it.hasNext()) {
            HashMap.Entry pair = (HashMap.Entry) it.next();
            String key = (String) pair.getKey();
            championDetailResponse = (ChampionDetailResponse) pair.getValue();
            it.remove();
            return;
        }
    }
}
