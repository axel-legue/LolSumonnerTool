package com.legue.axel.lolsummonertool;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.legue.axel.lolsummonertool.wiki.WikiFragment;

import butterknife.BindView;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private final String TAG = MainActivity.class.getName();

    @BindView(R.id.fl_content)
    FrameLayout flContent;

    private FirebaseAnalytics mFirebaseAnalytics;
    private Context mContext;
    SuperApplication application;

    //TODO : General : add relation in Models and add default Dao CRUD
    //TODO : General : add a WorkManager for Database Insertion ?

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        application = (SuperApplication) this.getApplication();
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

        displaySelectedScreen(R.id.nav_builds);

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
        displaySelectedScreen(id);
        return true;
    }

    private void displaySelectedScreen(int id) {
        Fragment fragment = null;

        switch (id) {
            case R.id.nav_builds:
                fragment = new WikiFragment();
                break;
            case R.id.nav_profil:
                // TODO : replace with correct fragment
                fragment = new WikiFragment();
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



}
