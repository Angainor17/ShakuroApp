package com.voronin.shakuro.contributorsList.viewModel

import android.util.Log
import androidx.annotation.NonNull
import androidx.core.os.bundleOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PositionalDataSource
import com.voronin.shakuro.R
import com.voronin.shakuro.app.App
import com.voronin.shakuro.contributorsList.client.ContributorClient
import com.voronin.shakuro.contributorsList.models.CONTRIBUTOR_TAG
import com.voronin.shakuro.contributorsList.models.Contributor
import com.voronin.shakuro.navActivity.viewModel.NavViewModel
import kotlinx.coroutines.*
import org.kodein.di.generic.instance
import kotlin.coroutines.CoroutineContext

const val PAGE_SIZE = 30

class ContributorListViewModel : ViewModel() {

    private val parentJob = Job()
    private val coroutineContext: CoroutineContext = parentJob + Dispatchers.Default

    private val client: ContributorClient by App.kodein.instance()
    private val navViewModel: NavViewModel by App.kodein.instance()

    val contributorsList = ArrayList<Contributor>()
    val loadingLiveData = MutableLiveData<Boolean>()

    val dataSource = ContributorDataSource()

    override fun onCleared() {
        super.onCleared()
        cancelAllRequests()
    }

    fun onContributorSelected(it: Contributor) {
        navViewModel.navigateScreen(
            R.id.action_contributorListScreen_to_contributorDetailScreen,
            bundleOf(CONTRIBUTOR_TAG to it)
        )
    }

    private fun setLoading(isVisible: Boolean) {
        loadingLiveData.postValue(isVisible)
    }

    private fun cancelAllRequests() = coroutineContext.cancel()

    inner class ContributorDataSource : PositionalDataSource<Contributor>() {

        override fun loadInitial(@NonNull params: LoadInitialParams, @NonNull callback: LoadInitialCallback<Contributor>) {
            loadPage(0) { callback.onResult(it, 0) }
        }

        override fun loadRange(@NonNull params: LoadRangeParams, @NonNull callback: LoadRangeCallback<Contributor>) {
            loadPage(params.startPosition / PAGE_SIZE) { callback.onResult(it) }
        }

        private fun loadPage(pageNumber: Int, callback: (List<Contributor>) -> Unit) {
            setLoading(true)
            CoroutineScope(coroutineContext).launch {
                try {
                    val list = client.getContributors(PAGE_SIZE * pageNumber)
                    callback.invoke(list)
                } catch (e: Exception) {
                    Log.d("debugCustom", e.message)
                    //show error message
                } finally {
                    setLoading(false)
                }
            }
        }
    }
}