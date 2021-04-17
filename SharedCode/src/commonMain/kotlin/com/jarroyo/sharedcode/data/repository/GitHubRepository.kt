package com.jarroyo.sharedcode.data.repository

import com.jarroyo.sharedcode.base.Response
import com.jarroyo.sharedcode.data.source.disk.IDiskDataSource
import com.jarroyo.sharedcode.data.source.network.INetworkDataSource
import com.jarroyo.sharedcode.db.User
import com.jarroyo.sharedcode.domain.model.github.GitHubRepo
import com.jarroyo.sharedcode.domain.usecase.github.getRepos.GetGitHubRepoListRequest
import com.jarroyo.sharedcode.domain.usecase.user.CreateUserRequest


class GitHubRepository(
    private val diskDataSource: IDiskDataSource,
    private val networkDataSource: INetworkDataSource
) {

    /***********************************************************************************************
     * GET USER LIST
     **********************************************************************************************/
    suspend fun getUserList(): Response<List<User>> {
        return Response.Success(diskDataSource.getUserList())
    }

    /***********************************************************************************************
     * CREATE USER
     **********************************************************************************************/
    suspend fun createUser(request: CreateUserRequest?): Response<List<User>> {
        diskDataSource.insertUser(request?.userName ?: "Unknown")
        return Response.Success(diskDataSource.getUserList())
    }

    /***********************************************************************************************
     * GET REPOS
     **********************************************************************************************/
    suspend fun getRepos(request: GetGitHubRepoListRequest): Response<List<GitHubRepo>> {
        val userList = diskDataSource.getUserList()
        val response = networkDataSource.getGitHubRepoList(userList[0].name)
        return response
    }
}