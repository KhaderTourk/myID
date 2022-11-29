package com.example.splashandviewbager.screens.chatScreens

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.BitmapFactory
import android.net.ConnectivityManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.core.app.NotificationCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.splashandviewbager.*
import com.example.splashandviewbager.Units.Constants.RECEIVE_ID
import com.example.splashandviewbager.Units.Constants.SEND_ID
import com.example.splashandviewbager.Units.Companion
import com.example.splashandviewbager.Units.MyConnectionListener
import com.example.splashandviewbager.Units.Time
import com.example.splashandviewbager.modles.Message
import com.example.splashandviewbager.screens.HomeScreens.UserScreen
import com.example.splashandviewbager.adapters.MessageAdapter
import com.github.nkzawa.emitter.Emitter
import com.github.nkzawa.socketio.client.Socket
import kotlinx.coroutines.*
import org.json.JSONObject

@Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class ChatScreen : AppCompatActivity(), MyConnectionListener.ConnectionReceiverListener {

    private lateinit var adapter: MessageAdapter
    private lateinit var recycle: RecyclerView

    private var etMessage : EditText? = null
    private var tvTitle : TextView? = null
    private var tvImg : TextView? = null
    var btnSend : Button? = null
    var btnBack : ImageView? = null

    lateinit var progressDialog: ProgressDialog
    lateinit var app: SocketCreate
    private var mSocket: Socket? = null

    private val main_id = Companion.myId
    private var des_id :String = Companion.friendId

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_screen)
        progressDialog = ProgressDialog(this)
        progressDialog.setMessage("No Connection")
        progressDialog.setCancelable(false)
        supportActionBar?.hide()

        recycle = findViewById(R.id.rv_messages)
        btnSend = findViewById(R.id.btn_send)
        etMessage = findViewById(R.id.et_message)
        tvTitle = findViewById(R.id.tv_chat_title)
        tvImg = findViewById(R.id.tv_chat_img)
        btnBack = findViewById(R.id.btn_back_from_chat)

        tvTitle!!.text = Companion.friendName
        tvImg!!.text = Companion.friendName.substring(0, 2)

        app = application as SocketCreate
        mSocket = app.getSocket()

        registerReceiver(MyConnectionListener(), IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
        recyclerView()

        btnBack!!.setOnClickListener {
            val intent = Intent(this , UserScreen::class.java)
            startActivity(intent)
        }

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


        btnSend!!.setOnClickListener {
            sendMessage()

            etMessage!!.setOnFocusChangeListener { _, hasFocus ->
                if (hasFocus){
                    MessageAdapter(this, Companion.messagesArray).notifyDataSetChanged()
                }
            }
            etMessage!!.setOnClickListener {
                GlobalScope.launch {
                    delay(100)

                    withContext(Dispatchers.Main) {
                        recycle.scrollToPosition(adapter.itemCount - 1)

                    }
                }
            }
        }
        mSocket!!.on("messagePrivate",Emitter.Listener { args ->
            runOnUiThread {
                try {
                    Log.e("Tag",args[0].toString())
                    val message = JSONObject(args[0].toString())
                    val destancId = message.getString("id").toString()
                    if (destancId == main_id){
                        val msg = message.getString("text").toString()
                        val username = message.getString("username").toString()
                        createNotificationChannel()
                        GlobalScope.launch {
                            delay(100)
                            withContext(Dispatchers.Main) {
                                val timeStamp = Time.timeStamp()

                                Companion.messagesArray.add(Message( SEND_ID, msg , timeStamp,username))
                                for (i in 0 until Companion.messagesArray.size){
                                    if (Companion.messagesArray[i].message == msg ){
                                        Companion.messagesArray.drop(i)
                                    }
                                    if (Companion.messagesArray[i].sender == Companion.myName){
                                        Companion.messagesArray.drop(i)
                                    }
                                }
                                MessageAdapter(this@ChatScreen, Companion.messagesArray).notifyDataSetChanged()
                                recycle.scrollToPosition(adapter.itemCount - 1)
                                Toast.makeText(this@ChatScreen,msg, Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                }catch (e: Exception){
                    Log.e("Tag",e.message.toString())
                }
            }
        })
        mSocket!!.connect()
    }

    private fun recyclerView() {
        adapter = MessageAdapter(this, Companion.messagesArray)
        recycle.adapter = adapter
        recycle.layoutManager = LinearLayoutManager(applicationContext)

    }
    override fun onStart() {
        super.onStart()
        //In case there are messages, scroll to bottom when re-opening app
        GlobalScope.launch {
            delay(100)
            withContext(Dispatchers.Main) {
                recycle.scrollToPosition(adapter.itemCount - 1)
            }
        }
    }
    private fun sendMessage() {
        val message = etMessage!!.text.toString()
        val timeStamp = Time.timeStamp()
        val msg = JSONObject()

        if (message.isNotEmpty()) {
            //Adds it to our local list
            msg.put("msg", etMessage!!.text.toString())
            msg.put("myId", main_id)
            msg.put("friendId", des_id)
            msg.put("username", Companion.myName)
            Log.e("Tagname :", Companion.myName)
            mSocket!!.emit("privateChatMessage", msg)

            Companion.messagesArray.add(Message(RECEIVE_ID,message,timeStamp , Companion.myName))
            etMessage!!.setText("")
            MessageAdapter(this, Companion.messagesArray).notifyDataSetChanged()
            recycle.scrollToPosition(adapter.itemCount - 1)
        }
    }

    override fun onPause() {
        Companion.messagesArray.clear()
        super.onPause()
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
    private fun createNotificationChannel() {
        val CHANNEL_ID = "ServiceChannelExample"
        var manager: NotificationManager?=null
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val serviceChannel = NotificationChannel(CHANNEL_ID, "Example Service Channel",
                NotificationManager.IMPORTANCE_DEFAULT)
            manager = getSystemService(NotificationManager::class.java)
            manager.createNotificationChannel(serviceChannel)
        } else {
            manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        }
        val notificationIntent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0)

        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("New Message from "+ Companion.messagesArray.last().sender)
            .setContentText(Companion.messagesArray.last().message)
            .setSmallIcon(R.drawable.l4)
            .setLargeIcon(BitmapFactory.decodeResource(resources, R.drawable.l4))
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .build()

        manager!!.notify(1,notification)
    }
}