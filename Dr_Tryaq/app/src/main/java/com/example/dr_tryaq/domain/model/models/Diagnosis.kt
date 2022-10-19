package com.example.dr_tryaq.domain.model.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.dr_tryaq.util.Constants.DIAGNOSIS_DATABASE_TABLE
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = DIAGNOSIS_DATABASE_TABLE)
data class Diagnosis(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val doctorId: Int,
    val patientId: Int,
    val serviceId: Int,
    val appointmentId: Int,
    val serviceName: String,
    val analysisRequired: String,
    val medicineRequired: String,
    val date: String,
    val time: String,
    val analyzesResult: String,
    val isAnalysis: Int,
    val note: String,
    val patientName: String,
    val patientImage: String,
    val serviceImage: String,
    val doctorName: String
)
