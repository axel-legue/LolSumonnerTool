package com.legue.axel.lolsummonertool.wiki.fragment

import android.app.AlertDialog
import android.appwidget.AppWidgetManager
import android.content.*
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.os.Parcelable
import android.util.Log
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.legue.axel.lolsummonertool.Constants
import com.legue.axel.lolsummonertool.R
import com.legue.axel.lolsummonertool.SuperApplication
import com.legue.axel.lolsummonertool.adapter.ChampionsAdapter
import com.legue.axel.lolsummonertool.adapter.ChampionsAdapter.ChampionListener
import com.legue.axel.lolsummonertool.database.model.champion.Champion
import com.legue.axel.lolsummonertool.database.viewmodel.ChampionViewModel
import com.legue.axel.lolsummonertool.network.retrofit.RetrofitConstants
import com.legue.axel.lolsummonertool.network.retrofit.RetrofitHelper.getChampions
import com.legue.axel.lolsummonertool.widget.ChampionWidget
import com.legue.axel.lolsummonertool.wiki.activity.WikiChampionInformations
import kotlinx.android.synthetic.main.fragment_wiki_data.*
import java.util.*

class WikiChampionFragment : Fragment() {
    companion object {
        private val TAG = WikiChampionFragment::class.java.name
        @JvmStatic
        fun newInstance(page: Int, title: String?): WikiChampionFragment {
            val wikiChampionFragment = WikiChampionFragment()
            val args = Bundle()
            args.putInt(Constants.KEY_WIKI_PAGE_NUMBER, page)
            args.putString(Constants.KEY_WIKI_PAGE_NAME, title)
            wikiChampionFragment.arguments = args
            return wikiChampionFragment
        }
    }

