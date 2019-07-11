package com.voronin.shakuro.contributorsList.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.voronin.shakuro.app.App
import com.voronin.shakuro.contributorsList.client.ContributorClient
import com.voronin.shakuro.contributorsList.models.Contributor
import kotlinx.coroutines.*
import org.kodein.di.generic.instance
import kotlin.coroutines.CoroutineContext

class ContributorListViewModel : ViewModel() {

    private val parentJob = Job()
    private val coroutineContext: CoroutineContext = parentJob + Dispatchers.Default
    private val scope = CoroutineScope(coroutineContext)


    val client: ContributorClient by App.Companion.kodein.instance()
    val contributorsList = ArrayList<Contributor>()
    val contributorLiveData = MutableLiveData<ArrayList<Contributor>>()

    fun fetchList() {
        if (contributorsList.isEmpty()) {
            try {
                scope.launch {
                    contributorLiveData.postValue(client.getContributors(0))
                }
            } catch (e: Exception) {

            }
        }
    }

    fun cancelAllRequests() = coroutineContext.cancel()
}