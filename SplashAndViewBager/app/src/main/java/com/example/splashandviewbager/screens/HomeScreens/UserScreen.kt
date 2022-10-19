package com.example.splashandviewbager.screens.HomeScreens

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.core.app.NotificationCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.splashandviewbager.*
import com.example.splashandviewbager.Units.Companion
import com.example.splashandviewbager.modles.User
import com.example.splashandviewbager.adapters.UserAdapter
import com.example.splashandviewbager.screens.chatScreens.ChatScreen
import com.github.nkzawa.emitter.Emitter
import com.github.nkzawa.socketio.client.Socket
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.json.JSONObject

class UserScreen : AppCompatActivity(), UserAdapter.onClick{

    override fun onClickItem(position: Int) {
        if (Companion.usersArray[position].id != Companion.myId) {
            val intent = Intent(this, ChatScreen::class.java)
            Companion.friendId = Companion.usersArray[position].id
            Companion.friendName = Companion.usersArray[position].name
            startActivity(intent)
        }
    }

    lateinit var app: SocketCreate
    private var mSocket: Socket? = null
    private var btnSearch: ImageView? = null
    private var tvTitle: TextView? = null
    private var tvImg: TextView? = null
    var bottomNavigationView: BottomNavigationView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_screen)
        supportActionBar?.hide()

        app = application as SocketCreate
        mSocket = app.getSocket()
        bottomNavigationView = findViewById(R.id.bottom_navigation_users)

        btnSearch = findViewById(R.id.btn_search)
        tvTitle = findViewById(R.id.tv_users_my_name)
        tvImg = findViewById(R.id.tv_users_my_img)

        tvTitle!!.text = Companion.myName
        tvImg!!.text = Companion.myName.substring(0, 2)

        val recycle: RecyclerView = findViewById(R.id.recycle_users)
        val swiper: SwipeRefreshLayout = findViewById(R.id.swipe_users)

        recycle.adapter = UserAdapter(this, Companion.usersArray, this)
        recycle.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)



        swiper.setOnRefreshListener {
            mSocket!!.emit("allUsersArray")
            mSocket!!.on("returnAllUsers", Emitter.Listener { args ->
                runOnUiThread {
                    try {
                        Companion.usersArray.clear()
                        val jsonObject = JSONObject(args[0].toString())
                        val jsonArray = jsonObject.optJSONArray("array")
                        for (i in 0 until jsonArray.length()) {
                            val jsonObject = jsonArray.getJSONObject(i)
                            Companion.usersArray.add(User(
                                jsonObject.optString("id"),
                                jsonObject.optString("username"),
                                jsonObject.optString("phone")
                            ))
                        }
                        UserAdapter(this, Companion.usersArray, this).notifyDataSetChanged()
                        swiper.isRefreshing = false

                        Log.e("main :", args[0].toString())
                    } catch (e: Exception) {
                        Log.e("mainTag1", e.message.toString())
                    }
                }
            })
        }

        mSocket!!.on("returnAllUsersRe", Emitter.Listener { args ->
            runOnUiThread {
                try {
                    Companion.usersArray.clear()
                    val jsonObject = JSONObject(args[0].toString())
                    val jsonArray = jsonObject.optJSONArray("array")
                    for (i in 0 until jsonArray.length()) {
                        val jsonObject = jsonArray.getJSONObject(i)
                        Companion.usersArray.add(User(
                                jsonObject.optString("id"),
                                jsonObject.optString("username"),
                                jsonObject.optString("phone")
                        ))
                    }
                    createNotificationChannel()
                    UserAdapter(this, Companion.usersArray, this).notifyDataSetChanged()
                    swiper.isRefreshing = false

                    Log.e("main :", args[0].toString())
                } catch (e: Exception) {
                    Log.e("mainTag1", e.message.toString())
                }
            }
        })

        bottomNavigationView!!.setOnNavigationItemSelectedListener { item ->
            when(item.itemId) {
                R.id.page_1 -> {
                    // Respond to navigation item 1 click
                    true
                }
                R.id.page_2 -> {
                    // Respond to navigation item 2 click
                    val intent = Intent(this , GroupsScreen::class.java)
                    startActivity(intent)
                    true
                }
                else -> false
            }
        }

        btnSearch!!.setOnClickListener {
            val intent = Intent(this , SearshUser::class.java)
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
        if (Companion.isNotExist) {
            val mes1 = JSONObject()
            mes1.put("id", Companion.myId)
            mes1.put("username", Companion.myName)
            mes1.put("phone", Companion.myPhone)
            mSocket!!.emit("appUsers", mes1)
            Companion.isNotExist = false
        }
        mSocket!!.connect()
    }

    override fun onBackPressed() {
        finishAffinity()
        super.onBackPressed()
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
            .setContentTitle("New User Connected")
            .setContentText(Companion.usersArray.last().name +" is connected")
            .setSmallIcon(R.drawable.l4)
            .setLargeIcon(BitmapFactory.decodeResource(resources, R.drawable.l4))
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .build()

        manager!!.notify(1,notification)
    }
}