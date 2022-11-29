package com.example.splashandviewbager

import android.app.ProgressDialog
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.splashandviewbager.Units.Companion
import com.example.splashandviewbager.Units.MyConnectionListener
import com.example.splashandviewbager.modles.User
import com.github.nkzawa.emitter.Emitter
import com.github.nkzawa.socketio.client.Socket
import org.json.JSONObject


class MainActivity : AppCompatActivity(), MyConnectionListener.ConnectionReceiverListener {

    lateinit var app: SocketCreate
    private var mSocket: Socket? = null
    lateinit var progressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        progressDialog = ProgressDialog(this)
        progressDialog.setMessage("No Connection")
        progressDialog.setCancelable(false)

        supportActionBar?.hide()
        app = application as SocketCreate
        mSocket = app.getSocket()

        registerReceiver(MyConnectionListener(), IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))

        mSocket!!.on(Socket.EVENT_CONNECT_ERROR) {
            runOnUiThread {
                Log.e("EVENT_CONNECT_ERROR", "EVENT_CONNECT_ERROR: ")
            }
        }
        mSocket!!.on(Socket.EVENT_CONNECT_TIMEOUT) {
            runOnUiThread {
                Log.e("EVENT_CONNECT_TIMEOUT", "EVENT_CONNECT_TIMEOUT: ")

            }
        }
        mSocket!!.on(Socket.EVENT_CONNECT) {
            Log.e("onConnect", "Socket Connected!")

        }

        mSocket!!.on(Socket.EVENT_DISCONNECT) {
            runOnUiThread {
                Log.e("onDisconnect", "Socket onDisconnect!")

            }
        }

        mSocket!!.emit("allUsersArray")
        mSocket!!.on("returnAllUsers", Emitter.Listener { args ->
            runOnUiThread {
                try {
                    val jsonObject = JSONObject(args[0].toString())
                    val jsonArray = jsonObject.optJSONArray("array")
                    for (i in 0 until jsonArray.length()) {
                        val jsonObject = jsonArray.getJSONObject(i)
                        Companion.usersArray.add(User(
                            jsonObject.optString("id"),
                            jsonObject.optString("username"),
                            jsonObject.optString("phone")
                        ))
                      //  Log.e("main :", Companion.usersArray[1].name.toString())
                    }
                    Log.e("main :", args[0].toString())

                } catch (e: Exception) {
                    Log.e("mainTag1", e.message.toString())
                }
            }
        })
        //    mSocket!!.on("message",onNewMessage)
        mSocket!!.connect()

    }
    private val onNewMessage = Emitter.Listener { args ->
        runOnUiThread {
            try {
                Toast.makeText(this,args[0].toString(), Toast.LENGTH_SHORT).show()
                Log.e("Tag args :",args[0].toString())       //{"username":"first Room","text":"Welcome to ChatCord!","time":"17:29:30"}
                // etMessage!!.setText(message.getString("message"))
            }catch (e: Exception){
                Log.e("Tag",e.message.toString())
            }
        }
    }

    override fun onNetworkConnected(isConnected: Boolean) {
        if (!isConnected){
            //on disconnected event
            showDialog()
        }else{
            //on connected
            hideDialog()
        }
    }

//    override fun onPause() {
//        finish()
//        super.onPause()
//    }

    override fun onResume() {
        MyConnectionListener.connectionListener = this
        super.onResume()
    }
    private fun showDialog() {
        progressDialog = ProgressDialog(this)
        progressDialog.setMessage("No Connection")
        progressDialog.setCancelable(false)
        progressDialog.show()
    }
    private fun hideDialog(){
        if(progressDialog.isShowing)
            progressDialog.dismiss()
    }
}