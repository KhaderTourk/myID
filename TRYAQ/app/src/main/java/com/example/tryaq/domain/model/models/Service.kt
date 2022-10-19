package com.example.tryaq.domain.model.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.tryaq.util.Constants
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = Constants.SERVICES_DATABASE_TABLE)
data class Service(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val name: String,
    var image: String,
    val price: Double,
    val departmentId: Int,
    val timesOfWork: String,
    val doctorName: String,
    val doctorImage: String,
    val status: Int
)
