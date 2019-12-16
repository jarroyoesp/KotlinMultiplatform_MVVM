package com.jarroyo.sharedcode.data.repository

import com.jarroyo.kotlinmultiplatform.source.network.GitHubApi
import com.jarroyo.sharedcode.base.Response
import com.jarroyo.sharedcode.domain.model.github.GitHubRepo
import com.jarroyo.sharedcode.domain.usecase.github.getRepos.GetGitHubRepoListRequest


class GitHubRepository(
    private val gitHubApi: GitHubApi
) {

    /***********************************************************************************************
     * GET COUNTER
     **********************************************************************************************/
    suspend fun getRepos(request: GetGitHubRepoListRequest): Response<List<GitHubRepo>> {
        val response = gitHubApi.getGitHubRepoList(request.userName)
        return response
    }
}