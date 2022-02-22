package com.example.entities

import me.liuwj.ktorm.schema.Table
import me.liuwj.ktorm.schema.int
import me.liuwj.ktorm.schema.varchar

object ExampleEntity: Table<Nothing>("messages"){
    val id = int("id").primaryKey()
    val name = varchar("name")
    val email = varchar("email")
    val message = varchar("message")
}