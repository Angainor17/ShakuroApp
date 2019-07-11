package com.voronin.shakuro

import com.voronin.shakuro.contributorsList.client.ContributorClient
import com.voronin.shakuro.contributorsList.client.ContributorClientImpl
import com.voronin.shakuro.contributorsList.client.GITHUB_BASE_URL
import com.voronin.shakuro.net.NetRequestTest
import com.voronin.shakuro.utils.createRetrofit
import kotlinx.coroutines.runBlocking
import org.junit.Test

class ApiTest : NetRequestTest() {

    private val retrofit = createRetrofit(GITHUB_BASE_URL)
    private val client: ContributorClient = ContributorClientImpl(retrofit)

    /**
     * Check if request is correct
     */
    @Test
    fun getContributorsTest() = runBlocking {
        val list = client.getContributors(0)
        assert(list.isNotEmpty())
    }
}