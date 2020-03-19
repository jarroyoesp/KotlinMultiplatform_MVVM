package com.jarroyo.sharedcode.data.repository

import co.touchlab.firebase.firestore.*
import com.jarroyo.sharedcode.base.Response
import com.jarroyo.sharedcode.domain.model.firebase.FirebaseUser
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow

class FirebaseRepository {

    /***********************************************************************************************
     * GET USERS FROM FIREBASE
     **********************************************************************************************/
    suspend fun getFirebaseUser(): Response<List<FirebaseUser>> {
        var list = mutableListOf<FirebaseUser>()
        getFromFirebase(proc = {
                list.addAll(it)
            })
        delay(3000)
        return Response.Success(list)
    }

    fun getFromFirebase(proc: (list: List<FirebaseUser>) -> Unit) {
        getFirebaseInstance()
            .collection("Users")
            .get_()
            .addListeners({
                proc(parseFirebaseUserList(it.documents_))
            },{
                error(it)
            })
    }


    private fun parseFirebaseUserList(documentSnapshots: List<DocumentSnapshot>): List<FirebaseUser> {
        return documentSnapshots.map {
            parseFirebaseUser(it)
        }
    }

    private fun parseFirebaseUser(documentSnapshot: DocumentSnapshot): FirebaseUser {
        val level = documentSnapshot.data_()
        @Suppress("UNCHECKED_CAST")
        val name = level?.get("name") as String
        return FirebaseUser(name)
    }

    /***********************************************************************************************
     * GET USERS FROM FIREBASE FLOW
     **********************************************************************************************/
    suspend fun getDiscoverFlow() = flow {
        emit(getFirebaseUser())
    }
}