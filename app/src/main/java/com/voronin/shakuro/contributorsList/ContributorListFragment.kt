package com.voronin.shakuro.contributorsList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.observe
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.voronin.shakuro.R
import com.voronin.shakuro.contributorsList.adapters.ContributorListAdapter
import com.voronin.shakuro.contributorsList.viewModel.ContributorListViewModel
import com.voronin.shakuro.utils.BaseFragment
import kotlinx.android.synthetic.main.contributor_list_fragment.*

class ContributorListFragment : BaseFragment() {

    private lateinit var viewModel: ContributorListViewModel
    private val listAdapter = ContributorListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ContributorListViewModel::class.java)
        viewModel.contributorLiveData.observe(this) {
            listAdapter.update(it)
        }
        viewModel.fetchList()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.contributor_list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        contributorList.itemAnimator = DefaultItemAnimator()
        contributorList.layoutManager = LinearLayoutManager(context)
        contributorList.adapter = listAdapter
    }
}