package com.voronin.shakuro.contributorsList.client

import com.voronin.shakuro.contributorsList.models.ContributorResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ContributorApi {

    @GET("/users")
    suspend fun getContributors(
        @Query("since") since: Int
    ): ArrayList<ContributorResponse>
}