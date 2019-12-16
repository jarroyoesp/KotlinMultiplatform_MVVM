package com.jarroyo.sharedcode.domain.usecase.counter

import com.jarroyo.sharedcode.base.Response
import com.jarroyo.sharedcode.data.repository.CounterRepository
import com.jarroyo.sharedcode.domain.usecase.base.BaseUseCase


open class GetCounterUseCase(val repository: CounterRepository) : BaseUseCase<GetCounterRequest, Int>() {

    override suspend fun run(): Response<Int> {
        return repository.getCounter(request!!)
    }
}