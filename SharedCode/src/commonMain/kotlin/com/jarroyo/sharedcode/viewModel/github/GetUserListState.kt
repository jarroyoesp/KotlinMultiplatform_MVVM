package com.jarroyo.sharedcode.viewModel.github

import com.jarroyo.sharedcode.base.Response
import com.jarroyo.sharedcode.db.User
import com.jarroyo.sharedcode.domain.model.github.GitHubRepo


sealed class GetUserListState {
    abstract val response: Response<List<User>>?
}
data class SuccessGetUserListState(override val response: Response<List<User>>) : GetUserListState()
data class LoadingGetUserListState(override val response: Response<List<User>>? = null) : GetUserListState()
data class ErrorGetUserListState(override val response: Response<List<User>>) : GetUserListState()