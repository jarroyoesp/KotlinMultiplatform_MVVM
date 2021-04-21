package com.jarroyo.sharedcode.data.repository

import com.jarroyo.sharedcode.base.Response
import com.jarroyo.sharedcode.base.exception.NetworkConnectionException
import com.jarroyo.sharedcode.data.source.disk.DiskDataSourceImpl
import com.jarroyo.sharedcode.data.source.disk.IDiskDataSource
import com.jarroyo.sharedcode.data.source.network.INetworkDataSource
import com.jarroyo.sharedcode.domain.usecase.github.getRepos.GetGitHubRepoListRequest
import com.jarroyo.sharedcode.runTest
import com.jarroyo.sharedcode.db.User
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.unmockkAll
import io.mockk.coEvery
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.withContext
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class GitHubRepositoryTest {
    @MockK
    lateinit var networkDataSource: INetworkDataSource
    @MockK
    lateinit var diskDataSource: IDiskDataSource

    lateinit var gitHubRepository: GitHubRepository

    @BeforeTest
    fun setUp() {
        MockKAnnotations.init(this)
        gitHubRepository = GitHubRepository(diskDataSource, networkDataSource)
    }

    @AfterTest
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun GIVEN_gitHub_username_WHEN_request_repo_list_THEN_return_responseSuccess() = runTest {
        coEvery { diskDataSource.getUserList() } returns listOf(User(1L, "user"))
        coEvery { networkDataSource.getGitHubRepoList(any()) } returns Response.Success(emptyList())
        val request = GetGitHubRepoListRequest("GitHubUserName")

        val response = gitHubRepository.getRepos(request)

        assert(response is Response.Success)

    }

    @Test
    fun GIVEN_gitHub_username_WHEN_request_repo_list_THEN_return_responseError() = runTest {
        coEvery { diskDataSource.getUserList() } returns listOf(User(1L, "user"))
        coEvery { networkDataSource.getGitHubRepoList(any()) } returns Response.Error(NetworkConnectionException())
        val request = GetGitHubRepoListRequest("GitHubUserName")

        val response = gitHubRepository.getRepos(request)

        assert(response is Response.Error)

    }
}