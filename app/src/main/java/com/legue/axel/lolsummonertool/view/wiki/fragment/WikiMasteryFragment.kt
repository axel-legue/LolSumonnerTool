package com.legue.axel.lolsummonertool.view.wiki.fragment

import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.os.Parcelable
import android.util.Log
import android.view.*
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.legue.axel.lolsummonertool.Constants
import com.legue.axel.lolsummonertool.R
import com.legue.axel.lolsummonertool.SuperApplication
import com.legue.axel.lolsummonertool.view.adapter.MasteriesAdapter
import com.legue.axel.lolsummonertool.view.adapter.MasteriesAdapter.MasteryListener
import com.legue.axel.lolsummonertool.database.model.mastery.Mastery
import com.legue.axel.lolsummonertool.viewmodel.MasteryViewModel
import com.legue.axel.lolsummonertool.network.retrofit.RetrofitConstants
import com.legue.axel.lolsummonertool.network.retrofit.RetrofitHelper.getMasteries
import kotlinx.android.synthetic.main.fragment_wiki_data.*

class WikiMasteryFragment : Fragment() {
    companion object {
        private val TAG = WikiMasteryFragment::class.java.name
        @JvmStatic
        fun newInstance(page: Int, title: String?): WikiMasteryFragment {
            val masteryFragment = WikiMasteryFragment()
            val args = Bundle()
            args.putInt(Constants.KEY_WIKI_PAGE_NUMBER, page)
            args.putString(Constants.KEY_WIKI_PAGE_NAME, title)
            masteryFragment.arguments = args
            return masteryFragment
        }
    }

    private lateinit var adapter: MasteriesAdapter
    private lateinit var application: SuperApplication
    private lateinit var fragment: WikiMasteryFragment
    private lateinit var masteryList: MutableList<Mastery>
    private var savedRecyclerLayoutState: Parcelable? = null
    private var masteryListener = object : MasteryListener {
        override fun masterySelected(position: Int, mastery: Mastery?) {
            Toast.makeText(application, getString(R.string.toast_mastery_details, position), Toast.LENGTH_SHORT).show()
        }
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
        actionFilter.isEnabled = false
        actionFilter.isVisible = false
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
            R.id.action_filter -> return true
            else -> {
            }
        }
        return false
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_wiki_data, container, false)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(Constants.KEY_MASTERY_GRID_LAYOUT_MANAGER, rv_wiki_data.layoutManager!!.onSaveInstanceState())
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        if (savedInstanceState != null && savedInstanceState.containsKey(Constants.KEY_MASTERY_GRID_LAYOUT_MANAGER)) {
            savedRecyclerLayoutState = savedInstanceState.getParcelable(Constants.KEY_MASTERY_GRID_LAYOUT_MANAGER)
            rv_wiki_data.layoutManager!!.onRestoreInstanceState(savedRecyclerLayoutState)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        application = activity?.application as SuperApplication
        fragment = this
        initData()
    }

    private fun initData() {
        masteryList = arrayListOf()
        loadData()
        adapter = MasteriesAdapter(application, masteryList, masteryListener, fragment)
        rv_wiki_data.layoutManager = GridLayoutManager(application, 4)
        rv_wiki_data.adapter = adapter
        rv_wiki_data.setHasFixedSize(true)
    }

    private fun loadData() {
        getMasteries(
                RetrofitConstants.ACTION_COMPLETE,
                masteryHandler,
                application)
    }

    private val masteryHandler = Handler(Handler.Callback { msg: Message ->
        when (msg.what) {
            RetrofitConstants.ACTION_COMPLETE -> {
                Log.i(TAG, "ACTION_COMPLETE ")
                val masteryViewModel = ViewModelProviders.of(fragment).get(MasteryViewModel::class.java)
                masteryViewModel.masteries.observe(viewLifecycleOwner, Observer { masteries: List<Mastery> ->
                    if (masteries.isNotEmpty()) {
                        masteryList.clear()
                        masteryList.addAll(masteries)
                        rv_wiki_data.layoutManager!!.onRestoreInstanceState(savedRecyclerLayoutState)
                        adapter.notifyDataSetChanged()
                    }
                })
            }
            RetrofitConstants.ACTION_ERROR -> {
            }
        }
        true
    })
}