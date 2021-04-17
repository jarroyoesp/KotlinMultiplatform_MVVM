package com.jarroyo.sharedcode.data.source.network

import com.jarroyo.kotlinmultiplatform.source.network.GitHubApi
import com.jarroyo.sharedcode.base.Response
import com.jarroyo.sharedcode.domain.model.github.GitHubRepo
import io.ktor.client.*

class NetworkDataSource(private val gitHubApi: GitHubApi,
                        private val client: HttpClient): INetworkDataSource() {
    /**
     * GET GITHUB REPO LIST
     */
    override suspend fun getGitHubRepoList(userName: String): Response<List<GitHubRepo>> {
       return gitHubApi.getGitHubRepoList(client, userName)
    }
}