package com.voronin.shakuro.contributorsList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.observe
import androidx.paging.PagedList
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.voronin.shakuro.R
import com.voronin.shakuro.contributorsList.adapters.ContributorListAdapter
import com.voronin.shakuro.contributorsList.models.Contributor
import com.voronin.shakuro.contributorsList.viewModel.ContributorListViewModel
import com.voronin.shakuro.contributorsList.viewModel.PAGE_SIZE
import com.voronin.shakuro.utils.MainThreadExecutor
import kotlinx.android.synthetic.main.contributor_list_fragment.*
import java.util.concurrent.Executors

class ContributorListFragment : Fragment() {

    private lateinit var viewModel: ContributorListViewModel
    private lateinit var listAdapter: ContributorListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()

        initListAdapter()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.contributor_list_fragment, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this).get(ContributorListViewModel::class.java)
        viewModel.loadingLiveData.observe(this) {
            swipeRefresh.isEnabled = it
            swipeRefresh.isRefreshing = it
        }
    }

    private fun initViews() {
        contributorList.itemAnimator = DefaultItemAnimator()
        contributorList.layoutManager = LinearLayoutManager(context)
        contributorList.adapter = listAdapter
        listAdapter.onClickListener = { viewModel.onContributorSelected(it) }

        swipeRefresh.isEnabled = false
    }

    private fun initListAdapter() {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(PAGE_SIZE)
            .build()

        val pagedList = PagedList.Builder<Int, Contributor>(viewModel.dataSource, config)
            .setFetchExecutor(Executors.newSingleThreadExecutor())
            .setNotifyExecutor(MainThreadExecutor())
            .build()

        listAdapter = ContributorListAdapter(object : DiffUtil.ItemCallback<Contributor>() {
            override fun areItemsTheSame(oldItem: Contributor, newItem: Contributor) = oldItem == newItem
            override fun areContentsTheSame(oldItem: Contributor, newItem: Contributor) = oldItem == newItem
        })
        listAdapter.submitList(pagedList)
    }
}