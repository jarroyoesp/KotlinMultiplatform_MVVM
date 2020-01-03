package com.jarroyo.sharedcode.data.repository

import com.jarroyo.sharedcode.base.Response
import com.jarroyo.sharedcode.data.source.network.INetworkDataSource
import com.jarroyo.sharedcode.domain.model.github.GitHubRepo
import com.jarroyo.sharedcode.domain.usecase.github.getRepos.GetGitHubRepoListRequest


class GitHubRepository(
    private val networkDataSource: INetworkDataSource
) {

    /***********************************************************************************************
     * GET REPOS
     **********************************************************************************************/
    suspend fun getRepos(request: GetGitHubRepoListRequest): Response<List<GitHubRepo>> {
        val response = networkDataSource.getGitHubRepoList(request.userName)
        return response
    }
}