package com.jarroyo.sharedcode


expect fun platformName(): String
// Common
@Suppress("UnusedPrivateMember")
internal expect fun <T> runTest(block: suspend () -> T)
