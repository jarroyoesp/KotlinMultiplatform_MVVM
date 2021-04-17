package com.jarroyo.sharedcode.data.source.disk

import com.jarroyo.sharedcode.DatabaseDriverFactory
import com.jarroyo.sharedcode.db.AppDatabase
import com.jarroyo.sharedcode.db.User
import com.squareup.sqldelight.db.SqlDriver


class Database(sqlDriver: SqlDriver) {
    private val database = AppDatabase(sqlDriver)
    private val dbQuery = database.appDatabaseQueries

    fun clearDatabase() {
        dbQuery.transaction {
            dbQuery.removeAllUser()
        }
    }

    fun getAllUser(): List<User> {
        return dbQuery.selectAllUser().executeAsList()
    }

    fun insertUser(name: String) {
        dbQuery.insertUser(
            name = name
        )
    }
}