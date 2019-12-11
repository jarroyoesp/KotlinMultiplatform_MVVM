/*
 * Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package com.jarroyo.sharedcode.viewModel

import com.jarroyo.sharedcode.base.Response
import dev.icerock.moko.mvvm.livedata.LiveData
import dev.icerock.moko.mvvm.livedata.MutableLiveData
import dev.icerock.moko.mvvm.livedata.map
import dev.icerock.moko.mvvm.viewmodel.ViewModel

class CounterViewModel : ViewModel() {
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
}