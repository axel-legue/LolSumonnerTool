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
import butterknife.ButterKnife
import com.legue.axel.lolsummonertool.Constants
import com.legue.axel.lolsummonertool.R
import com.legue.axel.lolsummonertool.SuperApplication
import com.legue.axel.lolsummonertool.view.adapter.SummonerSpellAdapter
import com.legue.axel.lolsummonertool.view.adapter.SummonerSpellAdapter.SummonerSpellListener
import com.legue.axel.lolsummonertool.database.model.summonerspell.SummonerSpell
import com.legue.axel.lolsummonertool.viewmodel.SummonerSpellViewModel
import com.legue.axel.lolsummonertool.network.retrofit.RetrofitConstants
import com.legue.axel.lolsummonertool.network.retrofit.RetrofitHelper.getSummonerSpells
import kotlinx.android.synthetic.main.fragment_wiki_data.*

class WikiSummonerSpellFragment : Fragment() {
    companion object {
        private val TAG = WikiSummonerSpellFragment::class.java.name
        @JvmStatic
        fun newInstance(page: Int, title: String?): WikiSummonerSpellFragment {
            val wikiMasteryFragment = WikiSummonerSpellFragment()
            val args = Bundle()
            args.putInt(Constants.KEY_WIKI_PAGE_NUMBER, page)
            args.putString(Constants.KEY_WIKI_PAGE_NAME, title)
            wikiMasteryFragment.arguments = args
            return wikiMasteryFragment
        }
    }

    private lateinit var adapter: SummonerSpellAdapter
    private lateinit var application: SuperApplication
    private lateinit var fragment: WikiSummonerSpellFragment
    private lateinit var summonerSpellList: MutableList<SummonerSpell>
    private var savedRecyclerLayoutState: Parcelable? = null
    private var summonerSpellListener = object : SummonerSpellListener {
        override fun SummonerSpellSelected(position: Int, summonerSpell: SummonerSpell?) {
            Toast.makeText(application, getString(R.string.toast_spell_details, position), Toast.LENGTH_SHORT).show()
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
        val view = inflater.inflate(R.layout.fragment_wiki_data, container, false)
        ButterKnife.bind(this, view)
        return view
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(Constants.KEY_SPELL_GRID_LAYOUT_MANAGER, rv_wiki_data.layoutManager!!.onSaveInstanceState())
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        if (savedInstanceState != null && savedInstanceState.containsKey(Constants.KEY_SPELL_GRID_LAYOUT_MANAGER)) {
            savedRecyclerLayoutState = savedInstanceState.getParcelable(Constants.KEY_SPELL_GRID_LAYOUT_MANAGER)
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

        summonerSpellList = arrayListOf()
        loadData()
        adapter = SummonerSpellAdapter(application, summonerSpellList, summonerSpellListener, fragment)
        rv_wiki_data.layoutManager = GridLayoutManager(application, 4)
        rv_wiki_data.adapter = adapter
        rv_wiki_data.setHasFixedSize(true)
    }

    private fun loadData() {
        getSummonerSpells(
                RetrofitConstants.ACTION_COMPLETE,
                summonerHandler,
                application)
    }

    private val summonerHandler = Handler(Handler.Callback { msg: Message ->
        when (msg.what) {
            RetrofitConstants.ACTION_COMPLETE -> {
                Log.i(TAG, "ACTION_COMPLETE ")
                val summonerSpellViewModel = ViewModelProviders.of(fragment).get(SummonerSpellViewModel::class.java)
                summonerSpellViewModel.summonerSpells.observe(viewLifecycleOwner, Observer { summonerSpells: List<SummonerSpell> ->
                    if (summonerSpells.isNotEmpty()) {
                        summonerSpellList.clear()
                        summonerSpellList.addAll(summonerSpells)
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