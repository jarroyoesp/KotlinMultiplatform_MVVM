package com.jarroyo.sharedcode.data.source.disk

import com.jarroyo.sharedcode.base.Response
import com.jarroyo.sharedcode.db.User
import com.jarroyo.sharedcode.domain.model.github.GitHubRepo

interface IDiskDataSource {
    suspend fun getUserList(): List<User>
    suspend fun insertUser(name: String)
}

class DiskDataSourceImpl(private val database: Database?): IDiskDataSource {
    override suspend fun getUserList(): List<User> {
        return database?.getAllUser() ?: emptyList()
    }

    override suspend fun insertUser(name: String) {
        database?.insertUser(name)
    }
}