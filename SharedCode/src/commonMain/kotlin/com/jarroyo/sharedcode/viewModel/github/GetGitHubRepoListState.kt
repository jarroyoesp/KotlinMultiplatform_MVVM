package com.jarroyo.sharedcode.viewModel.github

import com.jarroyo.sharedcode.base.Response
import com.jarroyo.sharedcode.domain.model.github.GitHubRepo


sealed class GetGitHubRepoListState {
    abstract val response: Response<List<GitHubRepo>>?
}
data class SuccessGetGitHubRepoListState(override val response: Response<List<GitHubRepo>>) : GetGitHubRepoListState()
data class LoadingGetGitHubRepoListState(override val response: Response<List<GitHubRepo>>? = null) : GetGitHubRepoListState()
data class ErrorGetGitHubRepoListState(override val response: Response<List<GitHubRepo>>) : GetGitHubRepoListState()