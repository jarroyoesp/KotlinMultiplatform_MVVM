package com.jarroyo.sharedcode.data.repository

import com.jarroyo.sharedcode.DatabaseCreator
import com.jarroyo.sharedcode.base.Response
import com.jarroyo.sharedcode.data.source.disk.Database
import com.jarroyo.sharedcode.domain.usecase.counter.GetCounterRequest
import com.jarroyo.sharedcode.utils.networkSystem.ContextArgs


class CounterRepository(
    contextArgs: ContextArgs?
) {
    private var mDatabase: Database? = DatabaseCreator.getDataBase(contextArgs)
    /***********************************************************************************************
     * GET COUNTER
     **********************************************************************************************/
    suspend fun getCounter(request: GetCounterRequest): Response<Int> {
        mDatabase?.insertUser(name = "Jarroyoesp")
        val userList = mDatabase?.getAllUser()
        return Response.Success(userList?.last()?.id?.toInt() ?: -1)
    }
}