package com.jarroyo.sharedcode.data.repository

import com.jarroyo.sharedcode.base.Response
import com.jarroyo.sharedcode.domain.usecase.counter.GetCounterRequest


class CounterRepository(
    /*private val diskDataSource: DiskDataSource,
    private val networkDataSource: INetworkDataSource*/
) {

    /***********************************************************************************************
     * GET COUNTER
     **********************************************************************************************/
    suspend fun getCounter(request: GetCounterRequest): Response<Int> {

        return Response.Success(12)
    }
}