package com.jarroyo.sharedcode.domain.usecase.counter

import com.jarroyo.sharedcode.domain.usecase.base.BaseRequest

class GetCounterRequest() : BaseRequest {
    override fun validate(): Boolean {
        return true
    }
}