package com.example.tryaq.domain.model.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.tryaq.util.Constants
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = Constants.APPOINTMENTS2_DATABASE_TABLE)
data class Appointment2(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val time: String,
    val day: String,
    val month: String,
    val date: String,
    val doctorName: String,
    val doctorImage: String,
    val departmentId: Int,
    val patientId: Int,
    val serviceId: Int,
    val serviceName: String,
    val status: Int,
    val patientName: String,
    val patientImage: String,
)