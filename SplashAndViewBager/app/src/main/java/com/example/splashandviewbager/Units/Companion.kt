package com.example.splashandviewbager.Units

import com.example.splashandviewbager.modles.Message
import com.example.splashandviewbager.modles.User

class Companion {
    companion object{

        var usersArray = ArrayList<User>()
        var messagesArray = ArrayList<Message>()
        var messagesGroupArray = ArrayList<Message>()
        lateinit var array_sort:ArrayList<User>

        var isNotExist :Boolean = false
        var myId :String = "null"
        var friendId :String = "null"
        var friendName :String = "null"
        var myName :String = "null"
        var myPhone :String = "null"
        var currentRoom :String = "null"
}

}
