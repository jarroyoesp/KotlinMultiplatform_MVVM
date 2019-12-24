package com.jarroyo.sharedcode.utils.networkSystem

import android.content.Context
import android.net.ConnectivityManager
import com.jarroyo.sharedcode.di.InjectorCommon

actual class ContextArgs(
    var mContext: Context
)

actual fun isNetworkAvailable(): Boolean{
    val connectivityManager = InjectorCommon.mContextArgs.mContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetworkInfo = connectivityManager.activeNetworkInfo
    return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting

}