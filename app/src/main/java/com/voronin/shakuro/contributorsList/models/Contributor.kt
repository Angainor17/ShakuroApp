package com.voronin.shakuro.contributorsList.models

/**
 * Contributor business model
 */
class Contributor(apiModel: ContributorResponse) {
    val title: String = apiModel.login
    val subTitle: String = apiModel.id.toString()
    val avatarUrl: String = apiModel.avatarUrl
}