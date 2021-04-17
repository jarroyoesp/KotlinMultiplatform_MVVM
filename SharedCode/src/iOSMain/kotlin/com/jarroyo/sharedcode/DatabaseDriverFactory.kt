package com.jarroyo.sharedcode

import com.jarroyo.sharedcode.db.AppDatabase
import com.jarroyo.sharedcode.utils.networkSystem.ContextArgs
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver

actual class DatabaseDriverFactory {
    actual fun createDriver(): SqlDriver {
        return NativeSqliteDriver(AppDatabase.Schema, "AppDatabase.db")
    }
}

actual fun getSqlDriver(contextArgs: ContextArgs?): SqlDriver {
    return NativeSqliteDriver(AppDatabase.Schema, "AppDatabase.db")
}
