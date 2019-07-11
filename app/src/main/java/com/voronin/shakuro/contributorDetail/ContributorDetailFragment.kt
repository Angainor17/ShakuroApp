package com.voronin.shakuro.contributorDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.observe
import com.voronin.shakuro.R
import com.voronin.shakuro.contributorDetail.viewModel.ContributorDetailViewModel
import com.voronin.shakuro.databinding.ContributorDetailFragmentBinding

/**
 * Open after item in ContributorListFragment selected
 */
class ContributorDetailFragment : Fragment() {

    private lateinit var viewModel: ContributorDetailViewModel
    private lateinit var dataBinding: ContributorDetailFragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ContributorDetailViewModel::class.java)
        viewModel.refreshScreen()
        arguments.let {
            viewModel.initialize(it)
        }

        viewModel.liveData.observe(this) {
            dataBinding.contributor = it
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.contributor_detail_fragment, container, false)
        return dataBinding.root
    }
}