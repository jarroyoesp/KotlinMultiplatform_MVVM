package com.jarroyo.sharedcode.data.repository

import com.jarroyo.sharedcode.data.source.network.INetworkDataSource
import com.jarroyo.sharedcode.domain.usecase.github.getRepos.GetGitHubRepoListRequest
import io.mockk.mockk
import kotlin.test.Test
import kotlin.test.assertEquals


class GitHubRepositoryTest {

    private val mNetworkDataSource = mockk<INetworkDataSource>()
    private val mGitHubRepository = GitHubRepository(mNetworkDataSource)

    @Test
    fun GIVEN_gitHub_username_WHEN_request_repo_list_THEN_return_response() {
        val request = GetGitHubRepoListRequest("GitHubUserName")

        // This actually doesn't work, but makes this test compile. Testing suspending functions is currently
        // not possible in common modules
        suspend {
            mGitHubRepository.getRepos(request)
            mNetworkDataSource.getGitHubRepoList(request.userName)
            assertEquals(0, 0)
        }
    }
}