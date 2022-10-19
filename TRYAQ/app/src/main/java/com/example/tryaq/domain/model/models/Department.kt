package com.example.tryaq.domain.model.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.tryaq.util.Constants.DEPARTMENT_DATABASE_TABLE
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = DEPARTMENT_DATABASE_TABLE)
data class Department (
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val name: String,
    val timesOfWork: String,
    val image: String?= null,
    val status: Int
)