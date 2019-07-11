package com.voronin.shakuro.contributorDetail.viewModel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.voronin.shakuro.contributorsList.models.CONTRIBUTOR_TAG
import com.voronin.shakuro.contributorsList.models.Contributor

class ContributorDetailViewModel : ViewModel() {

    val liveData = MutableLiveData<Contributor>()
    var contributor: Contributor? = null

    fun initialize(it: Bundle?) {
        contributor = it?.get(CONTRIBUTOR_TAG) as Contributor
        contributor.let {
            liveData.postValue(it)
        }
    }
}