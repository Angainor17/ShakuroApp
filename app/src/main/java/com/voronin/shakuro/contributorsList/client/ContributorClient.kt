package com.voronin.shakuro.contributorsList.client

import com.voronin.shakuro.contributorsList.models.Contributor

interface ContributorClient {

    suspend fun getContributors(since: Int): ArrayList<Contributor>

}