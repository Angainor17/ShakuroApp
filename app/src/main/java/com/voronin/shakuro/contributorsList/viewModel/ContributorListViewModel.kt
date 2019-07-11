package com.voronin.shakuro.contributorsList.viewModel

import android.util.Log
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

    private val client: ContributorClient by App.kodein.instance()
    val contributorsList = ArrayList<Contributor>()
    val contributorLiveData = MutableLiveData<ArrayList<Contributor>>()

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
        Log.d("debugCustom", "" + it.title)
    }

    private fun cancelAllRequests() = coroutineContext.cancel()

    override fun onCleared() {
        super.onCleared()
        cancelAllRequests()
    }
}