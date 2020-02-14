package com.legue.axel.lolsummonertool.view.wiki.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.legue.axel.lolsummonertool.R
import com.legue.axel.lolsummonertool.SuperApplication
import com.legue.axel.lolsummonertool.view.adapter.WikiFragmentAdapter
import kotlinx.android.synthetic.main.fragment_wiki_main.*

class WikiFragment : Fragment() {

    companion object {

        private val TAG = WikiFragment::class.java.name
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_wiki_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //pbLoading.setVisibility(View.GONE);

        //TODO : testing purpose => update code and move it at a better place
        val application = activity?.application as SuperApplication

        val mWikiFragmentAdapter = WikiFragmentAdapter(childFragmentManager)
        vp_wiki.adapter = mWikiFragmentAdapter
        tl_wiki.setupWithViewPager(vp_wiki)

        vp_wiki.currentItem = 0
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        retainInstance = true
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }

}
