package com.jarroyo.sharedcode.di

import com.jarroyo.sharedcode.utils.networkSystem.ContextArgs
import kotlin.native.concurrent.ThreadLocal

@ThreadLocal
object InjectorCommon {

    var mContextArgs: ContextArgs? = null

    fun provideContextArgs(contextArgs: ContextArgs): ContextArgs? {
        mContextArgs = contextArgs
        return mContextArgs
    }


}
