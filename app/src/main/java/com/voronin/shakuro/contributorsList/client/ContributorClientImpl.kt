package com.voronin.shakuro.contributorsList.client

import retrofit2.Retrofit

/**
 * Implementation of {@link ContributorClient}
 */
class ContributorClientImpl(retrofit: Retrofit) : ContributorClient {

    val contributorApi = retrofit.create(ContributorApi::class.java)


}