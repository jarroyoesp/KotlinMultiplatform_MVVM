/*
 * Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package com.jarroyo.sharedcode.viewModel.firebase

import com.jarroyo.sharedcode.base.Response
import com.jarroyo.sharedcode.di.KodeinInjector
import com.jarroyo.sharedcode.domain.model.firebase.FirebaseUser
import com.jarroyo.sharedcode.domain.usecase.firebase.getUsers.GetFirebaseUserListFlowUseCase
import com.jarroyo.sharedcode.domain.usecase.firebase.getUsers.GetFirebaseUserListUseCase
import com.jarroyo.sharedcode.utils.coroutines.launchSilent
import dev.icerock.moko.mvvm.livedata.MutableLiveData
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import org.kodein.di.erased.instance
import kotlin.coroutines.CoroutineContext

class FirebaseViewModel : ViewModel() {

    // LIVE DATA
    var mGetFirebaseUserListLiveData = MutableLiveData<GetFirebaseUserListState>(LoadingGetFirebaseUserListState())

    // USE CASE
    private val mGetFirebaseUserListUseCase by KodeinInjector.instance<GetFirebaseUserListUseCase>()
    private val mGetFirebaseUserListFlowUseCase by KodeinInjector.instance<GetFirebaseUserListFlowUseCase>()

    // ASYNC - COROUTINES
    private val coroutineContext by KodeinInjector.instance<CoroutineContext>()
    private var job: Job = Job()
    private val exceptionHandler = CoroutineExceptionHandler { _, _ -> }


    /**
     * GET FIREBASE USER LIST
     */
    fun getFirebaseUserList(username: String) = launchSilent(coroutineContext, exceptionHandler, job) {
        mGetFirebaseUserListLiveData.postValue(LoadingGetFirebaseUserListState())

        val response = mGetFirebaseUserListUseCase.execute()
        processFirebaseUserListResponse(response)
    }

    /**
     * GET FIREBASE USER LIST FLOW
     */
    fun getFirebaseUserListFlow(username: String) = launchSilent(coroutineContext, exceptionHandler, job) {
        mGetFirebaseUserListLiveData.postValue(LoadingGetFirebaseUserListState())

        val flowResponse = mGetFirebaseUserListFlowUseCase.execute()

        flowResponse.collect {
            processFirebaseUserListResponse(it)
        }
    }

    /**
     * PROCCESS FIREBASE USER LIST RESPONSE
     */
    fun processFirebaseUserListResponse(response: Response<List<FirebaseUser>>){
        if (response is Response.Success) {
            mGetFirebaseUserListLiveData.postValue(
                SuccessGetFirebaseUserListState(
                    response
                )
            )
        } else if (response is Response.Error) {
            mGetFirebaseUserListLiveData.postValue(
                ErrorGetFirebaseUserListState(
                    response
                )
            )
        }
    }
}