package com.jarroyo.sharedcode

import com.jarroyo.sharedcode.data.source.disk.Database
import com.jarroyo.sharedcode.utils.networkSystem.ContextArgs
import com.squareup.sqldelight.db.SqlDriver


expect class DatabaseDriverFactory {
    fun createDriver(): SqlDriver
}

expect fun getSqlDriver(contextArgs: ContextArgs? = null): SqlDriver

object DatabaseCreator {
    fun getDataBase(contextArgs: ContextArgs?): Database? {
        val sqlDriver  = getSqlDriver(contextArgs)
        if (sqlDriver != null) {
            return Database(sqlDriver)
        } else {
            return null
        }
    }
}
