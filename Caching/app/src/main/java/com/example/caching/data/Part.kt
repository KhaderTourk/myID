package com.example.caching.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "parts")
data class Part (
    @PrimaryKey val id :String,
    val title : String,
    val shortd : String,
    val logo : String,
    val discription : String,
        )