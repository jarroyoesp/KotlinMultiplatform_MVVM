package com.jarroyo.sharedcode.viewModel.firebase

import com.jarroyo.sharedcode.base.Response
import com.jarroyo.sharedcode.domain.model.firebase.FirebaseUser


sealed class GetFirebaseUserListState {
    abstract val response: Response<List<FirebaseUser>>?
}
data class SuccessGetFirebaseUserListState(override val response: Response<List<FirebaseUser>>) : GetFirebaseUserListState()
data class LoadingGetFirebaseUserListState(override val response: Response<List<FirebaseUser>>? = null) : GetFirebaseUserListState()
data class ErrorGetFirebaseUserListState(override val response: Response<List<FirebaseUser>>) : GetFirebaseUserListState()