    private lateinit var adapter: ChampionsAdapter
    private lateinit var application: SuperApplication
    private lateinit var fragment: WikiChampionFragment
    private lateinit var championList: MutableList<Champion>
    private lateinit var mGridLayoutManager: GridLayoutManager
    private var filterOptions: Array<String>? = null
    private var mIndexfilterOptionSelected = 0
    private var mfilterOptionSelected: String? = null
    private lateinit var mSharedPreferences: SharedPreferences
    private lateinit var mEditor: SharedPreferences.Editor
    private var savedRecyclerLayoutState: Parcelable? = null
    private val championListener = ChampionListener { position: Int, champion: Champion ->
        val intent = Intent(activity, WikiChampionInformations::class.java)
        intent.putExtra(Constants.WIKI_CHAMPION_KEY, champion.key)
        intent.putExtra(Constants.WIKI_CHAMPION_ID, champion.id)
        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        Log.i(TAG, "onCreate: ")
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        val actionSettings = menu.findItem(R.id.action_settings)
        actionSettings.isEnabled = false
        actionSettings.isVisible = false
        val actionFilter = menu.findItem(R.id.action_filter)
        actionFilter.isEnabled = true
        actionFilter.isVisible = true
        val actionChooseRegion = menu.findItem(R.id.action_region)
        actionChooseRegion.isVisible = false
        actionChooseRegion.isEnabled = false
        val actionSearch = menu.findItem(R.id.action_search)
        actionSearch.isVisible = false
        actionSearch.isEnabled = false
        val searchView = menu.findItem(R.id.action_search).actionView as SearchView
        searchView.visibility = View.GONE
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_filter -> {
                displayFilterDialog()
                return true
            }
            else -> {
            }
        }
        return false
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? { // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_wiki_data, container, false)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(Constants.KEY_CHAMPION_GRID_LAYOUT_MANAGER, rv_wiki_data.layoutManager?.onSaveInstanceState())
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        if (savedInstanceState != null && savedInstanceState.containsKey(Constants.KEY_CHAMPION_GRID_LAYOUT_MANAGER)) {
            savedRecyclerLayoutState = savedInstanceState.getParcelable(Constants.KEY_CHAMPION_GRID_LAYOUT_MANAGER)
            rv_wiki_data.layoutManager?.onRestoreInstanceState(savedRecyclerLayoutState)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //TODO : testing purpose => update code and move it at a better place
        application = activity?.application as SuperApplication
        fragment = this
        mSharedPreferences = activity!!.getPreferences(Context.MODE_PRIVATE)
        mEditor = mSharedPreferences.edit()
        if (mSharedPreferences.contains(Constants.KEY_INDEX_SELECTED_OPTION_FILTER)) {
            mIndexfilterOptionSelected = mSharedPreferences.getInt(Constants.KEY_INDEX_SELECTED_OPTION_FILTER, 0)
            if (filterOptions == null) {
                filterOptions = activity!!.resources.getStringArray(R.array.filter_options_array)
                mfilterOptionSelected = filterOptions!![mIndexfilterOptionSelected]
            }
        } else {
            mEditor.putInt(Constants.KEY_INDEX_SELECTED_OPTION_FILTER, 0) // All
            mEditor.apply()
        }
        initData()
    }

    private fun initData() {
        championList = arrayListOf()
        loadChampions()
        adapter = ChampionsAdapter(application, championList, championListener, fragment)
        mGridLayoutManager = GridLayoutManager(application, 4)
        rv_wiki_data.layoutManager = mGridLayoutManager
        rv_wiki_data.adapter = adapter
        rv_wiki_data.setHasFixedSize(true)
    }

    private fun loadChampions() { //TODO : testing purpose => update code and move it at a better place
        getChampions(
                RetrofitConstants.ACTION_COMPLETE,
                championHandler,
                application)
    }

    private fun displayFilterDialog() { // setup the alert builder
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Filter")
        //add a checkbox list
        if (filterOptions == null) {
            filterOptions = activity!!.resources.getStringArray(R.array.filter_options_array)
            mfilterOptionSelected = filterOptions!![mIndexfilterOptionSelected]
        }
        builder.setSingleChoiceItems(filterOptions, mIndexfilterOptionSelected) { dialogInterface: DialogInterface, i: Int ->
            mEditor = mSharedPreferences.edit()
            mEditor.putInt(Constants.KEY_INDEX_SELECTED_OPTION_FILTER, i)
            mEditor.apply()
            mIndexfilterOptionSelected = i
            mfilterOptionSelected = filterOptions!![mIndexfilterOptionSelected]
            // Send Filter option
            adapter.filter.filter(mfilterOptionSelected!!.toLowerCase(Locale.ROOT))
            dialogInterface.dismiss()
        }
        // create and show the alert dialog
        val dialog = builder.create()
        dialog.show()
    }

    private val championHandler = Handler(Handler.Callback { msg: Message ->
        when (msg.what) {
            RetrofitConstants.ACTION_COMPLETE -> {
                Log.i(TAG, "ACTION_COMPLETE ")
                val championViewModel = ViewModelProviders.of(fragment).get(ChampionViewModel::class.java)
                championViewModel.champions.observe(viewLifecycleOwner, Observer { champions: List<Champion> ->
                    if (champions.isNotEmpty()) {
                        championList.clear()
                        championList.addAll(champions)
                        if (mfilterOptionSelected != null) {
                            adapter.filter.filter(mfilterOptionSelected!!.toLowerCase(Locale.ROOT))
                        }
                        rv_wiki_data.layoutManager?.onRestoreInstanceState(savedRecyclerLayoutState)
                        adapter.notifyDataSetChanged()
                        updateWidget()
                    }
                })
            }
            RetrofitConstants.ACTION_ERROR -> {
            }
        }
        true
    })

    private fun updateWidget() {
        Log.i(TAG, "updateWidget: ")
        val intent = Intent(application.applicationContext, ChampionWidget::class.java)
        intent.action = Constants.ACTION_UPDATE_WIDGET
        val ids = AppWidgetManager.getInstance(application).getAppWidgetIds(ComponentName(application, ChampionWidget::class.java))
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, ids)
        activity!!.sendBroadcast(intent)
    }
}