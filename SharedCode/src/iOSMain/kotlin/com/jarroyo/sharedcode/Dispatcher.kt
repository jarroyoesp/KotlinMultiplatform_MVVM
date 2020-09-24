package com.jarroyo.sharedcode

import kotlinx.coroutines.*
import platform.darwin.*
import kotlin.coroutines.CoroutineContext
import io.ktor.client.engine.*
import io.ktor.client.engine.ios.*

internal actual val ApplicationDispatcher: CoroutineContext  = //Dispatchers.Main
    NsQueueDispatcher(dispatch_get_main_queue())

internal class NsQueueDispatcher(
    private val dispatchQueue: dispatch_queue_t
) : CoroutineDispatcher() {
    override fun dispatch(context: CoroutineContext, block: Runnable) {
        dispatch_async(dispatchQueue) {
            block.run()
        }
    }
}


actual val httpClientEngine: HttpClientEngine
    get() = Ios.create()