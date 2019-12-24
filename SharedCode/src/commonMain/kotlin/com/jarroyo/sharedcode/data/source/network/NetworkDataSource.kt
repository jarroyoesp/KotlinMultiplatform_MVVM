package com.jarroyo.sharedcode.data.source.network

import com.jarroyo.kotlinmultiplatform.source.network.GitHubApi
import com.jarroyo.sharedcode.base.Response
import com.jarroyo.sharedcode.domain.model.github.GitHubRepo

class NetworkDataSource(private val gitHubApi: GitHubApi): INetworkDataSource() {
    /**
     * GET GITHUB REPO LIST
     */
    override suspend fun getGitHubRepoList(userName: String): Response<List<GitHubRepo>> {
       return gitHubApi.getGitHubRepoList(userName)
    }
}