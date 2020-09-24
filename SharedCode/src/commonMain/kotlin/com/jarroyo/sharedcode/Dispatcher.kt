package com.jarroyo.sharedcode

import io.ktor.client.engine.HttpClientEngine
import kotlin.coroutines.CoroutineContext

internal expect val ApplicationDispatcher: CoroutineContext

expect val httpClientEngine: HttpClientEngine