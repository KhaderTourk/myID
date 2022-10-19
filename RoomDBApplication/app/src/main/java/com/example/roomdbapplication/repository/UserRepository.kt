package com.example.roomdbapplication.repository

import androidx.lifecycle.LiveData
import com.example.roomdbapplication.data.User
import com.example.roomdbapplication.data.UserDao

class UserRepository(private val  userDao: UserDao) {
    val readAllData : LiveData<List<User>> = userDao.readAllData()

    suspend fun addUser(user: User){
        userDao.addUser(user)
    }

    suspend fun updateUser(user: User){
        userDao.updateUser(user)
    }

    suspend fun deleteUser(user: User){
        userDao.deleteUser(user)
    }

    suspend fun deleteAllUsers(){
        userDao.deleteAllUsers()
    }

}