package com.davidwinnicki.myrepo.utils

import android.content.Context
import android.net.ConnectivityManager

class NetworkHelper {

    fun isNetworkAvailable(context: Context?): Boolean {
        val activeNetworkInfo = (context?.getSystemService(Context.CONNECTIVITY_SERVICE)
                as ConnectivityManager).activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }
}
