package com.jarroyo.sharedcode.domain.usecase.base

import com.jarroyo.sharedcode.base.Response
import kotlinx.coroutines.flow.Flow

abstract class BaseUseCaseFlow<R : BaseRequest, T> {

    protected var mRequest: R? = null

    suspend fun execute(request: R? = null): Flow<Response<T>> {
        mRequest = request

        /*val validated = request?.validate() ?: true
        if (validated)*/  return run()
        /*return  Flow<Response.Error(IllegalArgumentException())>*/
    }

    abstract suspend fun run(): Flow<Response<T>>
}
