package com.example.hashgeneratorapp.models

import android.util.Log
import java.security.MessageDigest

class Model {

    fun getHash(plainText:String , algorithm:String):String{
        val bytes = MessageDigest.getInstance(algorithm).digest(plainText.toByteArray())
        Log.e("error1",bytes.toString())
        return toHex(bytes)
    }

    private fun toHex(byteArray: ByteArray):String{
        Log.e("error2",byteArray.joinToString("") { "%02x".format(it) })
        return byteArray.joinToString("") { "%02x".format(it) }
    }
}