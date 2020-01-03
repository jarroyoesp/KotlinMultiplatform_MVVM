/*
 * Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package com.jarroyo.sharedcode.viewModel.github

import com.jarroyo.sharedcode.base.Response
import com.jarroyo.sharedcode.di.KodeinInjector
import com.jarroyo.sharedcode.domain.model.github.GitHubRepo
import com.jarroyo.sharedcode.domain.usecase.github.getRepos.GetGitHubRepoListRequest
import com.jarroyo.sharedcode.domain.usecase.github.getRepos.GetGitHubRepoListUseCase
import com.jarroyo.sharedcode.utils.coroutines.launchSilent
import dev.icerock.moko.mvvm.livedata.MutableLiveData
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Job
import org.kodein.di.erased.instance
import kotlin.coroutines.CoroutineContext

class GitHubViewModel : ViewModel() {

    // LIVE DATA
    var mGetGitHubRepoListLiveData = MutableLiveData<GetGitHubRepoListState>(LoadingGetGitHubRepoListState())

    // USE CASE
    private val mGetGitHubRepoListUseCase by KodeinInjector.instance<GetGitHubRepoListUseCase>()

    // ASYNC - COROUTINES
    private val coroutineContext by KodeinInjector.instance<CoroutineContext>()
    private var job: Job = Job()
    private val exceptionHandler = CoroutineExceptionHandler { _, _ -> }


    /**
     * GET GITHUB REPO LIST
     */
    fun getGitHubRepoList(username: String) = launchSilent(coroutineContext, exceptionHandler, job) {
        mGetGitHubRepoListLiveData.postValue(LoadingGetGitHubRepoListState())

        val request = GetGitHubRepoListRequest(username)
        val response = mGetGitHubRepoListUseCase.execute(request)
        processGitHubRepoListResponse(response)
    }

    /**
     * PROCCESS RESPONSE
     */
    fun processGitHubRepoListResponse(response: Response<List<GitHubRepo>>){
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
}