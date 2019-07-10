package com.voronin.shakuro.contributorsList.client

import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query


interface ContributorApi {

    @GET("users")
    fun getContributors(
        @Query("since") since: Int
    ): Deferred<ArrayList<ContributorResponse>>
}