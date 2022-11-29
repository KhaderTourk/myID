package com.example.splashandviewbager.Units

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager

class MyConnectionListener:BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (connectionListener != null){
            connectionListener!!.onNetworkConnected(checkConnection(context!!))
        }
    }
    private fun checkConnection(context: Context):Boolean{
        val connectionManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val info = connectionManager.activeNetworkInfo
        return info != null && info.isConnectedOrConnecting
    }
    interface ConnectionReceiverListener{
        fun onNetworkConnected(isConnected : Boolean)
    }
    companion object{
        var connectionListener: ConnectionReceiverListener? = null
    }
}
