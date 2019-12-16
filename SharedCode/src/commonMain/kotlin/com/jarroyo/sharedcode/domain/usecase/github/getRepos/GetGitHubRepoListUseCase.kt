package com.jarroyo.sharedcode.domain.usecase.github.getRepos

import com.jarroyo.sharedcode.base.Response
import com.jarroyo.sharedcode.data.repository.GitHubRepository
import com.jarroyo.sharedcode.domain.model.github.GitHubRepo
import com.jarroyo.sharedcode.domain.usecase.base.BaseUseCase


open class GetGitHubRepoListUseCase(val repository: GitHubRepository) : BaseUseCase<GetGitHubRepoListRequest, List<GitHubRepo>>() {

    override suspend fun run(): Response<List<GitHubRepo>> {
        return repository.getRepos(request!!)
    }
}