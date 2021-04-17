package com.jarroyo.sharedcode

import android.content.Context
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import com.jarroyo.sharedcode.db.AppDatabase
import com.jarroyo.sharedcode.utils.networkSystem.ContextArgs


actual class DatabaseDriverFactory(private val context: Context) {
    actual fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(AppDatabase.Schema, context, "AppDatabase.db")
    }
}

actual fun getSqlDriver(contextArgs: ContextArgs?): SqlDriver {
    return AndroidSqliteDriver(AppDatabase.Schema, contextArgs?.mContext!!, "AppDatabase.db")
}
