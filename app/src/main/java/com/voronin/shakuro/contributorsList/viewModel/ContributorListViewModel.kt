package com.voronin.shakuro.contributorsList.viewModel

import androidx.core.os.bundleOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.voronin.shakuro.R
import com.voronin.shakuro.app.App
import com.voronin.shakuro.contributorsList.client.ContributorClient
import com.voronin.shakuro.contributorsList.models.CONTRIBUTOR_TAG
import com.voronin.shakuro.contributorsList.models.Contributor
import com.voronin.shakuro.navActivity.viewModel.NavViewModel
import kotlinx.coroutines.*
import org.kodein.di.generic.instance
import kotlin.coroutines.CoroutineContext

class ContributorListViewModel : ViewModel() {

    private val parentJob = Job()
    private val coroutineContext: CoroutineContext = parentJob + Dispatchers.Default
    private val scope = CoroutineScope(coroutineContext)

    private val client: ContributorClient by App.kodein.instance()
    private val navViewModel: NavViewModel by App.kodein.instance()

    val contributorsList = ArrayList<Contributor>()
    val contributorLiveData = MutableLiveData<ArrayList<Contributor>>()

    override fun onCleared() {
        super.onCleared()
        cancelAllRequests()
    }

    fun fetchList() {
        if (contributorsList.isEmpty()) {
            scope.launch {
                try {
                    val list = client.getContributors(0)
                    contributorLiveData.postValue(list)
                } catch (e: Exception) {

                }
            }
        }
    }

    fun onContributorSelected(it: Contributor) {
        navViewModel.navigateScreen(
            R.id.action_contributorListScreen_to_contributorDetailScreen,
            bundleOf(CONTRIBUTOR_TAG to it)
        )
    }

    private fun cancelAllRequests() = coroutineContext.cancel()
}