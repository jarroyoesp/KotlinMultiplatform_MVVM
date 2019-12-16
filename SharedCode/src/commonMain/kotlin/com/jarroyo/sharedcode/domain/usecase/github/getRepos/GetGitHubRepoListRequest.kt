package com.jarroyo.sharedcode.domain.usecase.github.getRepos

import com.jarroyo.sharedcode.domain.usecase.base.BaseRequest

class GetGitHubRepoListRequest(val userName: String) : BaseRequest {
    override fun validate(): Boolean {
        return true
    }
}