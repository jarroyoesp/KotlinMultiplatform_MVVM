package com.jarroyo.sharedcode.data.source.network

import com.jarroyo.sharedcode.base.Response
import com.jarroyo.sharedcode.domain.model.github.GitHubRepo

abstract class INetworkDataSource {
    abstract suspend fun getGitHubRepoList(userName: String): Response<List<GitHubRepo>>
}