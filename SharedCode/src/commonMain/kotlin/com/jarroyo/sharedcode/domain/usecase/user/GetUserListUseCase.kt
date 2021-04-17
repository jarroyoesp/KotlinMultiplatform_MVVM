package com.jarroyo.sharedcode.domain.usecase.user

import com.jarroyo.sharedcode.base.Response
import com.jarroyo.sharedcode.data.repository.GitHubRepository
import com.jarroyo.sharedcode.db.User
import com.jarroyo.sharedcode.domain.model.github.GitHubRepo
import com.jarroyo.sharedcode.domain.usecase.base.BaseUseCase


open class GetUserListUseCase(val repository: GitHubRepository) : BaseUseCase<Nothing, List<User>>() {

    override suspend fun run(): Response<List<User>> {
        return repository.getUserList()
    }
}