package com.jarroyo.sharedcode.viewModel

import com.jarroyo.sharedcode.base.Response


sealed class GetCounterState {
    abstract val response: Response<Int>?
}
data class SuccessGetCounterState(override val response: Response<Int>) : GetCounterState()
data class LoadingGetCounterState(override val response: Response<Int>? = null) : GetCounterState()
data class ErrorGetCounterState(override val response: Response<Int>) : GetCounterState()