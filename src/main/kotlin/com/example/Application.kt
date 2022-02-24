package com.example

import com.example.entities.ExampleEntity
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import me.liuwj.ktorm.database.Database
import me.liuwj.ktorm.dsl.insert

fun main(args: Array<String>): Unit =
    io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused")
fun Application.module() {
//    val database = Database.connect(
//        url = "jdbc:mysql://localhost:3306/databasefortest",
//        driver = "com.mysql.cj.jdbc.Driver",
//        user = "root",
//        password = ""
//    )
    val database = Database.connect(
        url = "jdbc:mysql://eanl4i1omny740jw.cbetxkdyhwsb.us-east-1.rds.amazonaws.com:3306/ycym0h06or0v206s",
        driver = "com.mysql.cj.jdbc.Driver",
        user = "p71vmz3w4ilo2adk",
        password = "qve00ehsoi063am8"
    )

    database.insert(ExampleEntity){
        set(it.name, "second name")
        set(it.email, "second@hhh.com")
        set(it.message, "second message")
    }
    
    routing {
        get("/"){
            call.respondText("Hello world !!")
        }
    }
}
@Suppress("unused")
fun Application.module2() {
    routing {
        get("/book") {
            call.respondText("Books Page !!")
        }
    }
}
