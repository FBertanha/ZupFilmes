package br.com.felipebertanha.zupfilmes.utils

import android.content.Context
import android.net.ConnectivityManager

class InternetUtils {
    companion object {
        fun temConexaoInternet(context: Context): Boolean {
            val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            return activeNetworkInfo != null && activeNetworkInfo.isConnected
        }
    }


}