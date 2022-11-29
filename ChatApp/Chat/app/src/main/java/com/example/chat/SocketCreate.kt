package com.example.splashandviewbager


import android.app.Application
import com.github.nkzawa.socketio.client.IO
import com.github.nkzawa.socketio.client.Socket
import java.net.URISyntaxException

class SocketCreate:Application() {

    private var mSocket: Socket? = IO.socket("http://192.168.1.113:8080")


    fun getSocket(): Socket? {
        return mSocket
    }

}