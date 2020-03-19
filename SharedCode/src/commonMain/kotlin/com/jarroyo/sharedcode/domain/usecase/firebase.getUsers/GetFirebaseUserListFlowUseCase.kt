package com.jarroyo.sharedcode.domain.usecase.firebase.getUsers

import com.jarroyo.sharedcode.data.repository.FirebaseRepository
import com.jarroyo.sharedcode.domain.model.firebase.FirebaseUser
import com.jarroyo.sharedcode.domain.usecase.base.BaseUseCaseFlow


open class GetFirebaseUserListFlowUseCase(val repository: FirebaseRepository) : BaseUseCaseFlow<Nothing, List<FirebaseUser>>() {

    override suspend fun run() =
       repository.getDiscoverFlow()

}