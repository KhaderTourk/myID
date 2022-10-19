package com.example.tryaq.domain.model.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.tryaq.util.Constants
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = Constants.MEDICINES_DATABASE_TABLE)
data class Medicine(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val name: String,
    val price: Double,
    val image: String,
    val status: Int
)
