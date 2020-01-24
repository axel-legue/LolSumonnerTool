package com.legue.axel.lolsummonertool.wiki.fragment

import android.content.Intent
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
import com.legue.axel.lolsummonertool.adapter.ItemAdapter
import com.legue.axel.lolsummonertool.database.model.item.Item
import com.legue.axel.lolsummonertool.database.viewmodel.ItemViewModel
import com.legue.axel.lolsummonertool.network.retrofit.RetrofitConstants
import com.legue.axel.lolsummonertool.network.retrofit.RetrofitHelper.getItems
import com.legue.axel.lolsummonertool.wiki.activity.WikiItemInformation
import kotlinx.android.synthetic.main.fragment_wiki_data.*

class WikiItemFragment : Fragment() {

    companion object {
        private val TAG = WikiItemFragment::class.java.name
        @JvmStatic
        fun newInstance(page: Int, title: String?): WikiItemFragment {
            val itemsFragment = WikiItemFragment()
            val args = Bundle()
            args.putInt(Constants.KEY_WIKI_PAGE_NUMBER, page)
            args.putString(Constants.KEY_WIKI_PAGE_NAME, title)
            itemsFragment.arguments = args
            return itemsFragment
        }
    }

    private lateinit var adapter: ItemAdapter
    private lateinit var application: SuperApplication
    private lateinit var fragment: WikiItemFragment
    private lateinit var itemList: MutableList<Item>
    private var savedRecyclerLayoutState: Parcelable? = null
    private var itemListener = object : ItemAdapter.ItemListener {
        override fun itemSelected(position: Int, item: Item) {
            val intent = Intent(activity, WikiItemInformation::class.java)
            intent.putExtra(Constants.WIKI_ITEM_ID, item.id)
            startActivity(intent)
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
            R.id.action_filter ->  // TODO: 16/05/2019 Implement filter Item by type of stats
                return true
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
        outState.putParcelable(Constants.KEY_ITEM_GRID_LAYOUT_MANAGER, rv_wiki_data.layoutManager?.onSaveInstanceState())
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        if (savedInstanceState != null && savedInstanceState.containsKey(Constants.KEY_ITEM_GRID_LAYOUT_MANAGER)) {
            savedRecyclerLayoutState = savedInstanceState.getParcelable(Constants.KEY_ITEM_GRID_LAYOUT_MANAGER)
            rv_wiki_data.layoutManager?.onRestoreInstanceState(savedRecyclerLayoutState)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        application = activity?.application as SuperApplication
        fragment = this
        initData()
    }

    private fun initData() {
        Log.i(TAG, "initData: ")
        itemList = arrayListOf()
        loadItems()
        adapter = ItemAdapter(application, itemList, itemListener, fragment)
        rv_wiki_data.layoutManager = GridLayoutManager(application, 4)
        rv_wiki_data.adapter = adapter
        rv_wiki_data.setHasFixedSize(true)
    }

    private fun loadItems() {
        Log.i(TAG, "loadItems: ")
        getItems(
                RetrofitConstants.ACTION_COMPLETE,
                itemHandler,
                application)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG, "onDestroy: ")
        itemList.clear()
    }

    private val itemHandler = Handler(Handler.Callback { msg: Message ->
        when (msg.what) {
            RetrofitConstants.ACTION_COMPLETE -> {
                Log.i(TAG, "ACTION_COMPLETE ")
                val itemViewModel = ViewModelProviders.of(fragment).get(ItemViewModel::class.java)
                itemViewModel.items.observe(viewLifecycleOwner, Observer { items: List<Item> ->
                    if (items.isNotEmpty()) {
                        itemList.clear()
                        itemList.addAll(items)
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