package com.jarroyo.sharedcode

import kotlinx.coroutines.runBlocking

actual fun platformName(): String {
    return "iOS"
}

internal actual fun <T> runTest(block: suspend () -> T) {
    runBlocking { block() }
}