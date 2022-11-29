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
import com.example.splashandviewbager.Units.Companion
import com.example.splashandviewbager.Units.Constants
import com.example.splashandviewbager.Units.MyConnectionListener
import com.example.splashandviewbager.Units.Time
import com.example.splashandviewbager.modles.Message
import com.example.splashandviewbager.adapters.MessageGroupAdapter
import com.example.splashandviewbager.screens.HomeScreens.GroupsScreen
import com.github.nkzawa.emitter.Emitter
import com.github.nkzawa.socketio.client.Socket
import kotlinx.coroutines.*
import org.json.JSONObject

@Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class ChatGroup : AppCompatActivity() , MyConnectionListener.ConnectionReceiverListener {

    private lateinit var adapter: MessageGroupAdapter
    private lateinit var recycle: RecyclerView

    private var etMessage : EditText? = null
    private var tvTitle : TextView? = null
    private var tvImg : TextView? = null
    var btnSend : Button? = null
    var btnBack : ImageView? = null

    lateinit var progressDialog: ProgressDialog
    lateinit var app: SocketCreate
    private var mSocket: Socket? = null
    var myMsg :String = ""

    private val main_id = Companion.myId

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_group)
        progressDialog = ProgressDialog(this)
        progressDialog.setMessage("No Connection")
        progressDialog.setCancelable(false)
        supportActionBar?.hide()

        recycle = findViewById(R.id.rv_messages_group)
        btnSend = findViewById(R.id.btn_send_group)
        btnBack = findViewById(R.id.btn_back_from_chat_group)
        etMessage = findViewById(R.id.et_message_group)
        tvTitle = findViewById(R.id.tv_chat_group_title)
        tvImg = findViewById(R.id.tv_chat_group_img)

        tvTitle!!.text = Companion.currentRoom
        tvImg!!.text = Companion.currentRoom.substring(0, 2)

        app = application as SocketCreate
        mSocket = app.getSocket()

        registerReceiver(MyConnectionListener(), IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
        recyclerView()

        btnBack!!.setOnClickListener {
            val intent = Intent(this , GroupsScreen::class.java)
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
            sendMessageGroup()

            etMessage!!.setOnFocusChangeListener { _, hasFocus ->
                if (hasFocus){
                    MessageGroupAdapter(this, Companion.messagesGroupArray).notifyDataSetChanged()
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



        mSocket!!.on("messageRoom", Emitter.Listener { args ->
            runOnUiThread {
                try {
                    Log.e("Tag",args[0].toString())
                    val message = JSONObject(args[0].toString())
                    val room = message.getString("room").toString()
                    val msg = message.getString("text").toString()
                    val username = message.getString("username").toString()

                    if (room == Companion.currentRoom && msg != myMsg && username != Companion.myName) {
                            GlobalScope.launch {
                                delay(100)
                                withContext(Dispatchers.Main) {
                                    val timeStamp = Time.timeStamp()
                                    Companion.messagesGroupArray.add(
                                        Message(
                                                Constants.SEND_ID,
                                            msg,
                                            timeStamp,
                                            username
                                        )
                                    )
                                    createNotificationChannel()
                                    for (i in 0 until Companion.messagesGroupArray.size){
                                        if (Companion.messagesGroupArray[i].message == msg ){
                                            Companion.messagesGroupArray.drop(i)
                                        }
                                        if (Companion.messagesGroupArray[i].sender == Companion.myName){
                                            Companion.messagesGroupArray.drop(i)
                                        }
                                    }
                                    MessageGroupAdapter(
                                        this@ChatGroup,
                                            Companion.messagesGroupArray
                                    ).notifyDataSetChanged()
                                    recycle.scrollToPosition(adapter.itemCount - 1)
                                    Toast.makeText(this@ChatGroup, msg, Toast.LENGTH_SHORT).show()
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
        adapter = MessageGroupAdapter(this, Companion.messagesGroupArray)
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

    private fun sendMessageGroup() {
        val message = etMessage!!.text.toString()
        myMsg = message
        val timeStamp = Time.timeStamp()
        val msg = JSONObject()

        if (message.isNotEmpty()) {
            //Adds it to our local list
            msg.put("msg", etMessage!!.text.toString())
            msg.put("myId", main_id)
            msg.put("username", Companion.myName)
            msg.put("room", Companion.currentRoom)
            Log.e("Tagname :", Companion.myName)
            mSocket!!.emit("chatMessage", msg)

            Companion.messagesGroupArray.add(Message(Constants.RECEIVE_ID,message,timeStamp , Companion.myName))
            etMessage!!.setText("")
            MessageGroupAdapter(this, Companion.messagesGroupArray).notifyDataSetChanged()
            recycle.scrollToPosition(adapter.itemCount - 1)
        }
    }

    override fun onPause() {
        Companion.messagesGroupArray.clear()
        finish()
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
            .setContentTitle("New Message in room  ")
            .setContentText(Companion.messagesGroupArray.last().message)
            .setSmallIcon(R.drawable.l4)
            .setLargeIcon(BitmapFactory.decodeResource(resources, R.drawable.l4))
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .build()

        manager!!.notify(1,notification)
    }
}
