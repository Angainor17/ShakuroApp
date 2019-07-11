package com.voronin.shakuro.contributorsList.client

import com.voronin.shakuro.contributorsList.models.Contributor
import retrofit2.Retrofit

/**
 * Implementation of {@link ContributorClient}
 */
class ContributorClientImpl(retrofit: Retrofit) : ContributorClient {

    private val contributorApi = retrofit.create(ContributorApi::class.java)

    override suspend fun getContributors(since: Int) =
        ArrayList(contributorApi.getContributors(since).await().map { Contributor(it) })

}