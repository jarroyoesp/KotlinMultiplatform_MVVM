/*
 * Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package com.jarroyo.sharedcode.viewModel

import com.jarroyo.sharedcode.ApplicationDispatcher
import com.jarroyo.sharedcode.base.Response
import com.jarroyo.sharedcode.data.repository.CounterRepository
import com.jarroyo.sharedcode.domain.usecase.counter.GetCounterRequest
import com.jarroyo.sharedcode.domain.usecase.counter.GetCounterUseCase
import com.jarroyo.sharedcode.utils.coroutines.launchSilent
import dev.icerock.moko.mvvm.livedata.LiveData
import dev.icerock.moko.mvvm.livedata.MutableLiveData
import dev.icerock.moko.mvvm.livedata.map
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

class CounterViewModel(
) : ViewModel() {
    private val _counter: MutableLiveData<Int> = MutableLiveData(0)
    val counter: LiveData<String> = _counter.map { it.toString() }


    var mGetCounterLiveData = MutableLiveData<GetCounterState>(LoadingGetCounterState())

    fun onCounterButtonPressed() {
        val current = _counter.value
        _counter.value = current + 1
    }

    fun readState() {
        onCounterButtonPressed()
        mGetCounterLiveData.postValue(LoadingGetCounterState())
        onProcessResponse(Response.Success(_counter.value))
    }

    fun onProcessResponse(response: Response<Int>) {
        if (response is Response.Success) {
            mGetCounterLiveData.postValue(SuccessGetCounterState(response))
        } else {
            mGetCounterLiveData.postValue(ErrorGetCounterState(response))
        }
    }


    private var job: Job = Job()
    private val coroutineContext: CoroutineContext = ApplicationDispatcher
    private val exceptionHandler = CoroutineExceptionHandler { _, _ ->
    }

    private var mGetCounterUseCase = GetCounterUseCase(CounterRepository())

    /**
     * GET COUNTER
     */
    fun getCounter() = launchSilent(coroutineContext, exceptionHandler, job) {
        mGetCounterLiveData.postValue(LoadingGetCounterState())

        //Logger.d("COUNTER VIEWMODEL", "my message")
        val request = GetCounterRequest()
        val response = mGetCounterUseCase.execute(request)
        processSaveUserResponse(response)
    }

    fun processSaveUserResponse(response: Response<Int>){
        if (response is Response.Success) {
            mGetCounterLiveData.postValue(
                SuccessGetCounterState(
                    response
                )
            )
        } else if (response is Response.Error) {
            mGetCounterLiveData.postValue(
                SuccessGetCounterState(
                    response
                )
            )
        }
    }

}