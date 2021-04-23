/*
 * Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package com.jarroyo.sharedcode.viewModel.github

import com.jarroyo.sharedcode.base.Response
import com.jarroyo.sharedcode.db.User
import com.jarroyo.sharedcode.di.KodeinInjector
import com.jarroyo.sharedcode.domain.model.github.GitHubRepo
import com.jarroyo.sharedcode.domain.usecase.github.getRepos.GetGitHubRepoListRequest
import com.jarroyo.sharedcode.domain.usecase.github.getRepos.GetGitHubRepoListUseCase
import com.jarroyo.sharedcode.domain.usecase.user.CreateUserRequest
import com.jarroyo.sharedcode.domain.usecase.user.CreateUserUseCase
import com.jarroyo.sharedcode.domain.usecase.user.GetUserListUseCase
import dev.icerock.moko.mvvm.livedata.MutableLiveData
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import org.kodein.di.instance

@ExperimentalCoroutinesApi
class GitHubViewModel : ViewModel() {

    // LIVE DATA
    var mGetGitHubRepoListLiveData =
        MutableLiveData<GetGitHubRepoListState>(LoadingGetGitHubRepoListState())

    var mGetUserListLiveData =
        MutableLiveData<GetUserListState>(LoadingGetUserListState())

    // USE CASE
    private val mGetGitHubRepoListUseCase by KodeinInjector.instance<GetGitHubRepoListUseCase>()
    private val mGetUserListUseCase by KodeinInjector.instance<GetUserListUseCase>()
    private val mCreateUserUseCase by KodeinInjector.instance<CreateUserUseCase>()

    /**
     * GET GITHUB REPO LIST
     */
    fun getGitHubRepoListMokko(username: String) {
        viewModelScope.launch() {

            mGetGitHubRepoListLiveData.postValue(LoadingGetGitHubRepoListState())

            val request = GetGitHubRepoListRequest(username)
            val response = mGetGitHubRepoListUseCase.execute(request)
            processGitHubRepoListResponse(response)
        }
    }

    /**
     * PROCCESS RESPONSE
     */
    fun processGitHubRepoListResponse(response: Response<List<GitHubRepo>>) {
        if (response is Response.Success) {
            mGetGitHubRepoListLiveData.postValue(
                SuccessGetGitHubRepoListState(
                    response
                )
            )
        } else if (response is Response.Error) {
            mGetGitHubRepoListLiveData.postValue(
                ErrorGetGitHubRepoListState(
                    response
                )
            )
        }
    }

    fun getUserList() {
        viewModelScope.launch() {

            mGetUserListLiveData.postValue(LoadingGetUserListState())

            val response = mGetUserListUseCase.execute()
            processUserList(response)
        }
    }

    fun processUserList(response: Response<List<User>>) {
        if (response is Response.Success) {
            mGetUserListLiveData.postValue(
                SuccessGetUserListState(
                    response
                )
            )
        } else if (response is Response.Error) {
            mGetUserListLiveData.postValue(
                ErrorGetUserListState(
                    response
                )
            )
        }
    }

    fun createUser(name: String) {
        viewModelScope.launch() {

            mGetUserListLiveData.postValue(LoadingGetUserListState())

            val response = mCreateUserUseCase.execute(CreateUserRequest(name))
            processUserList(response)
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}