package com.example.splashandviewbager.screens.HomeScreens

import android.app.ProgressDialog
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.splashandviewbager.*
import com.example.splashandviewbager.Units.Companion
import com.example.splashandviewbager.Units.MyConnectionListener
import com.example.splashandviewbager.modles.Group
import com.example.splashandviewbager.adapters.GroupAdapter
import com.example.splashandviewbager.screens.chatScreens.ChatGroup
import com.github.nkzawa.emitter.Emitter
import com.github.nkzawa.socketio.client.Socket
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.textfield.TextInputLayout
import org.json.JSONObject

class GroupsScreen : AppCompatActivity(), GroupAdapter.onClick , MyConnectionListener.ConnectionReceiverListener {

    override fun onClickItem(position: Int) {
        Toast.makeText(this, "iti", Toast.LENGTH_LONG).show()
        Companion.currentRoom = groupList[position].room

        val mes = JSONObject()
        mes.put("name", Companion.myName)
        mes.put("room",groupList[position].room)
        mes.put("id", Companion.myId)
        mSocket!!.emit("joinRoom", mes )
        val intent = Intent(this , ChatGroup::class.java)
        startActivity(intent)
    }

    private var btnCreateRoom: TextView? = null
    private var recycle: RecyclerView? = null
    private var tvTitle: TextView? = null
    private var tvImg: TextView? = null
    var bottomNavigationView: BottomNavigationView? = null

    lateinit var app: SocketCreate
    lateinit var adapter: GroupAdapter
    private var mSocket: Socket? = null
    lateinit var progressDialog: ProgressDialog

    lateinit var groupList: ArrayList<Group>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_groups_screen)
        progressDialog = ProgressDialog(this)
        progressDialog.setMessage("No Connection")
        progressDialog.setCancelable(false)
        supportActionBar?.hide()

        app = application as SocketCreate
        mSocket = app.getSocket()

        btnCreateRoom = findViewById(R.id.btn_create_group)
        bottomNavigationView = findViewById(R.id.bottom_navigation_groups)
        tvTitle = findViewById(R.id.tv_groups_my_name)
        tvImg = findViewById(R.id.tv_groups_my_img)
        btnCreateRoom!!.setOnClickListener {launchCustomDialog() }

        tvTitle!!.text = Companion.myName
        tvImg!!.text = Companion.myName.substring(0, 2)

        groupList = ArrayList()
        recycle = findViewById(R.id.recycle_groups)

        adapter = GroupAdapter(this, groupList, this)
        recycle!!.adapter = adapter
        recycle!!.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        val swiper: SwipeRefreshLayout = findViewById(R.id.swipe_groups)
        swiper.setOnRefreshListener {
            mSocket!!.emit("allGroups")
            mSocket!!.on("allRooms",onNewMessage )
            swiper.isRefreshing = false
        }

        registerReceiver(MyConnectionListener(), IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))

        bottomNavigationView!!.setOnNavigationItemSelectedListener { item ->
            when(item.itemId) {
                R.id.page_1 -> {
                    // Respond to navigation item 1 click
                    val intent = Intent(this , UserScreen::class.java)
                    startActivity(intent)
                    true
                }
                R.id.page_2 -> {
                    // Respond to navigation item 2 click
                    true
                }
                else -> false
            }
        }

        mSocket!!.on(Socket.EVENT_CONNECT_ERROR) {
            runOnUiThread {
                showDialog()
                Log.e("EVENT_CONNECT_ERROR", "EVENT_CONNECT_ERROR: ")
            }
        }
        mSocket!!.on(Socket.EVENT_CONNECT_TIMEOUT) {
            runOnUiThread {
                showDialog()
                Log.e("EVENT_CONNECT_TIMEOUT", "EVENT_CONNECT_TIMEOUT: ")

            }
        }
        mSocket!!.on(Socket.EVENT_CONNECT) {
            hideDialog()
            Log.e("onConnect", "Socket Connected!")

        }
        mSocket!!.on(Socket.EVENT_DISCONNECT) {
            runOnUiThread {
                Log.e("onDisconnect", "Socket onDisconnect!")

            }
        }
        mSocket!!.on("message",Emitter.Listener { args ->
            runOnUiThread {
                try {
                    Log.e("Tag2",args[0].toString())
                }catch (e: Exception){
                    Log.e("Tag1",e.message.toString())
                }
            }})
       // mSocket!!.on("message",onNewMessage)
        mSocket!!.connect()

    }

    override fun onStart() {
        super.onStart()
        mSocket!!.emit("allGroups")
        mSocket!!.on("allRooms",onNewMessage )
    }
    private val onNewMessage = Emitter.Listener { args ->
        runOnUiThread {
            try {
                groupList.clear()
                val jsonObject = JSONObject(args[0].toString())
                val jsonArray = jsonObject.optJSONArray("array")
                for (i in 0 until jsonArray.length()) {
                    val jsonObject = jsonArray.getJSONObject(i)
                    groupList.add(Group(
                            Companion.myName,
                            jsonObject.optString("room")
                    ))
                }
                GroupAdapter(this, groupList, this).notifyDataSetChanged()
                recycle!!.scrollToPosition(adapter.itemCount - (adapter.itemCount +1) )
                Log.e("main :", args[0].toString())
            } catch (e: Exception) {
                Log.e("mainTag1", e.message.toString())
            }
        }
    }

    private fun launchCustomDialog() {
        val customLayout = LayoutInflater.from(this).inflate(R.layout.custom_dialog, null)
        val editMessage: TextInputLayout = customLayout.findViewById(R.id.edit_message)
        val builder = AlertDialog.Builder(this)
                .setView(customLayout)
                .setPositiveButton("Submit") { dialogInterface, _ ->
                    val message = editMessage.editText!!.text.toString()

                    val mes = JSONObject()
                    mes.put("room",message)
                    mSocket!!.emit("newRoom", mes )
                    mSocket!!.emit("allGroups")
                    mSocket!!.on("allRooms",onNewMessage )
                    // Dismiss Dialog
                    dialogInterface.dismiss()
                    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
                }
                .setNegativeButton("Cancel") { dialogInterface, _ -> dialogInterface.cancel() }
        builder.show()
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

}

