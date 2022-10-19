package com.example.caching.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Part::class], version = 1)
abstract class PartDataBase : RoomDatabase() {

    abstract fun partDao(): PartDao
}