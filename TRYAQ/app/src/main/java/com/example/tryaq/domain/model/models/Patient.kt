package com.example.tryaq.domain.model.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.tryaq.util.Constants
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = Constants.PATIENT_DATABASE_TABLE)
data class Patient (
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val name: String,
    val phone: String,
    var password: String,
    val gender: String,
    val dob: String,
    val email: String,
    val address: String,
    val image: String,
    val status: Int
)