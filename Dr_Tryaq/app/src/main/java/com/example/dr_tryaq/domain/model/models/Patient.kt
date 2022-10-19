package com.example.dr_tryaq.domain.model.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.dr_tryaq.util.Constants.PATIENT_DATABASE_TABLE
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = PATIENT_DATABASE_TABLE)
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