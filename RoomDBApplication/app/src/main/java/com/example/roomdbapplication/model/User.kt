package com.example.roomdbapplication.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "user_table")
data class User(
        @PrimaryKey(autoGenerate = true)
        var id: Int,
        val name: String,
        val age: Int
):Parcelable


//        ,
//        @Embedded
//        val address: Address


//data class Address(
//        val streetName: String,
//        val streetNumber: Int
//)