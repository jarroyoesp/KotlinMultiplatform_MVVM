package com.jarroyo.sharedcode.domain.usecase.user

import com.jarroyo.sharedcode.domain.usecase.base.BaseRequest

class CreateUserRequest(val userName: String) : BaseRequest {
    override fun validate(): Boolean {
        return true
    }
}