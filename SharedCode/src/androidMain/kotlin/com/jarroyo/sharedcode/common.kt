package com.jarroyo.sharedcode
// Android
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking

actual fun platformName(): String {
    return "Android"
}



@UseExperimental(ExperimentalCoroutinesApi::class)
internal actual fun <T> runTest(block: suspend () -> T) {
    runBlocking { block() }
}