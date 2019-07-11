package com.voronin.shakuro.app

import android.app.Application
import com.voronin.shakuro.contributorsList.client.ContributorClient
import com.voronin.shakuro.contributorsList.client.ContributorClientImpl
import com.voronin.shakuro.contributorsList.client.GITHUB_BASE_URL
import com.voronin.shakuro.utils.createRetrofit
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton
import retrofit2.Retrofit


class App : Application() {

    companion object {
        val kodein = Kodein {
            bind<Retrofit>() with singleton { createRetrofit(GITHUB_BASE_URL) }
            bind<ContributorClient>() with singleton { ContributorClientImpl(instance()) }
        }
    }
}