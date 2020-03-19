package com.jarroyo.sharedcode.domain.usecase.firebase.getUsers

import com.jarroyo.sharedcode.base.Response
import com.jarroyo.sharedcode.data.repository.FirebaseRepository
import com.jarroyo.sharedcode.domain.model.firebase.FirebaseUser
import com.jarroyo.sharedcode.domain.usecase.base.BaseUseCase

open class GetFirebaseUserListUseCase(val repository: FirebaseRepository) : BaseUseCase<Nothing, List<FirebaseUser>>() {

    override suspend fun run(): Response<List<FirebaseUser>> {
        return repository.getFirebaseUser()
    }
}