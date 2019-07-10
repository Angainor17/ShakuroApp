package com.voronin.shakuro.contributorsList.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.voronin.shakuro.contributorsList.models.Contributor

class ContributorListViewModel : ViewModel() {

    val contributorsList = ArrayList<Contributor>()

    val contributorLiveData = MutableLiveData<ArrayList<Contributor>>()

    fun fetchList() {
        if (contributorsList.isEmpty()) {

        }
    }
}