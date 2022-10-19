package com.example.todo_for_blockchain

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.example.todo_for_blockchain.util.CCcompanion

class LogActivity : AppCompatActivity() {

    private lateinit var etPurser: EditText
    private lateinit var etPrivateKey: EditText
    private lateinit var cbRememberMe: CheckBox
    private lateinit var login: Button
    private lateinit var pb: ProgressBar
    private var isExist = false
    private lateinit var shared: SharedPreferences
    private lateinit var shareEditor: SharedPreferences.Editor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log)
        shared = this.getSharedPreferences("wallet",Context.MODE_PRIVATE)
        shareEditor = shared.edit()

        cbRememberMe = findViewById(R.id.cb_remember_me)
        etPrivateKey = findViewById(R.id.et_private_key)
        etPurser = findViewById(R.id.et_address)
        pb = findViewById(R.id.pb_log)
        login = findViewById(R.id.btn_log)


        if (shared.getBoolean("remember",false)){
            val intent = Intent(this, MainActivity::class.java)
            CCcompanion.ADDRESS = shared.getString("purser","")!!
            CCcompanion.PRIVATE_KEY = shared.getString("key","")!!
            startActivity(intent)
        }

        login.setOnClickListener {
            val key: String
            val purser: String
            if (etPurser.text.toString() != "" && etPrivateKey.text.toString() != ""){
                 key = etPrivateKey.text.toString()
                 purser = etPurser.text.toString()
                getJSONObject(purser,key)
            }else{
                Toast.makeText(this, "Please fill fields!",Toast.LENGTH_LONG).show()
            }
            }

    }

    private fun getJSONObject(address: String, key: String) {
        val uriGetMyActions = "${CCcompanion.MAIN_PATH}/getUser?purse=${address}&privateKey=${key}"
        pb.visibility = View.VISIBLE
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, uriGetMyActions, null,
            { response ->
                val message = response.getString("message")
                if (message == "successfully"){
                    CCcompanion.ADDRESS = address
                    CCcompanion.PRIVATE_KEY = key
                    if (cbRememberMe.isChecked)
                    {
                        shareEditor.putString("purser",address)
                        shareEditor.putString("key",key)
                        shareEditor.putBoolean("remember",true)
                        shareEditor.apply()
                    }else{
                        shareEditor.putBoolean("remember",false)
                        shareEditor.apply()
                    }
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }else{
                    addUser(address, key)
                }

                pb.visibility = View.GONE
            },
            { error ->
                Log.e("hzm Error", error.toString())
                pb.visibility = View.GONE
            })

        MySingleton.getInstance()!!.addRequestQueue(jsonObjectRequest)

    }

    private fun addUser(address: String, key: String) {
        val uriGetMyActions = "${CCcompanion.MAIN_PATH}/newUser?purse=${address}&privateKey=${key}"
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.POST, uriGetMyActions, null,
            { response ->
                val message = response.getString("message")
                if (message == "successfully"){
                    isExist = true
                    CCcompanion.ADDRESS = address
                    CCcompanion.PRIVATE_KEY = key
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }else{
                    Toast.makeText(this,"Error in wallet data!!",Toast.LENGTH_LONG).show()
                }
            },
            { error ->
                Log.e("hzm Error", error.toString())
                isExist = false
            })

        MySingleton.getInstance()!!.addRequestQueue(jsonObjectRequest)

    }
}