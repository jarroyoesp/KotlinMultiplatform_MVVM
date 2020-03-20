package com.jarroyo.sharedcode.data.repository

import co.touchlab.firebase.firestore.*
import com.jarroyo.sharedcode.base.Response
import com.jarroyo.sharedcode.domain.model.firebase.FirebaseUser
import kotlinx.coroutines.flow.flow

class FirebaseRepository {

    /***********************************************************************************************
     * GET USERS FROM FIREBASE
     **********************************************************************************************/
    suspend fun getFirebaseUser(): Response<List<FirebaseUser>> {
        var list = getFromFirebase()
        return Response.Success(list)
    }

    suspend fun getFromFirebase(): List<FirebaseUser> {
        val reponse = getFirebaseInstance()
            .collection("Users")
            .suspendGet()

        val list = parseFirebaseUserList(reponse.documents_)
        return list
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