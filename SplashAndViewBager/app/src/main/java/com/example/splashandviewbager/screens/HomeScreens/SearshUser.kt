package com.example.splashandviewbager.screens.HomeScreens

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.splashandviewbager.screens.chatScreens.ChatScreen
import com.example.splashandviewbager.Units.Companion
import com.example.splashandviewbager.R
import com.example.splashandviewbager.modles.User
import com.example.splashandviewbager.adapters.SearchAdapter
import java.util.*

class SearshUser : AppCompatActivity()  {


    private var recyclerView: RecyclerView? = null
    private var adapter: SearchAdapter? = null
    private var etsearch: EditText? = null
    private var btnBack: ImageView? = null
    internal var textlength = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_searsh_user)

        supportActionBar?.hide()


        recyclerView = findViewById(R.id.recycle_users_search)
        btnBack = findViewById(R.id.btn_back_from_search)
        btnBack!!.setOnClickListener {
            val intent = Intent(this , UserScreen::class.java)
            startActivity(intent)
        }

        Companion.usersArray = populateList()
        Log.d("hjhjh",  Companion.usersArray.size.toString() + "")
        adapter = SearchAdapter(this, Companion.usersArray)
        recyclerView!!.adapter = adapter
        recyclerView!!.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)


        etsearch = findViewById(R.id.editText) as EditText
        Companion.array_sort = ArrayList<User>()
        Companion.array_sort = populateList()

        recyclerView!!.addOnItemTouchListener(
            RecyclerTouchListener(
                applicationContext,
                recyclerView!!,
                object : ClickListener {

                    override fun onClick(view: View, position: Int) {
                        val intent = Intent(this@SearshUser, ChatScreen::class.java)
                        Companion.friendId = Companion.usersArray[position].id
                        startActivity(intent)
                    }

                    override fun onLongClick(view: View?, position: Int) {

                    }
                })
        )


        etsearch!!.addTextChangedListener(object : TextWatcher {


            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                textlength = etsearch!!.text.length
                Companion.array_sort.clear()
                for (i in  Companion.usersArray.indices) {
                    if (textlength <=  Companion.usersArray[i].name.length) {
                        Log.d("ertyyy",  Companion.usersArray[i].name.toLowerCase(Locale.ROOT).trim())
                        if ( Companion.usersArray[i].name.toLowerCase(Locale.ROOT).trim().contains(
                                etsearch!!.text.toString().toLowerCase(Locale.ROOT).trim { it <= ' ' })
                        ) {
                            Companion.array_sort.add(Companion.usersArray[i])
                        }
                    }
                }
                adapter = SearchAdapter(this@SearshUser, Companion.array_sort)
                recyclerView!!.adapter = adapter
                recyclerView!!.layoutManager =
                    LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)

            }
        })

    }

    private fun populateList(): ArrayList<User> {

        val list = ArrayList<User>()

        for (i in 0 until  Companion.usersArray.size) {
            val imageModel = User(Companion.usersArray[i].id, Companion.usersArray[i].name, Companion.usersArray[i].phone)
            list.add(imageModel)
        }

        return list
    }

    interface ClickListener {
        fun onClick(view: View, position: Int)

        fun onLongClick(view: View?, position: Int)
    }

    internal class RecyclerTouchListener(
        context: Context,
        recyclerView: RecyclerView,
        private val clickListener: ClickListener?
    ) : RecyclerView.OnItemTouchListener {

        private val gestureDetector: GestureDetector

        init {
            gestureDetector = GestureDetector(context, object : GestureDetector.SimpleOnGestureListener() {
                override fun onSingleTapUp(e: MotionEvent): Boolean {
                    return true
                }

                override fun onLongPress(e: MotionEvent) {
                    val child = recyclerView.findChildViewUnder(e.x, e.y)
                    if (child != null && clickListener != null) {
                        clickListener.onLongClick(child, recyclerView.getChildPosition(child))
                    }
                }
            })
        }

        override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
            val child = rv.findChildViewUnder(e.x, e.y)
            if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
                clickListener.onClick(child, rv.getChildPosition(child))
            }
            return false
        }

        override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {}

        override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {

        }
    }

}
