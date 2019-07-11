package com.voronin.shakuro.contributorsList.models

import java.io.Serializable

const val CONTRIBUTOR_TAG = "contributor"

/**
 * Contributor business model
 */
data class Contributor(val apiModel: ContributorResponse) : Serializable {
    val title: String = apiModel.login
    val subTitle: String = apiModel.id.toString()
    val avatarUrl: String = apiModel.avatarUrl
}