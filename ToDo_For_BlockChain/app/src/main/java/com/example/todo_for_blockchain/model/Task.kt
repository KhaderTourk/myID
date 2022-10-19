package com.example.todo_for_blockchain.model

data class Task (
    val id:String,
    val title: String,
    val description: String,
    val owner: String,
    val reward: String,
    val winner: String,
    val date: String,
    val isDone: Boolean,
    val isToggled: Boolean
        )
