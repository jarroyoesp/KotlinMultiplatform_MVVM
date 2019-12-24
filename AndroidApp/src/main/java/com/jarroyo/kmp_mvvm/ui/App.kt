package com.jarroyo.kmp_mvvm.ui

import androidx.multidex.MultiDexApplication
import com.jarroyo.sharedcode.di.InjectorCommon
import com.jarroyo.sharedcode.utils.networkSystem.ContextArgs

open class App : MultiDexApplication() {

/*    companion object {
        lateinit var graph: ApplicationComponent
    }*/

    override fun onCreate() {
        super.onCreate()
        InjectorCommon.provideContextArgs(ContextArgs(this))
    }

    /*private fun initializeDagger() {
        graph = DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this))
            .build()

        InjectorCommon.provideContextArgs(ContextArgs(this))
    }*/
}