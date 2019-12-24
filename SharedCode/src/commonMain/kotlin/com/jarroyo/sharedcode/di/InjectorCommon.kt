package com.jarroyo.sharedcode.di

import com.jarroyo.sharedcode.utils.networkSystem.ContextArgs
import kotlin.native.concurrent.ThreadLocal

@ThreadLocal
object InjectorCommon {

    lateinit var mContextArgs: ContextArgs

    fun provideContextArgs(contextArgs: ContextArgs): ContextArgs {
        mContextArgs = contextArgs
        return mContextArgs
    }


}
