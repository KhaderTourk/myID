package com.example.dr_tryaq.domain.model.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.dr_tryaq.util.Constants.DOCTOR_DATABASE_TABLE
import kotlinx.serialization.Serializable


@Serializable
@Entity(tableName = DOCTOR_DATABASE_TABLE)
data class Doctor (
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val name: String,
    val dob: String,
    val specialization: String,
    val phone: String?="",
    val image: String,
    var password: String,
    val gender: String,
    val email: String?="",
    val roomNumber: Int,
    val typeOfContract: String,
    val departmentId: Int,
    val status: Int
    )
