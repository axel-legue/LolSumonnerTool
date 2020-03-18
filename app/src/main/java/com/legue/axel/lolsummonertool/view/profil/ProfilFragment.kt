package com.legue.axel.lolsummonertool.view.profil


import android.app.AlertDialog
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import com.legue.axel.lolsummonertool.Constants
import com.legue.axel.lolsummonertool.R
import com.legue.axel.lolsummonertool.network.retrofit.RetrofitConstants


class ProfilFragment : Fragment() {

    companion object {
        private val TAG = ProfilFragment::class.java.name

        // TODO: Rename and change types and number of parameters
        fun newInstance(param1: String, param2: String): ProfilFragment {
            return ProfilFragment()
        }
    }

    private lateinit var mRegionSelected: String
    private var mRegionPrefix: String? = null
    private lateinit var regions: Array<String>
    private lateinit var prefixRegions: Array<String>
    private var mIndexRegionSelected = 0
    private lateinit var mSharedPreferences: SharedPreferences
    private lateinit var mEditor: SharedPreferences.Editor

    private val mOnQueryTextListener = object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(s: String): Boolean {
            return if (s.matches(RetrofitConstants.REGEX_VALIDATION_NAME.toRegex())) {
                false
            } else {
                // TODO: 27/04/2019 Change waring text  and icons
                AlertDialog.Builder(context)
                        .setTitle(R.string.search_error_title)
                        .setMessage(R.string.search_error_message)
                        .setPositiveButton(android.R.string.yes) { dialog, which -> }
                        .setIcon(R.drawable.ic_warning_red_24dp)
                        .show()
                true
            }
        }

        override fun onQueryTextChange(s: String): Boolean {
            return false
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        Log.i(TAG, "onCreate: ")


    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)

        val actionSettings = menu!!.findItem(R.id.action_settings)
        actionSettings.isEnabled = false
        actionSettings.isVisible = false

        val actionFilter = menu.findItem(R.id.action_filter)
        actionFilter.isEnabled = false
        actionFilter.isVisible = false

        val actionChooseRegion = menu.findItem(R.id.action_region)
        actionChooseRegion.isVisible = true
        actionChooseRegion.isEnabled = true

        val searchView = menu.findItem(R.id.action_search).actionView as SearchView
        searchView.isIconifiedByDefault = false
        searchView.setOnQueryTextListener(mOnQueryTextListener)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_region) {
            displayRegion()
            return true
        }
        return false
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profil, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.i(TAG, "onViewCreated: ")
        activity!!.invalidateOptionsMenu()

        mSharedPreferences = activity!!.getPreferences(Context.MODE_PRIVATE)
        mEditor = mSharedPreferences.edit()

        if (mSharedPreferences.contains(Constants.KEY_INDEX_SELECTED_REGION)) {
            mIndexRegionSelected = mSharedPreferences.getInt(Constants.KEY_INDEX_SELECTED_REGION, 0)
        } else {
            mEditor.putInt(Constants.KEY_INDEX_SELECTED_REGION, 1) // Euw region
            mEditor.apply()
        }

        if (mSharedPreferences.contains(Constants.KEY_PREFIX_SELECTED_REGION)) {
            mRegionPrefix = mSharedPreferences.getString(Constants.KEY_PREFIX_SELECTED_REGION, null)
        } else {
            mEditor.putString(Constants.KEY_PREFIX_SELECTED_REGION, getString(R.string.default_region))
            mEditor.apply()
        }

    }


    private fun displayRegion() {
        // setup the alert builder
        val builder = AlertDialog.Builder(context)
        builder.setTitle(getString(R.string.choose_region))

        // add a checkbox list
        prefixRegions = activity!!.resources.getStringArray(R.array.regions_index_array)
        regions = activity!!.resources.getStringArray(R.array.regions_array)
        mRegionSelected = regions[mIndexRegionSelected]


        builder.setSingleChoiceItems(regions, mIndexRegionSelected) { dialogInterface, i ->
            mEditor = mSharedPreferences.edit()
            mEditor.putInt(Constants.KEY_INDEX_SELECTED_REGION, i)
            mEditor.putString(Constants.KEY_PREFIX_SELECTED_REGION, prefixRegions[i])
            mEditor.apply()

            mIndexRegionSelected = i
            mRegionSelected = regions[mIndexRegionSelected]
            mRegionPrefix = prefixRegions[i]
            dialogInterface.dismiss()
        }

        // create and show the alert dialog
        val dialog = builder.create()
        dialog.show()
    }

}
