package com.legue.axel.lolsummonertool.view

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.provider.SearchRecentSuggestions
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.analytics.FirebaseAnalytics
import com.legue.axel.lolsummonertool.Constants
import com.legue.axel.lolsummonertool.ProfilSuggestionProvider
import com.legue.axel.lolsummonertool.R
import com.legue.axel.lolsummonertool.SuperApplication
import com.legue.axel.lolsummonertool.database.SummonerToolDatabase
import com.legue.axel.lolsummonertool.network.retrofit.RetrofitConstants
import com.legue.axel.lolsummonertool.network.retrofit.RetrofitHelper
import com.legue.axel.lolsummonertool.view.profil.ProfilFragment
import com.legue.axel.lolsummonertool.utils.Utils
import com.legue.axel.lolsummonertool.view.wiki.fragment.WikiFragment

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private val TAG = MainActivity::class.java.name


    private lateinit var adView: AdView
    private var currentFragment: Fragment? = null
    private var fragmentTag: String? = null
    private var mFirebaseAnalytics: FirebaseAnalytics? = null
    private lateinit var application: SuperApplication

    private val summonerhandler = Handler { msg ->

        when (msg.what) {
            RetrofitConstants.ACTION_GET_SUMMONER -> {
                Log.i(TAG, "ACTION_GET_SUMMONER ")

                // Here we can only have 1 summoner save in database

                SummonerToolDatabase.getInstance(this).summonerDao().getSummoners().observe(this, Observer { summoners ->
                    if ( summoners.size == 1) {
                        loadMatchesDetails(summoners[0].accountId)
                    }
                })
            }
            RetrofitConstants.ACTION_GET_SUMMONER_MACTHES -> {
                Log.i(TAG, "ACTION_GET_SUMMONER_MACTHES ")
                // TODO: 25/05/2019 Display Profil Information
                Snackbar.make(adView, R.string.request_succeed, Snackbar.LENGTH_LONG)
                        .show()
            }


            RetrofitConstants.ACTION_ERROR -> Snackbar.make(adView, R.string.request_failed, Snackbar.LENGTH_LONG)
                    .show()
        }
        true
    }
    //TODO : General : add a WorkManager for Database Insertion ?
    // TODO: 27/04/2019 Change ResponseBody with SummonerObject
    // TODO: 27/04/2019 Implement Mechanism for saving only 1 Profil in database
    // TODO: 27/04/2019  /lol/Match/v4/matchlists/by-account/{encryptedAccountId}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        application = this.getApplication() as SuperApplication
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)
        val toggle = ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer.addDrawerListener(toggle)
        toggle.syncState()

        val navigationView = findViewById<NavigationView>(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)

        if (savedInstanceState == null) {
            displaySelectedScreen(R.id.nav_builds)
        } else {
            if (savedInstanceState.containsKey("frag")) {
                fragmentTag = savedInstanceState.getString("frag")
                currentFragment = supportFragmentManager.findFragmentByTag(fragmentTag)
                if (currentFragment != null) {
                    supportFragmentManager
                            .beginTransaction()
                            .replace(R.id.fl_content, currentFragment!!, fragmentTag)
                            .commit()
                }
            }

        }

        adView = findViewById(R.id.ad_banner)

        val adRequest = AdRequest.Builder().build()
        adView.loadAd(adRequest)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("frag", fragmentTag)
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        handleIntent(intent)
    }

    override fun onBackPressed() {
        val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        // Do something that differs the Activity's menu here
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu.findItem(R.id.action_search).actionView as SearchView
        searchView.isIconifiedByDefault = true
        searchView.maxWidth = Utils.convertDpToPixel(300, this)
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId


        return if (id == R.id.action_settings) {
            true
        } else super.onOptionsItemSelected(item)

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        val id = item.itemId
        displaySelectedScreen(id)
        displayTitleName(id)
        return true
    }

    private fun displayTitleName(id: Int) {
        if (id != R.id.nav_profil) {
            supportActionBar!!.title = getString(R.string.app_name)
        } else {
            supportActionBar!!.title = null
        }
    }

    private fun displaySelectedScreen(id: Int) {

        when (id) {
            R.id.nav_builds -> {
                fragmentTag = Constants.DRAWER_ITEM_BUILD
                Toast.makeText(application, getString(R.string.soon_available), Toast.LENGTH_SHORT).show()
                currentFragment = null
            }
            R.id.nav_profil -> {
                fragmentTag = Constants.DRAWER_ITEM_PROFIL
                currentFragment = ProfilFragment()
            }
            R.id.nav_bans -> {
                fragmentTag = Constants.DRAWER_ITEM_BANS
                // TODO : replace with correct fragment
                Toast.makeText(application, getString(R.string.soon_available), Toast.LENGTH_SHORT).show()
                currentFragment = null
            }
            R.id.nav_wiki -> {
                fragmentTag = Constants.DRAWER_ITEM_WIKI
                // TODO : replace with correct fragment
                currentFragment = WikiFragment()
            }
            R.id.nav_feedback -> {
                val emailIntent = Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        getString(R.string.scheme_mail), getString(R.string.ssp), null))
                startActivity(Intent.createChooser(emailIntent, "Feed Back..."))
            }
            R.id.nav_privacy -> {
                fragmentTag = Constants.DRAWER_ITEM_PRIVACY
                // TODO : replace with correct fragment
                Toast.makeText(application, getString(R.string.soon_available), Toast.LENGTH_SHORT).show()
                currentFragment = null
            }
        }


        if (currentFragment != null) {
            val fragmentManager = supportFragmentManager
            fragmentManager.beginTransaction()
                    .replace(R.id.fl_content, currentFragment!!, fragmentTag)
                    .commit()
        }

        val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)
        drawer.closeDrawer(GravityCompat.START)
    }


    private fun handleIntent(intent: Intent) {
        if (Intent.ACTION_SEARCH == intent.action) {
            val query = intent.getStringExtra(SearchManager.QUERY)

            val suggestions = SearchRecentSuggestions(this,
                    ProfilSuggestionProvider.AUTHORITY, ProfilSuggestionProvider.MODE)
            suggestions.saveRecentQuery(query, null)

            findSummoner(query)

        }
    }

    private fun findSummoner(summonerName: String) {
        Log.d(TAG, "findSummoner : $summonerName")
        RetrofitHelper.getSummonerName(
                RetrofitConstants.ACTION_GET_SUMMONER,
                this,
                summonerName,
                summonerhandler,
                getApplication() as SuperApplication
        )
    }

    private fun loadMatchesDetails(accountId: String) {
        RetrofitHelper.getSummonerMatches(
                RetrofitConstants.ACTION_GET_SUMMONER_MACTHES,
                this,
                accountId,
                10,
                0,
                summonerhandler,
                getApplication() as SuperApplication
        )
    }


}
